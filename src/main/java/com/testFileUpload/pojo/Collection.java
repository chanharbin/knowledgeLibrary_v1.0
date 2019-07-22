package com.testFileUpload.pojo;

/**
 * 用户收藏信息
 * @author HUANGZHONGGUI3
 */
public class Collection {
    private int id;
    private int collectionId;
    private int userId;
    private int collectionType;
    private int collectionFileId;

    public Collection() {
    }

    public Collection(int id, int collectionId, int userId,
                      int collectionType, int collectionFileId) {
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

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(int collectionType) {
        this.collectionType = collectionType;
    }

    public int getCollectionFileId() {
        return collectionFileId;
    }

    public void setCollectionFileId(int collectionFileId) {
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
