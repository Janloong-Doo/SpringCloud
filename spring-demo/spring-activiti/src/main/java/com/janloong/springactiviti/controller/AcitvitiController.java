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

import cn.hutool.json.JSONUtil;
import com.janloong.common.utils.ResponseResult;
import com.janloong.springactiviti.config.SecurityUtil;
import com.janloong.springactiviti.entity.ProcessVO;
import com.janloong.springactiviti.entity.UserTaskVO;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-10-30 15:32
 **/
@Slf4j
@RestController
@RequestMapping("activiti")
public class AcitvitiController {

    @Resource
    private ProcessRuntime processRuntime;
    @Resource
    private RuntimeService RuntimeService;
    @Resource
    private TaskRuntime taskRuntime;
    @Resource
    private SecurityUtil securityUtil;

    /**
     * 查询流程定义
     */
    @RequestMapping("/getProcessDefinition")
    public void getProcess() {
        //查询所有流程定义信息
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("当前流程定义的数量：" + processDefinitionPage.getTotalItems());
        //获取流程信息
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            log.info("流程定义信息" + processDefinition);
        }
    }

    /**
     * 开始流程
     */
    @PostMapping("startProcess")
    public Object startProcess(@RequestBody ProcessVO vo) {
        log.debug(vo.toString());
        StartProcessPayload build = ProcessPayloadBuilder.start()
                .withProcessDefinitionKey(vo.getId())
                .withName(vo.getName())
                .withVariables(vo.getVariable())
                .build();
        //StartProcessPayload build = ProcessPayloadBuilder.start().withProcessDefinitionId(id).build();
        ProcessInstance instance = processRuntime.start(build);
        log.info(instance.getId());
        return instance;
    }

    /**
     * 获取流程信息参数
     *
     * @author <a href ="https://blog.janloong.com">Janloong</a>
     * @since 2020/11/2 14:00
     **/
    @GetMapping("/getProcessInstanceVariable")
    public ResponseResult getProcessVariable() {
        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(Pageable.of(0, 10));
        List<ProcessInstance> content = processInstancePage.getContent();
        content.forEach(processInstance -> {
            List<VariableInstance> variables = processRuntime.variables(
                    ProcessPayloadBuilder
                            .variables()
                            .withProcessInstance(processInstance)
                            .build());

            String s = JSONUtil.toJsonPrettyStr(variables);
            log.info("获取到的流程实例信息为:{}\n获取到对应的参数为:{}", processInstance, s);
        });
        return ResponseResult.success();
    }


    /**
     * 创建任务
     *
     * @author <a href ="https://blog.janloong.com">Janloong</a>
     * @since 2020/11/2 17:07
     **/
    @PostMapping("/createTask")
    public ResponseResult createTask(@RequestBody UserTaskVO vo) {
        securityUtil.logInAs(vo.getUsername());

        Task task = taskRuntime.create(TaskPayloadBuilder.create()
                .withName(vo.getName())
                .withDescription(vo.getDescription())
                .withCandidateGroup(vo.getCandidateGroup())
                .withPriority(vo.getPriority())
                .build());
        return ResponseResult.success("创建成功", task);
    }

    /**
     * 获取任务列表
     *
     * @author <a href ="https://blog.janloong.com">Janloong</a>
     * @since 2020/11/2 17:23
     **/
    @RequestMapping("/getUserTask")
    public ResponseResult getUserTask(String username) {
        securityUtil.logInAs(username);
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        return ResponseResult.success("获取成功", tasks);
    }

    /**
     * 领取任务
     *
     * @author <a href ="https://blog.janloong.com">Janloong</a>
     * @since 2020/11/2 17:26
     **/
    @RequestMapping("/claimUserTask")
    public ResponseResult claimUserTask(String taskId, String username) {
        securityUtil.logInAs(username);
        Task claim = taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(taskId).build());
        return ResponseResult.success("领取任务成功", claim);
    }

    /**
     * 完成任务
     *
     * @author <a href ="https://blog.janloong.com">Janloong</a>
     * @since 2020/11/2 17:29
     **/
    @RequestMapping("/completeUserTask")
    public ResponseResult completeUserTask(String taskId, String username) {
        securityUtil.logInAs(username);
        Task complete = taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        return ResponseResult.success("完成任务", complete);
    }
}
