/*
package com.testFileUpload.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.testFileUpload.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {
    final static int EXPIRE_TIME = 600000;
    public String getToken(User user) {
        String token="";
        Date date = new Date(System.currentTimeMillis() +EXPIRE_TIME);
        token= JWT.create().withAudience(String.valueOf(user.getUserId()),user.getUserName())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getUserPwd()));
        return token;
    }
}
*/
