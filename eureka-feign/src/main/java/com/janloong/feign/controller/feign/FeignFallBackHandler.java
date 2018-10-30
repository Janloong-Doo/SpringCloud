/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: FeignFallBackHandler.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:43
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.feign.controller.feign;


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
