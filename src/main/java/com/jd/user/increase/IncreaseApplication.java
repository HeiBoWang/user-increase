package com.jd.user.increase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jd.user.increase.dao")
public class IncreaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncreaseApplication.class, args);
    }

}
