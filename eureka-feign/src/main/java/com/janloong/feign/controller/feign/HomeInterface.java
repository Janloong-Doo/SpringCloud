/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeInterface.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:43
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.feign.controller.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "provide-2", fallback = HomeFallBackHandler.class)
public interface HomeInterface {

    @RequestMapping("home")
    String home();
}
