/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CountdownLatchDemo.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/13 上午11:34
 : LastModify: 2019/8/13 上午11:34
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.thread;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-13 11:34
 */
@Slf4j
@Component
public class CountDownLatchDemo {

    static final CountDownLatch latch = new CountDownLatch(10);

    public void start() {
        //模拟随机耗时任务
        Runnable runnable = () -> {
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println("check complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //计数减一
                //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
                latch.countDown();
            }

        };

        try {
            ExecutorService exec = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                exec.submit(runnable);
            }
            // 等待检查
            latch.await();

            // 校验所有任务完成
            System.out.println("执行完成!");

            // 关闭线程池
            exec.shutdown();
        } catch (Exception exception) {
            log.error("模拟执行任务失败");
        }
    }
}
