package com.janloong.consumer.feign;


import com.janloong.common.utils.ResponseResult;
import com.janloong.consumer.feign.handler.ProviderHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 20:13
 **/
@FeignClient(value = "doo-provider", fallback = ProviderHandler.class)
public interface ProviderFeign {

    @RequestMapping("provider/hello")
    ResponseResult hello(@RequestParam("name") String name);

}


