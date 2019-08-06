package com.yxl.myhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.yxl.myhome")
@EnableTransactionManagement
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
