package com.janloong.demotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Driver;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTestApplicationTests {

    /**
     * Java SPI机制
     * <p>
     * 基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2019/5/8 15:29
     **/
    @Test
    public void contextLoads() {
        String PREFIX = "/META-INF/services/";
        Class cls = Driver.class;
        String infName = cls.getCanonicalName();
        System.out.println(infName);
        String fileName = cls.getResource(PREFIX + infName).getPath();
        System.out.println(fileName);
    }

}
