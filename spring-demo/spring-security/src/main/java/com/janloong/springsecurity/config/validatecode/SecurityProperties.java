package com.janloong.springsecurity.config.validatecode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:54
 **/
@Data
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
