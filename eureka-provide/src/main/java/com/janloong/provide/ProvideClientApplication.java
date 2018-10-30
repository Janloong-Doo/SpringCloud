/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ProvideClientApplication.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:42
 : LastModify: 18-10-30 上午10:35
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProvideClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideClientApplication.class, args);
    }
}
