package com.rzt.service;

import com.rzt.entity.PostInfo;
import com.rzt.entity.Tag;
import com.rzt.entity.User;
import com.rzt.entity.UserDetail;
import com.rzt.repository.PostInfoRepository;
import com.rzt.repository.TagRepository;
import com.rzt.repository.UserDetailRepository;
import com.rzt.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserSerivceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSerivce userSerivce;
    @Autowired
    PostInfoRepository postInfoRepository;
    @Autowired
    UserDetailRepository userDetailRepository;
    @Autowired
    TagRepository tagRepository;

    @Test
    public void getInfo() {
        List<User> all = userRepository.findAll();
        System.out.println("======1" + all);
        System.out.println("======1" + all.get(0).getPostInfos());
        System.out.println("======1" + all.get(0).getUserDetail());

        List<PostInfo> a = postInfoRepository.findAll();
        System.out.println("======2" + a);
        System.out.println("======2" + a.get(0).getUser());
        System.out.println("======2" + a.get(0).getTags());
        System.out.println("======2" + a.get(1).getTags());

        List<Tag> aa = tagRepository.findAll();
        System.out.println("======3" + aa);
        System.out.println("======3" + aa.get(0).getPostInfos());
        System.out.println("======3" + aa.get(1).getPostInfos());

        List<UserDetail> all1 = userDetailRepository.findAll();
        System.out.println("======4" + all1);
        System.out.println("======4" + all1.get(0).getUser());
    }


    @Test
    public void addUser() {
        User user = new User();
        user.setUserId("005");
        user.setName("doo");
        user.setAddress("df");
        user.setEmail("807110586@qq.com");
        //UserDetail userDetail = new UserDetail();
        //userDetail.setLover("doo");
        //userDetail.setDetailId(1L);
        //user.setUserDetail(userDetail);
        //User user2 = new User();
        //user2.setUserId("2");
        //user2.setName("rzt");
        //List<User> users = new LinkedList<>();
        //users.add(user);
        //users.add(user2);
        //PostInfo postInfo = new PostInfo();
        //postInfo.setPostId("6");
        //postInfo.setTitle("6");
        //HashSet<PostInfo> objects = new HashSet<>();
        //objects.add(postInfo);

        //user.setPostInfos(objects);
        userRepository.save(user);
        //userRepository.saveAndFlush(user);
        //userDetailRepository.save(userDetail);

    }

    @Test
    public void addPost() {

        PostInfo postInfo = new PostInfo();
        postInfo.setPostId("4");
        postInfo.setContent("doo");
        postInfo.setTitle("rzt");
        User user = new User();
        user.setUserId("002");
        postInfo.setUser(user);
        PostInfo postInfo2 = new PostInfo();
        postInfo2.setPostId("5");
        postInfo2.setContent("dooddd");
        postInfo2.setTitle("janloongdd");
        postInfo2.setUser(user);
        List<PostInfo> list = new ArrayList<>();
        list.add(postInfo);
        list.add(postInfo2);
        postInfoRepository.saveAll(list);
    }

    @Test
    public void test3() {
        UserDetail userDetail = new UserDetail();
        userDetail.setAddress("bj");
        userDetail.setLover("doo");
        User user = new User();
        user.setUserId("001");
        userDetail.setUser(user);
        //userRepository.save(user);
        //userDetailRepository.save(userDetail);
        List<UserDetail> all = userDetailRepository.findAll();
        System.out.println("=============");
        System.out.println(all);

    }

    @Test
    public void test4() {
        //User byName = userRepository.findByName("dd");
        //Collection<UserProject> byName = userRepository.findTest();
        //byName.forEach(userProject -> {
        //    System.out.println("=============");
        //    System.out.println(userProject.getFullInfo());
        //    System.out.println(userProject.getAddress());
        //    System.out.println(userProject.getName());
        //    System.out.println("=============");
        //});
        //方法测试
        //UserDTO byName = userRepository.findTest2();
        //System.out.println("=============");
        //System.out.println(byName);
        //System.out.println("=============");

        //方法测试
        Specification<User> name = Specification.where((root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("name"),
                "%" + "d" + "%"));
        List<User> all = userRepository.findAll(name);
        System.out.println("=============");
        System.out.println(all);
        System.out.println("=============");
    }

    @Test
    public void validateParams() {
        //参数校验测试
        User user = new User();
        userSerivce.validateInsert(user);

        //userRepository.saveAll();
    }

    @Test
    @Transactional
    public void update() {
        //User user = new User();
        //user.setUserId("005");
        //Example<User> of = Example.of(user);
        //Optional<User> one = userRepository.findOne(of);
        //User user1 = one.get();
        User user1 = userRepository.getOne("005");
        user1.setName("hello JanLoongDoo");
        User save = userRepository.save(user1);
        System.out.println("=======");
        System.out.println(save);
    }

    @Test
    @Transactional
    public void optionalTest() {
        Optional<User> user = userRepository.findByUserId("001");
        System.out.println("1: " + user.isPresent());
        user.ifPresent(user1 -> {
            //StringUtils.isEmpty(user1.getUserId());
            System.out.println("存在" + user);
        });

        System.out.println("2: ");

        User usern = new User();
        usern.setUserId("asd");
        User userNew = user.orElse(usern);
        System.out.println("3: " + userNew);

        System.out.println("4: ");
        //user.ofNullable(user).map()

    }

    @Test
    public void testasd() {
        User test3 = userRepository.findTest3();
        Stream<User> test4 = userRepository.findTest4();
        //User user = test3.get();
        System.out.println("==============");
        System.out.println(test3.getName());
        System.out.println(test3.getUserId());
    }
}