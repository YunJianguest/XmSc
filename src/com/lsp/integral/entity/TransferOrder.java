package com.lsp.integral.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * 转账订单
 * @author lsp
 *
 */
public class TransferOrder extends ReflectionDBObject{

	private String custid;
	/**
	 * 转出账户
	 */
	private String fromid;
	/**
	 * 转入账户
	 */
	private String toid;
	/**
	 * 转账金额
	 */
	private double price;
	/**
	 * 0发起申请1转账成功2转账失败
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
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
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
	
 
	
}
