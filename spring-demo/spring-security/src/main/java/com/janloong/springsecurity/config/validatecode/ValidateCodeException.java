package com.janloong.springsecurity.config.validatecode;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义 验证码异常类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:43
 **/

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
