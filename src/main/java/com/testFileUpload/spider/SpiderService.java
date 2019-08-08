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

    public void start() throws Exception {
        Spider spider = new Spider();
        System.out.println(urlList.size());
        spider.runSpider(urlList);
    }

}
