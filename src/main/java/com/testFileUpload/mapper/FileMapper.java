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
    /**
     * 删除文件接口，当用户需要删除文件时，在数据库中修改文件的状态即可，
     * 将 is_delete 修改为 1 表示已删除
     * @param fileId
     * @return
     */
    @Update("update file set is_delete='1'where file_id=#{file_Id}")
    int deleteFileByFileId(@Param("file_id") String fileId);

    /**
     * 查询访问量前十，返回文件 list
     * @return
     */
    @Select("select * from file a where 10 > (select count(*) from file where visits > a.visits)")
    List<File> displayFileByVisits();

    /**
     * 根据用户输入的关键字key,对文件的keyWord进行模糊查询，找出所有符合条件的文件
     * @param key
     * @return
     */
    @Select("select * from file where key_word like %+#{key}+%")
    List<File> selectFileByTitleType(@Param("key") String key);

    /**
     * 根据 file_id 查询文件
     * @param fileId
     * @return
     */
    @Select("select * from file where file_id = #{file_id}")
    File selectByFileId(@Param("file_id")String fileId);
}
