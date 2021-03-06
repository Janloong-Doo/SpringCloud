/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CustomRouteConfiguration.java
 : Author: janloongdoo@gmail.com
 : Date: 18-9-27 下午2:26
 : LastModify: 18-9-27 下午2:25
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.zuul.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-26 15:56
 */
@Configuration
public class CustomRouteConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(CustomRouteConfiguration.class);


    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private DispatcherServletPath server;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CustomZuulRouteLocator dynamicLocator() {
        CustomZuulRouteLocator customZuulRouteLocator = new CustomZuulRouteLocator(server.getPrefix(), zuulProperties);
        logger.info("初始化成功: doo init success");
        customZuulRouteLocator.setJdbcTemplate(jdbcTemplate);
        return customZuulRouteLocator;

    }
}
