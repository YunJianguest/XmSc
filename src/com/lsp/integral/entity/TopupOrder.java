package com.lsp.integral.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * 充值订单
 * @author lsp
 *
 */
public class TopupOrder extends ReflectionDBObject{

	private String custid;
	/**
	 * 充值账户
	 */
	private String fromid;
	/**
	 * 第三方订单编号
	 */
	private String fromorderid;
	/**
	 * 金额
	 */
	private double price;
	/**
	 * 0发起申请1充值成功2充值失败
	 */
	private int state;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 发起时间
	 */
	private Date createdate;
	/**
	 * 处理时间
	 */
	private Date updatedate;
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}  
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getFromorderid() {
		return fromorderid;
	}
	public void setFromorderid(String fromorderid) {
		this.fromorderid = fromorderid;
	}
	
 
	
}
