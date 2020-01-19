/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskService.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:16
 * LastModify: 2020/1/10 上午11:15
 */

package com.janloong.springsecurity.selfschedule.service;


import com.janloong.springsecurity.selfschedule.entity.ScheduledTask;

import java.util.List;

/**
 * The type Schedule task service.
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019 -04-12 10:42
 */
public interface ScheduleTaskService {

    /**
     * 所有任务列表
     */
    List<ScheduledTask> taskList();

    /**
     * 根据任务key 启动任务
     */
    Boolean start(String taskKey);

    /**
     * 根据任务key 停止任务
     */
    Boolean stop(String taskKey);

    /**
     * 根据任务key 重启任务
     */
    Boolean restart(String taskKey);


    /**
     * 程序启动时初始化  ==> 启动所有正常状态的任务
     */
    void initAllTask(List<ScheduledTask> scheduledTaskBeanList);

    /**
     * 获取程序初始化需要自启的任务信息
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/9 10:17
     **/
    List<ScheduledTask> getAllNeedStartTask();

}
