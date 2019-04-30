/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: DemoServiceImpl.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-23 下午3:39
 : LastModify: 19-4-23 下午3:39
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.dubboserver.service.impl;


import com.janloong.dubboserver.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-23 15:39
 */
@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    
    @Override
    public String hello(String name) {
        return "Hello, " + name;
    }
}
