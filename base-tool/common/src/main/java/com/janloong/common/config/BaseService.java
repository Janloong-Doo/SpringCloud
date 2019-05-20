/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BaseService.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-10-16 上午9:26
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.config;


import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-20 15:24
 */
public abstract class BaseService<Repo> {

    private Repo repository;

    public Repo getRepository() {
        if (repository == null) {
            synchronized (this) {
                if (repository == null) {
                    setRepository(repository);
                }
            }
        }
        return repository;
    }

    @Autowired
    public void setRepository(Repo repository) {
        this.repository = repository;
    }
}
