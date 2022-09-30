package router

import (
	"github.com/gin-gonic/gin"

	jwt "go-admin/core/sdk/pkg/jwtauth"
)

// TODO 路由示例
func InitExamplesRouter(r *gin.Engine, authMiddleware *jwt.GinJWTMiddleware) *gin.Engine {
	return r
}
