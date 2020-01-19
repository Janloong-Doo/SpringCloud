/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: QuartzManager.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 下午7:13
 * LastModify: 2020/1/10 下午7:13
 */

package com.janloong.springsecurity.quartz.config;

import com.alibaba.fastjson.JSONObject;
import com.janloong.springsecurity.quartz.job.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Quartz manager.
 *
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.quartz.config
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020 -01-10 19:13
 */
@Slf4j
@Configuration
public class QuartzManager {

    public static final String JOB1 = "job1";
    public static final String GROUP1 = "group1";
    /**默认每个星期凌晨一点执行*/
    //public static final String DEFAULT_CRON="0 0 1 ? * L";
    /**
     * 默认5秒执行一次
     */
    public static final String DEFAULT_CRON = "*/5 * * * * ?";

    /**
     * 任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 开始执行定时任务
     */
    public void startJob() throws SchedulerException {
        startJobTask(scheduler);
        scheduler.start();
    }

    /**
     * 启动定时任务
     *
     * @param scheduler
     */
    private void startJobTask(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                //自定义的业务类
                .newJob(MyJob.class)
                //对于该jobDetail的一个id
                .withIdentity(JOB1, GROUP1)
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                //关联键值对
                .usingJobData("msg", "Hello Quartz")
                //即使没有Trigger关联时，也不需要删除该JobDetail
                //.storeDurably()
                .build();
        //cron触发器构建
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                //关联job, 不关联有什么影响
                //.forJob(jobDetail)
                .withIdentity(JOB1, GROUP1)
                .withSchedule(cronScheduleBuilder)
                .build();
        //任务调度器调度工作
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * Add job.
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020 /1/19 11:46
     */
    public void addJob(Class<? extends Job> clazz, String jobName, String jobGroup) throws SchedulerException {
        addJob(clazz, jobName, jobGroup, new JobDataMap());
    }

    public void addJob(Class<? extends Job> clazz, String jobName, String jobGroup, JobDataMap jobDataMap) throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                //自定义的业务类
                .newJob(clazz)
                //对于该jobDetail的一个id
                .withIdentity(jobName, jobGroup)
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                //关联键值对
                .usingJobData(jobDataMap)
                //即使没有Trigger关联时，也不需要删除该JobDetail
                //.storeDurably()
                .build();

        //cron触发器构建
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                //关联job, 不关联有什么影响
                //.forJob(jobDetail)
                .withIdentity(jobName, jobGroup)
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @param group
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * Gets all job info.
     *
     * @return the all job info
     * @throws SchedulerException the scheduler exception
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020 /1/19 11:29
     */
    public Object getAllJobInfo() throws SchedulerException {
        List<JobDetail> results = new ArrayList<>();
        //List<String> jobGroupNames = scheduler.getJobGroupNames();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
            jobKeys.forEach(jobKey -> {
                try {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    results.add(jobDetail);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            });

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Map<String, List<Object>> group = results.stream().map(JSONObject::toJSON).collect(Collectors.groupingBy(o -> JSONObject.parseObject(o.toString()).get("group").toString()));
        //List<Object> collect = results.stream().map(JSONObject::toJSON).collect(Collectors.toList());
        return group;
    }

    /**
     * 修改任务的执行时间
     *
     * @param name
     * @param group
     * @param cron  cron表达式
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String cron) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cron)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
        log.debug("暂停任务:{}-{}", name, group);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException the scheduler exception
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     *
     * @param name  the name
     * @param group the group
     * @throws SchedulerException the scheduler exception
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.resumeJob(jobKey);
        log.debug("恢复任务:{}-{}", name, group);

    }

    /**
     * 删除某个任务
     *
     * @param name  the name
     * @param group the group
     * @throws SchedulerException the scheduler exception
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

}
