/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ConsumeFeign.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-29 上午10:50
 : LastModify: 18-10-29 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.user.feign;


import com.janloong.security.user.feign.handler.ConsumeHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-29 10:50
 */
@FeignClient(value = "provide-1", fallback = ConsumeHandler.class)
public interface ConsumeFeign {

    @RequestMapping(value = "/home")
    String home(@RequestParam("name") String name);


}
