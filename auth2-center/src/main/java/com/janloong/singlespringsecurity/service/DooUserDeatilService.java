package com.janloong.singlespringsecurity.service;


import com.janloong.singlespringsecurity.entity.User;
import com.janloong.singlespringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-17 21:33
 */
@Service
public class DooUserDeatilService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findOneByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //User user = new User();
        //user.setId(1001);
        //user.setUsername("admin");
        //user.setPassword("admin+");
        //user.setName("janloongdoo");
        //user.setCreateAt(new Date());

        return user;
    }
}
