package com.rzt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
