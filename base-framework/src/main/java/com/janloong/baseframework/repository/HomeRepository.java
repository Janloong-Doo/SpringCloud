/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeRepository.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-16 上午9:31
 : LastModify: 18-10-16 上午9:31
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.repository;


import com.janloong.baseframework.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-16 9:31
 */
@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, String> {

}
