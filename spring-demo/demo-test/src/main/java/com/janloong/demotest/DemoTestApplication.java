package com.janloong.demotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(value = "com.janloong")
public class DemoTestApplication {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println("启动参数");
        System.out.println(list);
        JanloongBanner banner = new JanloongBanner();
        SpringApplication application = new SpringApplication(DemoTestApplication.class);
        application.setBanner(banner);
        application.run(args);
        //SpringApplication.run(DemoTestApplication.class, args);
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }


}
