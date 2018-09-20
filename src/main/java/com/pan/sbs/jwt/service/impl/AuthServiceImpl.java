package com.pan.sbs.jwt.service.impl;

import com.alibaba.fastjson.JSON;
import com.pan.sbs.jwt.dao.SysUserRepository;
import com.pan.sbs.jwt.pojo.SysUser;
import com.pan.sbs.jwt.service.AuthService;
import com.pan.sbs.jwt.utils.RedisUtils;
import com.pan.sbs.jwt.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserRepository userRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Autowired
    private RedisUtils redisUtils;

//    @Autowired
//    public AuthServiceImpl(
//            AuthenticationManager authenticationManager,
//            UserDetailsService userDetailsService,
//            JwtTokenUtil jwtTokenUtil,
//            SysUserRepository userRepository) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userRepository = userRepository;
//    }

    @Override
    public SysUser register(SysUser userToAdd) {
        final String username = userToAdd.getUsername();
        if(userRepository.findByUsername(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
//        userToAdd.setRoles(asList("ROLE_USER"));
        return userRepository.save(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null){
          return null;
        }

        String token = TokenUtils.createToken(userDetails);
        redisUtils.set(token, JSON.toJSONString(userDetails),30*60);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
//        final String token = oldToken.substring(tokenHead.length());
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        SysUser user = (SysUser) userDetailsService.loadUserByUsername(username);
//
//
//
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
//
//
//            String s = jwtTokenUtil.refreshToken(token);
//            System.out.println(token);
//            System.out.println(s);
//
//            return s;
//        }
        return null;
    }
}
