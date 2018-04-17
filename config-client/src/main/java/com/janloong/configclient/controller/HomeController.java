package com.janloong.configclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-13 15:37
 */
@RestController
public class HomeController {

    @Value("${doo}")
    String test;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/13 15:38
     **/
    @RequestMapping("/test")
    public String test(String name) {

        return test;
    }
}
