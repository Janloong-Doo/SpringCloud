package com.janloong.processor;

import com.janloong.pipeline.NormalPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author Janloong
 * @create 2017-11-16 15:16
 **/
public class ZGCProcessor implements PageProcessor {

    public static int size = 0;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        //品牌
        String pinpai = page.getHtml().xpath("//*[@id=\"J_ManuFilter\"]/strong/text()").get();
        List<String> pinpailist = page.getHtml().xpath("//*[@id=\"J_ParamBrand\"]/a/text()").all();
        //尺寸
        String chicun = page.getHtml().xpath("//*[@id=\"pamItem2\"]/strong/text()").get();
        List<String> cclist = page.getHtml().xpath("//*[@id=\"J_ParamItem2\"]/a/text()").all();
    //

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待:"+",  一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(new ZGCProcessor())
                .addUrl("http://nb.zol.com.cn/")
                .addPipeline(new NormalPipeline())
                //.addPipeline(new FilePipeline("D:\\webmagic\\"))
                .thread(5);
        spider.run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + JDProceesor.size + "篇文章，耗时约" + ((endTime - startTime) / 1000) +
                "秒，已保存到数据库，请查收！");
    }
}
