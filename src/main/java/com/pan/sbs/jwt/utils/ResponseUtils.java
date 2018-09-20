package com.pan.sbs.jwt.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @PckageName com.pan.sbs.jwt.utils
 * @ClassName ResponseUtils
 * @Description Response 工具累
 * @Author pzg
 * @Date 2018/9/19
 * @Version 1.0.0
 * @Remark
 **/
public class ResponseUtils {
    public static void sendJSON(HttpServletResponse response,String string){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(string);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
