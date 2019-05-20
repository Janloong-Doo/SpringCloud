package com.janloong.controller;

import com.janloong.pipeline.NormalPipeline;
import com.janloong.processor.TaobaoProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;

/**
 * @author Janloong
 * @create 2017-11-16 11:18
 **/
@Controller
public class TaobaoController {

    @RequestMapping("/")
    public String index() {
        return "/html/index";
    }

    @RequestMapping("/spiderUrl")
    public String  spiderAttr(Model model,String url) {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待:"+url+",  一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(new TaobaoProcessor())
                .addUrl(url)
                .addPipeline(new NormalPipeline())
                //.addPipeline(new FilePipeline("D:\\webmagic\\"))
                .thread(5);
        spider.run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + TaobaoProcessor.size + "篇文章，耗时约" + ((endTime - startTime) / 1000) +
                "秒，已保存到数据库，请查收！");
        model.addAttribute("callUrl", url);
        return "html/index";
    }


}
