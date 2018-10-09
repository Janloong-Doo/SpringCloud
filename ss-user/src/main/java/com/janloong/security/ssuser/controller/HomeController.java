package com.janloong.security.ssuser.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-25 15:42
 */
@RestController
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/9/25 15:42
     **/
    @RequestMapping("/home")
    public String home() {
        return "请求成功";
    }

}
