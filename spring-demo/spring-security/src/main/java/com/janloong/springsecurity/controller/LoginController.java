/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LoginController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:15
 : LastModify: 19-3-29 下午5:15
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 17:15
 */
@Controller
@Slf4j
public class LoginController {


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/8 13:38
     **/
    @GetMapping("/doo")
    @ResponseBody
    public String doo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //try {
        //    //response.sendRedirect("http://localhost:8889/spring");
            response.sendRedirect("http://192.168.236.1:8889/#/spring");
        //} catch (IOException e) {
        //    log.error("请求转发错误: {}", e.getMessage());
        //}
        //SavedRequest savedRequest = requestCache.getRequest(request, response);
        //if (savedRequest != null) {
        //    String targetUrl = savedRequest.getRedirectUrl();
        //    //if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
        //    redirectStrategy.sendRedirect(request, response, "http://192.168.236.1:8889/spring");
        //    //}
        //}
        return "访问的资源需要身份认证！";
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/29 17:36
     **/
    @PostMapping("/dooLogin")
    @ResponseBody
    public ResponseResult dooLogin(String username, String password) {
        Map<String, String> map = Map.of(username, password);
        return ResponseResult.success(map);
    }

}
