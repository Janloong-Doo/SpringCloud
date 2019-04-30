/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: DemoController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-23 下午4:10
 : LastModify: 19-4-23 下午4:10
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.dubboclient.controller;

import com.janloong.dubboclient.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-23 16:10
 */
@RestController
public class DemoController {
    @Reference(version = "1.0.0",url = "dubbo://localhost:20880")
    DemoService demoService;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/23 16:15
     **/
    @RequestMapping("/hello")
    public String hello(String name) {
        return demoService.hello(name);
    }

}
