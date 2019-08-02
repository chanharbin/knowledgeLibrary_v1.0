package com.testFileUpload.service;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.common.error.common.Errors;
import com.testFileUpload.common.error.server.CommonError;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    @Transactional
    public int uploadFile(MultipartFile file,String url,String token,String description,String fileType,String keyWord) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        String path = "D:/fileUpload/" +fileName;
        java.io.File dest = new java.io.File(path);
        if (dest.exists()) {
            throw Errors.wrap(CommonError.UNIQUE_IDENTITY_DUPLICATION);
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        //上传文件
        file.transferTo(dest); //保存文件
        System.out.print("保存文件路径"+path+"\n");
        System.out.print("保存文件绝对路径"+path+"\n");
        //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
        url="http://localhost:8080/files/"+fileName;//本地运行项目
        Date date = new Date();
        com.testFileUpload.pojo.File file1 = new com.testFileUpload.pojo.File();
        String userId = JWT.decode(token).getAudience().get(0);
        String userName = JWT.decode(token).getAudience().get(1);
        file1.setFileId(String.valueOf(UUID.randomUUID()));
        file1.setAddTime(date);
        file1.setFileName(fileName);
        file1.setDescription(description);
        file1.setFilePath(url);
        file1.setFileType(fileType);
        file1.setUploaderId(userId);
        file1.setAuthor(userName);
        file1.setFileSize(dest.length());
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
