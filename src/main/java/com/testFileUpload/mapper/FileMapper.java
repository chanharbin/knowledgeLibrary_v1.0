package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.testFileUpload.pojo.File;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper extends BaseMapper<File> {
    @Update("update file set is_delete='1'where file_id=#{file_Id}")
    int deleteFileByFileId(@Param("file_id") String fileId);

    @Select("select * from file a where 10 > (select count(*) from file where visits > a.visits)")
    List<File> displayFileByVisits();
}
