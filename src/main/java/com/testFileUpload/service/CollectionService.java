package com.testFileUpload.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.mapper.CollectionMapper;
import com.testFileUpload.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收藏服务，实现CollectionMapper
 * @author HUANGZHONGGUI3
 */
@Service
public class CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    /**
     * 插入一条收藏
     * @param collection
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
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
        Integer delete = collectionMapper.deleteByCollectionId(collectionId);
        return delete;
    }

    /**
     * 根据收藏Id查询该条收藏信息
     * @param collectionId
     * @return
     */
    public Collection selectByCollectionId(String collectionId){
        Collection coll = collectionMapper.selectById(collectionId);
        return coll;
    }

    /**
     * 分页查询用户的全部收藏信息
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public List<Collection> selectCollectionByUserIdToPage(int pageNum,int pageSize,String userId){
        return collectionMapper.selectPage(new Page<Collection>(pageNum,pageSize),new EntityWrapper<Collection>().eq("user_id",userId));
    }
}
