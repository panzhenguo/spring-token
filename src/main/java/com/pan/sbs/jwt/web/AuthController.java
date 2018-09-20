package com.pan.sbs.jwt.web;

import com.pan.sbs.jwt.core.Result;
import com.pan.sbs.jwt.core.ResultGenerator;
import com.pan.sbs.jwt.pojo.SysUser;
import com.pan.sbs.jwt.service.AuthService;
import com.pan.sbs.jwt.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping(value = "${crm.login.url}", method = RequestMethod.POST)
    public Result createAuthenticationToken(HttpServletRequest request,String userName, String password) throws AuthenticationException {

        String token = request.getHeader("token");

        if(token!=null){
            String tokeVaule = redisUtils.getString(token);
            if(!StringUtils.isEmpty(tokeVaule)){
                redisUtils.setExpire(token,30*60);
                return ResultGenerator.getSuccessResult(token);
            }
        }

        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return ResultGenerator.getFailResult("账号或密码错误！");
        }

        String loginToken = authService.login(userName, password);
        if(StringUtils.isEmpty(loginToken)){
            return ResultGenerator.getFailResult("账号或密码错误！");
        }
        return ResultGenerator.getSuccessResult(loginToken);
    }


    public static String token = null;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/auth/add", method = RequestMethod.GET)


    public Result addUser() {
        stringRedisTemplate.opsForValue().set("zs","1111",60, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("zs"));

//        SysUser user = new SysUser();
//        user.setUsername("zs");
//        user.setPassword("123");
//        if (token == null) {
//            token = jwtTokenUtil.generateToken(user);
//        }
//        System.out.println("token : " + token);
//        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
//        //System.out.println("username : " + usernameFromToken);
//        lastData = jwtTokenUtil.getCreatedDateFromToken(token);
//        //System.out.println(createdDateFromToken);
//        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        //System.out.println(jwtTokenUtil);
//        System.out.println("token 初始化时间：" + format.format(lastData) + "-------------" + format.format(jwtTokenUtil.getExpirationDateFromToken(token)));
        return null;
    }

    @RequestMapping(value = "/auth/res", method = RequestMethod.GET)
    public Result resUser() {
        if (token != null) {
            System.out.println(token);
           // String s = jwtTokenUtil.refreshToken(token);
           // System.out.println(s);
            Date d = new Date();
           // boolean f = jwtTokenUtil.canTokenBeRefreshed(token, d);
            //System.out.println(f);


        }
        return null;
    }

    /**
     * @MethodName
     * @Description TODO
     * @Author pzg
     * @Date 2018/9/17
     * @Param
     * @Return
     * @Version 1.0.0
     **/
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public SysUser register(@RequestBody SysUser addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }
}
