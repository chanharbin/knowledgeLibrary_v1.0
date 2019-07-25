package com.testFileUpload.controller;

import com.auth0.jwt.JWT;
import com.testFileUpload.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
public class MyFileController {
    @Autowired
    private FileService fileService;
    private String  url;
    @Autowired
    HttpServletRequest httpServletRequest;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name ="keyWord" , value = "检索关键字", required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "description", value = "文档描述", required = false,dataType ="String" ,paramType = "query"),
            @ApiImplicitParam(name = "fileType", value = "文件类型", required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "fileName", value = "文件", required = true,dataType = "MultipartFile",paramType = "query"),
    })
    @ApiOperation(value = "上传文件",httpMethod = "POST",response = ResponseBody.class)
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "fileId", value = "文件编号", required = true,dataType = "String",paramType = "query"),
    })
    @ApiOperation(value = "删除文件",httpMethod = "POST",response = ResponseBody.class)
    @RequestMapping(value="/deleteFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteFile(@RequestParam("file_id") String fileId){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        com.testFileUpload.pojo.File file = fileService.selectFileByFileId(fileId);
        String fileName = file.getFileName();
        fileService.deleteFileByFileId(fileId);
        return "文件"+fileName+"已删除";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query")
    })
    @ApiOperation(value = "检索文件",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles({"user","admin"})
    @RequestMapping(value="/searchFile",produces="application/json;charset=UTF-8")
    public List<com.testFileUpload.pojo.File> seachFile(@RequestParam("keyWord")String keyword,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        List<com.testFileUpload.pojo.File> files = fileService.searchFile(keyword, pageNum, pageSize);
        return files;
    }
    @ApiOperation(value = "查询所有文件",httpMethod = "GET",response = ResponseBody.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query")
    })
    @RequestMapping(value = "/getAllFile",method = RequestMethod.GET)
    public List<com.testFileUpload.pojo.File> getAllFile(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        return fileService.getAllFile(pageNum,pageSize);
    }
}
