package com.rzt.eurekaclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-08 15:37
 */
@RestController
public class HomeController {

    @Value("${server.port}")
    String port;

    @RequestMapping("home")
    public String home(@RequestParam(defaultValue = "服务提供方client1") String name) {
        return name + "port: " + port;
    }


    public static boolean dbStatus = true;

    @RequestMapping("visitStatus")
    public boolean serviceDb(boolean status) {
        dbStatus = status;
        return dbStatus;
    }
}
