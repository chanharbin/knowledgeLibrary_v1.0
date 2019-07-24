package com.testFileUpload.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.auth0.jwt.JWT;
import com.testFileUpload.pojo.Collection;
import com.testFileUpload.service.CollectionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param collectionId
     * @param userId
     * @param collectionType
     * @param collectionFileId
     * @return
     */
    @RequestMapping(value="/collectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String collectionFile(@RequestParam("collectionId") String collectionId,
                                 @RequestParam("userId")int userId,
                                 @RequestParam("collectionType") String collectionType,
                                 @RequestParam("collectionFileId") String collectionFileId){

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        Collection collection = new Collection();
        collection.setCollectionFileId(collectionFileId);
        collection.setCollectionId(String.valueOf(UUID.randomUUID()));
        collection.setCollectionType(collectionType);
        collection.setUserId(userId);
        int coll = collectionService.insert(collection);
        System.out.println("收藏成功" + coll);
        return "收藏成功";
    }

    /**
     * 根据用户选择的收藏Id，删除该条收藏
     * @param collectionId
     * @return
     */
    @RequestMapping(value="/deleteCollectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteCollection(@RequestParam("collectionId") String collectionId){
        collectionService.delete(collectionId);
        return "删除成功";
    }

    /**
     * 根据用户的Id搜索该用户的所有的收藏
     * @param userId
     * @return
     */
    @RequestMapping(value="/selectAllCollectionFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public List<Collection> selectAllCollection(@RequestParam("userId")int userId){
        List<Collection> list = collectionService.selectByUserId(userId);
        System.out.println("用户收藏信息搜索成功");
        return list;
    }



}
