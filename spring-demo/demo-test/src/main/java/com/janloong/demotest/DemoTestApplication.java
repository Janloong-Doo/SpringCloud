package com.janloong.demotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.janloong")
public class DemoTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }

}
