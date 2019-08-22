package com.testFileUpload.service;

import com.auth0.jwt.JWT;
import com.testFileUpload.aop.LogForSpider;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import com.testFileUpload.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceForSpider {
    @Autowired
    private FileMapper fileMapper;
    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int uploadFile(String filePathAbsolute,String author, String description, String fileType, String keyWord, long length) throws IOException {
        MultipartFile multipartFile = FileUtil.transferFile(filePathAbsolute);
        String fileName = multipartFile.getOriginalFilename();
        String url = "http://localhost:8080/files/"+fileName;
        String path = "D:/FileForCopy/" + fileName;
        java.io.File file2 = new java.io.File(path);
        multipartFile.transferTo(file2);
        String userId = "1";
        Date date = new Date();
        File file1 = new File();
        file1.setFileId(String.valueOf(UUID.randomUUID()));
        file1.setAddTime(date);
        file1.setFileName(fileName);
        file1.setDescription(description);
        file1.setFilePath(url);
        file1.setFileType(fileType);
        file1.setUploaderId(userId);
        file1.setAuthor(author);
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

    //抛异常的插入入库
    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int uploadFileWithException(String filePathAbsolute,String author, String description, String fileType, String keyWord, long length) throws IOException {
        MultipartFile multipartFile = FileUtil.transferFile(filePathAbsolute);
        String fileName = multipartFile.getOriginalFilename();
        String url = "http://localhost:8080/files/"+fileName;
        String path = "D:/FileForCopy/" + fileName;
        java.io.File file2 = new java.io.File(path);
        multipartFile.transferTo(file2);
        String userId = "1";
        Date date = new Date();
        File file1 = new File();
        file1.setFileId(String.valueOf(UUID.randomUUID()));
        file1.setAddTime(date);
        file1.setFileName(fileName);
        file1.setDescription(description);
        file1.setFilePath(url);
        file1.setFileType(fileType);
        file1.setUploaderId(userId);
        file1.setAuthor(author);
        file1.setFileSize(length);
        file1.setState("1");
        file1.setKeyWord(keyWord);
        //TODO
        file1.setUpdateTime(date);
        int result= fileMapper.insert(file1);
        throw new RuntimeException();
//        System.out.print("插入结果"+result+"\n");
//        System.out.print("保存的完整url===="+url+"\n");
//        return result;
    }
}
