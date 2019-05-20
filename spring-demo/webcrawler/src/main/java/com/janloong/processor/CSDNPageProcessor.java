package com.janloong.processor;

import com.janloong.domain.CsdnBlog;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;
import java.util.List;

/**
 * @author Janloong
 * @create 2017-11-15 11:57
 **/
public  class CSDNPageProcessor implements PageProcessor {
    private static String username = "du807110586";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        // 列表页
        if (!page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/\\d+").match()) {
            // 添加所有文章页
            page.addTargetRequests(page.getHtml().xpath("//div[@id='article_list']").links()// 限定文章列表获取区域
                    .regex("/" + username + "/article/details/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                    .all());
            // 添加其他列表页
            page.addTargetRequests(page.getHtml().xpath("//div[@id='papelist']").links()// 限定其他列表页获取区域
                    .regex("/" + username + "/article/list/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                    .all());
            // 文章页
        } else {
            size++;// 文章数量加1
            // 用CsdnBlog类来存抓取到的数据，方便存入数据库
            CsdnBlog csdnBlog = new CsdnBlog();
            // 设置编号
            int i = Integer.parseInt(page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/(\\d+)").get());
            csdnBlog.setId(i);
            // 设置标题
            String s = page.getHtml().xpath("//h1[@class='csdn_top']/text()").get();
            csdnBlog.setTitle(s);
            // 设置日期
            String date = page.getHtml().xpath("//div[@class='artical_tag']/span[@class='time']/text()").get();
            csdnBlog.setDate(date);
            // 设置标签（可以有多个，用,来分割）
            String tags = listToString(page.getHtml().xpath("//ul[@class='article_tags clearfix " +
                    "csdn-tracking-statistics']/li/a[@target='_blank']/text()").all());
            csdnBlog.setTags(tags);

            // 设置阅读人数
            String s1 = page.getHtml().xpath("//ul[@class='right_bar']/li/button[@class='btn-noborder']/span[@class" +
                    "='txt'" +
                    "]/text()").get();
            int view = Integer.parseInt(s1);
            csdnBlog.setView(view);
            // 设置是否原创
            //int bog_copyright = page.getHtml().regex("bog_copyright").match() ? 1 : 0;
            String bog_copyright = page.getHtml().xpath("//div[@class='artical_tag']/span[@class='original']/text()")
                    .get();
            csdnBlog.setCopyright(bog_copyright);
            // 把对象存入数据库
            //new CsdnBlogDao().add(csdnBlog);
            // 把对象输出控制台
            System.out.println(csdnBlog);
            String s2 = page.getHtml().xpath("//div[@id='article_content']/tidyText()").get();
            System.out.println(s2 + "==========");
            page.putField(i+"",csdnBlog.toString());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    // 把list转换为string，用,分割
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static void main(String[] args) throws JMException {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CSDNPageProcessor())
                .addUrl("http://blog.csdn.net/" + username)
                .addPipeline(new FilePipeline("D:\\webmagic\\"))
                .thread(5).run();
        //为spider注册监控
        //Spider thread = Spider.create(new CSDNPageProcessor())
        //        .addUrl("http://blog.csdn.net/" + username)
        //        .addPipeline(new FilePipeline("D:\\webmagic\\"))
        //        .thread(5);
        //SpiderMonitor.instance().register(thread);
        //thread.start();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + size + "篇文章，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
    }
}