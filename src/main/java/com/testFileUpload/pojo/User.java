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
    //用户类型，0是普通用户，1是管理员
    private int state;

    private String role;
}
