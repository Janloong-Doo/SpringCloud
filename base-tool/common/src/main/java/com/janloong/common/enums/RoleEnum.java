/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RoleEnum.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-10-16 上午9:28
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.enums;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2018/7/12 10:29
 **/
public enum RoleEnum {
    超级管理员("SUPER_ADMIN"),
    普通管理员("ADMIN");

    RoleEnum(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
