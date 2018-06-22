package com.rzt.repository;


import com.rzt.entity.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-01 14:27
 */
@Repository
public interface PostInfoRepository extends JpaRepository<PostInfo, String> {
}
