package com.testFileUpload.service;

import com.testFileUpload.mapper.LogMapper;
import com.testFileUpload.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int uploadLog(Log log){
        Integer insert = logMapper.insert(log);
        return insert;
    }
}
