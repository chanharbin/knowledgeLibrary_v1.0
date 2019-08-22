package com.testFileUpload.service;

import com.testFileUpload.aop.LogForSpider;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import com.testFileUpload.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceForSpider {
    @Autowired
    private FileMapper fileMapper;

    /**
     * 将爬虫爬取到的文件存入数据库中
     * @param filePathAbsolute
     * @param author
     * @param description
     * @param fileType
     * @param keyWord
     * @param length
     * @return
     * @throws IOException
     */
    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
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
        return result;
    }
}
