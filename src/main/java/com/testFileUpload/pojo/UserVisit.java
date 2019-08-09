package com.testFileUpload.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVisit implements Serializable {
    private int id;
    private String userId;
    private String fileId;
    private Date visitDate;
}
