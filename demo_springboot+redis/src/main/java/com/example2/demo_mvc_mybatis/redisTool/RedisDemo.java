package com.example2.demo_mvc_mybatis.redisTool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDemo {

    public static Jedis getJedis() {
        //默认127.0.0.0 6379
        Jedis jedis=new Jedis();
        //认证密码
        jedis.auth("root");
        return jedis;
    }

    public static void main(String[] args) {
//        //默认127.0.0.0 6379
//        Jedis jedis=new Jedis();
//        //认证密码
//        jedis.auth("root");
        //连接池配置信息
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(1);//最大空闲数
        poolConfig.setMaxTotal(10);//最大连接数
        //连接池
        JedisPool pool=new JedisPool(poolConfig);
        Jedis jedis1=pool.getResource();
        jedis1.auth("root");
        System.out.println(jedis1.ping());
    }

}
