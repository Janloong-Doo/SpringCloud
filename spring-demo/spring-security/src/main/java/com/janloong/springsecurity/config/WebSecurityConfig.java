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
import com.janloong.springsecurity.config.validatecode.SecurityProperties;
import com.janloong.springsecurity.config.validatecode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;

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
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置密码加密类型
        //DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //encoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        auth
                .userDetailsService(userAuthService)
                //.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
                .passwordEncoder(getPasswordEncoder());
        //创建内容中的临时用户
        //auth.inMemoryAuthentication()
        //.passwordEncoder(NoOpPasswordEncoder.getInstance());
        //.withUser("doo").password("doo").roles("USER");
    }

    //@Override
    //public void configure(WebSecurity web)  {
    //    super.configure(web);
    //}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.sessionManagement().enableSessionUrlRewriting(true);
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http
                .cors()
                .configurationSource(getUrlBasedCorsConfigurationSource())
                .and()
                .csrf().disable()
                //在登录认证前增加过滤器
                .addFilterBefore(getValidateCodeFillter(), UsernamePasswordAuthenticationFilter.class)
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
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(
                        "/login"
                        , "/thread/**"
                        , "/request/**"
                        , "/schedule/**"
                        , "/quartz/**"
                        , "/redis/**"
                        , "/localDateTime/**"
                        , "/oauth/**"
                        , "/oauth/token"
                        , "/doo"
                        , "/validate/imageCode"
                        , "/user"
                        , "/user/add"
                        //, "/dooLogin"
                        , "/h2-console"
                        , "/send/**"
                        , "/lock/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .logoutUrl("/logout")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(getAuthenticationEntryPoint())//重写认证异常的登录页重定向
        //.logoutSuccessUrl("/logout")
        ;
    }

    private UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("http://localhost:8889");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 需要时禁止跳转登录页 返回json
     */
    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        //return new CustomLoginUrlAuthenticationEntryPoint(loginForm.getLoginPageUrl());
        return new CustomLoginUrlAuthenticationEntryPoint("/spring/login");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public ValidateCodeFilter getValidateCodeFillter() throws ServletException {
        /**
         * 创建 验证码 过滤器 ，并将该过滤器的Handler 设置成自定义登录失败处理器
         */
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(authFailureHandler);
        //将 securityproperties 设置进去
        validateCodeFilter.setSecurityProperties(securityProperties);
        //调用 装配 需要图片验证码的 url 的初始化方法
        validateCodeFilter.afterPropertiesSet();
        return validateCodeFilter;
    }

}
