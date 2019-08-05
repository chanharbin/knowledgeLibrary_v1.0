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

import java.io.*;
import java.util.ArrayList;

public class SpiderService {
    public static void main(String[] args) throws Exception {
        Spider spider = new Spider();
        for (int i = 0; i < 1; i++) {


            //爬取首页的信息
            String indexHtml = getIndex();
            //解析首页 得到首页里面的所有的id(根据id来查询每一个页面的信息) 存储到集合里面
            ArrayList<String> ids = parseIndexHtml(indexHtml);
            spider.addUrl(ids);

            //得到了所有详情文章的id 通过文章的id来查询每一篇文章的信息 并且把这些信息保存在自己的数据库里面
            //parseXianQingYeMian(ids);
        }
        spider.run();
    }

    //解析数据 得到url
    private static ArrayList<String> parseIndexHtml(String indexHtml) {
        // TODO Auto-generated method stub
        if(indexHtml != null){
            ArrayList<String> urls = new ArrayList<String>();
            //解析得到的页面的信息 将其变成文档对象
            Document document = Jsoup.parse(indexHtml);
            //得到document对象后 就可以通过document对象来得到需要的东西
            Elements elements = document.select(".note-list .title");
            //System.out.println(elements);
            for (Element element : elements) {
                String url = element.attr("href");
                urls.add(url);
            }
            return urls;
        }
        return null;
    }
    //首页的获取
    private static String getIndex() throws Exception {
        String url = "https://www.jianshu.com/c/V2CqjW";
        //发起一个get请求
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        //返回页面的信息
        return getHtml(httpGet);
    }
    //执行发送请求的方法
    private static String getHtml(HttpGet httpGet) throws Exception {
        // TODO Auto-generated method stub
        String html = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        //判断响应码是否为200
        if(execute.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = execute.getEntity();
            html = EntityUtils.toString(entity);
        }
        return html;
    }



}
