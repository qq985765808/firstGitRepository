package com.wupeng.demo.controller;

import com.wupeng.demo.util.NetWorkUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController  implements Serializable {

    private static final long serialVersionUID = 1L;
    private  final  static Log log = LogFactory.getLog(UserInfoController.class);

    @RequestMapping
    public  Object getUserInfoView(
            HttpServletRequest request,
            Model model){
        log.info("访问IP为："+ NetWorkUtil.getIpAddress(request));
        model.addAttribute("info","此页面正在开发中.......");
        return "userInfo";
    }


}
