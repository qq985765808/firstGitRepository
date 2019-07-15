package com.wupeng.demo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name="tab_prepayment_info")
public class PrepaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prepayment_id")
    private  Long prepaymentId;
    @Column(name = "order_id")
    private  Long orderId;
    @Column(name = "prepayment_money")
    private BigDecimal prepaymentMoney;
    @Column(name = "prepayment_total_money")
    private  BigDecimal prepaymentTotalMoney;
    @Column(name = "prepayment_pay_status")
    private  String prepaymentPayStatus;
    @Column(name = "prepayment_remarks")
    private  String prepaymentRemarks;
    @Column(name = "prepayment_pay_type")
    private  String prepaymentPayType;

    public Long getPrepaymentId() {
        return prepaymentId;
    }

    public void setPrepaymentId(Long prepaymentId) {
        this.prepaymentId = prepaymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrepaymentMoney() {
        return prepaymentMoney;
    }

    public void setPrepaymentMoney(BigDecimal prepaymentMoney) {
        this.prepaymentMoney = prepaymentMoney;
    }

    public BigDecimal getPrepaymentTotalMoney() {
        return prepaymentTotalMoney;
    }

    public void setPrepaymentTotalMoney(BigDecimal prepaymentTotalMoney) {
        this.prepaymentTotalMoney = prepaymentTotalMoney;
    }

    public String getPrepaymentPayStatus() {
        return prepaymentPayStatus;
    }

    public void setPrepaymentPayStatus(String prepaymentPayStatus) {
        this.prepaymentPayStatus = prepaymentPayStatus;
    }

    public String getPrepaymentRemarks() {
        return prepaymentRemarks;
    }

    public void setPrepaymentRemarks(String prepaymentRemarks) {
        this.prepaymentRemarks = prepaymentRemarks;
    }

    public String getPrepaymentPayType() {
        return prepaymentPayType;
    }

    public void setPrepaymentPayType(String prepaymentPayType) {
        this.prepaymentPayType = prepaymentPayType;
    }
}
