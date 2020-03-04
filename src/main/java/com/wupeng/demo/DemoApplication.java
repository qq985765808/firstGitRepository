package com.wupeng.demo;

import com.wupeng.demo.pojo.OrderInfo;
import com.wupeng.demo.pojo.RecordIp;
import com.wupeng.demo.pojo.SeckillingActivity;
import com.wupeng.demo.pojo.UserInfo;

import com.wupeng.demo.service.*;
import com.wupeng.demo.support.base.BaseController;
import com.wupeng.demo.support.service.GeocodingService;
import com.wupeng.demo.util.HttpUtil;
import com.wupeng.demo.vo.Login;
import com.wupeng.demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@ServletComponentScan
@Controller
@RequestMapping(value = "index")
public class DemoApplication  extends SpringBootServletInitializer  implements Serializable {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            return builder.sources(DemoApplication.class);
    }

    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SeckillingActivityService seckillingActivityService;
    @Autowired
    private RecordIpService recordIpService;
    @Autowired
    private GeocodingService geocodingService;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "getIndex")
    public Object getLogin(
            @RequestParam(value = "userName",required = false) String userName,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam(value = "recordIpUrl",required = false)String recordIpUrl,
            @RequestParam(value = "recordIpAddress",required = false)String recordIpAddress,
            @RequestParam(value = "recordIpBrower",required = false)String recordIpBrower,
            Model model
    ){
        if(userName!=null && password!=null
                && userInfoService.getUserInfoByUserNameAndPassword(userName,password).size()>0 ){
            redisService.set("userLogin",userName);
            model.addAttribute("msg","登录成功");
            model.addAttribute("status",true);
            return  "index";
        }
        if(recordIpUrl!=null && !"".equals(recordIpUrl)){
            RecordIp recordIp = new RecordIp();
            recordIp.setRecordIpUrl(recordIpUrl);
            recordIp.setRecordIpAddress(recordIpAddress);
            recordIp.setRecordIpBrower(recordIpBrower);
            recordIp.setRecordIpCreateTime(new Date());
            recordIpService.saveEntity(recordIp);
            model.addAttribute("msg","保存ip信息成功 ");
            model.addAttribute("status",true);
        }
/*        model.addAttribute("msg","登录失败,账号或者密码错误！");
        model.addAttribute("status",false);
        return "error";*/
       return  "index";
    }

    @RequestMapping(value = "/login")
    public  Object userLogin(
            @ModelAttribute Login login,
            Model model
            ){
        if(login.getUserName()!=null && login.getPassword()!=null &&
                userInfoService.getUserInfoByUserNameAndPassword(login.getUserName(),login.getPassword()).size()>0){
            redisService.set("userLogin",login.getUserName());
            model.addAttribute("msg","登录成功");
            model.addAttribute("status",true);
            return  "productsShow";
        }
        return  "index";
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

    @RequestMapping(value = "/saveRecordIpInfo")
    @ResponseBody
    public  Object  saveRecordIpInfo(
            @RequestParam(value = "recordIpUrl",required = false)String recordIpUrl,
            @RequestParam(value = "recordIpAddress",required = false)String recordIpAddress,
            @RequestParam(value = "recordIpBrower",required = false)String recordIpBrower
    ){
        Map<String,Object> map = new HashMap<>();
        if (recordIpUrl!=null && !"".equals(recordIpUrl)){
            RecordIp recordIp = new RecordIp();
            recordIp.setRecordIpUrl(recordIpUrl);
            recordIp.setRecordIpAddress(recordIpAddress);
            recordIp.setRecordIpBrower(recordIpBrower);
            recordIp.setRecordIpCreateTime(new Date());
            recordIpService.saveEntity(recordIp);
            map.put("msg","保存ip信息成功");
            map.put("status",true);
        }
        map.put("msg","记录ip信息为空");
        map.put("status",false);
        return map;
    }

    @RequestMapping(value = "/registered")
    public  Object registered(Model model){
        System.out.println("ss");
        return "registered";
    }

    @RequestMapping(value = "/productsShow")
    public  Object productsShow(Model model){
        System.out.println("ss");
        return "productsShow";
    }

    @RequestMapping(value = "/updateUserInfo")
    public  Object updateUserInfo(Model model){

        return "updateUserInfo";
    }

    @RequestMapping(value = "/updateUserInfoByUserName")
    @ResponseBody
    public  Object  updateUserInfoByUserName(
            UserInfoVO userInfoVO
    ){
        Map<String,Object> map = new HashMap<>();
        if(userInfoVO!=null && userInfoVO.getUserName()!=null && userInfoVO.getPassword()!=null){
            if(userInfoService.getUserInfoByUserNameAndPassword(userInfoVO.getUserName(),userInfoVO.getPassword()).size()==0){
                map.put("msg","用户名或者密码错误，修改失败。");
                map.put("status",false);
                return  map;
            }
            if (userInfoService.updateUserInfoByUserName(userInfoVO.getResetPassword(),userInfoVO.getUserName())==0){
                map.put("msg","新密码错误，修改失败。");
                map.put("status",false);
                return  map;
            }
            map.put("msg","新密码修改成功。");
            map.put("status",true);
    }
        return  map;
    }

    @RequestMapping(value = "/getMain")
    public  Object getMainView(Model model){
        model.addAttribute("userName",redisService.get("userLogin")+",欢迎您");
        return  "main";
    }

    @RequestMapping(value = "/getMapView")
    public  Object getMapView(
            Model model){
        return  "map";
    }

    @RequestMapping(value = "/getMapParam")
    @ResponseBody
    public  Object getMapParam(){

        return geocodingService.getMapResult();
    }


    @RequestMapping(value = "/testListString")
    public  Object  testListString(Model model){
        List<String> list =  new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        model.addAttribute("key",list);
        return "orderDto";
    }

}
