package com.wupeng.demo.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class SeckillingActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seckilling_activity_id")
    private  Long  seckillingActivityId;
    @Column(name = "seckilling_activity_name")
    private  String seckillingActivityName;
    @Column(name = "products_id")
    private  Long   productsId;
    @Column(name = "seckilling_activity_beginTime")
    private  Date   seckillingActivityBeginTime;
    @Column(name = "seckilling_activity_endTime")
    private  Date   seckillingActivityEndTime;
    @Column(name = "seckilling_activity_productsNum")
    private  Integer seckillingActivityProductsNum;
    @Column(name = "seckilling_activity_introduction")
    private  String  seckillingActivityIntroduction;


    public Long getSeckillingActivityId() {
        return seckillingActivityId;
    }

    public void setSeckillingActivityId(Long seckillingActivityId) {
        this.seckillingActivityId = seckillingActivityId;
    }

    public String getSeckillingActivityName() {
        return seckillingActivityName;
    }

    public void setSeckillingActivityName(String seckillingActivityName) {
        this.seckillingActivityName = seckillingActivityName;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public Date getSeckillingActivityBeginTime() {
        return seckillingActivityBeginTime;
    }

    public void setSeckillingActivityBeginTime(Date seckillingActivityBeginTime) {
        this.seckillingActivityBeginTime = seckillingActivityBeginTime;
    }

    public Date getSeckillingActivityEndTime() {
        return seckillingActivityEndTime;
    }

    public void setSeckillingActivityEndTime(Date seckillingActivityEndTime) {
        this.seckillingActivityEndTime = seckillingActivityEndTime;
    }

    public Integer getSeckillingActivityProductsNum() {
        return seckillingActivityProductsNum;
    }

    public void setSeckillingActivityProductsNum(Integer seckillingActivityProductsNum) {
        this.seckillingActivityProductsNum = seckillingActivityProductsNum;
    }

    public String getSeckillingActivityIntroduction() {
        return seckillingActivityIntroduction;
    }

    public void setSeckillingActivityIntroduction(String seckillingActivityIntroduction) {
        this.seckillingActivityIntroduction = seckillingActivityIntroduction;
    }


}
