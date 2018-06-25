package com.lsp.integral.entity;

import java.util.Date;


import com.mongodb.ReflectionDBObject;
/**
 * 货币设置
 * @author lsp
 *
 */
public class InteCurrency extends ReflectionDBObject {
    
	private String custid;
	private String name;//货币名称
	private String remark;//备注
	private Date createdate;
	
	public String getCustid() {
		return custid;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	 
}
