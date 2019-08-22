package com.testFileUpload.recommendation;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.Comment;
import com.testFileUpload.pojo.File;
import com.testFileUpload.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSet {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private CommentMapper commentMapper;
    public UserSet initialUserSet(){
        UserSet userSet = new UserSet();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        List<User> users = userMapper.selectList(userEntityWrapper);
        EntityWrapper<File> fileEntityWrapper = new EntityWrapper<>();
        List<File> files = fileMapper.selectList(fileEntityWrapper);
        EntityWrapper<Comment> commentEntityWrapper = new EntityWrapper<>();
        List<Comment> comments = commentMapper.selectList(commentEntityWrapper);
        for(User u : users){
            UserSet.User put = userSet.put(u.getUserName());
            for(File f : files){
                boolean isEsist = false;
                for(Comment comment : comments){
                    if(comment.getCommentFileId().equals(f.getFileId()) && comment.getCommentUsername().equals(u.getUserName())){
                        put.set(f.getFileId(),comment.getPoint());
                        isEsist = true;
                        break;
                    }
                }
                if(!isEsist){
                    put.set(f.getFileId(),0);
                }
            }
            put.create();
        }
        return userSet;
    }
}
