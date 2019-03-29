package com.janloong.auth2center.repository;


import com.janloong.auth2center.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xw on 2017/3/17.
 * 2017-03-17 19:59
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findOneByUsername(String userName);
}
