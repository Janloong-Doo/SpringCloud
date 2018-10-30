/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MyHealthCheckHandler.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:42
 : LastModify: 18-10-12 下午6:07
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.provide.health;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private MyHealthIndicator halthIndicator;

    /**
     * 通知server对应该实例服务的状态
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/4/10 11:36
     **/
    @Override
    public InstanceStatus getStatus(InstanceStatus currentStatus) {
        Status status = halthIndicator.health().getStatus();
        if (status.equals(Status.UP)) {
            return InstanceStatus.UP;
        } else {
            return InstanceStatus.DOWN;
        }
    }

}
