/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduledTask01.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/8 下午8:15
 * LastModify: 2020/1/8 下午8:15
 */

package com.janloong.springsecurity.selfschedule.job;

import com.janloong.springsecurity.selfschedule.config.ScheduleTaskJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.selfschedule.job
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-08 20:15
 **/
@Slf4j
public class ScheduledTask03 implements ScheduleTaskJob {
    @Override
    public void run() {
        log.info("这是任务计划3");
    }
}
