package com.testFileUpload.controller;

import com.auth0.jwt.JWT;
import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.pojo.Comment;
import com.testFileUpload.service.CommentService;
import com.testFileUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String commentSubmit(@RequestParam("file_id") String fileId, @RequestParam("content") String content){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userName = JWT.decode(token).getAudience().get(0);
        Comment comment = new Comment(userName,content);
        comment.setCommentFileId(String.valueOf(UUID.randomUUID()));
        comment.setCommentTime(new Date());
        comment.setCommentFileId(fileId);
        comment.setState("1");
        comment.setCommentId(Integer.valueOf(String.valueOf(UUID.randomUUID())));
        commentService.insert(comment);
        return "评论成功！";
    }

    @RequestMapping(value = "/comment_display_file", method=RequestMethod.GET)
    public String displayComment(@RequestParam("file_id") String fileId){

        return "";
    }
}
