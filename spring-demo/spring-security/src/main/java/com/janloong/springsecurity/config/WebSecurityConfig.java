/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: WebSecurityConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午3:35
 : LastModify: 19-3-29 下午3:35
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 15:35
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置密码加密类型
        //DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //encoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        auth
                .userDetailsService(userAuthService)
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        //创建内容中的临时用户
        //auth.inMemoryAuthentication()
        //.passwordEncoder(NoOpPasswordEncoder.getInstance());
        //.withUser("doo").password("doo").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                .formLogin()
                //.loginProcessingUrl("doo")
                .loginPage("/doo")
                .loginProcessingUrl("dooLogin")
                //.successForwardUrl("/index")
                .and()
                .authorizeRequests()
                .antMatchers(
                        //"/login/**"
                        "/doo"
                        , "/user/add"
                        , "/dooLogin"
                        , "/static/**"
                        //, "/**"
                        , "/h2-console"
                        //, "/doo.html"
                        //, "/oauth/**"
                        //, "/user"
                        //, "/webjars/**"
                        //, "/error/**"
                        //, "/oauth/**"
                        //, "/before"
                        //, "/before2"
                        //, "/oauth/authorize"
                        //, "/oauth/confirm_access"
                )
                .permitAll()

                .anyRequest()
                .authenticated()
                //.failureForwardUrl("/doo.html?error")
                //.permitAll()
                .and()
                .logout().logoutUrl("/logout")
        //.logoutSuccessUrl("/logout")
        ;
    }
}
