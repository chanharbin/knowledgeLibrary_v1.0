package com.testFileUpload.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

public class PageProcessorUtils implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(1000);
    @Override
    public void process(Page page){
        page.putField("author",page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name",page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if(page.getResultItems().get("name") == null){
            page.setSkip(true);
        }
        page.putField("readme",page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite(){
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
                .thread(5)
                .run();
    }
}
