/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:42
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.provide.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-08 15:37
 */
@RestController
@Slf4j
public class HomeController {

    @Value("${server.port}")
    String port;

    @RequestMapping("home")
    public String home(@RequestParam(defaultValue = "服务提供方client1") String name) {
        String result = name + "port: " + port;
        log.info(result);
        return result;
    }


    public static boolean dbStatus = true;

    @RequestMapping("visitStatus")
    public boolean serviceDb(boolean status) {
        dbStatus = status;
        return dbStatus;
    }
}
