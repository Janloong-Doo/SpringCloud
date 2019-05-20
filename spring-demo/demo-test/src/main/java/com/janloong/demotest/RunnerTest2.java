/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RunnerTest.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-16 下午4:21
 : LastModify: 19-5-16 下午4:21
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demotest;


import com.janloong.common.enums.RequestEnum;
import com.janloong.springbootstartercustom.entity.AuthorInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-16 16:21
 */
@Component
public class RunnerTest2 implements ApplicationRunner {
    @Autowired
    private AuthorInfoProvider authorInfo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("runner 开始运行");
        System.out.println(RequestEnum.a01.getDes());
        System.out.println(authorInfo.getAuthrorInfo().getName());
        System.out.println(authorInfo.getAuthrorInfo().getDomain());
        System.out.println(authorInfo.getAuthrorInfo().getDescription());
    }
}
