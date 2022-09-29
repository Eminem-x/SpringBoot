package models

import (
	"time"

	"gorm.io/gorm"
)

type ControlBy struct {
	CreatedBy int `json:"createBy" gorm:"index;comment:创建者"`
	UpdatedBy int `json:"updateBy" gorm:"index;comment:更新者"`
}

func (e *ControlBy) SetCreatedBy(createdBy int) {
	e.CreatedBy = createdBy
}

func (e *ControlBy) SetUpdatedBy(updatedBy int) {
	e.UpdatedBy = updatedBy
}

type Model struct {
	Id int `json:"id" gorm:"primaryKey;autoIncrement;comment:主键编码"`
}

type ModelTime struct {
	CreatedAt time.Time      `json:"createdAt" gorm:"comment:创建时间"`
	UpdatedAt time.Time      `json:"updatedAt" gorm:"comment:最后更新时间"`
	DeletedAt gorm.DeletedAt `json:"-" gorm:"index;comment:删除时间"`
}
