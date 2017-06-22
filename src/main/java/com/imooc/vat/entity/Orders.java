package com.imooc.vat.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private Integer orderId;

    private String shipmentsNo;

    private BigDecimal totalPrice;

    private String custName;

    private String orderType;

    private String mobile;

    private Date createTime;

    private Date payTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShipmentsNo() {
        return shipmentsNo;
    }

    public void setShipmentsNo(String shipmentsNo) {
        this.shipmentsNo = shipmentsNo == null ? null : shipmentsNo.trim();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}