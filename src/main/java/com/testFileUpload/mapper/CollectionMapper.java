package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.pojo.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectionMapper extends BaseMapper<Collection> {

    @Select("select * from collection where user_id=#{user_id}")
    Page<Collection> selectPageVo(Page page, @Param("user_id") String userId);

    @Delete("delete from collection where collection_id = #{collection_id}")
    Integer deleteByCollectionId(@Param("collection_id") String collectionId);

}
