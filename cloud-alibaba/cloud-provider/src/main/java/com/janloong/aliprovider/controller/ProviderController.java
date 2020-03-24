/*
 * Copyright (c) 2019  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ProviderController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2019/12/31 下午8:14
 * LastModify: 2019/12/31 下午8:14
 */

package com.janloong.aliprovider.controller;

import com.janloong.common.utils.ResponseResult;
import lombok.var;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.AbstractRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 20:14
 **/
@RestController
@RequestMapping("provider")
public class ProviderController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/12/31 20:15
     **/
    @RequestMapping("/hello")
    public ResponseResult hello() throws InterruptedException {
        //public ResponseResult hello(HttpServletRequest request) throws InterruptedException {
        //    return ResponseResult.success("hello , " + g
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(httpServletRequest.getContextPath());
        new HashMap<>();

        return null;
    }


    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("doo1");
        Thread.sleep(2000L);
        stopWatch.stop();
        stopWatch.start("doo2");
        Thread.sleep(3000L);
        stopWatch.stop();
        if (1 == 0) {

        }
        System.out.println(stopWatch.prettyPrint());
        //System.out.println(stopWatch.prettyPrint());
        //System.out.println(stopWatch.shortSummary());
        //System.out.println(stopWatch.shortSummary());
    }
}
