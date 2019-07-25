package com.testFileUpload.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.pojo.Comment;
import com.testFileUpload.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public int insert(Comment comment){
        return commentMapper.insert(comment);
    }


    public List<Comment> displayCommentFile(Page<Comment> page,String commentFileId){
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        return commentMapper.selectPage(page,wrapper.eq("comment_file_id", commentFileId));
    }

    public List<Comment> displayCommentUser(int pageNum, int pageSize,String userName){
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        return commentMapper.selectPage(new Page<Comment>(pageNum,pageSize),wrapper.eq("comment_username", userName));
    }

    public int deleteFileByCommentId(int commentId){
        Integer delete = commentMapper.delete(new EntityWrapper<Comment>().eq(
                "comment_id",commentId));
        return delete;
    }
}
