package com.testFileUpload.service;

import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    public int uploadFile(File file){
        Integer insert = fileMapper.insert(file);
        return insert;
    }
    public int deleteFileByFileId(String fileId){
        Integer delete = fileMapper.deleteFileByFileId(fileId);
        return delete;
    }
    public File selectFileByFileId(String fileId){
        File file = fileMapper.selectById(fileId);
        return file;
    }
}
