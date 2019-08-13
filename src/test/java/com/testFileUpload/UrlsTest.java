package com.testFileUpload;

import com.testFileUpload.pojo.SpiderUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class UrlsTest {
//    @Value("#{'${sprider.url-List}'.split(',')}")
//     private List<String> urllist;

    @Test
    public void test(){
        SpiderUrl spiderUrl = new SpiderUrl();
        System.out.println(spiderUrl.getSpiderUrl());
        //System.out.println(urllist);
    }


}
