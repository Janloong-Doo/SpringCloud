/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: TaskExecutePool.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/14 下午5:47
 : LastModify: 2019/8/14 下午5:47
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.thread.async;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-14 17:47
 */
@Configuration
@EnableAsync
public class TaskExecutePool {

    //自定义线程池执行器
   @Bean("myTaskAsyncPool")
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(60);
        executor.setMaxPoolSize(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setQueueCapacity(2);
        executor.setKeepAliveSeconds(120);
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
