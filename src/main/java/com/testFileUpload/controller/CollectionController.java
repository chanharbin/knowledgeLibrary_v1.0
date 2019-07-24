package com.testFileUpload.controller;

import com.auth0.jwt.JWT;
import com.testFileUpload.pojo.Collection;
import com.testFileUpload.service.CollectionService;
import com.testFileUpload.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @UserLoginToken
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
}
