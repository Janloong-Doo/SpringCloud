/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LoginController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:15
 : LastModify: 19-3-29 下午5:15
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.controller;


import com.janloong.springsecurity.common.utils.WebApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 17:15
 */
@RestController
public class LoginController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/29 17:36
     **/
    @RequestMapping("/dooLogin")
    public WebApiResponse dooLogin(@RequestParam String name) {

        return WebApiResponse.success(null);
    }

}
