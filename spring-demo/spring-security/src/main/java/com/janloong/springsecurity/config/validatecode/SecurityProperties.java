package com.janloong.springsecurity.config.validatecode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:54
 **/
@Data
@Configuration
//@EnableConfigurationProperties
//@ConfigurationPropertiesScan("com.janloong")
@ConfigurationProperties(prefix = "doo.security")
public class SecurityProperties {

    /**
     * 浏览器 属性类
     */
    //private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码 属性类
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
