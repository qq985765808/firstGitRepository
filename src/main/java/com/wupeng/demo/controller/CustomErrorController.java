package com.wupeng.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常控制器<br></>
 * 自定义错误状态码返回页面
 * */

@Controller
public class CustomErrorController implements ErrorController {

    private final  static Log log = LogFactory.getLog(CustomErrorController.class);

    @RequestMapping(value = "/error")
    public  String handlerError(HttpServletRequest request, Model model){
        //获取statusCode:401 404 500...
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        log.info("响应状态码="+statusCode);
        switch (statusCode){
            case 400:
                model.addAttribute("msg","您好:你的请求信息不合法,谢谢！");
                return "/401";
            case 401:
                model.addAttribute("msg","您好:你的身份没有经过服务器认证,请先认证谢谢！");
                return "/401";
            case 404:
                model.addAttribute("msg","您好:你请求的页面不存在,谢谢！");
                return "/404";
            case 405:
                model.addAttribute("msg","您好:你请求的方法被禁止,请换一种请求方式谢谢！");
                return "/403";
            default:
                model.addAttribute("msg","您好:服务器异常,请稍微等待一下谢谢！");
                return "/500";
        }

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
