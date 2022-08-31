package config

type Application struct {
	ReadTimeout  int
	WriteTimeout int
	Host         string
	Port         int64
	Name         string
	JwtSecret    string
	Mode         string
	DemoMsg      string
	EnableDP     bool
}

var ApplicationConfig = new(Application)
