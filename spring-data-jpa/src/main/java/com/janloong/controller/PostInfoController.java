package com.janloong.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-13 16:46
 */
@RestController
@RequestMapping(value = "postInfo")
public class PostInfoController {
//public class PostInfoController extends CurdController<PostInfo, PostInfoService> {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/6/13 17:16
     **/
    @RequestMapping("/list")
    //@ApiOperation(value = "ceshi", notes = "ceshi")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "name", value = "mingzi", required = true, dataType = "String")
    //})
    public String list(String name) {
        //RedisUtil redisUtil = new RedisUtil();
        //redisUtil.addLink("aa", "aaaa");
        return name;
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/8/2 17:02
     **/
    @RequestMapping("/list2")
    public Object list2(String name) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("5", "1");
        map.put("66", "1");
        map.put("123", "1");
        map.put("12123", "1");
        map.put("23", "1");
        List<Object> objects = new ArrayList<>();
        //objects.add("1");
        //objects.add("1");
        //objects.add("1");
        //objects.add("1");
        //objects.add("1");
        //objects.add("1");
        objects.add(map);
        return objects;
    }
}
