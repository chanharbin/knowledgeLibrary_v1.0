package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class User {
    private int id;
    private Long userId;
    private String userName;
    private String userPwd;
    private String sex;
    private String email;
    private String photo;
    private Date addTime;
    private int point;
    private int state;
}
