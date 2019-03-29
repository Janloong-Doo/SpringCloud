package com.janloong.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAdminServer
//@EnableScheduling
@SpringBootApplication
@EnableEurekaClient
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    //@Configuration
    //public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http.authorizeRequests().anyRequest().permitAll()
    //                .and().csrf().disable();
    //    }
    //}
}
