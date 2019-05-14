/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CustomRuleConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:41
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.ribbon.config;


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
