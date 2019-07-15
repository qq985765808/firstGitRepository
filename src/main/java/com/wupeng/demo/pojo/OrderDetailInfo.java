package com.wupeng.demo.pojo;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tab_order_detail_info")
public class OrderDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private  Long orderDetailId;
    @Column(name = "products_id")
    private  Long productsId;
    @Column(name = "products_name")
    private  String productsName;
    @Column(name = "products_price")
    private BigDecimal productsPrice;
    @Column(name = "order_detail_remarks")
    private  String orderDetailRemarks;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public BigDecimal getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(BigDecimal productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getOrderDetailRemarks() {
        return orderDetailRemarks;
    }

    public void setOrderDetailRemarks(String orderDetailRemarks) {
        this.orderDetailRemarks = orderDetailRemarks;
    }
}
