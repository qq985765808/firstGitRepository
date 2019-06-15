package com.wupeng.demo.interceptor;

import com.wupeng.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author  king
 * 自定义拦截器
 * */
public class PassInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;


    @Override
   public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        System.out.println(url);
        // url.indexOf("getIndex")==-1

        if(url!=null ){
            try{
                if( redisService.get("userLogin")==null){
                    response.sendRedirect("/index/getIndex");
                    return false;
                }
                return   true;
            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/index/getIndex");
                return  false;
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }



}
