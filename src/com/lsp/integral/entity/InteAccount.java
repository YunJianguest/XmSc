package com.lsp.integral.entity;

import java.util.Date;


import com.mongodb.ReflectionDBObject;
/**
 * 账户
 * @author lsp
 *
 */
public class InteAccount extends ReflectionDBObject {
    
	private String custid;
	private String account;//账户名称
	private String remark;//备注
	private String cid;//币种id
	private Date createdate;
	
	public String getCustid() {
		return custid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCustid(String custid) {
		this.custid = custid;
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
	
	 
}
