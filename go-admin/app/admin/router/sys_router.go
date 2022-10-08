package router

import (
	"github.com/gin-gonic/gin"

	"go-admin/core/sdk/config"
	jwt "go-admin/core/sdk/pkg/jwtauth"
)

// TODO
func InitSysRouter(r *gin.Engine, authMiddleware *jwt.GinJWTMiddleware) *gin.RouterGroup {
	g := r.Group("")
	sysBaseRouter(g)
	// 静态文件
	sysStaticFileRouter(g)
	// swagger: 注意 prod 环境下可以注释掉
	if config.ApplicationConfig.Mode != "prod" {
		sysSwaggerRouter(g)
	}
	// 需要认证
	sysCheckRoleRouterInit(g, authMiddleware)
	return nil
}

func sysBaseRouter(r *gin.RouterGroup) {

}

func sysStaticFileRouter(r *gin.RouterGroup) {

}

func sysSwaggerRouter(r *gin.RouterGroup) {

}

func sysCheckRoleRouterInit(r *gin.RouterGroup, authMiddleware *jwt.GinJWTMiddleware) {

}

func registerBaseRouter(v1 *gin.RouterGroup, authMiddleware *jwt.GinJWTMiddleware) {

}
