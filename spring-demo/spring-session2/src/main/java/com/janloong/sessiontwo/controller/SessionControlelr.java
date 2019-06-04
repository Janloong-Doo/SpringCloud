/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: SessionControlelr.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-4 下午5:30
 : LastModify: 19-6-4 下午5:30
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.sessiontwo.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-04 17:30
 */
@RestController
public class SessionControlelr {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/4 17:30
     **/
    @RequestMapping("/get")
    public ResponseResult get(HttpSession httpSession, @RequestParam(defaultValue = "ses") String key) {
        Object ses = httpSession.getAttribute(key);
        return ResponseResult.success(ses);
    }
}
