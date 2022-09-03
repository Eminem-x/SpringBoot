package config

var (
	ExtendConfig interface{}
)

type Settings struct {
}

// Config 配置集合
type Config struct {
	Application *Application `yaml:"application"`
	Ssl         *Ssl         `yaml:"ssl"`
	Logger      *Logger      `yaml:"logger"`
}
