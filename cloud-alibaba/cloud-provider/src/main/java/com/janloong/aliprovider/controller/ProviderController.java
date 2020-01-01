/*
 * Copyright (c) 2019  All Rights Reserved.
 * ProjectName: cloud
 * FileName: ProviderController.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2019/12/31 下午8:14
 * LastModify: 2019/12/31 下午8:14
 */

package com.janloong.aliprovider.controller;

import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 20:14
 **/
@RestController
@RequestMapping("provider")
public class ProviderController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/12/31 20:15
     **/
    @RequestMapping("/hello")
    public ResponseResult hello(String name) {
        return ResponseResult.success("hello , " + name);
    }
}
