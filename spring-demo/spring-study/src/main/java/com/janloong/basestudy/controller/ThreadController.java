/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ThreadController.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/31 下午5:04
 : LastModify: 2019/7/31 下午5:04
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.basestudy.config.thread.CompletionServiceTask;
import com.janloong.basestudy.config.thread.CustomFutureTask;
import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-31 17:04
 */
@RestController
@RequestMapping("thread")
@Slf4j
public class ThreadController {
    @Autowired
    private CustomFutureTask task;
    @Autowired
    private CompletionServiceTask serviceTask;


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/31 17:22
     **/
    @RequestMapping("/exec")
    public ResponseResult exec() {
        System.out.println("ThreadController的线程:" + Thread.currentThread());
        long begin = System.currentTimeMillis();
        Map<String, Object> doo = task.getAggregatedTest("doo");
        long end = System.currentTimeMillis();
        System.out.println("===============总耗时:" + (end - begin) / 1000.0000 + "秒");
        return ResponseResult.success(doo);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/8/1 17:32
     **/
    @RequestMapping("/execTask")
    public ResponseResult execTask(String name) {
        serviceTask.getAggregatedTest(name);
        return ResponseResult.success(null);
    }
}
