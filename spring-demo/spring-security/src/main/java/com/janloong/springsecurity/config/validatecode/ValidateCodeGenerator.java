package com.janloong.springsecurity.config.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:43
 **/
public interface ValidateCodeGenerator {
    /**
     * 创建验证码
     */
    ImageCode  createCode(ServletWebRequest request);
}
