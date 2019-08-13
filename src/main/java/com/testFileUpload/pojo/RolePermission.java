package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName(value = "role_permission")
public class RolePermission {
    private int id;

    private int rid;

    private int pid;
}
