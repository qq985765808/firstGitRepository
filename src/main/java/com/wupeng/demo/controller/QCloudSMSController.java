package com.wupeng.demo.controller;

import com.wupeng.demo.service.RedisService;
import com.wupeng.demo.sms.QCloudSMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * QCloudSMS控制器
 * */
@Controller
@RequestMapping(value = "/sms")
public class QCloudSMSController  implements Serializable {

    private static final long serialVersionUID = 1L;
    private  final  static Log log = LogFactory.getLog(QCloudSMS.class);

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/sendVerificationCode")
    @ResponseBody
    public  Object sendVerificationCode(
            @RequestParam(value = "phoneNum",required = false)String phoneNum,
            @RequestParam(value = "code",required = false)String code
    ){
        Map<String,Object> map = new HashMap<>();
        String result = QCloudSMS.sendVerificationCodeSMS(phoneNum,code);
        if(result.equals("SUCCESS")){
            redisService.set(phoneNum,code,600l, TimeUnit.SECONDS);
            map.put("msg","验证码短信发送成功。");
            map.put("returnMsg","此验证码十分钟有效,请尽快验证");
            map.put("status",result);
        }else if(result.equals("ERROR")){
            map.put("msg","验证码短信发送失败。");
            map.put("status",result);
        }else{
            map.put("msg","验证码短信发送失败，服务器异常！");
            map.put("status",result);
        }
        return  map;
    }


    @RequestMapping(value = "/sendNoticeSMS")
    @ResponseBody
    public  Object sendNoticeSMS(
            @RequestParam(value = "phoneNum",required = false)String phoneNum,
            @RequestParam(value = "message",required = false)String message
    ){
        Map<String,Object> map = new HashMap<>();
        String result = QCloudSMS.sendNoticeSMS(phoneNum,message);
        if(result.equals("SUCCESS")){
            map.put("msg","推广短信发送成功。");
            map.put("status",result);
        }else if(result.equals("ERROR")){
            map.put("msg","推广短信发送失败。");
            map.put("status",result);
        }else{
            map.put("msg","推广短信发送失败，服务器异常！");
            map.put("status",result);
        }
        return  map;
    }


}
