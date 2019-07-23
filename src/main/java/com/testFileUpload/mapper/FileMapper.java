package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.testFileUpload.pojo.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<File> {
}
