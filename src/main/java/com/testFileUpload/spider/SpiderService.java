package com.testFileUpload.spider;

import com.testFileUpload.service.FileService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderService {
    @Value("#{'${sprider.url-List}'.split(',')}")
    private List<String> urlList;

    public  void add() throws Exception {
        Spider spider = new Spider();

        //爬取首页的信息
//        String indexHtml = getIndex();
//        //解析首页 得到首页里面的所有的id(根据id来查询每一个页面的信息) 存储到集合里面
//        ArrayList<String> ids = parseIndexHtml(indexHtml);
//        spider.addUrl(ids);
        //spider.setUrls(ids);

        //得到了所有详情文章的id 通过文章的id来查询每一篇文章的信息 并且把这些信息保存在自己的数据库里面
        //parseXianQingYeMian(ids);
        System.out.println(urlList.size());
        System.out.println(urlList);
        spider.run(urlList);
    }




}
