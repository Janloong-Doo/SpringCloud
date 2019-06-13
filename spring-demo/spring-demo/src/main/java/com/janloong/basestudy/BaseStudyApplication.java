/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BaseStudyApplication.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午4:03
 : LastModify: 19-6-10 下午4:03
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy;


import com.janloong.common.JanloongApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-10 16:03
 */
@SpringBootApplication
@ComponentScan(value = "com.janloong")
public class BaseStudyApplication {
    public static void main(String[] args) {
        JanloongApplication.start(args, BaseStudyApplication.class);
    }
}
