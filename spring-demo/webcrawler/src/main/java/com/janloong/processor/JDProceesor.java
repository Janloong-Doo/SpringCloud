package com.janloong.processor;

import com.janloong.pipeline.NormalPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author Janloong
 * @create 2017-11-16 15:41
 **/
public class JDProceesor implements PageProcessor {

    public static int size = 0;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        size++;
        Html html = page.getHtml();
        String s = html.toString();
        //String all = page.getHtml().xpath("//*[@id=\"J_selector\"]/div[1]/div/div[1]/strong/text()").get();
        String all = page.getHtml().xpath("//*[@id=\"苹果Apple\"]/a/div/span[1]/text()")
                .get();

        System.out.println("--------------------"+s);
        System.out.println("--------------------"+all);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待:"+",  一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(new JDProceesor())
                .addUrl("https://search.suning.com/a/")
                .addPipeline(new NormalPipeline())
                //.addPipeline(new FilePipeline("D:\\webmagic\\"))
                .thread(5);
        spider.run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + JDProceesor.size + "篇文章，耗时约" + ((endTime - startTime) / 1000) +
                "秒，已保存到数据库，请查收！");
    }
}
