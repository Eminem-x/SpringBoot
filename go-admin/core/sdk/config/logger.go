package config

type Logger struct {
	Type     string
	Path     string
	Level    string
	Stdout   string
	EnableDB bool
	Cap      uint
}
