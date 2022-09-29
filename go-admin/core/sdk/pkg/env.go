package pkg

type (
	Mode string
)

const (
	ModeDev  Mode = "dev"     // 开发模式
	ModeTest Mode = "test"    // 测试模式
	ModeProd Mode = "prod"    // 生产模式
	Mysql         = "mysql"   // mysql 数据库标识
	Sqlite        = "sqlite3" // sqlite 数据库标识
)

func (e Mode) String() string {
	return string(e)
}
