package com.wupeng.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "index")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "getIndex")
    public Object getLogin(
            @RequestParam(value ="userId",required = false)Long userId,
            @RequestParam(value ="password",required = false)String password
    ){
        if(userId!=null && userId==1
                && password!=null &&  "123456".equals(password)){
            return  "index";
        }
        return "error";
    }


}
