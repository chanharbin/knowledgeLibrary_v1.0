package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.testFileUpload.pojo.Collection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectionMapper extends BaseMapper<Collection> {

    @Select("select * from collection where user_id=#{user_id}")
    Page<Collection> selectPageVo(Page page, @Param("user_id") String userId);


}
