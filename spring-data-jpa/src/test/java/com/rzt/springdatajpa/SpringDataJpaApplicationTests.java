package com.rzt.springdatajpa;

import com.rzt.entity.User;
import org.junit.Test;

import java.util.Optional;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringDataJpaApplicationTests {

    @Test
    public void contextLoads() {
        User user = new User();
        //User user = null;
        Optional<User> of = Optional.of(user);
        boolean present = of.isPresent();
        System.out.println("1: " + present);
        of.ifPresent(user1 -> {
            //StringUtils.isEmpty(user1.getUserId());
            System.out.println("存在" + user);
        });
        System.out.println("2: ");

        User usern = new User();
        usern.setUserId("asd");
        User userNew = of.orElse(usern);
        System.out.println("3: " + userNew);

        System.out.println("1: " + present);

        //User u = null;
        User u = new User();
        u.setName("asdad");
        Object none = Optional.ofNullable(u).map(User::getName).orElse("none");
        System.out.println(none);
    }

}
