package com.testFileUpload.service;

import com.testFileUpload.mapper.CollectionMapper;
import com.testFileUpload.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    public int insert(Collection collection){
        Integer insert = collectionMapper.insert(collection);
        return insert;
    }

    public int delete(Long collectionId){
        Integer delete = collectionMapper.deleteById(collectionId);
        return delete;
    }

    public Collection selectByCollectionId(Long collectionId){
        Collection coll = collectionMapper.selectById(collectionId);
        return coll;
    }

    public List<Collection> selectByUserId(int userId){
        List<Collection> list = collectionMapper.selectByUserId(userId);
        return list;
    }
}
