/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: User.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-13 下午3:51
 : LastModify: 19-6-13 下午3:51
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.entity;


import lombok.Data;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-13 15:51
 */
@Data
public class User {

    private String userId;
    private String username;
    private String password;
    private String role;
    private String permission;

}
