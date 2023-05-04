package com.rainy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan({"com.rainy.service_user.mapper", "com.rainy.service_house.mapper"})
@EnableAsync
@EnableCaching
public class RealEstateApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.rainy.RealEstateApplication.class, args);
    }
}

