package com.wupeng.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * 网络控制器
 * @author  king
 * */
@Controller
@RequestMapping(value = "/network")
public class RecordIpController implements Serializable {

    private static final long serialVersionUID = 1L;
    private  final  static Log log = LogFactory.getLog(RecordIpController.class);

    @RequestMapping
    public  Object  getNetWorkView(Model model){
        return  "network";
    }


}
