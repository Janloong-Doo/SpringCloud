/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-12 上午10:55
 : LastModify: 19-4-12 上午10:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.janloong.springsecurity.common.config.BaseController;
import com.janloong.springsecurity.common.utils.WebApiResponse;
import com.janloong.springsecurity.entity.User;
import com.janloong.springsecurity.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public WebApiResponse getOne(@PathVariable Long id) {
        User one = this.getService().getRepository().getOne(id);
        return WebApiResponse.success(one);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/12 11:58
     **/
    @GetMapping()
    public WebApiResponse getAll() {
        List<User> all = this.getService().getRepository().findAll();
        return WebApiResponse.success(all);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/12 11:38
     **/
    @PostMapping("/add")
    public WebApiResponse add(@RequestParam String username, @RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        User user = new User(username, password, LocalDateTime.now());
        System.out.println(user);
        User save = this.getService().getRepository().save(user);
        return WebApiResponse.success(save);
    }
}
