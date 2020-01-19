/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: MyJob.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 下午7:16
 * LastModify: 2020/1/10 下午7:16
 */

package com.janloong.springsecurity.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.quartz.job
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-10 19:16
 **/
@Slf4j
//public class MyJob implements Job {
public class MyJob extends QuartzJobBean {

    //@Override
    ////public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    //    String msg = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("msg");
    //    log.info("开始执行任务了,job中的关联数据为：{}", msg);
    //}

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        String msg = (String) jobDetail.getJobDataMap().get("msg");
        log.info("执行任务:{}-{},job中的关联数据为：{}", key.getName(), key.getGroup(), msg);
    }
}
