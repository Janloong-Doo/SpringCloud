package com.janloong.demoall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.janloong")
public class DemoAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAllApplication.class, args);
    }
    
}

