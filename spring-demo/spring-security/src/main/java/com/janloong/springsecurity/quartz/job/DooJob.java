/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: DooJob.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/19 上午11:36
 * LastModify: 2020/1/19 上午11:36
 */

package com.janloong.springsecurity.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-01-19 11:36
 **/
@Slf4j
public class DooJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        log.info("执行任务:{}-{}", key.getName(), key.getGroup());
    }
}
