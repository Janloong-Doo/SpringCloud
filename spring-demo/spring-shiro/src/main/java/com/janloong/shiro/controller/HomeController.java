/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-13 下午3:49
 : LastModify: 19-6-13 下午3:49
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.controller;


import com.janloong.common.utils.ResponseResult;
import com.janloong.shiro.annotation.LoginAuth;
import com.janloong.shiro.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-13 15:49
 */
@RestController
@LoginAuth
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/13 15:50
     **/
    @RequestMapping("/home")
    public ResponseResult home(User user) {
        return ResponseResult.success(user);
    }
}
