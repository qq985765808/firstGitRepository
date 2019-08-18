package com.wupeng.demo.config;

import com.wupeng.demo.interceptor.PassInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    //viewResolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //viewResolver.setPrefix("/WEB-INF/classes/templates/");//打war后默认编译的路径
        viewResolver.setPrefix("/WEB-INF/templates/");//使用tomcat7:run插件后要放的位置
        viewResolver.setSuffix(".html");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * addPathPatterns：需要拦截的访问路径
         * excludePathPatterns：不需要拦截的路径，String数组类型可以写多个用","分割
         * */
        registry.addInterceptor(new PassInterceptor()).excludePathPatterns("/static/**").excludePathPatterns("/index/getIndex","\n" +
                "/index/saveRecordIpInfo","/index.html");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 设置默认的首页html页面
         * */
        registry.addViewController( "/index" ).setViewName( "index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }
}
