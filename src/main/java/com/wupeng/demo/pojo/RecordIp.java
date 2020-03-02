package com.wupeng.demo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 记录访问ip信息
 * */

@Entity
@Table(name = "network.tab_record_ip",schema = "root")
public class RecordIp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_ip_id")
    private  Long recordIpId;
    @Column(name = "record_ip_url")
    private  String recordIpUrl;
    @Column(name = "record_ip_address")
    private  String recordIpAddress;
    @Column(name = "record_ip_brower")
    private  String recordIpBrower;
    @Column(name = "record_ip_lng")
    private  String recordIpLng;
    @Column(name = "record_ip_lat")
    private  String recordIpLat;
    @Column(name = "record_ip_postCode")
    private  String recordIpPostCode;
    @Column(name = "record_ip_createTime")
    private Date recordIpCreateTime;


    public Long getRecordIpId() {
        return recordIpId;
    }

    public void setRecordIpId(Long recordIpId) {
        this.recordIpId = recordIpId;
    }

    public String getRecordIpUrl() {
        return recordIpUrl;
    }

    public void setRecordIpUrl(String recordIpUrl) {
        this.recordIpUrl = recordIpUrl;
    }

    public String getRecordIpAddress() {
        return recordIpAddress;
    }

    public void setRecordIpAddress(String recordIpAddress) {
        this.recordIpAddress = recordIpAddress;
    }

    public String getRecordIpBrower() {
        return recordIpBrower;
    }

    public void setRecordIpBrower(String recordIpBrower) {
        this.recordIpBrower = recordIpBrower;
    }

    public String getRecordIpLng() {
        return recordIpLng;
    }

    public void setRecordIpLng(String recordIpLng) {
        this.recordIpLng = recordIpLng;
    }

    public String getRecordIpLat() {
        return recordIpLat;
    }

    public void setRecordIpLat(String recordIpLat) {
        this.recordIpLat = recordIpLat;
    }

    public String getRecordIpPostCode() {
        return recordIpPostCode;
    }

    public void setRecordIpPostCode(String recordIpPostCode) {
        this.recordIpPostCode = recordIpPostCode;
    }

    public Date getRecordIpCreateTime() {
        return recordIpCreateTime;
    }

    public void setRecordIpCreateTime(Date recordIpCreateTime) {
        this.recordIpCreateTime = recordIpCreateTime;
    }

    @Override
    public String toString() {
        return "RecordIP{" +
                "recordIpId=" + recordIpId +
                ", recordIpUrl='" + recordIpUrl + '\'' +
                ", recordIpAddress='" + recordIpAddress + '\'' +
                ", recordIpBrower='" + recordIpBrower + '\'' +
                ", recordIpLng='" + recordIpLng + '\'' +
                ", recordIpLat='" + recordIpLat + '\'' +
                ", recordIpPostCode='" + recordIpPostCode + '\'' +
                ", recordIpCreateTime='" + recordIpCreateTime + '\'' +
                '}';
    }
}
