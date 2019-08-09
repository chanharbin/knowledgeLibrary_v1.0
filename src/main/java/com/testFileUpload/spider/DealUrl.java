package com.testFileUpload.spider;

import com.testFileUpload.config.ApplicationContextProviders;
import com.testFileUpload.pojo.SpiderUrl;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class DealUrl {
    private Logger logger = LoggerFactory.getLogger(DealUrl.class);
    private FileServiceForSpider fileServiceForSpider;
    private String titleType;

    public DealUrl(){
        this.fileServiceForSpider = ApplicationContextProviders.getBean(FileServiceForSpider.class);
    }

    public void dealWithUrl(SpiderUrl spiderUrl) throws Exception {
        String indexHtml=getIndex(spiderUrl.getUrl());
        System.out.println(indexHtml);
        ArrayList<String> ids = parseIndexHtml(spiderUrl,indexHtml);
        if(ids==null){
            System.out.println("null:\t"+spiderUrl.getUrl());
        } else {
            for (String curUrl : ids) {
                try {
                    parseXianQingYeMian(curUrl,spiderUrl.getOwnText(),spiderUrl.getAuthor(),spiderUrl.getText());
                    Thread.sleep(1000);
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public  String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    //解析数据 得到url
    private  ArrayList<String> parseIndexHtml(SpiderUrl spiderUrl,String indexHtml) {
        // TODO Auto-generated method stub
        if(indexHtml != null){
            ArrayList<String> urls = new ArrayList<String>();
            //解析得到的页面的信息 将其变成文档对象
            Document document = Jsoup.parse(indexHtml);
            //得到document对象后 就可以通过document对象来得到需要的东西
            //System.out.println(spiderUrl);
            //System.out.println(spiderUrl.getTitleList());
            Elements elements = document.select(spiderUrl.getTitleList());
            titleType = document.select(spiderUrl.getTitleType()).get(0).text();
            for (Element element : elements) {
                String url = element.attr("href");
                urls.add(url);
            }
            return urls;
        }
        return null;
    }

    //首页的获取
    private  String getIndex(String url) throws Exception {
        //发起一个get请求
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        //返回页面的信息
        return getHtml(httpGet);
    }
    //执行发送请求的方法
    private   String getHtml(HttpGet httpGet) throws Exception {
        // TODO Auto-generated method stub
        String html = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        //判断响应码是否为200
        if(execute.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = execute.getEntity();
            html = EntityUtils.toString(entity);
        }else{
            System.out.println("not 200! "+execute.getStatusLine().getStatusCode());
        }
        return html;
    }

    private void parseXianQingYeMian(String pid,String ownText1,String author1,String text) throws IOException {
        FileService fileService = null;
        //创建发送请求
        String url = pid;
        HttpGet httpGet = null;
        if (url.length() >= 20){
            httpGet = new HttpGet(url);
        }else{
            httpGet = new HttpGet("https://www.jianshu.com"+ url);
        }
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
            String ownText = document.select(ownText1).get(0).ownText();
            //获取作者
            String author = document.select(author1).get(0).text();
            //获取文章内容
            Elements elements = document.select(text);
            String pathName = "D:\\Spider\\" + titleType + (new Date()).getTime() + ".txt";
            File file =new File(pathName);
            if (!file.exists()){
                file.createNewFile();
            }
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
            long length = file.length();
            //fileServiceForSpider.uploadFile(pathName,author,"12","1","1",length);
        }
    }
}
