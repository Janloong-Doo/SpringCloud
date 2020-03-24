/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduledTask.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:15
 * LastModify: 2020/1/9 上午9:43
 */

package com.janloong.springsecurity.selfschedule.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Scheduled task.
 *
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.config.schedule
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020 -01-08 17:05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "scheduled_task")
public class ScheduledTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 任务key值 唯一
     */
    @Column(name = "task_key")
    private String taskKey;
    /**
     * 任务描述
     */
    @Column(name = "task_desc")
    private String taskDesc;
    /**
     * 任务表达式
     */
    @Column(name = "task_cron")
    private String taskCron;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    @Column(name = "init_start_flag")
    private Integer initStartFlag;

    /**
     * 当前是否已启动
     */
    @Column(name = "start_flag")
    private boolean startFlag;

    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
