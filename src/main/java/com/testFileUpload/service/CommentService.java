package com.testFileUpload.service;

import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public int insert(Comment comment){
        return commentMapper.insert(comment);
    }
}
