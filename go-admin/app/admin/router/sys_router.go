package router

import (
	"github.com/gin-gonic/gin"

	jwt "go-admin/core/sdk/pkg/jwtauth"
)

// TODO
func InitSysRouter(r *gin.Engine, authMiddleware *jwt.GinJWTMiddleware) *gin.RouterGroup {
	return nil
}
