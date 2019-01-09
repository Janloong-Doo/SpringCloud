/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RedisDemoController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-1-9 下午1:47
 : LastModify: 19-1-9 下午1:47
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall.controller;


import com.janloong.base.utils.WebApiResponse;
import com.janloong.demoall.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-01-09 13:47
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisDemoController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 13:47
     **/
    @RequestMapping("/get")
    public WebApiResponse get(String key) {
        Object o = redisUtil.get(key);
        return WebApiResponse.success(o);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 13:59
     **/
    @RequestMapping("/set")
    public WebApiResponse set(String key, String value, long time) {
        log.info("time:" + time);
        boolean set = redisUtil.set(key, value, time);
        return WebApiResponse.success(set);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 14:53
     **/
    @RequestMapping("/del")
    public WebApiResponse del(String... keys) {
        redisUtil.del(keys);
        return WebApiResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 16:39
     **/
    @RequestMapping("/incr")
    public WebApiResponse incr(String key, long delta) {
        long incr = redisUtil.incr(key, delta);
        return WebApiResponse.success(incr);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 16:39
     **/
    @RequestMapping("/decr")
    public WebApiResponse decr(String key, long delta) {
        long decr = redisUtil.decr(key, delta);
        return WebApiResponse.success(decr);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:06
     **/
    @RequestMapping("/hget")
    public WebApiResponse hget(String key, String item) {
        Object hget = redisUtil.hget(key, item);
        return WebApiResponse.success(hget);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:13
     **/
    @RequestMapping("/hset")
    public WebApiResponse hset(String key, String item, String value, @RequestParam(defaultValue = "0") long time) {
        boolean hset = redisUtil.hset(key, item, value, time);
        return WebApiResponse.success(hset);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 17:25
     **/
    @RequestMapping("/hdel")
    public WebApiResponse hdel(String key, String... item) {
        redisUtil.hdel(key, item);
        return WebApiResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 17:50
     **/
    @RequestMapping("/hHasKey")
    public WebApiResponse hHasKey(String key, String item) {
        redisUtil.hHasKey(key, item);
        return WebApiResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:51
     **/
    @RequestMapping("/hmget")
    public WebApiResponse hmget(String key) {
        Map<Object, Object> hmget = redisUtil.hmget(key);
        return WebApiResponse.success(hmget);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:53
     **/
    @RequestMapping("/hmset")
    public WebApiResponse hmset(String name) {

        return WebApiResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:13
     **/
    @RequestMapping("/hgetall")
    public WebApiResponse hgetall(String name) {

        return WebApiResponse.success(null);
    }


}
