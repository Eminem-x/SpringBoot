package cache

import (
	"time"

	"github.com/go-redis/redis/v7"
)

// NewRedis redis 模式
func NewRedis(client *redis.Client, options *redis.Options) (*Redis, error) {
	if client == nil {
		client = redis.NewClient(options)
	}
	r := &Redis{
		client: client,
	}
	err := r.connect()
	if err != nil {
		return nil, err
	}
	return r, nil
}

// Redis cache implement
type Redis struct {
	client *redis.Client
}

func (*Redis) String() string {
	return "redis"
}

func (r *Redis) connect() error {
	var err error
	_, err = r.client.Ping().Result()
	return err
}

func (r *Redis) Get(key string) (string, error) {
	return r.client.Get(key).Result()
}

// Set value with key and expire time
func (r *Redis) Set(key string, val interface{}, expire int) error {
	return r.client.Set(key, val, time.Duration(expire)*time.Second).Err()
}

func (r *Redis) Del(key string) error {
	return r.client.Del(key).Err()
}

// HashGet from key
func (r *Redis) HashGet(hk, key string) (string, error) {
	return r.client.HGet(hk, key).Result()
}

// HashDel from key
func (r *Redis) HashDel(hk, key string) error {
	return r.client.HDel(hk, key).Err()
}

func (r *Redis) Increase(key string) error {
	return r.client.Incr(key).Err()
}

func (r *Redis) Decrease(key string) error {
	return r.client.Decr(key).Err()
}

// Expire set ttl
func (r *Redis) Expire(key string, dur time.Duration) error {
	return r.client.Expire(key, dur).Err()
}

// GetClient return origin redis.Client
func (r *Redis) GetClient() *redis.Client {
	return r.client
}
