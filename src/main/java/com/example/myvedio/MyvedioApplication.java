package com.example.myvedio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.myvedio.entity")
@MapperScan("com.example.myvedio.mapper")
public class MyvedioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyvedioApplication.class, args);
    }

}
