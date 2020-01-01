/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: UserDetailImpl.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-20 上午11:32
 : LastModify: 19-6-20 上午11:32
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.entity;


import lombok.Data;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-20 11:32
 */
@Data
public class UserDetailImpl extends User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    //private User user;

    //public UserDetailImpl(User user) {
    //    this.user = user;
    //}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auths;
    }


    @Override
    public boolean isAccountNonExpired() {
        //return !user.getExpire();
        return !super.getExpire();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !super.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
