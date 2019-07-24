package com.testFileUpload.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    public int uploadFile(File file){
        Integer insert = fileMapper.insert(file);
        return insert;
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
}
