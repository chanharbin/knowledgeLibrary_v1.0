package com.testFileUpload.pojo;


/**
 * 用户收藏信息
 * @author HUANGZHONGGUI3
 */
public class Collection {
    private int id;
    private String collectionId;
    private String userId;
    private String collectionType;
    private String collectionFileId;

    public Collection() {
    }

    public Collection(int id, String collectionId, String userId,
                      String collectionType, String collectionFileId) {
        this.id = id;
        this.collectionId = collectionId;
        this.userId = userId;
        this.collectionType = collectionType;
        this.collectionFileId = collectionFileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getCollectionFileId() {
        return collectionFileId;
    }

    public void setCollectionFileId(String collectionFileId) {
        this.collectionFileId = collectionFileId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", collectionId=" + collectionId +
                ", userId=" + userId +
                ", collectionType=" + collectionType +
                ", collectionFileId=" + collectionFileId +
                '}';
    }
}
