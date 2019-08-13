package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.pojo.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

/**
 * 收藏Mapper，声明collection服务的接口
 * @author HUANGZHONGGUI3
 */
@Repository
public interface CollectionMapper extends BaseMapper<Collection> {
    /**
     * 根据用户Id查询用户的收藏并分页
     * @param page
     * @param userId
     * @return
     */
    @Select("select * from collection where user_id=#{user_id}")
    Page<Collection> selectPageVo(Page page, @Param("user_id") String userId);

    /**
     * 删除收藏Id为collection_id的收藏信息
     * @param collectionId
     * @return
     */
    @Delete("delete from collection where collection_id = #{collection_id}")
    Integer deleteByCollectionId(@Param("collection_id") String collectionId);

}
