package com.wupeng.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author  king
 * 自定义拦截器
 * */
public class PassInterceptor implements HandlerInterceptor {

    @Override
   public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        System.out.println(url);
        return true;
    }

}
