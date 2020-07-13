/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: QuartzController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 下午7:31
 * LastModify: 2020/1/10 下午7:31
 */

package com.janloong.springsecurity.quartz.controller;

import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.quartz.config.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Quartz controller.
 *
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.quartz.controller
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020 -01-10 19:31
 */
@Slf4j
@RestController
@RequestMapping("quartz")
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/10 19:32
     **/
    @RequestMapping("/update")
    public ResponseResult update(String name) throws Exception {
        /*10秒执行一次*/
        String cron = "*/10 * * * * ?";
        quartzManager.pauseJob(QuartzManager.JOB1, QuartzManager.GROUP1);
        quartzManager.modifyJob(QuartzManager.JOB1, QuartzManager.GROUP1, cron);
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/19 11:33
     **/
    @RequestMapping("/add")
    public ResponseResult add(@RequestParam String className
            , @RequestParam String jobName
            , @RequestParam String jobGroup
            , @RequestParam String date
            , @RequestParam String type
    ) {
        Class<? extends Job> aClass = null;
        try {
            aClass = (Class<? extends Job>) Class.forName(className);
            switch (type) {
                case "1":
                default:
                    quartzManager.addJobWithCron(aClass, jobName, jobGroup);
                    break;
                case "2":
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", date);
                    quartzManager.addJobWithSimple(aClass, jobName, jobGroup, map);
                    break;
            }
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/19 10:52
     **/
    @RequestMapping("/list")
    public ResponseResult list(String jobName, String jobGroup) {
        try {
            if (StringUtils.isEmpty(jobName) && StringUtils.isEmpty(jobGroup)) {
                Object allJobInfo = quartzManager.getAllJobInfo();
                return ResponseResult.success(allJobInfo);
            }
            String jobInfo = quartzManager.getJobInfo(jobName, jobGroup);
            return ResponseResult.success(jobInfo);
        } catch (SchedulerException e) {
            log.error("任务信息获取失败：{}-{}", jobName, jobGroup);
            e.printStackTrace();
        }
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/19 11:11
     **/
    @RequestMapping("/remove")
    public ResponseResult remove(String jobName, String jobGroup) {
        try {
            quartzManager.deleteJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            log.error("任务删除失败：{}-{}", jobName, jobGroup);
            e.printStackTrace();
        }
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/19 13:49
     **/
    @RequestMapping("/pause")
    public ResponseResult pause(String jobName, String jobGroup) {
        try {
            quartzManager.pauseJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/1/19 15:00
     **/
    @RequestMapping("/resume")
    public ResponseResult resume(String jobName, String jobGroup) {
        try {
            if (StringUtils.isEmpty(jobName) && StringUtils.isEmpty(jobGroup)) {
                quartzManager.resumeAllJob();
                return ResponseResult.success();
            }
            quartzManager.resumeJob(jobName, jobGroup);
            return ResponseResult.success();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(null);
    }
}
