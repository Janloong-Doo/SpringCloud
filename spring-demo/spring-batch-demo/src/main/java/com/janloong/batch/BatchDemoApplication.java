/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BatchDemoApplication.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-5 上午10:14
 : LastModify: 19-7-5 上午10:14
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.batch;


import com.janloong.common.JanloongApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-05 10:14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BatchDemoApplication {
    public static void main(String[] args) {
        JanloongApplication.start(args, BatchDemoApplication.class);
    }
}
