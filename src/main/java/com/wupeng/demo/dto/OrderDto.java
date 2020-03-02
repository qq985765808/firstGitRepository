package com.wupeng.demo.dto;

import java.io.Serializable;
import java.util.Date;


public class OrderDto  {

    private  Long orderId;

    private  String orderSysNum;

    private  Date  orderTime;



    public OrderDto (){
        super();
    }

    public OrderDto(Long orderId, String orderSysNum, Date orderTime) {
        this.orderId = orderId;
        this.orderSysNum = orderSysNum;
        this.orderTime = orderTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSysNum() {
        return orderSysNum;
    }

    public void setOrderSysNum(String orderSysNum) {
        this.orderSysNum = orderSysNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", orderSysNum='" + orderSysNum + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
