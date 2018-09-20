package com.pan.sbs.jwt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PckageName com.pan.sbs.jwt.web
 * @ClassName ErrorController
 * @Description TODO
 * @Author pzg
 * @Date 2018/9/19
 * @Version 1.0.0
 * @Remark
 **/
@RestController
public class ErrorController {
    @RequestMapping("/e403")
    public void e403(){
        System.out.println("403");
    }
    @RequestMapping("/e404")
    public void e404(){
        System.out.println("404");
    }
    @RequestMapping("/e500")
    public void e500(){
        System.out.println("500");
    }
}
