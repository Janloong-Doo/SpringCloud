/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: WebSecurityConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午3:35
 : LastModify: 19-3-29 下午3:35
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 15:35
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserAuthService userAuthService;
    //@Override
    //protected UserDetailsService userDetailsService() {
    //    //return super.userDetailsService();
    //    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //    manager.createUser(User.withDefaultPasswordEncoder().username("doo").password("doo").roles("USER").build());
    //    return manager;
    //}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("doo").password("doo").roles("USER");
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
                .and()
                .authorizeRequests()
                .antMatchers(
                        //"/login/**"
                        "/doo"
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
        //.and()
        //.logout().logoutUrl("/logout").logoutSuccessUrl("/")
        ;
    }
}
