/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HeadController.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-22 下午2:12
 : LastModify: 19-5-22 下午2:12
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demotest.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-22 14:12
 */
@RestController
public class HeadController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/5/22 14:14
     **/
    @GetMapping(value = "/head")
    public ResponseResult head(@RequestParam String name) {

        return ResponseResult.success(null);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/5/31 22:44
     **/
    @PostMapping("/excel")
    public ResponseResult excel(MultipartHttpServletRequest request, String dateTime) {
        MultipartFile file = request.getFile("file");
        String filename = file.getResource().getFilename();
        long size = file.getSize();
        String name = file.getName();
        System.out.println(dateTime);
        System.out.println(name);
        System.out.println(filename);
        System.out.println(size);
        return ResponseResult.success(null);
    }
}
