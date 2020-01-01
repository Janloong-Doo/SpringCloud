/*
 * Copyright (c) 2019  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ConsumerController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2019/12/31 下午8:03
 * LastModify: 2019/12/31 下午8:03
 */

package com.janloong.consumer.controller;

import com.janloong.common.utils.ResponseResult;
import com.janloong.consumer.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 20:03
 **/
@RestController
//@RefreshScope
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private ProviderFeign providerFeign;

    //@Value("${doo.config}")
    private String config;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/12/31 20:18
     **/
    @RequestMapping("/hello")
    public ResponseResult hello(String name) {
        ResponseResult hello = providerFeign.hello(name);
        return hello;
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/1 11:19
     **/
    @RequestMapping("/config")
    public ResponseResult config() {
        return ResponseResult.success(config);
    }
}
