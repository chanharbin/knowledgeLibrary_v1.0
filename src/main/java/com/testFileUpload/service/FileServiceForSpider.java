package com.testFileUpload.service;

import com.auth0.jwt.JWT;
import com.testFileUpload.aop.LogForSpider;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceForSpider {
    @Autowired
    private FileMapper fileMapper;
    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int uploadFile(String fileName,String description,String fileType,String keyWord,long length) {
        String url = "http://localhost:8080/files/"+fileName;
        String userId = "1";
        String userName = "admin";
        Date date = new Date();
        File file1 = new File();
        file1.setFileId(String.valueOf(UUID.randomUUID()));
        file1.setAddTime(date);
        file1.setFileName(fileName);
        file1.setDescription(description);
        file1.setFilePath(url);
        file1.setFileType(fileType);
        file1.setUploaderId(userId);
        file1.setAuthor(userName);
        file1.setFileSize(length);
        file1.setState("1");
        file1.setKeyWord(keyWord);
        //TODO
        file1.setUpdateTime(date);
        int result= fileMapper.insert(file1);
        System.out.print("插入结果"+result+"\n");
        System.out.print("保存的完整url===="+url+"\n");
        return result;
    }
}
