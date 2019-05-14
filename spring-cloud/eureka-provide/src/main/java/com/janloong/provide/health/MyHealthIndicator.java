/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MyHealthIndicator.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:42
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.provide.health;

import com.janloong.provide.controller.HomeController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {


    /**
     * 设置实例的服务状态
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/10 11:37
     **/
    @Override
    public Health health() {
        if (HomeController.dbStatus) {
            return new Health.Builder(Status.UP).build();
        } else {
            return new Health.Builder(Status.DOWN).build();
        }
    }

}
