package com.wupeng.demo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tab_order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "order_sys_num")
    private String orderSysNum;
    @Column(name = "order_num")
    private Integer orderNum;
    @Column(name = "order_price")
    private BigDecimal orderPrice;
    @Column(name = "products_id")
    private Long productsId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_time")
    private Date orderTime;
    @Column(name = "order_status",length = 2)
    private String orderStatus;

    /** 订单状态：0异常,1正常,2退单*/
    public    static  final String ORDERSTATUS_ERROR="0",ORDERSTATUS_SUCCESS="1",CHAEGEEBACK="2";


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderSysNum() {
        return orderSysNum;
    }

    public void setOrderSysNum(String orderSysNum) {
        this.orderSysNum = orderSysNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
