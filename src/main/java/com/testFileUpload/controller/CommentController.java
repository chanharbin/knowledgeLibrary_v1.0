package com.testFileUpload.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.pojo.Comment;
import com.testFileUpload.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 提交评论
     * @param fileId
     * @param content
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "file_id",value = "文件id",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "评论",dataType = "String",paramType = "query")
    })
    @ApiOperation(value = "提交评论",httpMethod = "POST",response = ResponseBody.class)
    @RequiresRoles("user")
    @RequestMapping(value = "/commentSubmit", method = RequestMethod.POST)
    @LogAnnotation
    public ResultObject commentSubmit(@RequestParam("file_id") String fileId, @RequestParam("content") String content){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String commentUsername = JWT.decode(token).getAudience().get(1);
        Comment comment = new Comment(commentUsername,content);
        comment.setCommentFileId(String.valueOf(UUID.randomUUID()));
        comment.setCommentTime(new Date());
        comment.setCommentFileId(fileId);
        comment.setState("1");
        comment.setCommentId(String.valueOf(UUID.randomUUID()));
        commentService.insert(comment);
        return ResultObject.makeSuccess("评论成功");
    }


    /**
     * 查看文件评论
     * @param fileId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "file_id",value = "文件id",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query")
    })
    @ApiOperation(value = "查看文件评论",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles("user")
    @RequestMapping(value = "/comment_display_file", method=RequestMethod.GET)
    @LogAnnotation
    public ResultObject<List<Comment>> displayCommentFile(@RequestParam("file_id") String fileId,@RequestParam("pageNum")int pageNum,
                                            @RequestParam("pageSize")int pageSize){
        Page<Comment> page = new Page<>(pageNum,pageSize);
        return ResultObject.makeSuccess(commentService.displayCommentFile(page,fileId),"查看文件评论成功");
    }

    /**
     * 查看用户评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "pageNum",value = "页数",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页面数量",dataType = "Integer",paramType = "query")
    })
    @ApiOperation(value = "查看用户评论",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles("user")
    @RequestMapping(value = "/comment_display_user", method=RequestMethod.GET)
    @LogAnnotation
    public ResultObject<List<Comment>> displayCommentUser(@RequestParam("pageNum")int pageNum,
                                            @RequestParam("pageSize")int pageSize){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userName = JWT.decode(token).getAudience().get(1);
        List<Comment> commentsFile = commentService.displayCommentUser(pageNum,pageSize,userName);
        return ResultObject.makeSuccess(commentsFile,"查看用户评论成功");
    }

    /**
     * 删除用户评论
     * @param commentId
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "comment_id",value = "评论id",dataType = "String",paramType = "query")
    })
    @ApiOperation(value = "删除用户评论",httpMethod = "GET",response = ResponseBody.class)
    @RequiresRoles("user")
    @RequestMapping(value = "/comment_del", method=RequestMethod.GET)
    @LogAnnotation
    public  ResultObject delComment(@RequestParam("comment_id") int commentId){
        commentService.deleteFileByCommentId(commentId);
        return ResultObject.makeSuccess("评论删除成功！");
    }

}
