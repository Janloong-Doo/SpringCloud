package com.rzt.client3.controller.feign;


import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-13 10:31
 */
@Component
public class FeignFallBackHandler implements FeignInterface {

    @Override
    public String feign(String name) {
        return name + "- 服务出错";
    }
}
