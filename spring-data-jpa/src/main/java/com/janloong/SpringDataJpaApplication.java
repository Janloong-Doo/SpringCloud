package com.janloong;

import com.janloong.config.UserIDAuditorBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@Import(RztRepository.class)
//@Import(RedisConnectionFactory.class)
public class SpringDataJpaApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    UserIDAuditorBean auditorAware() {
        return new UserIDAuditorBean();
    }

    //@Autowired
    //RedisConnectionFactory redisConnectionFactory;
}
