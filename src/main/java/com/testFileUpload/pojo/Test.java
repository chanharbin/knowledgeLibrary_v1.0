package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName(value = "test")
public class Test {
    private int id;
    private String name;
}
