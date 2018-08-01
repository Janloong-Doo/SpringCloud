package com.janloong.singlespringsecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 自定义资源服务器配置
 * <p>
 * Created by xw on 2017/3/19.
 * 2017-03-19 8:09
 */
//@EnableResourceServer
//@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/user").authorizeRequests().anyRequest().authenticated();
    }
}
