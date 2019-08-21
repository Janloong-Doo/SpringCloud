/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: WebSecurityConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午3:35
 : LastModify: 19-3-29 下午3:35
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config;


import com.janloong.springsecurity.config.handler.AuthFailureHandler;
import com.janloong.springsecurity.config.handler.AuthSuccessHandler;
import com.janloong.springsecurity.config.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 15:35
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

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
        http.cors()
                .configurationSource(getUrlBasedCorsConfigurationSource())
                .and().csrf().disable()
                .formLogin()
                //.loginPage("/doo")
                .loginProcessingUrl("/login")
                //.failureUrl("/sign/error")
                //.failureUrl("http://192.168.236.1:8889/#/study")
                //.defaultSuccessUrl("/sign/successForward", true)
                //.successForwardUrl("/login/successForward")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .authorizeRequests()
                //此方法跨域想过未生效
                //.requestMatchers(CorsUtils::isCorsRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(
                        "/login"
                        , "/doo"
                        , "/user/add"
                        //, "/dooLogin"
                        , "/h2-console"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .logoutUrl("/logout")
        //.logoutSuccessUrl("/logout")
        ;
    }

    private UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
