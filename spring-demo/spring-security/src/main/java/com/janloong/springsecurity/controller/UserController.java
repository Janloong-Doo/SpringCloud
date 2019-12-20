/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-12 上午10:55
 : LastModify: 19-4-12 上午10:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.janloong.common.config.BaseController;
import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.entity.User;
import com.janloong.springsecurity.entity.UserDetailImpl;
import com.janloong.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-12 10:55
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService> {
    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/8/21 14:44
     **/
    @GetMapping("auth")
    public ResponseResult info(Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken userInfo = (UsernamePasswordAuthenticationToken) authentication;
            UserDetailImpl principal = (UserDetailImpl) userInfo.getPrincipal();
            //User user =  principal.getUser();
            //User user1 = new User();
            UserDetailImpl user1 = new UserDetailImpl();
            BeanUtils.copyProperties(principal,user1,"password");
            return ResponseResult.success(user1);
        }
        log.info("获取用户认证信息");
        return ResponseResult.success(authentication.getName());
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/12 11:36
     **/
    @RequestMapping("/{id}")
    public ResponseResult getOne(@PathVariable Long id) {
        User one = this.getService().getRepository().getOne(id);
        return ResponseResult.success(one);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/12 11:58
     **/
    @GetMapping()
    public ResponseResult getAll() {
        List<User> all = this.getService().getRepository().findAll();
        return ResponseResult.success(all);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/12 11:38
     **/
    @PostMapping("/add")
    public ResponseResult add(@RequestParam String username, @RequestParam String password, String roleId) {
        System.out.println(username);
        System.out.println(password);
        User user = new User();
        user.setUsername(username);
        String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
        user.setPassword(encode);
        user.setRoleId(roleId);
        System.out.println(user);
        User save = this.getService().getRepository().save(user);
        return ResponseResult.success(save);
    }
}
