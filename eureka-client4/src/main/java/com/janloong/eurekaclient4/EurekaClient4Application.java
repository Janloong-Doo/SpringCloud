package com.janloong.eurekaclient4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClient4Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient4Application.class, args);
    }
}
