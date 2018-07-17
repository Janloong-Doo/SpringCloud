package com.janloong.singlespringsecurity.controller;


import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-17 21:28
 */
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/7/17 21:30
     **/
    @RequestMapping("/home")
    public String home(String name) {

        return "授权后: " + name;
    }

    @RequestMapping("/authdoo")
    public String auth(String name) {

        return "授权前: " + name;
    }
}
