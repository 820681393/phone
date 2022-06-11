package com.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.ParseException;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages={"com.mobile","com.core"})
public class MobileApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(MobileApplication.class, args);
    }

}
