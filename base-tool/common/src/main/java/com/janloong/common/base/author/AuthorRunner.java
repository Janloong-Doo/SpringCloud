/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorRunner.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午3:51
 : LastModify: 19-6-10 下午3:51
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.base.author;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2019-06-10 15:51
 */
@Component
@Slf4j
public class AuthorRunner implements ApplicationRunner {

    @Autowired
    private AuthorInfoProvider authorInfo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AuthorInfo author = authorInfo.getAuthrorInfo();
        log.info("\n:: {} ::\n:: {} ::\n:: {} ::", author.getName(), author.getDomain(), author.getDescription());
    }
}
