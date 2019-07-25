/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: IndexController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-20 上午11:59
 : LastModify: 19-6-20 上午11:59
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-20 11:59
 */
@Controller
public class IndexController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/20 11:59
     **/
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/25 14:48
     **/
    @RequestMapping("/sign/{status}")
    @ResponseBody
    public ResponseResult login(@PathVariable String status) {
        System.out.println("status:" + status);
        return ResponseResult.success(status);
    }
}
