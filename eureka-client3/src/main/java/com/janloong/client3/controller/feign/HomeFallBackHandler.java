package com.janloong.client3.controller.feign;


import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-19 15:29
 */
@Component
public class HomeFallBackHandler implements HomeInterface {
    @Override
    public String home() {
        return "feign方式 服务出错";
    }
}
