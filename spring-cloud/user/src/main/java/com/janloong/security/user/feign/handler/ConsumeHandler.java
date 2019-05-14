/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ConsumeHandler.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:22
 : LastModify: 18-10-30 上午10:22
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.user.feign.handler;


import com.janloong.security.user.feign.ConsumeFeign;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-30 10:22
 */
@Component
public class ConsumeHandler implements ConsumeFeign {

    @Override
    public String home(String name) {
        return "获取home接口失败";
    }
}
