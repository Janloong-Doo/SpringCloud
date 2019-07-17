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


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/8 13:38
     **/
    @GetMapping("/doo")
    public void doo(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("http://localhost:8889/spring");
        } catch (IOException e) {
            log.error("请求转发错误: {}", e.getMessage());
        }

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
