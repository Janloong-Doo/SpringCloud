/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: User.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-10 下午3:09
 : LastModify: 19-4-10 下午3:09
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-10 15:09
 */
@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "expire")
    private Boolean expire;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;


}
