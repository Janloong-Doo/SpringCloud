package com.janloong.springdatajpa;

import com.janloong.entity.User;
import org.junit.Test;

import java.io.File;
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

    public static void main(String[] args) {
        String url = "upload/bbeceee1-0cb5-4953-84b0-d9da62aec66f/9074b0e2-6131-4ea7-ac2c-161f998811c0/test.zip";
        //String[] split = url.split("/");
        //url.lastIndexOf("/");
        int i = url.lastIndexOf("/");
        String a = url.substring(0, url.lastIndexOf("/")).replace("upload", "prod") + File.separator + "index.html";
        System.out.println(a);
    }
}
