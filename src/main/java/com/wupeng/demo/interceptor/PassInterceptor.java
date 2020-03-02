package com.wupeng.demo.interceptor;

import com.wupeng.demo.service.RedisService;
import com.wupeng.demo.util.NetWorkUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author  king
 * 自定义拦截器
 * */
@Component
public class PassInterceptor implements HandlerInterceptor {

     private  final static Log log = LogFactory.getLog(PassInterceptor.class);

    @Autowired
    private RedisService redisService;

    public static  PassInterceptor passInterceptor;

    @PostConstruct
    public  void init(){
        passInterceptor = this;
        passInterceptor.redisService =  this.redisService;
    }

    @Override
   public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        //System.out.println(url);
        log.info("请求路径为："+url);
        log.info("访问IP为："+ NetWorkUtil.getIpAddress(request));
        // url.indexOf("getIndex")==-1

        if(url!=null ){
            try{
                if( passInterceptor.redisService.get("userLogin")==null){
                    response.sendRedirect("/firstDemo/index/getIndex"); //war包版本的路径
                    //response.getOutputStream().write("Ne rajtigita".getBytes());
                    //response.sendRedirect("/index/getIndex");
                    return false;
                }
                return   true;
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/firstDemo/index/getIndex");//war包版本的路径
                //response.sendRedirect("/index/getIndex");
                return  false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("postHandle...");
        log.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("afterCompletion...");
        log.info("afterCompletion...");
    }



}
