package com.janloong.config;


import com.janloong.entity.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-08 11:49
 */
//@Configuration
public class UserIDAuditorBean implements AuditorAware<User> {

    private Optional<User> auditor = Optional.empty();

    public void setAuditor(User user) {
        this.auditor = Optional.of(user);
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        return auditor;
    }
}

