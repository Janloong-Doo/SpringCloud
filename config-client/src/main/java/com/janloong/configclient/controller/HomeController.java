package com.janloong.configclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-13 15:37
 */
@RestController
@RefreshScope
public class HomeController {

    /**
     * 直接读取配置中心
     */
    @Value("${doo}")
    private String test;
    /**
     * 通过配置文件转换读取
     */
    @Value("${from.config-server}")
    private String cus;
    /**
     * 公共配置文件
     */
    @Value("${eurekaUrl}")
    private String eurekaUrl;


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/13 15:38
     **/
    @RequestMapping("/test")
    public String test(String name) {
        return test + "=" + cus + "=" + eurekaUrl;
    }
}
