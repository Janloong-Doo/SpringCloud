/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LoginController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-12 上午9:12
 : LastModify: 19-6-12 上午9:12
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.controller;


import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-12 9:12
 */
@RestController
public class LoginController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/12 9:15
     **/
    @RequestMapping("/login")
    public ResponseResult login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (AuthenticationException e) {
            System.out.println("cuowu");
            return ResponseResult.error(ResultEnum.ACCOUNT_ERROR.getCode(), e.getMessage());
        }
        System.out.println("登录成功");
        return ResponseResult.success("返回登录成功");
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/13 16:57
     **/
    @RequestMapping("/loginUrl")
    public ResponseResult loginUrl() {
        return ResponseResult.success("loginUrl");
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/12 9:16
     **/
    @RequestMapping("/hello")
    public ResponseResult hello() {
        return ResponseResult.success("hello");
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/12 9:21
     **/
    @PostMapping("/doo")
    public ResponseResult doo(String name) {
        return ResponseResult.success(name);
    }
}
