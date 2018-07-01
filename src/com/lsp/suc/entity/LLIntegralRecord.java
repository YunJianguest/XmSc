package com.lsp.suc.entity;

import com.mongodb.ReflectionDBObject;

/**
 * 积分总计
 * @author lsp
 *
 */
public class LLIntegralRecord extends ReflectionDBObject {
	private String  custid; 
	private String  fromUserid;
	private double   value;
	private double   prostore;
	private double   uservalue;
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
	public double getProstore() {
		return prostore;
	}
	public void setProstore(double prostore) {
		this.prostore = prostore;
	}
	public double getUservalue() {
		return uservalue;
	}
	public void setUservalue(double uservalue) {
		this.uservalue = uservalue;
	}
    
	

}
