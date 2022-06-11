package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.ParseException;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ZdywCoreApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(ZdywCoreApplication.class, args);
    }

}
