package com.testFileUpload.service;

import com.testFileUpload.mapper.LogMapper;
import com.testFileUpload.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public int uploadLog(Log log){
        Integer insert = logMapper.insert(log);
        return insert;
    }
}
