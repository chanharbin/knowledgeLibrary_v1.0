package com.testFileUpload.spider;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@Service
public class HuXiu {

    public static void main (String[] args)throws Exception{
        String indexHtml = getIndex();
        ArrayList<String> ids = parseIndexHtml(indexHtml);
        parseXianQingYeMian(ids);
    }
    //@Autowired
    //private  FileServiceForSpider fileServiceForSpider;
    private static String titleType;
    //解析数据 得到url
    public static ArrayList<String> parseIndexHtml(String indexHtml) {
        // TODO Auto-generated method stub
        if(indexHtml != null){
            ArrayList<String> urls = new ArrayList<String>();
            //解析得到的页面的信息 将其变成文档对象
            Document document = Jsoup.parse(indexHtml);
            //得到document对象后 就可以通过document对象来得到需要的东西
            Elements elements = document.select("main ul.feedlist_mod li .list_con h2 a");
            System.out.println(elements);
            titleType = document.select("nav div div ul .active").get(0).text();
            System.out.println(titleType);
            for (Element element : elements) {
                String url = element.attr("href");
                urls.add(url);
                System.out.println(url);
            }
            return urls;
        }
        System.out.println();
        return null;
    }
    //首页的获取
    public  static String getIndex() throws Exception {
        String url = "https://blog.csdn.net/nav/java";
        //发起一个get请求
        HttpGet httpGet = new HttpGet(url);
        //设置请求头
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        //返回页面的信息

        return getHtml(httpGet);
    }
    //执行发送请求的方法
    public  static String getHtml(HttpGet httpGet) throws Exception {
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

    public static void parseXianQingYeMian(ArrayList<String> ids) throws IOException, ClientProtocolException {
        final Base64.Encoder encoder = Base64.getEncoder();
        if(ids.size() != 0){
            int i = 1;
            for (String pid : ids) {
                //创建发送请求
                HttpGet httpGet = new HttpGet(pid);
                //HttpGet httpGet = new HttpGet("https://www.huxiu.com/article/311419.html");
                System.out.println(pid);
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
                    String ownText = document.select("main div.blog-content-box .article-header-box .article-header div.article-title-box .title-article").get(0).ownText();
                    //获取作者
                    String author = document.select("aside #asideProfile .profile-intro .user-info p.name a").get(0).text();
                    System.out.println(author);
                    //获取文章内容
                    Elements elements = document.select("main div.blog-content-box article *");
                    //System.out.println(elements);
                    String pathName = titleType + (new Date()).getTime() + ".txt";
                    File file =new File("D:\\Spider\\" + pathName);
                    try{
                        FileWriter resultFile = new FileWriter(file, true);//true,则追加写入
                        PrintWriter myFile = new PrintWriter(resultFile);
                        for (Element element : elements) {
                            String str = element.text();
                            //写入
                            myFile.println(str);
                            myFile.println("\n");
                            System.out.println(str);
                        }
                        myFile.close();
                        resultFile.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    byte[] textByte = ownText.getBytes("UTF-8");
                    String encodedOwnText = encoder.encodeToString(textByte);
                    //fileServiceForSpider.uploadFile(encodedOwnText,"123","1","123",1024);
                }
            }
        }
    }
}