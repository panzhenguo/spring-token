package com.pan.sbs.jwt.conf;

import com.pan.sbs.jwt.core.ResultGenerator;
import com.pan.sbs.jwt.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PckageName com.pan.sbs.jwt.conf
 * @ClassName RestAuthenticationAccessDeniedHandler
 * @Description 拒绝访问结果集
 * @Author pzg
 * @Date 2018/9/19
 * @Version 1.0.0
 * @Remark
 **/
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseUtils.sendJSON(httpServletResponse, ResultGenerator.getUnForbiddenResult().toString());
    }
}
