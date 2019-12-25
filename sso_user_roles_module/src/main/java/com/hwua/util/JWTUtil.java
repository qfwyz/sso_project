package com.hwua.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * 对token处理
 * 生成token
 * 比对token
 * 反编译token
 */
public class JWTUtil {

    //构建token
    public static String createToken(String username,String psssword) throws Exception {
        //获取JWTCreator中的Builder对象
        JWTCreator.Builder builder = JWT.create().
                withClaim("username", username).
                //设置过期时间
                withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000));
        //构建一个加密的凭证
        Algorithm algorithm = Algorithm.HMAC256(psssword);
        //构建token
        String token = builder.sign(algorithm);
        return token;
    }

    //解码token
    public static String decodeToken(String token, String username) throws Exception {
        //构建解码器
        DecodedJWT decode = JWT.decode(token);
        //获取有效负载里面的数据
        String stringClaim = decode.getClaim(username).asString();
        return stringClaim;
    }

    //验证token
    public static boolean ckeckToken(String username,String password,String token) throws  Exception{
        //构建验证器
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        //执行验证方法
        String stringClaim = build.verify(token).getClaim("username").asString();
        if (stringClaim.equals(username)){
            return true;
        }
        return false;
    }
}
