/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CompletionServiceTask.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/1 下午4:34
 : LastModify: 2019/8/1 下午4:19
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 聚合业务测试，以不同的形式获取结果集，保证结果的有序处理和无序处理
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-01 14:08
 */
@Slf4j
@Component
public class CompletionServiceTask {
    @Autowired
    private AggregatedService aggregatedService;
    //
    private static ExecutorService executor = new ThreadPoolExecutor(8, 20,
            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactoryBuilder().setNameFormat("User1_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    private static ExecutorService executor2 = new ThreadPoolExecutor(8, 20,
            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactoryBuilder().setNameFormat("User2_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * jdk自带的线程池 初始化固定的线程数量
     * <p>
     * 任务超过线程数量的时候会等3个线程执行完毕后才会开始新的一轮的三个线程任务
     */
    private static ExecutorService executor3 = Executors.newFixedThreadPool(3);

    /**
     * 获取聚合信息
     * <p>
     * 结果封装进queue中取出，子业务结果执行完毕后悔塞进queue中，获得一个结果就能弹出取出一个结果
     * 单纯做结果聚合，对结果获得顺序没有特殊要求
     *
     * @param name
     * @return
     */
    @SuppressWarnings("all")
    public Map<String, Object> getAggregatedWithQueue(final String name) {

        log.info("CustomFutureTask的线程:" + Thread.currentThread());
        long test11 = 0, test12 = 0, test13 = 0,
                test14 = 0, test15 = 0, test16 = 0;
        try {
            ExecutorCompletionService<Long> aggregatedService = new ExecutorCompletionService<>(executor3);

            Future<Long> test1 = aggregatedService.submit(() -> this.aggregatedService.test1(name));
            Future<Long> test2 = aggregatedService.submit(() -> this.aggregatedService.test2(name));
            Future<Long> test3 = aggregatedService.submit(() -> this.aggregatedService.test3(name));
            Future<Long> test4 = aggregatedService.submit(() -> this.aggregatedService.test4(name));
            Future<Long> test5 = aggregatedService.submit(() -> this.aggregatedService.test5(name));
            Future<Long> test6 = aggregatedService.submit(() -> this.aggregatedService.test6(name));

            for (int i = 0; i < 6; i++) {
                System.out.println(aggregatedService.take().get());
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

    /**
     * 获取聚合信息
     * <p>
     * 阻塞方式获取处理结果，可以保证业务的顺序处理.
     *
     * @param name
     * @return
     */
    @SuppressWarnings("all")
    public Map<String, Object> getAggregatedSync(final String name) {
        log.info("CustomFutureTask的线程:" + Thread.currentThread());
        long test11 = 0, test12 = 0, test13 = 0,
                test14 = 0, test15 = 0, test16 = 0;
        long test101 = 0, test201 = 0, test301 = 0,
                test401 = 0, test501 = 0, test601 = 0;
        try {
            //Future<Long> test1 = executor.submit(() -> aggregatedService.test1(name));
            Future<Long> test2 = executor.submit(() -> aggregatedService.test2(name));
            //Future<Long> test3 = executor.submit(() -> aggregatedService.test3(name));
            //Future<Long> test4 = executor.submit(() -> aggregatedService.test4(name));
            Future<Long> test5 = executor.submit(() -> aggregatedService.test5(name));
            Future<Long> test6 = executor.submit(() -> aggregatedService.test6(name));

            Future<Long> test10 = executor2.submit(() -> aggregatedService.test1(name));
            Future<Long> test20 = executor2.submit(() -> aggregatedService.test2(name));
            Future<Long> test30 = executor2.submit(() -> aggregatedService.test3(name));
            Future<Long> test40 = executor2.submit(() -> aggregatedService.test4(name));
            Future<Long> test50 = executor2.submit(() -> aggregatedService.test5(name));
            Future<Long> test60 = executor2.submit(() -> aggregatedService.test6(name));
            //通过并行计算能提高串行的整体时间消费，
            //get阻塞  只有同一调度任务服务相关联的所有Future都执行完成的时候才能获得结果，会浪费许多等待结果
            //test11 = test1.get();
            //log.info("test11的值为:" + test11);
            test12 = test2.get();
            log.info("test12的值为:" + test12);
            //test13 = test3.get();
            //log.info("test13的值为:" + test13);
            //test14 = test4.get();
            //log.info("test14的值为:" + test14);
            test15 = test5.get();
            log.info("test15的值为:" + test15);
            test16 = test6.get();
            log.info("test16的值为:" + test16);

            test101 = test10.get();
            log.info("test101的值为:" + test101);
            test201 = test20.get();
            log.info("test201的值为:" + test201);
            test301 = test30.get();
            log.info("test301的值为:" + test301);
            test401 = test40.get();
            log.info("test401的值为:" + test401);
            test501 = test50.get();
            log.info("test501的值为:" + test501);
            test601 = test60.get();
            log.info("test601的值为:" + test601);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            log.error(">>>>>>聚合测试聚合信息异常:" + e + "<<<<<<<<<");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("test11", test11);
        map.put("test12", test12);
        map.put("test13", test13);
        map.put("test14", test14);
        map.put("test15", test15);
        map.put("test16", test16);

        return map;
    }
}
