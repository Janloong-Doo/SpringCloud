/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskServiceImpl.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:16
 * LastModify: 2020/1/10 上午11:15
 */

package com.janloong.springsecurity.selfschedule.service;


import com.janloong.common.config.BaseService;
import com.janloong.springsecurity.selfschedule.config.ScheduleTaskJob;
import com.janloong.springsecurity.selfschedule.entity.ScheduledTask;
import com.janloong.springsecurity.selfschedule.repository.ScheduleTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * The type Schedule task service.
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019 -04-12 10:42
 */
@Slf4j
//@Service
public class ScheduleTaskServiceImpl extends BaseService<ScheduleTaskRepository> implements ScheduleTaskService {

    /**
     * 可重入锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 定时任务线程池
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 所有定时任务存放Map key :任务 key value:任务实现
     */
    @Autowired
    @Qualifier(value = "scheduledTaskJobMap")
    private Map<String, ScheduleTaskJob> scheduledTaskJobMap;

    /**
     * 存放已经启动的任务map
     */
    private Map<String, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();


    @Override
    public List<ScheduledTask> taskList() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        Arrays.asList(stack).forEach(System.out::println);

        log.info(">>>>>> 获取任务列表开始 >>>>>> ");
        //数据库查询所有任务 => 未做分页
        List<ScheduledTask> taskBeanList = this.getRepository().findAll();
        if (CollectionUtils.isEmpty(taskBeanList)) {
            return new ArrayList<>();
        }

        for (ScheduledTask taskBean : taskBeanList) {
            String taskKey = taskBean.getTaskKey();
            //是否启动标记处理
            taskBean.setStartFlag(this.isStart(taskKey));
        }
        log.info("任务列表信息：{}", taskBeanList.stream().map(ScheduledTask::getTaskKey).collect(Collectors.toList()));
        log.info(">>>>>> 获取任务列表结束 >>>>>> ");
        return taskBeanList;
    }

    @Override
    public Boolean start(String taskKey) {
        log.info(">>>>>> 启动任务 {} 开始 >>>>>>", taskKey);
        //添加锁放一个线程启动，防止多人启动多次
        lock.lock();
        log.info(">>>>>> 添加任务启动锁完毕");
        try {
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                log.info(">>>>>> 当前任务已经启动，无需重复启动！");
                return false;
            }
            //校验任务是否存在
            if (!scheduledTaskJobMap.containsKey(taskKey)) {
                return false;
            }
            //根据key数据库获取任务配置信息
            //启动任务
            this.getRepository().findOne(Example.of(ScheduledTask.builder().taskKey(taskKey).build()))
                    .ifPresentOrElse(this::doStartTask, () -> log.debug("没有找到相应的任务信息：{}", taskKey));
        } finally {
            // 释放锁
            lock.unlock();
            log.info(">>>>>> 释放任务启动锁完毕");
        }
        log.info(">>>>>> 启动任务 {} 结束 >>>>>>", taskKey);
        return true;
    }

    @Override
    public Boolean stop(String taskKey) {
        log.info(">>>>>> 进入停止任务 {}  >>>>>>", taskKey);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskKey);
        log.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag);
        if (taskStartFlag) {
            //获取任务实例
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskKey);
            //关闭实例
            scheduledFuture.cancel(true);
        }
        log.info(">>>>>> 结束停止任务 {}  >>>>>>", taskKey);
        return taskStartFlag;
    }

    @Override
    public Boolean restart(String taskKey) {
        log.info(">>>>>> 进入重启任务 {}  >>>>>>", taskKey);
        //先停止
        this.stop(taskKey);
        //再启动
        return this.start(taskKey);
    }


    @Override
    public void initAllTask(List<ScheduledTask> scheduledTaskBeanList) {
        log.info("程序启动 ==> 初始化所有任务开始 ！size={},key={}", scheduledTaskBeanList.size(), scheduledTaskBeanList.stream().map(ScheduledTask::getTaskKey).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(scheduledTaskBeanList)) {
            return;
        }
        for (ScheduledTask scheduledTask : scheduledTaskBeanList) {
            //任务 key
            String taskKey = scheduledTask.getTaskKey();
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                continue;
            }
            //启动任务
            this.doStartTask(scheduledTask);
        }
        log.info("程序启动 ==> 初始化所有任务结束 ！size={}", scheduledTaskBeanList.size());
    }

    @Override
    public List<ScheduledTask> getAllNeedStartTask() {
        return this.getRepository().findAll(Example.of(ScheduledTask.builder().initStartFlag(1).build()));
    }

    /**
     * 执行启动任务
     */
    private void doStartTask(ScheduledTask scheduledTask) {
        //任务key
        String taskKey = scheduledTask.getTaskKey();
        //定时表达式
        String taskCron = scheduledTask.getTaskCron();
        //获取需要定时调度的接口
        ScheduleTaskJob scheduledTaskJob = scheduledTaskJobMap.get(taskKey);
        log.info(">>>>>> 任务 [ {} ] ,cron={}", scheduledTask.getTaskDesc(), taskCron);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob,
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        CronTrigger cronTrigger = new CronTrigger(taskCron);
                        return cronTrigger.nextExecutionTime(triggerContext);
                    }
                });
        //将启动的任务放入 map
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }

    /**
     * 任务是否已经启动
     */
    private Boolean isStart(String taskKey) {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap.get(taskKey).isCancelled()) {
                return true;
            }
        }
        return false;
    }
}
