package com.example2.demo_mvc_mybatis.redisTool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {
    private static JedisPool pool;
    static {
        //连接池配置信息
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(1);//最大空闲数
        poolConfig.setMaxTotal(10);//最大连接数
        //连接池
        pool=new JedisPool(poolConfig);

    }
    public static Jedis getJeris(){
        Jedis jedis=pool.getResource();
        jedis.auth("root");
        return jedis;
    }
    public static  void close(Jedis jedis){
        jedis.close();
    }
}
