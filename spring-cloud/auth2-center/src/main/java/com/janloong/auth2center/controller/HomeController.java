package com.janloong.auth2center.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-17 21:28
 */
@RestController
public class HomeController {


    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/7/17 21:30
     **/
    @RequestMapping("/after")
    public String home(String name) {

        return "授权后: " + name;
    }

    @RequestMapping("/before")
    public String auth(String name) {

        return "授权前: " + name;
    }

    @RequestMapping("/before2")
    public String auth2(String name) {

        return "授权前2: " + name;
    }

}
