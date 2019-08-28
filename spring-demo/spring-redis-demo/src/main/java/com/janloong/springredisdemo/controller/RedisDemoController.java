/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RedisDemoController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午1:59
 : LastModify: 19-3-25 下午4:39
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springredisdemo.controller;


import com.janloong.common.utils.ResponseResult;
import com.janloong.springredisdemo.entity.Account;
import com.janloong.springredisdemo.entity.Customer;
import com.janloong.springredisdemo.redis.RedisUtil;
import com.janloong.springredisdemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
    public ResponseResult get(String key) {
        Object o = redisUtil.get(key);
        return ResponseResult.success(o);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 13:59
     **/
    @RequestMapping("/set")
    public ResponseResult set(String key, String value, long time) {
        log.info("time:" + time);
        boolean set = redisUtil.set(key, value, time);
        return ResponseResult.success(set);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 14:53
     **/
    @RequestMapping("/del")
    public ResponseResult del(String... keys) {
        redisUtil.del(keys);
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 16:39
     **/
    @RequestMapping("/incr")
    public ResponseResult incr(String key, long delta) {
        long incr = redisUtil.incr(key, delta);
        return ResponseResult.success(incr);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 16:39
     **/
    @RequestMapping("/decr")
    public ResponseResult decr(String key, long delta) {
        long decr = redisUtil.decr(key, delta);
        return ResponseResult.success(decr);
    }
    //==================================================

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:06
     **/
    @RequestMapping("/hget")
    public ResponseResult hget(String key, String item) {
        Object hget = redisUtil.hget(key, item);
        return ResponseResult.success(hget);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:13
     **/
    @RequestMapping("/hset")
    public ResponseResult hset(String key, String item, String value, @RequestParam(defaultValue = "0") long time) {
        boolean hset = redisUtil.hset(key, item, value, time);
        return ResponseResult.success(hset);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 17:25
     **/
    @RequestMapping("/hdel")
    public ResponseResult hdel(String key, String... item) {
        redisUtil.hdel(key, item);
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 17:50
     **/
    @RequestMapping("/hHasKey")
    public ResponseResult hHasKey(String key, String item) {
        redisUtil.hHasKey(key, item);
        return ResponseResult.success(null);
    }

    /**
     * 获取hashkey对应的所有键值
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:51
     **/
    @RequestMapping("/hmget")
    public ResponseResult hmget(String key) {
        Map<Object, Object> hmget = redisUtil.hmget(key);
        return ResponseResult.success(hmget);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:53
     **/
    @RequestMapping("/hmset")
    public ResponseResult hmset(String key, long time, String[] item, String[] value) {
        HashMap<String, Object> map = new HashMap<>();
        List<String> item1 = List.of(item);
        List<String> value1 = List.of(value);
        AtomicReference<Integer> i = new AtomicReference<>(0);
        item1.stream().forEach(s -> {
            map.put(s, value1.get(i.get()));
            i.getAndSet(i.get() + 1);
        });
        redisUtil.hmset(key, map, time);
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/1/9 15:13
     **/
    @RequestMapping("/hgetall")
    public ResponseResult hgetall(String key) {
        Map<Object, Object> hmget = redisUtil.hmget(key);
        return ResponseResult.success(hmget);
    }


    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/25 15:17
     **/
    @RequestMapping("/readCustomer")
    public ResponseResult readCustomer(String name) {
        Iterable<Customer> all = customerRepository.findAll();
        List<Object> objects = new LinkedList<>();
        all.forEach(customer -> objects.add(customer));

        return ResponseResult.success(objects);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/25 15:2544
     **/
    @RequestMapping("/saveCustomer")
    public ResponseResult saveCustomer(String name) {
        Customer janloong = new Customer(102L, "10002", "janloongdoo");
        janloong.addAccount(new Account(201L, "20002", 2000));
        janloong.addAccount(new Account(202L, "20003", 3000));
        janloong.addAccount(new Account(203L, "20004", 4000));
        customerRepository.save(janloong);
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/25 16:22
     **/
    @RequestMapping("/readAccount")
    public ResponseResult readAccount(@RequestParam String name) {
        return ResponseResult.success(null);
    }
}
