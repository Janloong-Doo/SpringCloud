package com.janloong.springsecurity.config.validatecode;

import lombok.Data;

/**
 * 验证码配置类
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:39
 **/
@Data
public class ValidateCodeProperties {
    /**
     * 图形验证码 配置属性
     */
    private ImageCodeProperties image = new ImageCodeProperties();
}
