package com.testFileUpload.mapper;

import com.testFileUpload.pojo.File;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserVisitMapper {

    @Select("SELECT * from file,user_visit where user_id = #{userId} and user_visit.file_id = file.file_id")
    List<File> selectFileFromUserVisitByUserId(String userId);
}
