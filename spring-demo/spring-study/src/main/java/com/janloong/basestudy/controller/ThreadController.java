/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ThreadController.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/31 下午5:04
 : LastModify: 2019/7/31 下午5:04
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-31 17:04
 */
@RestController
@RequestMapping("thread")
@Slf4j
public class ThreadController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/31 17:22
     **/
    @RequestMapping("/exec")
    public ResponseResult exec(@RequestParam String name) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("任务执行开始");
            }
        };

        //FutureTask<Runnable> runnableFutureTask = new FutureTask<Runnable>(runnable,);
        return ResponseResult.success(null);
    }
}
