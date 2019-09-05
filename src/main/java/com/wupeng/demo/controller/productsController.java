package com.wupeng.demo.controller;


import com.wupeng.demo.support.base.BaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;


@Controller
@RequestMapping(value = "/products")
public class productsController  implements Serializable {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(BaseController.class);


    @RequestMapping
    public String getProductsView(){
        return "productsShow";
    }

}
