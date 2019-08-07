package com.testFileUpload.spider;

import com.testFileUpload.config.ApplicationContextProviders;
import com.testFileUpload.service.FileService;

import com.testFileUpload.service.FileServiceForSpider;
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
import java.util.Date;

public class Task implements Runnable {
    private FileServiceForSpider fileServiceForSpider;
    private Request request;
    //private ScheduleQueue sQueue;
    private String titleType;
    private String url;

    public Task(Request r, ScheduleQueue sQueue){
        this.url = r.getUrl();
        this.fileServiceForSpider = ApplicationContextProviders.getBean(FileServiceForSpider.class);
    }

    @Override
    public void run(){
        try{
            String indexHtml = getIndex();
            ArrayList<String> ids = parseIndexHtml(indexHtml);
            System.out.println(ids);
            if(ids==null){
                System.out.println(url);
            } else {
                for (String curUrl : ids) {
                    parseXianQingYeMian(curUrl);
                    Thread.sleep(3000);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    private   String getTitleType() {
        return titleType;
    }

    private void setTitleType(String titleType) {
        this.titleType = titleType;
    }


    //解析数据 得到url
    private ArrayList<String> parseIndexHtml(String indexHtml) {
        // TODO Auto-generated method stub
        if(indexHtml != null){
            ArrayList<String> urls = new ArrayList<String>();
            //解析得到的页面的信息 将其变成文档对象
            Document document = Jsoup.parse(indexHtml);
            //得到document对象后 就可以通过document对象来得到需要的东西
            Elements elements = document.select(".note-list .title");
            titleType = document.select(".collection .main .main-top .title .name").get(0).text();
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
    private String getIndex() throws Exception {
        //发起一个get请求
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        //返回页面的信息
        return getHtml(httpGet);
    }
    //执行发送请求的方法
    private  String getHtml(HttpGet httpGet) throws Exception {
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






    private void parseXianQingYeMian(String pid) throws IOException, ClientProtocolException {
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
            String pathName = "D:\\Spider\\" + titleType + (new Date()).getTime() + ".txt";
            File file =new File(pathName);
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
            fileServiceForSpider.uploadFile(pathName,author,"12","1","1",1);
        }


    }



}