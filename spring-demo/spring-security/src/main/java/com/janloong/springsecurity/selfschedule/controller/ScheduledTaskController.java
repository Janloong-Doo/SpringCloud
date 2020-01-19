/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduledTaskController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:14
 * LastModify: 2020/1/9 上午9:29
 */

package com.janloong.springsecurity.selfschedule.controller;

import com.janloong.springsecurity.selfschedule.entity.ScheduledTask;
import com.janloong.springsecurity.selfschedule.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.controller
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-09 09:27
 **/
//@RestController
@RequestMapping("schedule")
public class ScheduledTaskController {

    @Autowired
    private ScheduleTaskService scheduledTaskService;

    /**
     * 所有任务列表
     */
    @RequestMapping("/taskList")
    public List<ScheduledTask> taskList() {
        return scheduledTaskService.taskList();
    }

    /**
     * 根据任务key => 启动任务
     */
    @RequestMapping("/start")
    public String start(String taskKey) {
        scheduledTaskService.start(taskKey);
        return "start success";
    }

    /**
     * 根据任务key => 停止任务
     */
    @RequestMapping("/stop")
    public String stop(String taskKey) {
        scheduledTaskService.stop(taskKey);
        return "stop success";
    }

    /**
     * 根据任务key => 重启任务
     */
    @RequestMapping("/restart")
    public String restart(String taskKey) {
        scheduledTaskService.restart(taskKey);
        return "restart success";
    }
}
