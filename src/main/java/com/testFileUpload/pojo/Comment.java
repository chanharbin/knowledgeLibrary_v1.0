package com.testFileUpload.pojo;

import java.sql.Date;

/**
 * 评论信息
 * @author HUANGZHONGGUI3
 */
public class Comment {

    private int id;
    private int commentId;
    private String commentUserName;
    private String commentFileId;
    private String commentContext;
    private Date commentTime;
    private String state;

    public Comment() {
    }

    public Comment(int id, int commentId, String commentUserName,
                   String commentFileId, String commentContext,
                   Date commentTime, String state) {
        this.id = id;
        this.commentId = commentId;
        this.commentUserName = commentUserName;
        this.commentFileId = commentFileId;
        this.commentContext = commentContext;
        this.commentTime = commentTime;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentFileId() {
        return commentFileId;
    }

    public void setCommentFileId(String commentFileId) {
        this.commentFileId = commentFileId;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", commentUserName='" + commentUserName + '\'' +
                ", commentFileId='" + commentFileId + '\'' +
                ", commentContext='" + commentContext + '\'' +
                ", commentTime=" + commentTime +
                ", state='" + state + '\'' +
                '}';
    }
}
