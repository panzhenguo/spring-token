package com.pan.sbs.jwt.utils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @PckageName com.pan.sbs.jwt.utils
 * @ClassName TokenUtils
 * @Description TODO
 * @Author pzg
 * @Date 2018/9/18
 * @Version 1.0.0
 * @Remark
 **/
public class TokenUtils {


   /**
    * @MethodName
    * @Description 根据用户名密码生成token
    * @Author pzg
    * @Date 2018/9/19
    * @Param
    * @Return
    * @Version 1.0.0
    **/
    public static String createToken(UserDetails userDetails){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
       return encode.encode(userDetails.getUsername()+userDetails.getPassword());
    }


    /**
     * @MethodName  getUserNameByToken
     * @Description 根据token获取用户名
     * @Author pzg
     * @Date 2018/9/18
     * @Param 需要解析的token
     * @Return
     * @Version 1.0.0
     **/
    public static String getUserNameByToken(String token){

        return "zs";
    }
    public static void refreshToken(){
        System.out.println();
    }
}
