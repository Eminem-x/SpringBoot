package logger

import (
	"go-admin/core/logger"
	"io"
	"log"
	"os"

	"go-admin/core/debug/writer"
	"go-admin/core/plugins/logger/zap"
	"go-admin/core/sdk/pkg"
)

// SetupLogger 日志 cap 单位为 kb
func SetupLogger(opts ...Option) logger.Logger {
	op := setDefault()
	for _, o := range opts {
		o(&op)
	}
	if !pkg.PathExist(op.path) {
		err := pkg.PathCreate(op.path)
		if err != nil {
			log.Fatalf("create dir error: %s", err.Error())
		}
	}

	var err error
	var output io.Writer
	switch op.stdout {
	case "file":
		output, err = writer.NewFileWriter(
			writer.WithPath(op.path),
			writer.WithCap(op.cap<<10),
		)
		if err != nil {
			log.Fatalf("logger setup error: %s", err.Error())
		}
	default:
		output = os.Stdout
	}

	var level logger.Level
	level, err = logger.GetLevel(op.level)
	if err != nil {
		log.Fatalf("get logger level error, %s", err.Error())
	}

	switch op.driver {
	case "zap":
		logger.DefaultLogger, err = zap.NewLogger(logger.WithLevel(level), zap.WithOutput(output), zap.WithCallerSkip(2))
		if err != nil {
			log.Fatalf("new zap logger error, %s", err.Error())
		}
	default:
		logger.DefaultLogger = logger.NewLogger(logger.WithLevel(level), logger.WithOutput(output))
	}
	return logger.DefaultLogger
}
