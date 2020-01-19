/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskConfig.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:16
 * LastModify: 2020/1/8 下午8:37
 */

package com.janloong.springsecurity.selfschedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Map;

/**
 * 线程调度吃配置
 *
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.config.schedule
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @dep R &D SP Co.,Ltd
 * @create: 2020 -01-08 17:04
 */
@Slf4j
//@Configuration
public class ScheduleTaskConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        log.info("创建定时任务调度线程池 start");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        threadPoolTaskScheduler.setThreadNamePrefix("DooTaskExecutor-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        log.info("创建定时任务调度线程池 end");
        return threadPoolTaskScheduler;
    }

    @Bean(name = "scheduledTaskJobMap")
    public Map<String, ScheduleTaskJob> scheduledTaskJobMap() {
        return ScheduleTaskEnum.initScheduledTask();
    }
}
