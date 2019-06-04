/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: SessionController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-4 下午5:27
 : LastModify: 19-6-4 下午5:27
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.sessionone.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-06-04 17:27
 */
@RestController
public class SessionController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/6/4 17:27
     **/
    @RequestMapping("/set")
    public ResponseResult set(HttpSession httpSession, @RequestParam(defaultValue = "ses") String key, @RequestParam(defaultValue = "doo") String name) {
        httpSession.setAttribute(key, name);
        return ResponseResult.success("设置成功: " + key + "-" + name);
    }
}
