/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-12 上午10:55
 : LastModify: 19-4-12 上午10:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.janloong.common.config.BaseController;
import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.entity.User;
import com.janloong.springsecurity.service.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-12 10:55
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService> {

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
