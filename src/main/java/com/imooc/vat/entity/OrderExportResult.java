package com.imooc.vat.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.imooc.vat.util.FieldMeta;

public class OrderExportResult {
	@FieldMeta(name = "订单号")
	private String shipmentsNo;
	@FieldMeta(name = "总价")
    private BigDecimal totalPrice;
	@FieldMeta(name = "用户名")
    private String custName;
	@FieldMeta(name = "手机号")
    private String mobile;
	@FieldMeta(name = "创建时间")
    private Date createTime;
	public String getShipmentsNo() {
		return shipmentsNo;
	}
	public void setShipmentsNo(String shipmentsNo) {
		this.shipmentsNo = shipmentsNo;
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
		this.custName = custName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
