/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserDao.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-7 上午10:06
 : LastModify: 19-2-28 下午4:22
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall.impl;


import com.janloong.demoall.entity.UserEntity;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao  {

    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public int updateUser(UserEntity user);

    public void deleteUserById(Long id);

}
