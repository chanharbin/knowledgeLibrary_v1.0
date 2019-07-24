package com.testFileUpload.service;

import com.testFileUpload.mapper.CollectionMapper;
import com.testFileUpload.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    public int insert(Collection collection){
        Integer insert = collectionMapper.insert(collection);
        return insert;
    }

    /**
     * 删除collectionId对应的那一条收藏
     * @param collectionId
     * @return
     */
    public int delete(String collectionId){
        Integer delete = collectionMapper.deleteById(collectionId);
        return delete;
    }

    public Collection selectByCollectionId(String collectionId){
        Collection coll = collectionMapper.selectById(collectionId);
        return coll;
    }

    public List<Collection> selectByUserId(int userId){
        List<Collection> list = collectionMapper.selectByUserId(userId);
        return list;
    }
}
