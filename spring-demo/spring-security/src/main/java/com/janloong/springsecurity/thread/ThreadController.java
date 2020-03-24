/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ThreadController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/12 下午3:24
 * LastModify: 2020/1/12 下午3:24
 */

package com.janloong.springsecurity.thread;

import com.janloong.common.utils.ResponseResult;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.thread
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-12 15:24
 **/
@RestController
@RequestMapping("thread")
public class ThreadController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/12 15:24
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
}
