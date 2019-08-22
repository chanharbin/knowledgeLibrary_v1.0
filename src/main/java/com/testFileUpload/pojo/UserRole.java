package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user_role")
public class UserRole implements Serializable {
    private int id;

    private int uid;

    private int rid;
}
