/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: TaskExecutePool.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/7 下午2:01
 * LastModify: 2020/4/7 下午1:58
 */

package com.janloong.springsecurity.thread.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义spring提供的线程池管理
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020/4/7 14:01
 **/
@Configuration
public class SpringThreadPoolTaskExecutor {

    /**
     * rejectedExectutionHandler 参数字段用于配置绝策略，常用拒绝策略如下
     * <p>
     * AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
     * CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务(即相应环境下的主线程)。
     * DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
     * DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
     * </P>
     */
    @Bean("DooTaskAsyncPool")
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(60);
        executor.setMaxPoolSize(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setQueueCapacity(2);
        executor.setKeepAliveSeconds(120);
        executor.setThreadNamePrefix("Doo-Executor-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
