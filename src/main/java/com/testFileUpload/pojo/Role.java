package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName(value = "role")
@Data
public class Role {
    private int id;

    private String name;

    private String desc;
}
