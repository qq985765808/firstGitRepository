package com.wupeng.demo.interceptor;

import com.wupeng.demo.util.Token;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Token拦截器实现类
 * */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod  = (HandlerMethod) handler;
            Method method =  handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation!=null){
                boolean needSaveSession = annotation.save();
                if (needSaveSession){
                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession){
                    if (isRepeatSubmit(request)){
                        return false;
                    }
                    request.getSession(false).removeAttribute("token");
                }
            }
            return  true;
        }else {
            return  super.preHandle(request,response,handler);
        }
    }

    private  boolean isRepeatSubmit(HttpServletRequest request){
        String severToken = (String) request.getSession(false).getAttribute("token");
        if(severToken==null){
            return true;
        }
        String clientToken = request.getParameter("token");
        if (clientToken==null){
            return  true;
        }
        if (!severToken.equals(clientToken)){
            return  true;
        }
        return  false;
    }

}
