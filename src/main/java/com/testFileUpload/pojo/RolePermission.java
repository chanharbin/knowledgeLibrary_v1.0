package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "role_permission")
public class RolePermission implements Serializable {
    private int id;

    private int rid;

    private int pid;
}
