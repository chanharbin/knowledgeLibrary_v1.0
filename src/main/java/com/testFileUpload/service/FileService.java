package com.testFileUpload.service;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.common.error.common.Errors;
import com.testFileUpload.common.error.server.CommonError;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    @LogAnnotation
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int uploadFile(String fileName,String url,String token,String description,String fileType,String keyWord,long length) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userName = JWT.decode(token).getAudience().get(1);
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
//        throw new RuntimeException();
        System.out.print("插入结果"+result+"\n");
        System.out.print("保存的完整url===="+url+"\n");
        return result;
    }
    public List<File> getAllFile(int pageNum,int pageSize){
        return fileMapper.selectPage(new Page<File>(pageNum,pageSize),new EntityWrapper<File>().eq("state","1"));
    }
    public List<File> searchFile(String keyWord,int pageNum,int pageSize){
        EntityWrapper<File> wrapper = new EntityWrapper<>();
        List<File> files = fileMapper.selectPage(new Page<File>(pageNum,pageSize),wrapper.like("key_word",keyWord).eq("state","1"));
        return files;
    }
    public int deleteFileByFileId(String fileId){
        Integer delete = fileMapper.deleteFileByFileId(fileId);
        return delete;
    }
    public File selectFileByFileId(String fileId){
        File file = fileMapper.selectById(fileId);
        return file;
    }

    public List<File> displayByVisits(){
        return fileMapper.displayFileByVisits();
    }
}
