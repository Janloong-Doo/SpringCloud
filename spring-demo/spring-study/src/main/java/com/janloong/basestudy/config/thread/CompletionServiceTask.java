/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CompletionServiceTask.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/1 下午4:34
 : LastModify: 2019/8/1 下午4:19
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.thread;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-01 14:08
 */
@Slf4j
@Component
public class CompletionServiceTask {
    @Autowired
    private AggregatedService aggregatedService;

    private static ExecutorService executor = Executors.newFixedThreadPool(3);

    /**
     * 获取聚合信息
     *
     * @param name
     * @return
     */
    @SuppressWarnings("all")
    public Map<String, Object> getAggregatedTest(final String name) {

        log.info("CustomFutureTask的线程:" + Thread.currentThread());
        long test11 = 0, test12 = 0, test13 = 0,
                test14 = 0, test15 = 0, test16 = 0;
        try {
            ExecutorCompletionService<Long> service = new ExecutorCompletionService<>(executor);

            Future<Long> test1 = service.submit(() -> this.aggregatedService.test1(name));
            Future<Long> test2 = service.submit(() -> this.aggregatedService.test2(name));
            Future<Long> test3 = service.submit(() -> this.aggregatedService.test3(name));
            Future<Long> test4 = service.submit(() -> this.aggregatedService.test4(name));
            Future<Long> test5 = service.submit(() -> this.aggregatedService.test5(name));
            Future<Long> test6 = service.submit(() -> this.aggregatedService.test6(name));

            for (int i = 0; i < 6; i++) {
                System.out.println(service.take().get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            log.error(">>>>>>聚合测试聚合信息异常:" + e + "<<<<<<<<<");
        }
        //HashMap<String, Object> map = new HashMap<>();
        //map.put("test11", test11);
        //map.put("test12", test12);
        //map.put("test13", test13);
        //map.put("test14", test14);
        //map.put("test15", test15);
        //map.put("test16", test16);

        return null;
    }

}
