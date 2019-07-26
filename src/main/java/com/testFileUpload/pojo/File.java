package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 文件信息
 * @author HUANGZHONGGUI3
 */
@TableName(value = "file")
public class File {
    //编号
    private int id;
    //文件id
    private String fileId;
    //访问量
    private int visits;
    //文件作者
    private String author;
    //上传者Id，上传该文件的用户Id
    private String uploaderId;
    //文件名
    private String fileName;
    //文件大小
    private Long fileSize;
    //文件路径
    private String filePath;
    //添加文件的时间
    private Date addTime;
    //文件更新时间
    private Date updateTime;
    //文件描述
    private String description;
    //文件是否公开，0表示上传者私有，1表示公开
    private String state;
    //文件状态，0表示存在，1表示删除
    private String isDelete;
    //文件关键字
    private String keyWord;
    //文件类型
    private String fileType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", visits=" + visits +
                ", author='" + author + '\'' +
                ", uploaderId='" + uploaderId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
