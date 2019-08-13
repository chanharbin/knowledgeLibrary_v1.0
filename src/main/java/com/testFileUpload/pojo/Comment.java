package com.testFileUpload.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论信息
 * @author HUANGZHONGGUI3
 */
@Data
public class Comment implements Serializable {

    private int id;
    private String commentId;
    private String commentUsername;
    private String commentFileId;
    private String commentContext;
    private Date commentTime;
    private String state;

    public Comment(String commentUsername, String commentContext) {
        this.commentUsername = commentUsername;
        this.commentContext = commentContext;
    }

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
                '}';
    }
}
