/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: TaskServiceImpl.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/19 上午11:03
 * LastModify: 2020/1/19 上午11:03
 */

package com.janloong.springsecurity.quartz.service.impl;

import com.janloong.springsecurity.quartz.service.TaskService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-01-19 11:03
 **/
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * 任务调度器
     */
    @Autowired
    private Scheduler scheduler;


}
