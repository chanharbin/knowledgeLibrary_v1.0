package com.testFileUpload.pojo;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Data
@Component

public class SpiderUrl {
    private String url;
    private String author;
    private String text;
    private String titleType;
    private String ownText;
    private String titleList;

    public List<SpiderUrl> getSpiderUrl(){
        List<SpiderUrl> list = new ArrayList<>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("spider");
        for (int i = 1;i < 6;i++){
            String url1 = "spider.url"+ i;
            System.out.println(url1);
            String author1 = "url" + i + ".author";
            String ownText1 = "url" + i + ".ownText";
            String titleType1 = "url" + i + ".titleType";
            String text1 = "url" + i + ".text";
            String titleList1 = "url" + i + ".titleList";
            SpiderUrl spiderUrl = new SpiderUrl();
            spiderUrl.setUrl(resourceBundle.getString(url1));
            spiderUrl.setAuthor(resourceBundle.getString(author1));
            spiderUrl.setOwnText(resourceBundle.getString(ownText1));
            spiderUrl.setText(resourceBundle.getString(text1));
            spiderUrl.setTitleList(resourceBundle.getString(titleList1));
            spiderUrl.setTitleType(resourceBundle.getString(titleType1));
            list.add(spiderUrl);
        }
        return list;
    }
}
