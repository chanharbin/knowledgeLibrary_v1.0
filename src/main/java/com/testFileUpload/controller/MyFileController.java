package com.testFileUpload.controller;

import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.common.error.common.Errors;
import com.testFileUpload.common.error.server.CommonError;
import com.testFileUpload.pojo.File;
import com.testFileUpload.service.FileService;
import com.testFileUpload.service.OtherService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 收藏Controller，controller层实现对收藏的操作
 * @author HUANGZHONGGUI3
 */
@RestController
public class MyFileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private OtherService otherService;
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
    @RequiresRoles("user")
    @LogAnnotation
    public ResultObject<com.testFileUpload.pojo.File> uploadFile(@RequestParam("fileName") MultipartFile file,@RequestParam("description") String description, @RequestParam("keyWord") String keyWord,
                             @RequestParam("fileType")String fileType) {

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            throw Errors.wrap(CommonError.FILE_NOTFOUND_EXCEPTION);
        }
        try {
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
            long length = dest.length();
            fileService.uploadFile(fileName,url,token,description,fileType,keyWord,length);
        } catch (IOException e) {
            return ResultObject.makeFail("上传失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultObject.makeSuccess("文件上传成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "fileId", value = "文件编号", required = true,dataType = "String",paramType = "query"),
    })
    @ApiOperation(value = "删除文件",httpMethod = "POST",response = ResponseBody.class)
    @LogAnnotation
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequestMapping(value="/deleteFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResultObject<com.testFileUpload.pojo.File> deleteFile(@RequestParam("file_id") String fileId){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        com.testFileUpload.pojo.File file = fileService.selectFileByFileId(fileId);
        fileService.deleteFileByFileId(fileId);
        return new ResultObject<com.testFileUpload.pojo.File>("删除成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query")
    })
    @ApiOperation(value = "检索文件",httpMethod = "GET",response = ResponseBody.class)
    @LogAnnotation
    @RequiresRoles("user")
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
    @LogAnnotation
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequestMapping(value = "/getAllFile",method = RequestMethod.GET)
    public List<com.testFileUpload.pojo.File> getAllFile(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        return fileService.getAllFile(pageNum,pageSize);
    }

    /**
     * 文档访问量前十展示
     * @return
     */
    @LogAnnotation
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
    })
    @ApiOperation(value = "文件访问量前十排名",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequestMapping(value = "/dispalyFileByVisits",method = RequestMethod.GET)
    public List<com.testFileUpload.pojo.File> dispalyFileByVisits() throws Exception {
        return fileService.displayByVisits();
    }
    @LogAnnotation
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "key",value = "关键字",dataType = "String",paramType = "query"),
    })
    @ApiOperation(value = "模糊查询file",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequestMapping(value = "/selectFileByTitleType",method = RequestMethod.GET)
    public List<File> selectFileByTitleType(@RequestParam("key") String key){
        return fileService.selectFileByTitleType(key);
    }
}
