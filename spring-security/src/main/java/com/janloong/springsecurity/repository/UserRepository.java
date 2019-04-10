/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserRepository.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-10 下午3:30
 : LastModify: 19-4-10 下午3:30
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.repository;


import com.janloong.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-10 15:30
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
