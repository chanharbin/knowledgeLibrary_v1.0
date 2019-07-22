package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * 用户信息
 * @author HUANGZHONGGUI3
 */
@Data
@TableName(value = "user")
public class User {
    //Id
    private int id;
    //用户Id
    private long userId;
    //用户名
    private String userName;
    //用户密码
    private String password;
    //用户性别
    private String sex;
    //用户邮箱
    private String email;
    //用户头像路径
    private String photo;
    //用户注册时间
    private Date addTime;
    //用户积分
    private int point;
    //用户类型，0是普通用户，1是管理员
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoto(){
        return photo;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
    }
    public java.sql.Date getAddTime(){
        return addTime;
    }
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }

    public  int getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point = point;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", addTime=" + addTime +
                ", point=" + point +
                ", state='" + state + '\'' +
                '}';
    }
}
