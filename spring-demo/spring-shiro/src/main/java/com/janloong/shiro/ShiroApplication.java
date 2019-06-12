/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ShiroApplication.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-11 上午11:30
 : LastModify: 19-6-11 上午11:30
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro;


import com.janloong.common.base.JanloongBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-11 11:30
 */
@SpringBootApplication
@ComponentScan("com.janloong")
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ShiroApplication.class);
        springApplication.setBanner(new JanloongBanner());
        springApplication.run(args);
    }
}
