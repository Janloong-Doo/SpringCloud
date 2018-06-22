package com.rzt.entity.project;


import org.springframework.beans.factory.annotation.Value;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-12 9:58
 */
public interface UserProject {

    @Value("#{target.name + '-' + target.address}")
    String getFullInfo();

    String getName();

    String getAddress();
}
