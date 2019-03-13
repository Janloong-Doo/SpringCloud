/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MongodbController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-7 上午10:12
 : LastModify: 19-3-7 上午10:12
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall.controller;


import com.janloong.base.utils.WebApiResponse;
import com.janloong.demoall.entity.UserEntity;
import com.janloong.demoall.impl.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-07 10:12
 */
@RestController
@RequestMapping("mongodb")
public class MongodbController {

    @Autowired
    private UserDao userDao;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/7 10:12
     **/
    @RequestMapping("/save")
    public WebApiResponse save(UserEntity userEntity) {
        userDao.saveUser(userEntity);
        return WebApiResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/7 10:14
     **/
    @RequestMapping("/query")
    public WebApiResponse query(String name) {
        UserEntity userByUserName = userDao.findUserByUserName(name);
        return WebApiResponse.success(userByUserName);
    }
}
