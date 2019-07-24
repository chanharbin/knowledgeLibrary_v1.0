package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.testFileUpload.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper extends BaseMapper<Test> {
}
