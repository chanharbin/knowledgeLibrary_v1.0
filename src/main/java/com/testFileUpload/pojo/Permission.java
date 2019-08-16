package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "permission")
@Data
public class Permission implements Serializable {
    private int id;

    private String name;

    private String desc;

    private String url;
}
