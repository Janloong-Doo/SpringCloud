package com.rzt.client3.controller.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "provide-2", fallback = HomeFallBackHandler.class)
public interface HomeInterface {

    @RequestMapping("home")
    String home();
}
