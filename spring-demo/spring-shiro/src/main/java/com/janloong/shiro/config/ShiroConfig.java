/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ShiroConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-11 下午5:34
 : LastModify: 19-6-11 下午5:34
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.config;


import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-11 17:34
 */
@Configuration
public class ShiroConfig {

    @Bean
    CustomRealm getRealm() {
        return new CustomRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getRealm());
        return manager;
    }

    /**
     * 自定义过滤规则
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //放行规则   loginAuth达到同样效果处理
        //definition.addPathDefinition("/login", "anon");
        //definition.addPathDefinition("/loginUrl", "anon");
        //认证规则
        //definition.addPathDefinition("/**", "authc");
        return definition;
    }

}
