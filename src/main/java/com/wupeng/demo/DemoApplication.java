package com.wupeng.demo;

import com.wupeng.demo.pojo.OrderInfo;
import com.wupeng.demo.pojo.SeckillingActivity;
import com.wupeng.demo.pojo.UserInfo;
import com.wupeng.demo.repository.SeckillingActivityRepository;
import com.wupeng.demo.service.OrderInfoService;
import com.wupeng.demo.service.RedisService;
import com.wupeng.demo.service.SeckillingActivityService;
import com.wupeng.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@Controller
@RequestMapping(value = "index")
public class DemoApplication {

    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SeckillingActivityService seckillingActivityService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "getIndex")
    public Object getLogin(
            @RequestParam(value ="userName",required = false)String userName,
            @RequestParam(value ="password",required = false)String password,
            Model model
    ){
        if(userName!=null && password!=null
                && userInfoService.getUserInfoByUserNameAndPassword(userName,password).size()>0 ){
            redisService.set("userLogin",userName);
            model.addAttribute("msg","登录成功");
            model.addAttribute("status",true);
            return  "index";
        }
        model.addAttribute("msg","登录失败,账号或者密码错误！");
        model.addAttribute("status",false);
        return "error";
    }
    @RequestMapping(value = "saveOrder")
    public  Object saveOrder(@ModelAttribute OrderInfo orderInfo,
            Model model
    ){
        if(orderInfo != null  && orderInfo.getOrderSysNum()!=null){
            orderInfo.setOrderTime(new Date());
            orderInfoService.saveOrderInfo(orderInfo);
            redisService.set(orderInfo.getOrderId()+"",orderInfo);
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

    @RequestMapping(value = "getOrderInfoAll")
    public Object getOrderInfoAll(
            Model model
    ){
        model.addAttribute("orderList",orderInfoService.findAll());
        return "orderInfo";
    }

    @RequestMapping(value = "getOrderInfoDtoDate")
    public Object getOrderInfoDtoDate(
            Model model
    ){
        System.out.println(orderInfoService.getOrderInfoNameAndTime().toString());
       model.addAttribute("orderDtoList",orderInfoService.getOrderInfoNameAndTime());
        return "orderDto";
    }


    @RequestMapping(value = "saveUserInfo")
    public  Object saveUserInfo(@ModelAttribute UserInfo userInfo,
                             Model model
    ){
        if(userInfo != null  && userInfo.getUserName()!=null && userInfo.getPassword()!=null){
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            userInfoService.saveUserInfo(userInfo);
            redisService.set(userInfo.getUserId()+"",userInfo);
            model.addAttribute("msg","已经保存到redis缓存和数据库里面去了！");
            model.addAttribute("status",true);
            return  "index";
        }
        model.addAttribute("msg","新增用户信息失败,参数错误！");
        model.addAttribute("status",false);
        return "error";
    }

    @RequestMapping(value = "getSeckillingActivity")
    public Object getSeckillingActivity(
            @RequestParam(value = "seckillingActivityId",required = false)Long seckillingActivityId,
            Model model
    ){
        if(seckillingActivityId!=null && !"".equals(seckillingActivityId)){
            model.addAttribute("seckillingActivityList",seckillingActivityService.getSeckillingActivityById(seckillingActivityId));
        }else{
            model.addAttribute("seckillingActivityList",seckillingActivityService.findAll());
        }
       return  "seckillingActivity";
    }

    @RequestMapping(value = "saveSeckillingActivity")
    @ResponseBody
    public Object saveSeckillingActivity(
            SeckillingActivity seckillingActivity
    ){
        Map<String,Object> map = new HashMap<>();
        try{
            seckillingActivity.setSeckillingActivityBeginTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            seckillingActivity.setSeckillingActivityEndTime(sdf.parse("2019-09-01 23:59:59"));
            seckillingActivityService.save(seckillingActivity);
            redisService.set("seckillingActivityId="+seckillingActivity.getSeckillingActivityId(),seckillingActivity);
            map.put("msg","新增信息成功");
            map.put("status",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","新增信息失败");
            map.put("status",false);
        }
        return map;
    }

    @RequestMapping(value = "/updateSeckillingActivityProductsNumSubOneById")
    @ResponseBody
    public Object updateSeckillingActivityProductsNumSubOneById(
           @RequestParam(value = "seckillingActivityId",required = false)Long  seckillingActivityId
    ){
        Map<String,Object> map = new HashMap<>();
        if(seckillingActivityService.updateSeckillingActivityProductsNumSubOneById(seckillingActivityId)>0){
            map.put("msg","亲，下单成功，马上商品发货。");
            map.put("statuc",true);
        }else{
            map.put("msg","亲，下单失败，商品库存不足");
            map.put("statuc",false);
        }
        return map;
    }


    @RequestMapping(value = "/updateRedisCache")
    @ResponseBody
    public   Object  updateRedisCache(Long  seckillingActivityId
    ){
        Map<String,Object> map = new HashMap<>();
        SeckillingActivity seckillingActivity = null;
        synchronized(seckillingActivityId){
            try{
                seckillingActivity = (SeckillingActivity) redisService.get("seckillingActivityId=" + seckillingActivityId);
                if (seckillingActivity != null && seckillingActivity.getSeckillingActivityProductsNum() > 0) {
                    seckillingActivity.setSeckillingActivityProductsNum(seckillingActivity.getSeckillingActivityProductsNum() - 1);
                    redisService.set("seckillingActivityId=" + seckillingActivity.getSeckillingActivityId(), seckillingActivity);
                    map.put("msg", "亲，恭喜你已经抢到了商品，静等商品发货，谢谢！");
                    map.put("status", true);
                } else {
                    map.put("msg", "亲，此秒杀活动商品库存不足，请留意最近的的秒杀活动，谢谢！");
                    map.put("status", false);
                }

            }catch (Exception e){
                map.put("msg","亲，此秒杀活动信息已失效了，请留意最近的的秒杀活动，谢谢！");
                map.put("status",false);
            }
        }


        return map;
    }
}
