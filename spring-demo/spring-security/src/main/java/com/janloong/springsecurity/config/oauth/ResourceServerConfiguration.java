package com.janloong.springsecurity.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * 自定义资源服务器配置
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020/4/27 0027 22:14
 **/
@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //super.configure(resources);
        //resources.resourceId("doo2").stateless(true);
    }

    /**
     * 这里设置需要token验证的url
     * 这些url需要在WebSecurityConfigurerAdapter中排掉
     * 否则进入WebSecurityConfigurerAdapter,进行的是basic auth或表单认证,而不是token认证
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.requestMatchers()
                .antMatchers("/user")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
