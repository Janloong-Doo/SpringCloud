package com.janloong.eurekaclient2.config;


import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-12 11:23
 */
//@Configuration
public class CustomRuleConfig {
    @Bean
    public IRule getRule() {
        return new CustomRule();
    }


}
