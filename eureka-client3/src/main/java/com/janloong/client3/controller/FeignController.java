package com.janloong.client3.controller;


import com.janloong.client3.controller.feign.FeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-08 15:52
 */
@RestController
public class FeignController {

    @Autowired
    FeignInterface feignInterface;

    @RequestMapping("feign")
    public String feign() {
        String feign = feignInterface.feign("服务提供方");
        return "feign方式获得: " + feign;
    }
}
