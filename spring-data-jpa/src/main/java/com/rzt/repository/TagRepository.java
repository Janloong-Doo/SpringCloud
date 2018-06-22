package com.rzt.repository;


import com.rzt.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/6/11 16:57
 **/
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
