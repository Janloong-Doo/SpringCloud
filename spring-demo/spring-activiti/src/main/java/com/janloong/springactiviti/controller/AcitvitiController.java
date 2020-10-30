/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: AcitvitiController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/10/30 下午3:32
 * LastModify: 2020/10/30 下午3:32
 */

package com.janloong.springactiviti.controller;

import com.janloong.springactiviti.config.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-10-30 15:32
 **/
@RestController
@RequestMapping("activiti")
public class AcitvitiController {

    @Resource
    private ProcessRuntime processRuntime;
    @Resource
    private TaskRuntime taskRuntime;
    @Resource
    private SecurityUtil securityUtil;

    /**
     * 查询流程定义
     */
    @RequestMapping("/getProcess")
    public void getProcess() {
        //查询所有流程定义信息
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("当前流程定义的数量：" + processDefinitionPage.getTotalItems());
        //获取流程信息
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            System.out.println("流程定义信息" + processDefinition);
        }
    }

    /**
     * 开始流程
     */
    @RequestMapping("startProcess")
    public Object startProcess(@RequestParam String id) {
        //StartProcessPayload build = ProcessPayloadBuilder.start().withProcessDefinitionKey(key).build();
        StartProcessPayload build = ProcessPayloadBuilder.start().withProcessDefinitionId(id).build();
        ProcessInstance instance = processRuntime.start(build);
        System.out.println(instance.getId());
        return instance;
    }
}
