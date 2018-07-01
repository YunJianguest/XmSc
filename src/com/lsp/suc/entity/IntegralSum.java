package com.lsp.suc.entity;

import com.mongodb.ReflectionDBObject;

/**
 * 平台盼盼币总计
 * @author lsp
 *
 */
public class IntegralSum extends ReflectionDBObject {
	private String  custid; 
	private String  fromUserid;
	private double   value;
	private double   remaining;
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
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
    
	

}
