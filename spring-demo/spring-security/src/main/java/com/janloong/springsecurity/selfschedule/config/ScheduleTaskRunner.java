/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskRunner.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:16
 * LastModify: 2020/1/10 上午11:15
 */

package com.janloong.springsecurity.selfschedule.config;

import com.janloong.springsecurity.selfschedule.entity.ScheduledTask;
import com.janloong.springsecurity.selfschedule.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.config.schedule
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-09 10:06
 **/
//@Configuration
@Slf4j
public class ScheduleTaskRunner implements ApplicationRunner {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    /**
     * 程序启动完毕后,需要自启的任务
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 开始!");
        List<ScheduledTask> scheduledTaskBeanList = scheduleTaskService.getAllNeedStartTask();
        scheduleTaskService.initAllTask(scheduledTaskBeanList);
        log.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 结束！");
    }

}
