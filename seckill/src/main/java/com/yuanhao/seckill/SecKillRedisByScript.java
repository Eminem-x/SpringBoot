package com.yuanhao.seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author Yuanhao
 */
public class SecKillRedisByScript {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
        jedis.close();
    }


    public static boolean doSecKill(String userid, String productId) {
        // 1. uid和productId非空判断
        if (userid == null || productId == null) {
            return false;
        }

        // 2. 通过连接池连接Redis
        JedisPool jedisPoolUtil = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPoolUtil.getResource();
        // 3. 拼接Key
        // 3.1 库存Key
        String storeKey = "store:" + productId + ":qt";
        // 3.2 用户Key
        String userKey = "user:" + userid + "user";

        // 监视库存
        jedis.watch(storeKey);

        // 4. 获取库存，如果库存 null，秒杀还未开始
        String store = jedis.get(storeKey);
        if (store == null) {
            System.out.println("Not start, Please wait...");
            jedis.close();
            return false;
        }

        // 5. 判断用户是否重复秒杀操作
        if (jedis.sismember(userKey, userid)) {
            System.out.println("Can't join twice...");
            jedis.close();
            return false;
        }

        // 6. 如果商品库存数量小于 1，秒杀结束
        if (Integer.parseInt(store) <= 0) {
            System.out.println("It's over...");
            jedis.close();
            return false;
        }

        // 7. 秒杀过程
        // 使用事务
        Transaction multi = jedis.multi();
        // 组队
        multi.decr(storeKey);
        multi.sadd(userKey, userid);
        // 执行
        List<Object> results = multi.exec();
        jedis.close();

        if(results.size() == 0) {
            System.out.println("Fail...");
            return false;
        }
        System.out.println("Successful...");
        return true;
    }
}
