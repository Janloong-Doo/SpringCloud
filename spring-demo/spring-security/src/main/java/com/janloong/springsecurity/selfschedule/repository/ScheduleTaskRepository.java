/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ScheduleTaskRepository.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 上午11:15
 * LastModify: 2020/1/10 上午11:15
 */

package com.janloong.springsecurity.selfschedule.repository;


import com.janloong.springsecurity.selfschedule.entity.ScheduledTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-10 15:30
 */
@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduledTask, Long> {
    
}
