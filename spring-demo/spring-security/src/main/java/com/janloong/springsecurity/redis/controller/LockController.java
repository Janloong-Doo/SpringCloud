/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: LockController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/8 上午10:38
 * LastModify: 2020/4/8 上午10:36
 */

package com.janloong.springsecurity.redis.controller;


import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.redis.localsync.Resubmit;
import com.janloong.springsecurity.redis.redissync.CacheLock;
import com.janloong.springsecurity.redis.redissync.CacheParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-30 10:45
 */
@RestController
@RequestMapping("lock")
@Slf4j
public class LockController {

    /**
     * 本地锁防重复提交测试
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/30 11:55
     **/
    @RequestMapping("/reSubmit")
    @Resubmit
    public ResponseResult repeat(String name, String address) {
        log.info(name + "-" + address);
        return ResponseResult.success(null);
    }

    /**
     * 防重提交目的没达到，待整理
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/30 16:34
     **/
    @CacheLock(prefix = "doo")
    @RequestMapping("/repeat2")
    public ResponseResult repeat2(@CacheParam(name = "tt") @RequestParam String tt) {

        log.info(tt);
        return ResponseResult.success(tt);
    }
}
