package com.testFileUpload.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.auth0.jwt.JWT;
import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.pojo.Collection;
import com.testFileUpload.service.CollectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
@Controller
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 用户添加一条收藏
     * @param collectionType
     * @param collectionFileId
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "collectionType",value = "收藏类型",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "collectionFileId",value = "收藏文件的编号",required = true,dataType = "String",paramType = "query")
    })
    @ApiOperation(value = "收藏文件",httpMethod = "POST",response = ResponseBody.class)
    @RequestMapping(value="/collectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    @LogAnnotation
    public ResultObject collectionFile(@RequestParam("collectionType") String collectionType,
                                       @RequestParam("collectionFileId") String collectionFileId){

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        Collection collection = new Collection();
        collection.setCollectionFileId(collectionFileId);
        collection.setCollectionId(String.valueOf(UUID.randomUUID()));
        collection.setCollectionType(collectionType);
        collection.setUserId(userId);
        int coll = collectionService.insert(collection);
        return ResultObject.makeSuccess("收藏成功");
    }

    /**
     * 根据用户选择的收藏Id，删除该条收藏
     * @param collectionId
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "collectionId",value = "收藏Id",required = true,dataType = "String",paramType = "query"),
    })
    @ApiOperation(value = "删除收藏文件",httpMethod = "POST",response = ResponseBody.class)
    @RequestMapping(value="/deleteCollectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    @LogAnnotation
    public ResultObject deleteCollection(@RequestParam("collectionId") String collectionId){
        collectionService.delete(collectionId);
        return ResultObject.makeSuccess("删除成功");
    }

    /**
     * 根据用户的Id搜索该用户的所有的收藏
     * @param userId
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "userId",value = "用户Id",dataType = "String",paramType = "query")
    })
    @ApiOperation(value = "检索用户的收藏文件",httpMethod = "POST",response = ResponseBody.class)
    @RequestMapping(value="/selectAllCollectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    @LogAnnotation
    public ResultObject<List<Collection>> selectAllCollection(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize, @RequestParam("userId")String userId){
        List<Collection> list = collectionService.selectCollectionByUserIdToPage(pageNum,pageSize,userId);
        return  ResultObject.makeSuccess(list,"用户收藏信息搜索成功");
    }
}
