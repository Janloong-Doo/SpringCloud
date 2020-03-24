/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: DooBody.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/5 下午3:06
 * LastModify: 2020/1/5 下午3:06
 */

package com.janloong.consumer.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.consumer.controller
 * @description:
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @dep R&D SP Co.,Ltd
 * @create: 2020-01-05 15:06
 **/
@Data
public class DooBody implements Serializable {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
