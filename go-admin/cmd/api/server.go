package api

import (
	"github.com/spf13/cobra"

	ext "go-admin/config"
	"go-admin/core/sdk/config"
)

var (
	configYml string
	apiCheck  bool
	StartCmd  = &cobra.Command{
		Use:          "server",
		Short:        "Start API server",
		Example:      "go-admin server -c config/settings.yml",
		SilenceUsage: true,
		PreRun: func(cmd *cobra.Command, args []string) {
			setup()
		},
		RunE: func(cmd *cobra.Command, args []string) error {
			return run()
		},
	}
)

func setup() {
	// 注入配置拓展项
	config.ExtendConfig = &ext.ExtConfig
	// 1. 读取配置
	config.
}

func run() {
}
