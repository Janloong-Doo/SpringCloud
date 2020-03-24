/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskEnum.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:16
 * LastModify: 2020/1/10 上午11:15
 */

package com.janloong.springsecurity.selfschedule.config;

import com.janloong.springsecurity.selfschedule.job.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.config.schedule
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-08 20:00
 **/
public enum ScheduleTaskEnum {

    /**
     * 任务1
     */
    TASK_01("scheduledTask01", new ScheduledTask01()),
    /**
     * 任务2
     */
    TASK_02("scheduledTask02", new ScheduledTask02()),
    /**
     * 任务3
     */
    TASK_03("scheduledTask03", new ScheduledTask03());

    /**
     * 定时任务key
     */
    private String taskKey;
    /**
     * 定时任务 执行实现类
     */
    private ScheduleTaskJob scheduleTaskJob;


    ScheduleTaskEnum(String taskKey, ScheduleTaskJob scheduledTaskJob) {
        this.taskKey = taskKey;
        this.scheduleTaskJob = scheduledTaskJob;
    }

    /**
     * 初始化 所有任务
     */
    public static Map<String, ScheduleTaskJob> initScheduledTask() {
        if (ScheduleTaskEnum.values().length < 0) {
            return new ConcurrentHashMap<>();
        }
        Map<String, ScheduleTaskJob> scheduledTaskJobMap = new ConcurrentHashMap<>();
        for (ScheduleTaskEnum taskEnum : ScheduleTaskEnum.values()) {
            scheduledTaskJobMap.put(taskEnum.taskKey, taskEnum.scheduleTaskJob);
        }
        return scheduledTaskJobMap;
    }
}
