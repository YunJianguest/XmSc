package com.lsp.integral.entity;

import java.util.Date;


import com.mongodb.ReflectionDBObject;
/**
 * 预存账单
 * @author lsp
 *
 */
public class InteProstore extends ReflectionDBObject {
    
	private String custid;
	private String  fromUserid;  
	private Double money;
	/**
	 * 预存类型   
	 * ps_account 开通账户
	 * ps_recovery 回本后待返
	 * ps_shop 商城收益
	 */
	private String type;
	private int state;
	private Date createdate;

	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getFromUserid() {
		return fromUserid;
	}
	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
}
