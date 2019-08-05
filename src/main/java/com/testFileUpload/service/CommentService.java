package com.testFileUpload.service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**提交一条评论
     *
     * @param comment
     * @return
     */
    public int insert(Comment comment){
        return commentMapper.insert(comment);
    }

    /**显示该文件Id下所有的品论
     *
     * @param page
     * @param commentFileId
     * @return
     */
    public List<Comment> displayCommentFile(Page<Comment> page,String commentFileId){
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        return commentMapper.selectPage(page,wrapper.eq("comment_file_id", commentFileId));
    }

    /**显示登陆用户的所有评论
     *
     * @param pageNum
     * @param pageSize
     * @param userName
     * @return
     */
    public List<Comment> displayCommentUser(int pageNum, int pageSize,String userName){
        EntityWrapper<Comment> wrapper = new EntityWrapper<>();
        return commentMapper.selectPage(new Page<Comment>(pageNum,pageSize),wrapper.eq("comment_username", userName));
    }

    /**根据评论的id，将其从数据表comment中删掉
     *
     * @param commentId
     * @return
     */
    public int deleteFileByCommentId(int commentId){
        Integer delete = commentMapper.delete(new EntityWrapper<Comment>().eq(
                "comment_id",commentId));
        return delete;
    }
}
