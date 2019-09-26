package com.example2.demo_mvc_mybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example2.demo_mvc_mybatis.dao")
public class DemoMvcMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMvcMybatisApplication.class, args);
    }

}
