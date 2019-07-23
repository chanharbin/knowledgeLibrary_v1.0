package com.testFileUpload.controller;

import com.auth0.jwt.JWT;
import com.testFileUpload.service.FileService;
import com.testFileUpload.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@UserLoginToken
@Controller
public class MyFileController {
    @Autowired
    private FileService fileService;
    private String  url;
    @Autowired
    HttpServletRequest httpServletRequest;

    @UserLoginToken
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file,@RequestParam("description") String description, @RequestParam("keyWord") String keyWord,
                             @RequestParam("fileType")String fileType) {

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token



        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


        //加个时间戳，尽量避免文件名称重复
        String path = "D:/fileUpload/" +fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
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
            int result= fileService.uploadFile(file1);
            System.out.print("插入结果"+result+"\n");
            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            return "上传失败";
        }

        return "上传成功,文件url=="+url;
    }

}
