package com.janloong.security.ssuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsUserApplication.class, args);
    }
}
