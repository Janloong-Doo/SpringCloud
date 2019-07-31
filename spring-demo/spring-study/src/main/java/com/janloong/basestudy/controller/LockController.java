/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LockController.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/30 上午10:45
 : LastModify: 2019/7/30 上午10:45
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.basestudy.config.localsync.Resubmit;
import com.janloong.basestudy.config.redissync.CacheLock;
import com.janloong.basestudy.config.redissync.CacheParam;
import com.janloong.common.utils.ResponseResult;
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
     * 本地锁测试
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/30 11:55
     **/
    @RequestMapping("/repeat")
    @Resubmit
    public ResponseResult repeat(String name, String address) {
        log.info(name + "-" + address);
        return ResponseResult.success(null);
    }

    /**
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
