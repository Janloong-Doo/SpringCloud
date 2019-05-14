package com.janloong.auth2center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@SessionAttributes("authorizationRequest")
public class Auth2CenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Auth2CenterApplication.class, args);
    }
}
