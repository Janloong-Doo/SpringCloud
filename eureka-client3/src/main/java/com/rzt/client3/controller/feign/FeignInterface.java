package com.janloong.client3.controller.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provide-1",fallback = FeignFallBackHandler.class)
public interface FeignInterface {

    @RequestMapping("/home")
    String feign(@RequestParam("name") String name);
}
