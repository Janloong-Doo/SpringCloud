package com.janloong.singlespringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@SessionAttributes("authorizationRequest")
public class SingleSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleSpringSecurityApplication.class, args);
    }
}
