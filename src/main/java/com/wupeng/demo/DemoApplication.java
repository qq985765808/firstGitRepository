package com.wupeng.demo;

import com.wupeng.demo.pojo.OrderInfo;
import com.wupeng.demo.service.OrderInfoService;
import com.wupeng.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;


@SpringBootApplication
@Controller
@RequestMapping(value = "index")
public class DemoApplication {

    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderInfoService orderInfoService;

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

    @RequestMapping(value = "saveOrder")
    public  Object saveOrder(
            @RequestParam(value = "orderId",required = false)Long  orderId,
            @RequestParam(value = "orderSysNum",required = false)String orderSysNum,
            Model model
    ){
        if(orderId != null && !"".equals(orderSysNum) &&  orderSysNum!=null){
            OrderInfo orderInfo  = new OrderInfo();
            orderInfo.setOrderId(orderId);
            orderInfo.setOrderSysNum(orderSysNum);
            redisService.set(orderId+"",orderInfo);
            model.addAttribute("msg","已经保存到redis缓存里面去了！");
            model.addAttribute("status",true);
            return  "index";
        }
        model.addAttribute("msg","登录失败,参数错误！");
        model.addAttribute("status",false);
        return "error";
    }

    @RequestMapping(value = "getOrderInfoById")
    public  Object getOrderInfoById(
            @RequestParam(value = "orderId",required = false)Long  orderId,
            Model model
    ){
        if(orderId!=null){
            OrderInfo orderInfo = (OrderInfo) redisService.get(orderId+"");
            if(orderInfo==null){
                model.addAttribute("msg","查询失败,无此订单id！");
                model.addAttribute("status",false);
                return "error";
            }
            model.addAttribute("msg","订单id："+orderInfo.getOrderId()+"订单号:"+orderInfo.getOrderSysNum()+"");
            model.addAttribute("status",true);
            return  "index";
        }
        model.addAttribute("msg","登录失败,参数错误！");
        model.addAttribute("status",false);
        return "error";
    }

    @RequestMapping(value = "saveOrderInfo")
    public  Object saveOrderInfo(
            @RequestParam(value = "orderSysNum",required = false)String orderSysNum,
            @RequestParam(value = "orderName",required = false)String orderName,
            Model model
    ){
        if (orderSysNum!=null && orderName!=null ){
             OrderInfo orderInfo = new OrderInfo();
             orderInfo.setOrderName(orderName);
             orderInfo.setOrderSysNum(orderSysNum);
             orderInfo.setOrderNum(1);
             orderInfo.setOrderPrice(BigDecimal.TEN);
             orderInfo.setOrderTime(new Date());
             orderInfo.setOrderStatus(OrderInfo.ORDERSTATUS_SUCCESS);
             orderInfoService.saveOrderInfo(orderInfo);
            redisService.set(orderInfo.getOrderId()+"",orderInfo);
            model.addAttribute("msg","已经保存到redis缓存和数据库里面去了！");
            model.addAttribute("status",true);
            return  "index";

        }
        model.addAttribute("msg","保存订单失败,参数错误！");
        model.addAttribute("status",false);
        return "error";
    }



}
