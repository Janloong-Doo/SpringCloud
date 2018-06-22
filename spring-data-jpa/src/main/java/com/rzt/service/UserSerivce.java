package com.rzt.service;


import com.rzt.config.Group;
import com.rzt.entity.PostInfo;
import com.rzt.entity.User;
import com.rzt.repository.PostInfoRepository;
import com.rzt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-01 11:54
 */
@Service
public class UserSerivce {
//public class UserSerivce extends CurdService<User, UserRepository> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostInfoRepository postInfoRepository;


    public Object getInfo(Integer type) {
        if (type == 1) {
            //List<User> all = userRepository.findAll();
            User user1 = userRepository.getOne("005");
            user1.setName("hello janloongdoo");
            User save = userRepository.save(user1);
            System.out.println("=======");
            System.out.println(save);

            return save;
        } else {
            List<PostInfo> all = postInfoRepository.findAll();
            return all;
        }
    }


    public void validateInsert(@Validated(value = {Group.update.class}) User user) {

        System.out.println("========");
        System.out.println(user);
        System.out.println("========");
    }

    public void validateUpdate(@Validated(value = {Group.update.class, Group.Default.class}) User user) {
        System.out.println("========");
        System.out.println(user);
        System.out.println("========");


    }
}
