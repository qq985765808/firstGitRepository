package com.wupeng.demo.config;

import com.wupeng.demo.interceptor.PassInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * addPathPatterns：需要拦截的访问路径
         * excludePathPatterns：不需要拦截的路径，String数组类型可以写多个用","分割
         * */
        registry.addInterceptor(new PassInterceptor()).excludePathPatterns("/**").excludePathPatterns("/static/**");
    }


}
