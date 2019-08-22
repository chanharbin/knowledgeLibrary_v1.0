package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName(value = "user_role")
public class UserRole {
    private int id;

    private int uid;

    private int rid;
}
