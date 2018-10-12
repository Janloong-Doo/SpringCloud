package com.janloong.security.ssuser.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-25 15:42
 */
@RestController
@RefreshScope
public class HomeController {

    //@Value("${doo}")
    private String test;

    @Value("${dooo.custom}")
    private String ss;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/9/25 15:42
     **/
    @RequestMapping("/home")
    public String home() {
        return "请求成功: " + ss;
    }

    @RequestMapping("/home2")
    public String home2() {
        return "请求成功: " + test;
    }
}
