package com.janloong.singlespringsecurity.service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-17 21:33
 */
@Service
public class DooUserDeatilService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority user = new SimpleGrantedAuthority("USER");
        authorities.add(user);

        return new User("janloong", "doo+", authorities);
    }
}
