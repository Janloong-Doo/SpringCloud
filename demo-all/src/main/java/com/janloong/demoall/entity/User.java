/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: User.java
 : Author: janloongdoo@gmail.com
 : Date: 19-1-9 下午3:40
 : LastModify: 19-1-9 下午3:40
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall.entity;


import lombok.Data;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-01-09 15:40
 */
@Data
public class User {

    private String name;

    private String address;
    
    private Integer age;

}
