/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: RedisController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/9 下午1:51
 * LastModify: 2020/1/9 下午1:51
 */

package com.janloong.springsecurity.controller;

import com.janloong.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.controller
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-09 13:51
 **/
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/9 13:51
     **/
    @RequestMapping("/addKey")
    public ResponseResult addKey() {
        redisTemplate.opsForValue().set("doo_expire_test_5","5miaoguoqi",10000);
        return ResponseResult.success(null);
    }
}
