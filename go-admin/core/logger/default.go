package logger

import (
	"context"
	"os"
	"sync"
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

func logCallerFilePath(loggingFilePath string) string {
	// TODO
	return ""
}

func (l *defaultLogger) Log(level Level, v ...interface{}) {
	l.logf(level, "", v...)
}

func (l *defaultLogger) Logf(level Level, format string, v ...interface{}) {
	l.logf(level, format, v...)
}

func (l *defaultLogger) logf(level Level, format string, v ...interface{}) {
	// TODO
}

func (l *defaultLogger) Options() Options {
	// TODO
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
