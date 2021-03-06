/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorInfoAutoConfiguration.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-15 下午5:56
 : LastModify: 19-5-15 下午5:56
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springbootstartercustom.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-15 17:56
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
