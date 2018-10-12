package com.janloong.controller;


import com.janloong.config.Group;
import com.janloong.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-01 12:10
 */
@RestController
@RequestMapping("assd")
public class UserController2 {
//public class UserController2 extends CurdController<User, UserSerivce> {

    //@Autowired
    //UserSerivce userSerivce;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/6/1 12:11
     **/
    @RequestMapping(value = "/home")

    public String home(@Min(value = 1, message = "长度应大于0")
                       @NotBlank(message = "名字不能为空") @RequestParam String name) {
        return name;
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/6/6 14:16
     **/
    //@ApiOperation(value = "测试", notes = "测试详情")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Integer")
    //})
    //@RequestMapping("/info")
    public Object info(Integer type) {
        //return userSerivce.getInfo(type);
        //return this.service.getInfo(type);
        return null;
    }


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/6/12 11:09
     **/
    //@RequestMapping("/validateInsert")
    public User validateInsert(@Validated(value = {Group.Default.class}) User user) {
        return user;
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/6/12 11:09
     **/
    //@RequestMapping("/validateUpdate")
    public User validateUpdate(@Validated(value = {Group.update.class, Group.Default.class}) User user) {

        return user;
    }
}
