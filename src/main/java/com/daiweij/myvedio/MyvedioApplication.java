package com.daiweij.myvedio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.daiweij.myvedio.modules.sys.entity")
@MapperScan("com.daiweij.myvedio.modules.sys.mapper")
public class MyvedioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyvedioApplication.class, args);
    }

}
