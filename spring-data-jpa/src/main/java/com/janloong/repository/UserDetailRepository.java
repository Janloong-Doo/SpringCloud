package com.janloong.repository;


import com.janloong.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-11 10:59
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
