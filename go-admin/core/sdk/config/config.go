package config

import (
	"fmt"
	"log"
)

var (
	ExtendConfig interface{}
)

type Settings struct {
}

// TODO source queue 模块

// Config 配置集合
type Config struct {
	Application *Application          `yaml:"application"`
	Ssl         *Ssl                  `yaml:"ssl"`
	Logger      *Logger               `yaml:"logger"`
	Jwt         *Jwt                  `yaml:"jwt"`
	Database    *Database             `yaml:"database"`
	Databases   *map[string]*Database `yaml:"databases"`
	Gen         *Gen                  `yaml:"gen"`
	Cache       *Cache                `yaml:"cache"`
	Locker      *Locker               `yaml:"locker"`
	Extend      interface{}           `yaml:"extend"`
}

// 多db改造
func (e *Config) multiDatabase() {
	if len(*e.Databases) == 0 {
		*e.Databases = map[string]*Database{
			"*": e.Database,
		}

	}
}

// Setup 载入配置文件
func Setup(s source.Source,
	fs ...func()) {
	_cfg = &Settings{
		Settings: Config{
			Application: ApplicationConfig,
			Ssl:         SslConfig,
			Logger:      LoggerConfig,
			Jwt:         JwtConfig,
			Database:    DatabaseConfig,
			Databases:   &DatabasesConfig,
			Gen:         GenConfig,
			Cache:       CacheConfig,
			Queue:       QueueConfig,
			Locker:      LockerConfig,
			Extend:      ExtendConfig,
		},
		callbacks: fs,
	}
	var err error
	config.DefaultConfig, err = config.NewConfig(
		config.WithSource(s),
		config.WithEntity(_cfg),
	)
	if err != nil {
		log.Fatal(fmt.Sprintf("New config object fail: %s", err.Error()))
	}
	_cfg.Init()
}
