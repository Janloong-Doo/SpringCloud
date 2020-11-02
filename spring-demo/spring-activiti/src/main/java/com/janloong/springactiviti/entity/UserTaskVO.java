/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ParamVO.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/11/2 下午4:29
 * LastModify: 2020/11/2 下午4:29
 */

package com.janloong.springactiviti.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-11-02 16:29
 **/
@Getter
@Setter
@ToString
public class UserTaskVO {

    private String username;

    private String name;

    private String description;

    private String candidateGroup;

    private Integer Priority = 10;

    private Map<String, Object> variable;

}

