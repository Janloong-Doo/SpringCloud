/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserDao.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午2:23
 : LastModify: 19-3-7 上午10:09
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springmongodbdemo.impl;


import com.janloong.springmongodbdemo.entity.UserEntity;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao  {

    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public int updateUser(UserEntity user);

    public void deleteUserById(Long id);

}
