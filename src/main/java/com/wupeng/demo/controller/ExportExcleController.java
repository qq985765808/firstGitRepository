package com.wupeng.demo.controller;

import com.wupeng.demo.dto.OrderDto;
import com.wupeng.demo.service.OrderInfoService;
import com.wupeng.demo.util.ExportExcelUtil;
import com.wupeng.demo.util.ExportExcelWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;


/**
 * 导出Excel控制器</br>
 * 注:可以封装使用DTO类作为数据传输对象
 * */
@Controller
@RequestMapping(value = "/exportExcel")
public class ExportExcleController implements Serializable {

    private static final long serialVersionUID = 1L;
    private  final  static Log log = LogFactory.getLog(ExportExcleController.class);

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/getOrderInfoExcel")
    public  void  getUserInfoExcel(HttpServletRequest request, HttpServletResponse response)throws  Exception{
        //设置第一行列名
        String[] columnNames  = {"订单id","系统单号","下单时间"};
        //设置文件名称
        String fileName = "测试";
        ExportExcelWrapper<OrderDto> dto = new ExportExcelWrapper<>();
        dto.exportExcel(fileName, fileName, columnNames, orderInfoService.getOrderInfoNameAndTime(), response, ExportExcelUtil.EXCEL_FILE_2003);
    }

}
