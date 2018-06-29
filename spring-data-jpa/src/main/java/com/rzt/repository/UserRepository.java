package com.rzt.repository;

import com.rzt.entity.User;
import com.rzt.entity.dto.UserDTO;
import com.rzt.entity.project.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * jpa demo示例
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/6/1 11:07
 **/
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    //@QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_COMMENT, value = "根据用户名字查询")})
    User findByName(String name);

    //project 投影方式
    @Query(value = "select u.name as name ,u.address as address from User u")
    Collection<UserProject> findTest();

    //新实体类方式
    @Query(value = "select new com.rzt.entity.dto.UserDTO(u.name ,u.address ) from User u")
    UserDTO findTest2();


    Optional<User> findByUserId(String userId);

    @Query(value = "select u from #{#entityName} u where u.userId='001'")
    User findTest3();
    Stream<User> findTest4();
}
