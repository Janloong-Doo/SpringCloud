/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: RequestController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/12 上午9:45
 * LastModify: 2020/1/12 上午9:45
 */

package com.janloong.springsecurity.spring;

import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.spring
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-12 09:45
 **/
@RestController
@RequestMapping("request")
public class RequestController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/12 9:45
     **/
    @RequestMapping("/info")
    public ResponseResult info(HttpServletRequest request, HttpServletResponse response) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        Arrays.asList(stack).forEach(System.out::println);
        System.out.println(request);
        System.out.println(response);
        return ResponseResult.success(null);
    }
}
