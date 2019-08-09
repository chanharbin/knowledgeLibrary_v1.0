package com.testFileUpload.spider;

import com.testFileUpload.pojo.SpiderUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderService {
//    @Value("#{'${sprider.url-List}'.split(',')}")
//   private List<String> urlList;

    private List<SpiderUrl> list;
    public void start() throws Exception {
        SpiderUrl spiderUrl = new SpiderUrl();
        list = spiderUrl.getSpiderUrl();
        Spider spider = new Spider();
        System.out.println(list.size());
        spider.runSpider(list);
    }

}
