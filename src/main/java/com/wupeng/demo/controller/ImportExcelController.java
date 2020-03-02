package com.wupeng.demo.controller;

import com.wupeng.demo.dto.OrderDto;

import com.wupeng.demo.util.ImportExcelUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

@Controller

@RequestMapping(value = "/importExcel")
public class ImportExcelController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(ImportExcelController.class);

    @RequestMapping(value = "/importExcelByOrder")
    @ResponseBody
    public  Object importExcelByOrder(){
        ImportExcelUtil<OrderDto> order = new ImportExcelUtil<OrderDto>(new OrderDto());
        try {
//            Class c = GenericsUtil.getSuperClassGenricType(ImportExcelUtil.class,0);

            File file = new File("F:\\迅雷下载\\测试.xls");
            //List<OrderDto> list = order.importExcel(file);
            return  order.importExcel(file);
        }catch (Exception e){
           e.printStackTrace();
        }
        return  "test";
    }


}
