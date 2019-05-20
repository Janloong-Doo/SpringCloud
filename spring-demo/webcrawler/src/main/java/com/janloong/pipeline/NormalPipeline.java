package com.janloong.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @author Janloong
 * @create 2017-11-16 14:36
 **/
public class NormalPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();
        map.forEach((k, v) -> {
            System.out.println("key:"+k);
            System.out.println("value:"+v.toString());
        });
    }
}
