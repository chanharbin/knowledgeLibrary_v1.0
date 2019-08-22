package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "role")
@Data
public class Role implements Serializable {
    private int id;

    private String name;

    private String desc;
}
