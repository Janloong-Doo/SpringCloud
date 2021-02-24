/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorInfoAutoConfiguration.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午3:50
 : LastModify: 19-5-21 上午9:20
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.base.author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户信息自动配置
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2019-05-15 17:56
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(AuthorInfoProvider.class)
@EnableConfigurationProperties(AuthorInfo.class)
public class AuthorInfoAutoConfiguration {

    @Autowired
    private AuthorInfo authorInfo;

    @Bean
    @ConditionalOnMissingBean(AuthorInfoProvider.class)
    public AuthorInfoProvider provider() {
        AuthorInfoProvider authorInfoProvider = new AuthorInfoProvider();
        authorInfoProvider.setAuthrorInfo(authorInfo);
        return authorInfoProvider;
    }
}
