package com.testFileUpload.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT * FROM comment WHERE comment_file_id=#{commentFileId}")
    Page<Comment> displayCommentByFileId(@Param("commentFileId") String commentFileId,Page page);

    @Select("SELECT * FROM comment WHERE comment_username=#{commentUsername} ORDER BY comment_time")
    Page<Comment> displayCommentByUserName(Page<Comment> page, @Param("commentUsername") String commentUsername);

}
