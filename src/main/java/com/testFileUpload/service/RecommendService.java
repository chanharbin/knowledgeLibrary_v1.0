package com.testFileUpload.service;

import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import com.testFileUpload.recommendation.DataSet;
import com.testFileUpload.recommendation.Recommend;
import com.testFileUpload.recommendation.UserSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class RecommendService {
    @Autowired
    private DataSet dataSet;
    @Autowired
    private FileMapper fileMapper;
    public File recommend(String userName){
        UserSet userSet = dataSet.initialUserSet();
        Recommend recommend = new Recommend();
        String recommendFileId = recommend.Recommend(userSet, userName);
        File file = fileMapper.selectByFileId(recommendFileId);
        return file;
    }
}
