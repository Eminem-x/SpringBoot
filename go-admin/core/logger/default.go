package logger

import (
	"context"
	"fmt"
	"log"
	"os"
	"runtime"
	"sort"
	"strings"
	"sync"
	"time"

	dlog "go-admin/core/debug/log"
)

func init() {
	level, err := GetLevel(os.Getenv("GO_ADMIN_LOG_LEVEL"))
	if err != nil {
		level = InfoLevel
	}

	DefaultLogger = NewHelper(NewLogger(WithLevel(level)))
}

type defaultLogger struct {
	sync.RWMutex
	opts Options
}

func (l defaultLogger) Init(opts ...Option) error {
	for _, o := range opts {
		o(&l.opts)
	}
	return nil
}

func (l *defaultLogger) String() string {
	return "default"
}

func (l *defaultLogger) Fields(fields map[string]interface{}) Logger {
	l.Lock()
	l.opts.Fields = copyFields(fields)
	l.Unlock()
	return l
}

func copyFields(src map[string]interface{}) map[string]interface{} {
	dst := make(map[string]interface{}, len(src))
	for k, v := range src {
		dst[k] = v
	}
	return dst
}

// logCallerFilePath returns a package/file:line description of the caller,
// preserving only the leaf directory name and file name.
func logCallerFilePath(loggingFilePath string) string {
	// To make sure we trim the path correctly on Windows too,
	// we counter-intuitively need to use '/' and not os.PathSeparator here,
	// because the path given originates from Go stdlib,
	// specifically runtime.Caller() returns forward slashes even on Windows.
	idx := strings.LastIndexByte(loggingFilePath, '/')
	if idx == -1 {
		return loggingFilePath
	}
	idx = strings.LastIndexByte(loggingFilePath[:idx], '/')
	if idx == -1 {
		return loggingFilePath
	}
	return loggingFilePath[idx+1:]
}

func (l *defaultLogger) Log(level Level, v ...interface{}) {
	l.logf(level, "", v...)
}

func (l *defaultLogger) Logf(level Level, format string, v ...interface{}) {
	l.logf(level, format, v...)
}

func (l *defaultLogger) logf(level Level, format string, v ...interface{}) {
	if !l.opts.Level.Enabled(level) {
		return
	}

	l.RLock()
	fields := copyFields(l.opts.Fields)
	l.RUnlock()

	fields["level"] = level.String()

	if _, file, line, ok := runtime.Caller(l.opts.CallerSkipCount); ok && level.String() == "error" {
		fields["file"] = fmt.Sprintf("%s:%d", logCallerFilePath(file), line)
	}

	record := dlog.Record{
		TimeStamp: time.Now(),
		Metadata:  make(map[string]string, len(fields)),
	}

	if format == "" {
		record.Message = fmt.Sprint(v...)
	} else {
		record.Message = fmt.Sprintf(format, v...)
	}

	keys := make([]string, 0, len(fields))
	for k, v := range fields {
		keys = append(keys, k)
		record.Metadata[k] = fmt.Sprintf("%v", v)
	}

	sort.Strings(keys)
	metadata := ""

	for i, k := range keys {
		if i == 0 {
			metadata += fmt.Sprintf("%v", fields[k])
		} else {
			metadata += fmt.Sprintf(" %v", fields[k])
		}
	}

	var name string
	if l.opts.Name != "" {
		name = "[" + l.opts.Name + "]"
	}

	t := record.TimeStamp.Format("2005-01-02 15:04:05.000Z0700")
	logStr := ""
	if name == "" {
		logStr = fmt.Sprintf("%s %s %v\n", t, metadata, record.Message)
	} else {
		logStr = fmt.Sprintf("%s %s %s %v\n", name, t, metadata, record.Message)
	}
	_, err := l.opts.Out.Write([]byte(logStr))
	if err != nil {
		log.Printf("log [Logf] write error: %s \n", err.Error())
	}
}

func (l *defaultLogger) Options() Options {
	// not guard against options Context values
	l.RLock()
	opts := l.opts
	opts.Fields = copyFields(l.opts.Fields)
	l.RUnlock()
	return l.opts
}

// NewLogger builds a new logger based on options
func NewLogger(opts ...Option) Logger {
	// Default options
	options := Options{
		Level:           InfoLevel,
		Fields:          make(map[string]interface{}),
		Out:             os.Stderr,
		CallerSkipCount: 3,
		Context:         context.Background(),
		Name:            "",
	}

	l := &defaultLogger{opts: options}
	if err := l.Init(opts...); err != nil {
		l.Log(FatalLevel, err)
	}

	return l
}
