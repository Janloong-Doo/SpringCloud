/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RunnerTest.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-16 下午4:21
 : LastModify: 19-5-16 下午4:21
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demotest;


import com.janloong.springbootstartercustom.entity.AuthorInfoProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-16 16:21
 */
@Component
@Slf4j
public class RunnerTest2 implements ApplicationRunner {
    @Autowired
    private AuthorInfoProvider authorInfo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("runner 开始运行");
        log.debug(authorInfo.getAuthrorInfo().getName());
        log.debug(authorInfo.getAuthrorInfo().getDomain());
        log.debug(authorInfo.getAuthrorInfo().getDescription());
    }
}
