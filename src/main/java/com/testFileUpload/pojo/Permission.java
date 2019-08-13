package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName(value = "permission")
@Data
public class Permission {
    private int id;

    private String name;

    private String desc;

    private String url;
}
