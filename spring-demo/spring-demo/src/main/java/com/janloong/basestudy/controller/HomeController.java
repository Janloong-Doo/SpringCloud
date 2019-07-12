/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午5:32
 : LastModify: 19-6-10 下午5:32
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-10 17:32
 */
@RestController
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/10 17:32
     **/
    @RequestMapping("/home")
    public ResponseResult home(Boolean dealAble) {
        System.out.println("业务处理");
        return ResponseResult.success("SUCCESS:" + dealAble);
    }


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/13 17:32
     **/
    @RequestMapping("/home2")
    public String home2() {
        return "succ";
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/7/12 9:18
     **/
    @RequestMapping("/sign")
    public ResponseResult sign(String name, String address) {
        System.out.println(name + "==" + address);
        return ResponseResult.success(name + "=================" + address);
    }
}
