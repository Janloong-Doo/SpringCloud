/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserAuthService.java
 : Author: janloongdoo@gmail.com
 : Date: 19-4-10 下午2:47
 : LastModify: 19-4-10 下午2:47
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config;


import com.janloong.springsecurity.entity.User;
import com.janloong.springsecurity.entity.UserDetailImpl;
import com.janloong.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-04-10 14:47
 */
@Service
@Slf4j
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(s);
        log.info("查询当前用户名{}, 基础信息为{}", s, user.toString());
        Example<User> of = Example.of(user);
        Optional<User> one = userRepository.findOne(of);
        log.info("next");
        if (one.isEmpty()) {
            log.info("error");
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetailImpl userDetail = new UserDetailImpl(one.get());
        log.info("userdetail:\n" + userDetail.toString());
        return userDetail;
    }
}
