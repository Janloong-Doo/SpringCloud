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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-29 17:15
 */
//@RestController
@Controller
public class LoginController {


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/4/8 13:38
     **/
    @RequestMapping("/doo")
    public String doo(String msg) {
        return "/doo";
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/3/29 17:36
     **/
    @RequestMapping("/dooLogin")
    @ResponseBody
    public WebApiResponse dooLogin(String username, String password) {
        Map<String, String> map = Map.of(username, password);
        return WebApiResponse.success(map);
    }

}
