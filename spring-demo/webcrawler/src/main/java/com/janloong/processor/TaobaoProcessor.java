package com.janloong.processor;

import com.janloong.pipeline.NormalPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Janloong
 * @create 2017-11-16 11:11
 **/
public class TaobaoProcessor implements PageProcessor {
    public static int size = 0;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        size++;
        String a = page.getHtml().xpath("//*[@id=\"J_NavCommonRow_0\"]/div[1]/h4/span/text()").get();
        //List<String> all = page.getHtml().xpath("//*[@id=\"J_NavCommonRowItems_0\"]/a[1]/span[2]/text()").all();
        //String all = page.getHtml().xpath("//*[@id=\"J_NavCommonRowItems_0\"]/a[1]/span[2]/text()").get();
        String all = page.getHtml().xpath("/html/head/script[39]/text()").get();
        System.out.println("1------------------------"+a);
        System.out.println("1------------------------"+all.toString());
        //page.putField(a,all.toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待:"+",  一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(new TaobaoProcessor())
                .addUrl("https://s.taobao.com/search?q=%E7%AC%94%E8%AE%B0%E6%9C%AC%E7%94%B5%E8%84%91&imgfile=&ie=utf8")
                .addPipeline(new NormalPipeline())
                //.addPipeline(new FilePipeline("D:\\webmagic\\"))
                .thread(5);
        spider.run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + TaobaoProcessor.size + "篇文章，耗时约" + ((endTime - startTime) / 1000) +
                "秒，已保存到数据库，请查收！");
    }
}
