package com.testFileUpload;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.CommentMapper;
import com.testFileUpload.mapper.FileMapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.Comment;
import com.testFileUpload.pojo.File;
import com.testFileUpload.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private CommentMapper commentMapper;
    @org.junit.Test
    public void test(){
        /*User user = userMapper.selectById(1);
        System.out.println(user.getUserName());*/
        /*User user = new User();
        user.setUserName("chb");
        User user1 = userMapper.selectOne(user);
        System.out.println(JSON.toJSONString(user1));*/
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("user_name","chb");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(JSON.toJSONString(users));
    }

    @org.junit.Test
    public void testInsertUser(){
        for (int i = 0; i < 100;i++){
            User user = new User();
            String password = String.valueOf(1);
            String salt = new SecureRandomNumberGenerator().nextBytes().toString(); //盐量随机
            String encodedPassword= new SimpleHash("md5",password,salt,2).toString();
            user.setUserId(String.valueOf(UUID.randomUUID()));
            user.setUserPwd(encodedPassword);
            user.setUserName(String.valueOf(i));
            user.setSex("1");
            user.setRole("user");
            user.setPoint(1);
            user.setAddTime(new Date());
            user.setSalt(salt);
            userMapper.insert(user);
        }
    }

    @org.junit.Test
    public void testInsertComment(){
        EntityWrapper<User> wrapperUser = new EntityWrapper<>();
        EntityWrapper<File> wrapperFile = new EntityWrapper<>();
        List<User> listUser = userMapper.selectList(wrapperUser);
        List<File> listFile = fileMapper.selectList(wrapperFile);
        int[] a = new int[200];
        for (User user:listUser) {
            int count=0;
            while(count < a.length){
                Random random = new Random();
                boolean flag = true;//用来标志的变量
                int r = random.nextInt(207);
                for(int i=0;i<a.length;i++){
                    if(r == a[i]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    a[count] = r;
                    count++;
                }
            }
            for (int i = 0;i < a.length;i++){
                Comment comment = new Comment();
                comment.setPoint((int)(1+Math.random()*10));
                comment.setCommentId(String.valueOf(UUID.randomUUID()));
                comment.setCommentFileId(listFile.get(a[i]).getFileId());
                comment.setCommentTime(new Date());
                comment.setState("1");
                comment.setCommentUsername(user.getUserName());
                comment.setCommentUserId(user.getUserId());
                comment.setCommentContext("很好" + listFile.get(a[i]).getFileName());
                commentMapper.insert(comment);
            }
        }
    }

//    @org.junit.Test
//    public void testInsert100Comment(){
//        EntityWrapper<User> wrapperUser = new EntityWrapper<>();
//        EntityWrapper<File> wrapperFile = new EntityWrapper<>();
//        List<User> listUser = userMapper.selectList(wrapperUser);
//        List<File> listFile = fileMapper.selectList(wrapperFile);
//        //System.out.println(listUser.size());
//        int[] a = new int[10];
//        for (int j = 0;j < 10;j++) {
//            User user = listUser.get(j);
//            int count=0;
//            while(count < a.length){
//                Random random = new Random();
//                boolean flag = true;//用来标志的变量
//                int r = random.nextInt(207);
//                for(int i=0;i<a.length;i++){
//                    if(r == a[i]){
//                        flag = false;
//                        break;
//                    }
//                }
//                if(flag){
//                    a[count] = r;
//                    //System.out.println(r);
//                    count++;
//                }
//            }
//            for (int i = 0;i < a.length;i++){
//                Comment comment = new Comment();
//                comment.setPoint((int)(1+Math.random()*10));
//                comment.setCommentId(String.valueOf(UUID.randomUUID()));
//                comment.setCommentFileId(listFile.get(a[i]).getFileId());
//                comment.setCommentTime(new Date());
//                comment.setState("1");
//                comment.setCommentUsername(user.getUserName());
//                comment.setCommentUserId(user.getUserId());
//                comment.setCommentContext("很好" + listFile.get(a[i]).getFileName());
//                commentMapper.insert(comment);
//            }
//        }
//    }

}
