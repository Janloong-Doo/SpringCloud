/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MongodbController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午2:23
 : LastModify: 19-3-12 下午3:11
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springmongodbdemo.controller;


import com.janloong.springmongodbdemo.entity.UserEntity;
import com.janloong.springmongodbdemo.impl.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.janloong.common.utils.ResponseResult;
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
    public ResultResponse save(UserEntity userEntity) {
        userDao.saveUser(userEntity);
        return ResultResponse.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/7 10:14
     **/
    @RequestMapping("/query")
    public ResultResponse query(String name) {
        UserEntity userByUserName = userDao.findUserByUserName(name);
        return ResultResponse.success(userByUserName);
    }
}
