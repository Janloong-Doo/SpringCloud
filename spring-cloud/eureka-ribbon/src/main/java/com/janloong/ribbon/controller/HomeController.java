/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:41
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.ribbon.controller;


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
