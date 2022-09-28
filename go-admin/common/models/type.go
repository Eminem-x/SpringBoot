package models

import "gorm.io/gorm/schema"

type ActiveRecord interface {
	schema.Tabler
	SetCreatedBy(createdBy int)
	SetUpdatedBy(updatedBy int)
	Generate() ActiveRecord
	GetId() interface{}
}
