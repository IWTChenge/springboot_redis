package com.example2.demo_mvc_mybatis.redis;

import com.example2.demo_mvc_mybatis.pojo.Student;
import com.example2.demo_mvc_mybatis.redisTool.RedisDemo;
import com.example2.demo_mvc_mybatis.redisTool.RedisPoolUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class redisDemo {
    @Test
    public void test1() {
        Jedis jedis = RedisDemo.getJedis();
        String srt = "string_key";
        String srtvalue = "string_value";
        jedis.set(srt, srtvalue);
        System.out.println(jedis.get(srt));
        jedis.close();
    }
    /**
     * String类型
     */

    @Test
    public void test2(){
        Jedis jedis = RedisDemo.getJedis();
        String srt = "string_key";
        if(jedis.exists(srt)){
            System.out.println("Redis数据库查询："+jedis.get(srt));
        }else {
            //mysql查询
            String result="mysql";
            //存入redis
            jedis.set(srt,result);
            System.out.println("mysql数据库查询："+result);
        }
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void test3(){
        Jedis jedis = RedisPoolUtil.getJeris();
        String srt = "string_key";
        if(jedis.exists(srt)){
            System.out.println("Redis数据库查询："+jedis.get(srt));
        }else {
            //mysql查询
            String result="mysql";
            //存入redis
            jedis.set(srt,result);
            System.out.println("mysql数据库查询："+result);
        }
        RedisPoolUtil.close(jedis);
    }

    /**
     * hset() map类型
     */
    @Test
    public void test4(){
        Jedis jedis=RedisPoolUtil.getJeris();
        String key="user";
        if(jedis.exists(key)){
            Map<String,String> map=jedis.hgetAll(key);
            System.out.println("Redis中查询结果：");
            System.out.println(map.get("id")+"\t"+map.get("name")+"\t"+map.get("age"));
        }else {
            //查询数据库返回结果
            String id="1";
            String name="Redis";
            jedis.hset(key,"id",id);
            jedis.hset(key,"name",name);
            jedis.hset(key,"age","22");
            System.out.println("数据库mysql查询结果");
        }
        RedisPoolUtil.close(jedis);
    }

    /**
     * hmset() 多个map类型
     */
    @Test
    public void t5(){
        Jedis jedis=RedisPoolUtil.getJeris();
        String key="Student:1";
        if(jedis.exists(key)){
            Map<String,String> map=jedis.hgetAll(key);
            Student st=new Student();
            st.setStudent_id((map.get("id")));
            st.setName(map.get("name"));
            System.out.println("Redis中查询结果：");
            System.out.println(map.get("id")+"\t"+map.get("name")+"\t");
        }else {
            //查询数据库返回结果
            Student st=new Student();
            st.setName("T5");
            st.setStudent_id("22");
            Map<String ,String> map=new HashMap<>();
            map.put("id",st.getStudent_id());
            map.put("name",st.getName());
           jedis.hmset(key,map);
            System.out.println("数据库mysql查询结果");
        }
        RedisPoolUtil.close(jedis);
    }
}
