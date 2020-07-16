package com.dubbo.consumer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt工具类
 */

public class JWTUtil {
    /**
     * 解密 校验 token是否正确
     *  token 密钥
     *  secret 加密数字签名的密钥。
     *
     */
    public static boolean verity(String token, String username, String secret){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            System.out.println("token错误");
        }
        return false;
    }

    public static String getUsername(HttpServletRequest request){
        //取token
        String token = request.getHeader("Authorization");
        return null;
    }

    //从token中获取用户名
    public static String getUsername(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            System.out.println("获取用户名失败");
        }
        return null;
    }

}















