package com.testFileUpload.service;

import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.aop.LogForSpider;
import com.testFileUpload.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OtherService {
    @Autowired
    private UserService userService;
    @Autowired
    private FileServiceForSpider fileServiceForSpider;

    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadManyFiles(){
        for (int i = 0; i < 10; i++) {
            try{
                MultipartFile multipartFile = FileUtil.transferFile("C:/Users/chenhaobin6/Desktop/2.txt");
                fileServiceForSpider.uploadFile("123", "aa","123", "1", "123", 12);
                userService.addPoint("85851eea-1584-4c51-8a9d-eaa5903fc60a");
            }
            //捕捉异常 允许下一个线程访问而不中断回滚所有线程
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
