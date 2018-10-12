package com.janloong.eurekaclient2.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-08 15:37
 */
@RestController
public class HomeController {

    @RequestMapping("home")
    public String home(@RequestParam(defaultValue = "服务消费方custom1") String name) {
        return name;
    }
    
}
