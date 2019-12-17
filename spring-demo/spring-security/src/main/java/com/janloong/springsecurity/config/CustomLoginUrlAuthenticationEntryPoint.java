package com.janloong.springsecurity.config;

import com.alibaba.fastjson.JSONObject;
import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 取消未登录返回页面
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/17 18:18
 **/
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //super.commence(request, response, authException);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(ResponseResult.error(ResultEnum.ACCOUNT_ERROR.getCode(), ResultEnum.ACCOUNT_ERROR.getMsg())));
        writer.flush();
        writer.close();
    }
}
