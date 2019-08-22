package com.testFileUpload.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.testFileUpload.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select p.url from user u left join user_role ru on u.id = ru.uid left join role r on r.id = ru.rid left join role_permission rp on r.id = rp.rid left join permission p on p.id = rp.pid where u.user_name =#{user_name}")
    public List<String> listPermissionsByUserName(@Param("user_name") String userName);
    @Select("select url from permission")
    public List<String> list();
}
