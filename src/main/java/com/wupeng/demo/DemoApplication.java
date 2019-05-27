package com.wupeng.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@Controller
@RequestMapping(value = "index")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "getIndex")
    public Object getLogin(
            @RequestParam(value ="userId",required = false)Long userId,
            @RequestParam(value ="password",required = false)String password,
            Model model
    ){
        if(userId!=null && userId==1 && password!=null &&  "123456".equals(password)){
            model.addAttribute("msg","登录成功");
            model.addAttribute("status",true);
            return  "index";
        }
        model.addAttribute("msg","登录失败,账号或者密码错误！");
        model.addAttribute("status",false);
        return "error";
    }


}
