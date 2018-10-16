package com.janloong.baseframework.common.config;


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
