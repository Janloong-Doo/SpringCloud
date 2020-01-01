/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: CustomApplicationRunner.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/1 下午2:23
 * LastModify: 2020/1/1 下午2:23
 */

package com.janloong.aliprovider.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020/1/1 14:23
 **/
@Configuration
@Slf4j
public class CustomApplicationRunner implements ApplicationRunner {
    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("nacos属性展示");
        log.info(nacosConfigProperties.toString());
    }
}
