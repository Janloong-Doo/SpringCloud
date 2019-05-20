/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CustomerRepository.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午2:12
 : LastModify: 19-3-25 下午3:27
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springredisdemo.repository;


import com.janloong.springredisdemo.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-25 15:15
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByExternalId(String externalId);

    List findByAccountsId(Long id);
}
