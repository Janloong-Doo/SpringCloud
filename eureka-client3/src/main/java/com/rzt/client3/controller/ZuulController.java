package com.rzt.client3.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-13 12:53
 */
@RestController
//@RequestMapping("/rout-2")
public class ZuulController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/13 12:56
     **/
    @RequestMapping("/zuul")
    public String zuul(@RequestParam(defaultValue = "rout-2 zuul") String name) {

        return name;
    }


    //@Value("${test}")
    String test;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/13 14:55
     **/
    @RequestMapping("/config")
    public String config(String name) {

        return test;
    }

}
