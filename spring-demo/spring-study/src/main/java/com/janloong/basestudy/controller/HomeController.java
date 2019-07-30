/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午5:32
 : LastModify: 19-6-10 下午5:32
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-10 17:32
 */
@Slf4j
@RestController
public class HomeController {

    @Autowired
    CacheManager cacheManager;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/10 17:32
     **/
    @RequestMapping("/home")
    public ResponseResult home(Boolean dealAble) {
        System.out.println("业务处理");
        return ResponseResult.success("SUCCESS:" + dealAble);
    }


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/13 17:32
     **/
    @RequestMapping("/home2")
    public String home2() {
        return "succ";
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/12 9:18
     **/
    @RequestMapping("/sign")
    public ResponseResult sign(String name, String address) {
        System.out.println(name + "==" + address);
        return ResponseResult.success(name + "=================" + address);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/22 10:36
     **/
    @RequestMapping("/setcache")
    public ResponseResult setcache(String name) {
        Cache<String, String> defaultCache = cacheManager.getCache("defaultCache", String.class, String.class);
        defaultCache.put(name, "doo");
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/22 11:37
     **/
    @RequestMapping("/getcache")
    public ResponseResult getcache(String name) {
        Cache<String, String> defaultCache = cacheManager.getCache("defaultCache", String.class, String.class);
        boolean b = defaultCache.containsKey(name);
        return ResponseResult.success(b);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/27 0027 19:52
     **/
    @RequestMapping("/wait")
    public ResponseResult threadWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("已经执行完毕，现在可以关闭了");
        return ResponseResult.success("已经执行完毕，现在可以关闭了");
    }
}
