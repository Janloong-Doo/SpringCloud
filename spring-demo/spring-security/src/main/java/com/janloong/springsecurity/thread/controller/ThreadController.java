/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ThreadController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/7 下午2:10
 * LastModify: 2020/4/7 下午2:00
 */

package com.janloong.springsecurity.thread.controller;


import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.thread.CompletionServiceTask;
import com.janloong.springsecurity.thread.CountDownLatchDemo;
import com.janloong.springsecurity.thread.async.LoanCheckTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-31 17:04
 */
@RestController("dooThreadController")
@RequestMapping("thread")
@Slf4j
public class ThreadController {

    @Autowired
    private CompletionServiceTask serviceTask;
    @Autowired
    private CountDownLatchDemo latchDemo;

    //异步业务执行注入
    @Autowired
    private LoanCheckTask loanCheckTask;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/7 14:15
     **/
    @GetMapping("/async")
    public ResponseResult async() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("thread-async");
        CompletableFuture userInfoFuture = loanCheckTask.getUserInfo();
        CompletableFuture bankCreditInfoFuture = loanCheckTask.getBankCreditInfo();
        CompletableFuture<Boolean> future = userInfoFuture.thenCombine(bankCreditInfoFuture,
                (BiFunction<String, String, Boolean>) (x, y) -> loanCheckTask.getAllowLoan(x, y));
        Boolean join = future.join();
        stopWatch.stop();
        log.info("开通贷款服务time={}", stopWatch.prettyPrint());
        return ResponseResult.success(join);
    }


    /**
     * 聚合任务测试-阻塞方式获取业务结果。有顺序
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/31 17:22
     **/
    @GetMapping("/aggregateSync")
    public ResponseResult exec() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("aggregateSync");
        Map<String, Object> doo = serviceTask.getAggregatedSync("doo");
        long end = System.currentTimeMillis();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return ResponseResult.success(doo);
    }

    /**
     * 聚合任务测试-队列方式获取业务结果。结果无顺序
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/8/1 17:32
     **/
    @GetMapping("/aggregateQueue")
    public ResponseResult execTask(String name) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("aggregateQueue");
        serviceTask.getAggregatedWithQueue(name);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/8/13 13:23
     **/
    @GetMapping("/countDownLatch")
    public ResponseResult latch() {
        latchDemo.start();
        return ResponseResult.success(null);
    }

    /**
     * threadLocal 对应每个线程的一个单独的存储变量，
     * <p>
     * example:
     * 参考servlet过程中的context持久化
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/7 15:43
     * @see {@link org.springframework.context.i18n.LocaleContextHolder}
     **/
    @RequestMapping("/test")
    public ResponseResult test() {
        //初始化threadLocal 查看当前线程持有的信息 ????
        ThreadLocal<String> a1 = new ThreadLocal<>();
        a1.set("hello");
        ThreadLocal<String> a2 = new ThreadLocal<>();
        a2.set("janloong");
        ThreadLocal<String> a3 = new ThreadLocal<>();
        a3.set("loong");
        a3.set("doo");
        //hello
        System.out.println(a1.get());
        //janloong
        System.out.println(a2.get());
        //doo
        System.out.println(a3.get());
        ThreadLocal<String> a4 = new NamedThreadLocal<>("key");
        a4.set("value");
        //RequestContextHolder.getRequestAttributes()

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getContextPath());
        System.out.println(request.getMethod());
        //【错误用法】继承ServletRequestAttributes 代理的内容更全面，例如一些header 和params相关方法
        //HttpServletRequest request2 = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getRequest();
        //System.out.println(request2.getMethod());
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/7 15:52
     **/
    @RequestMapping("/schedule")
    public ResponseResult schedule(String name) {
       
        return ResponseResult.success(null);
    }
}
