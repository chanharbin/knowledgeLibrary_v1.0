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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Task implements Runnable {
    private Request request;
    private ScheduleQueue sQueue;
    public Task(Request r, ScheduleQueue sQueue){
        this.sQueue=sQueue;
        request = r;
    }
    @Override
    public void run(){
        try{
            parseXianQingYeMian(request.getUrl());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void parseXianQingYeMian(String pid) throws IOException, ClientProtocolException {
        FileService fileService = null;
        //创建发送请求
        HttpGet httpGet = new HttpGet("https://www.jianshu.com"+pid);
        //HttpGet httpGet = new HttpGet("https://www.huxiu.com/article/311419.html");
        System.out.println("https://www.jianshu.com"+pid);
        //消息头
        httpGet.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发送请求
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        //判断这个详细页面是否可以加载成功
        if(execute.getStatusLine().getStatusCode() == 200){//200表示加载到了详情的页面的信息
            HttpEntity entity = execute.getEntity();
            String html = EntityUtils.toString(entity);
            //将详细页面的信息 转换为文档对象
            Document document = Jsoup.parse(html);
            //获取文章的标题信息
            String ownText = document.select(".note .post .article .title").get(0).ownText();
            //获取文章主题
            //String titleType = document.select(".collection .main .main-top .title .name").get(0).text();
            //获取作者
            String author = document.select(".note .post .article .author .name").get(0).text();
            //获取文章内容
            Elements elements = document.select(".note .post .article .show-content ol, .note .post .article .show-content p, .note .post .article .show-content ul");
            String pathName = ownText + ".txt";
            File file =new File("D:\\Spider\\" + pathName);
            for (Element element : elements) {
                String str = element.text();
                FileWriter resultFile = new FileWriter(file, true);//true,则追加写入
                PrintWriter myFile = new PrintWriter(resultFile);
                //写入
                myFile.println(str);
                myFile.println("\n");
                myFile.close();
                resultFile.close();
                System.out.println(str);
            }
            //fileService.uploadFile(file,"D:\\Spider\\" + pathName,null,ownText,titleType);
        }


    }



}