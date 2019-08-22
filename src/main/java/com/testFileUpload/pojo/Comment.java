package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.annotations.Table;

import java.util.Date;

/**
 * 评论信息
 * @author HUANGZHONGGUI3
 */
@Data
@TableName(value = "comment")
public class Comment {

    private int id;
    private String commentId;
    private String commentUsername;
    private String commentFileId;
    private String commentContext;
    private Date commentTime;
    private String state;
    private String commentUserId;
    private int point;

    public Comment(String commentUsername, String commentContext) {
        this.commentUsername = commentUsername;
        this.commentContext = commentContext;
    }

    public Comment(){}

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", commentUsername='" + commentUsername + '\'' +
                ", commentFileId='" + commentFileId + '\'' +
                ", commentContext='" + commentContext + '\'' +
                ", commentTime=" + commentTime +
                ", state='" + state + '\'' +
                ", commentUserId='"+commentUserId + '\'' +
                ", point='"+point + '\'' +
                '}';
    }
}
