/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeEntity.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-16 上午9:33
 : LastModify: 18-10-16 上午9:33
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-16 9:33
 */
@Data
@Entity
@Table(name = "home_entity")
public class HomeEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name")
    private String age;

    @Column(name = "address")
    private String address;

}
