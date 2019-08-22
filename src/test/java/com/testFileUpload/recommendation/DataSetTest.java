package com.testFileUpload.recommendation;

import com.alibaba.fastjson.JSON;
import com.testFileUpload.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class DataSetTest {
    @Test
    public void initialUserSet() {
        DataSet dataSet = new DataSet();
        UserSet userSet = dataSet.initialUserSet();
        String string = JSON.toJSONString(userSet);
        System.out.println(string);
    }
}