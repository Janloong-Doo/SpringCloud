/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HomeService.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-16 上午9:30
 : LastModify: 18-10-16 上午9:30
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.service;


import com.janloong.baseframework.common.config.BaseService;
import com.janloong.baseframework.repository.HomeRepository;
import org.springframework.stereotype.Service;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-16 9:30
 */
@Service
public class HomeService extends BaseService<HomeRepository> {
}
