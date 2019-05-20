package com.janloong.springredisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.janloong")
public class SpringRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisDemoApplication.class, args);
    }

}
