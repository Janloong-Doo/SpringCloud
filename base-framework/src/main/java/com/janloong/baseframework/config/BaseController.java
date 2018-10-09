package com.janloong.baseframework.config;


import javax.annotation.Resource;

/**
 * 基础controller类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-20 15:16
 */
public abstract class BaseController<Service> {
    private Service Service;

    public Service getService() {
        if (Service == null) {
            synchronized (this) {
                if (Service == null) {
                    setService(Service);
                }
            }
        }
        return Service;
    }

    @Resource
    public void setService(Service Service) {
        this.Service = Service;
    }
}
