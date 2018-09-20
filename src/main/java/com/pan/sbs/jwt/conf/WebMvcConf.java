//package com.pan.sbs.jwt.conf;
//
//import com.pan.sbs.jwt.core.ResultGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.logging.Log;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * @PckageName com.pan.sbs.jwt.conf
// * @ClassName WebMvcConfigurer
// * @Description TODO
// * @Author pzg
// * @Date 2018/9/20
// * @Version 1.0.0
// * @Remark
// **/
//@Slf4j
//@Configuration
//public class WebMvcConf implements WebMvcConfigurer {
//    /**
//     * 解决跨域问题
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // **代表所有路径
//                        .allowedOrigins("*") // allowOrigin指可以通过的ip，*代表所有，可以使用指定的ip，多个的话可以用逗号分隔，默认为*
//                         .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE") // 指请求方式 默认为*
//                         .allowCredentials(false) // 支持证书，默认为true
//                         .maxAge(3600) // 最大过期时间，默认为-1
//                         .allowedHeaders("*");
//    }
//
//    /**
//     * 拦截器
//     * @param registry
//     */
////    @Override
////    public void addInterceptors(InterceptorRegistry registry){
////    }
//
//    /**
//     * 这里配置视图解析器
//     * @param registry
//     */
////    @Override
////    public void configureViewResolvers(ViewResolverRegistry registry){
////    }
//
//    /**
//     *  配置内容裁决的一些选项
//     * @param configurer
//     */
////    @Override
////    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
////    }
//
//    /**
//     * 视图跳转控制器
//     * @param registry
//     */
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry){
////    }
//
//    /**
//     * 静态资源处理
//     * @param registry
//     */
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry){
////    }
//
//    /**
//     * 默认静态资源处理器
//     * @param configurer
//     */
////    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
////    }
//
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(new HandlerExceptionResolver() {
//            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
////                                 if (e instanceof ServiceExceptio) {//业务失败的异常，如“账号或密码错误”
////                                         result = new ResponseMsg("501", "业务层出错：" + e.getMessage());
////                                         logger.info(e.getMessage());
////                                     } else
//                                    if (e instanceof NoHandlerFoundException) {
//                                        ResultGenerator.getFailResult("404 接口 [" + request.getRequestURI() + "] 不存在");
//                                    } else {
//                                        ResultGenerator.getFailResult("500接口 [" + request.getRequestURI() + "] 错误，请联系管理员！");
//
//                                         String message;
//                                         if (handler instanceof HandlerMethod) {
//                                                 HandlerMethod handlerMethod = (HandlerMethod) handler;
//                                                 message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
//                                                                request.getRequestURI(),
//                                                                 handlerMethod.getBean().getClass().getName(),
//                                                                handlerMethod.getMethod().getName(),
//                                                                e.getMessage());
//                                            } else {
//                                                 message = e.getMessage();
//                                         }
//                                         log.error(message, e);
//                                     }
//
//                return null;
//            }
//        });
//    }
//
//}
