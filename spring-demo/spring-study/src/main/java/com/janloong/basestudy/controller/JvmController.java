/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: JvmController.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/9/4 上午9:59
 : LastModify: 2019/9/4 上午9:51
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.controller;


import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-09-04 9:51
 */
@RestController
@RequestMapping("jvm")
@Slf4j
public class JvmController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/9/4 9:51
     **/
    @RequestMapping("/allocate")
    public ResponseResult allocate(String name) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900 * 1024];
        //allocation2 = new byte[900*1024];
        HashMap<Object, Object> map = new HashMap<>();
        map.put("中文", "可以吗");
        map.put("中文2", "可以吗");
        map.put("中文3", "可以吗");
        map.put("中文4", "可以吗");
        return ResponseResult.success(map);
    }

}
