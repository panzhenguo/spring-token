package com.pan.sbs.jwt.conf;

import com.pan.sbs.jwt.core.ResultGenerator;
import com.pan.sbs.jwt.service.AuthService;
import com.pan.sbs.jwt.utils.RedisUtils;
import com.pan.sbs.jwt.utils.ResponseUtils;
import com.pan.sbs.jwt.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PckageName com.pan.sbs.jwt.web
 * @ClassName TokenFilter
 * @Description token 拦截器
 * @Author pzg
 * @Date 2018/9/18
 * @Version 1.0.0
 * @Remark
 **/
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;

    @Value("${crm.login.url}")
    private String loginUrl;


    @Autowired
    private AuthService authService;


    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        boolean isLoginFlag=false;
        // 获取当前请求判断是否是登陆链接
        if(requestURI.startsWith(loginUrl)){
            isLoginFlag=true;
        }
        //该请求为登陆链接
        if(isLoginFlag){
            chain.doFilter(request, response);
            return ;
        }

        //验证token 的合法性
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            ResponseUtils.sendJSON(response,ResultGenerator.getUnAuthorizedResult("token is null").toString());
            return;
        }
        String redisToken = redisUtils.getString(token);
        if(StringUtils.isEmpty(redisToken)){
            ResponseUtils.sendJSON(response,ResultGenerator.getUnAuthorizedResult("redis token is null").toString());
            return;
        }

        // 根据token 获取用户基本信息
        redisUtils.setExpire(redisToken,30*60);
        String userNameByToken = TokenUtils.getUserNameByToken(redisToken);
        if(StringUtils.isEmpty(userNameByToken)){
            ResponseUtils.sendJSON(response,ResultGenerator.getUnAuthorizedResult("token user is null").toString());
            return;
        }
        // 对用户进行授权
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userNameByToken);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

    }
}



