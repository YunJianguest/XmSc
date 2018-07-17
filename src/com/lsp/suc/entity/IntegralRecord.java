package com.lsp.suc.entity;

import com.mongodb.ReflectionDBObject;

/**
 * 乐乐币总计
 * @author lsp
 *
 */
public class IntegralRecord extends ReflectionDBObject {
	private String  custid; 
	private String  fromUserid;
	private double   value;
	/**
	 * 消费记录PP币账户
	 */
	private double   prostore;
	/**
	 * 可用提成PP币账号
	 */
	private double   uservalue;
	/**
	 * 矿机产币账户
	 */
	private double   kjvalue; 
	/**
	 * 产币类型1为PP币2为比特币3为以太坊
	 */
	private int      kjlx;
	private double   llzvalue; //乐乐总计
	private double   llkyvalue; //乐乐可用
	private double   lldjvalue; //乐乐冻结 
	
	public int getKjlx() {
		return kjlx;
	}
	public void setKjlx(int kjlx) {
		this.kjlx = kjlx;
	}
	public double getKjvalue() {
		return kjvalue;
	}
	public void setKjvalue(double kjvalue) {
		this.kjvalue = kjvalue;
	}
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
	public double getLlzvalue() {
		return llzvalue;
	}
	public void setLlzvalue(double llzvalue) {
		this.llzvalue = llzvalue;
	}
	public double getLlkyvalue() {
		return llkyvalue;
	}
	public void setLlkyvalue(double llkyvalue) {
		this.llkyvalue = llkyvalue;
	}
	public double getLldjvalue() {
		return lldjvalue;
	}
	public void setLldjvalue(double lldjvalue) {
		this.lldjvalue = lldjvalue;
	}
	 
     

}
