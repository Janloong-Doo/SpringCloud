/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: MyJobFactory.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/10 下午7:20
 * LastModify: 2020/1/10 下午7:20
 */

package com.janloong.springsecurity.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.quartz.config
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020 -01-10 19:20
 */
//@Configuration
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
