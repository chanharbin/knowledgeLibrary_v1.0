package com.testFileUpload.service.impl;

import com.testFileUpload.mapper.PermissionMapper;
import com.testFileUpload.pojo.Permission;
import com.testFileUpload.pojo.Role;
import com.testFileUpload.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Primary
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Set<String> listPermissions(String userName) {
        List<String> permissions = permissionMapper.listPermissionsByUserName(userName);
        Set s = new HashSet(permissions);
        return s;
    }
    @Override
    public List<String> list() {
        return permissionMapper.list();
    }

    @Override
    public void add(Permission permission) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Permission get(Long id) {
        return null;
    }

    @Override
    public void update(Permission permission) {

    }

    @Override
    public List<Permission> list(Role role) {
        return null;
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        List<String> list = list();
        for (String s:list) {
            if(s.equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        return null;
    }

}
