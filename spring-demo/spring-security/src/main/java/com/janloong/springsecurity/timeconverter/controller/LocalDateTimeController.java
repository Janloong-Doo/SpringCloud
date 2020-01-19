/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: LocalDateTimeController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/11 下午5:30
 * LastModify: 2020/1/11 下午5:30
 */

package com.janloong.springsecurity.timeconverter.controller;

import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.timeconverter.controller
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-11 17:30
 **/
@RestController
@RequestMapping("localDateTime")
public class LocalDateTimeController {
    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/11 17:31
     **/
    @RequestMapping("/test")
    public ResponseResult test(@RequestBody String startTime) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        Arrays.asList(stack).forEach(System.out::println);
        System.out.println(startTime);
        var user = new User();
        System.out.println(user);
        System.out.println(startTime + "");
        return ResponseResult.success(null);
    }
}
