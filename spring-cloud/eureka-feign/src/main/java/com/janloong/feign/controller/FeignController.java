/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: FeignController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:43
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.feign.controller;


import com.janloong.feign.controller.feign.FeignInterface;
import com.janloong.feign.controller.feign.HomeInterface;
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
    @Autowired
    HomeInterface homeInterface;

    @RequestMapping("feign")
    public String feign() {
        String feign = feignInterface.feign("服务提供方");
        return "feign方式获得: " + feign;
    }


    @RequestMapping("home")
    public String home() {
        String home = homeInterface.home();
        return "feign方式获得:" + home;
    }
}
