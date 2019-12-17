/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthSuccessHandler.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-25 下午4:14
 : LastModify: 19-7-25 下午4:14
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config.handler;


import com.alibaba.fastjson.JSONObject;
import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.entity.UserDetailImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-25 15:59
 */
@Slf4j
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        log.info("用戶: " + userDetails.getUsername() + " 登录");

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info(savedRequest.getRedirectUrl());
            if (!StringUtils.isEmpty(targetUrl)) {
                redirectStrategy.sendRedirect(request, response, targetUrl);
            }
        } else {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(ResponseResult.success()));
            writer.flush();
            writer.close();
        }
    }
}
