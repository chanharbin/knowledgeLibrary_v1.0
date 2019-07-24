package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

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
    private String userId;
    //用户名
    private String userName;
    //用户密码
    private String userPwd;
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
    //用户权限
    private String role;

    public User(String userName, String userPwd, String sex, String email) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.sex = sex;
        this.email = email;
    }
}
