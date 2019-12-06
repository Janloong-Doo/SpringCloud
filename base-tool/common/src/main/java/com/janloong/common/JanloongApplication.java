/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: JanloongApplication.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-13 上午11:58
 : LastModify: 19-6-13 上午11:58
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common;


import com.janloong.common.base.JanloongBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.time.Duration;
import java.time.Instant;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-13 11:58
 */
public class JanloongApplication {

    public static void start(String[] args, Class<?>... primarySources) {
        Instant now = Instant.now();
        SpringApplication springApplication = new SpringApplication(primarySources);
        springApplication.setBanner(new JanloongBanner());
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
        System.out.println(AnsiOutput.toString(AnsiColor.RED, ":: Application started by Janloong_Doo  共" + Duration.between(now, Instant.now()).getSeconds() + "秒 :: "));
    }
}
