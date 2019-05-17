/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-16 上午9:29
 : LastModify: 18-10-16 上午9:29
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.controller;


import com.janloong.baseframework.common.annotation.CustomDoo;
import com.janloong.baseframework.common.config.BaseController;
import com.janloong.baseframework.common.utils.WebApiResponse;
import com.janloong.baseframework.service.HomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-16 9:29
 */
@RestController
public class HomeController extends BaseController<HomeService> {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/10/16 9:36
     **/
    @RequestMapping("/home")
    @CustomDoo(value = "janloong", doo = "doo")
    public WebApiResponse home(String name) {
        return WebApiResponse.success("Hello , " + name);
    }
}
