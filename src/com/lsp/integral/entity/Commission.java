package com.lsp.integral.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * 佣金提现
 * @author lsp
 *
 */
public class Commission extends ReflectionDBObject{

	private String custid;
	/**
	 * 提现人ID
	 */
	private String fromid; 
	/**
	 * 提现类型
	 * 1-银行卡  2-支付宝
	 */
	private int type; 
	private String account;
	private String yname; //所属银行
	/**
	 * 金额
	 */
	private double price;
	/**
	 * 手续费
	 */
	private double cost;
	/**
	 * 0发起申请1申请成功2申请失败3确认打款
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
	/**
	 * 打款时间
	 */
	private Date confirmdate;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getYname() {
		return yname;
	}
	public void setYname(String yname) {
		this.yname = yname;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getConfirmdate() {
		return confirmdate;
	}
	public void setConfirmdate(Date confirmdate) {
		this.confirmdate = confirmdate;
	}
	
	
}
