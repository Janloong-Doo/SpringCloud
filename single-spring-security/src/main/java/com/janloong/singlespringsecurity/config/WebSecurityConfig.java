package com.janloong.singlespringsecurity.config;


import com.janloong.singlespringsecurity.service.DooUserDeatilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-12 16:04
 */
@Configuration
@EnableWebSecurity
//@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DooUserDeatilService dooUserDeatilService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/**"
                        , "/oauth/**"
                        , "/user"
                        //, "/webjars/**"
                        //, "/error/**"
                        //, "/**"
                        //, "/oauth/**"
                        //, "/before"
                        //,"/before2"
                        //,"/oauth/authorize"
                        //,"/oauth/confirm_access"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //.loginProcessingUrl("doo")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
        //.and().authorizeRequests().anyRequest().authenticated()
        ;
    }


    /**
     * 配置用户认证方式和密码加密方式
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(dooUserDeatilService)
                .passwordEncoder(passwordEncoder())
        //.passwordEncoder(new PasswordEncoder() {
        //    @Override
        //    public String encode(CharSequence charSequence) {
        //        return charSequence.toString() + "+";
        //    }
        //
        //    @Override
        //    public boolean matches(CharSequence charSequence, String s) {
        //        return s.equals(charSequence.toString() + "+");
        //    }
        //})
        ;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
