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
	private String   value;
	/**
	 * 消费记录PP币账户
	 */
	private String   prostore;
	/**
	 * 可用提成PP币账号
	 */
	private String   uservalue;
	/**
	 * 佣金账户
	 */
	private String   yjvalue;
	/**
	 * 矿机产币账户
	 */
	private String   kjvalue; 
	/**
	 * 矿机提现账户
	 */
	private String   kjtxvalue;
	/**
	 * 矿机价值账户
	 */
	private String   kjjzvalue;
	/**
	 * 产币类型1为PP币2为比特币3为以太坊
	 */
	private int      kjlx;
	private String   llzvalue; //乐乐总计
	private String   llkyvalue; //乐乐可用
	private String   lldjvalue; //乐乐冻结 
	
	public int getKjlx() {
		return kjlx;
	}
	public void setKjlx(int kjlx) {
		this.kjlx = kjlx;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProstore() {
		return prostore;
	}
	public void setProstore(String prostore) {
		this.prostore = prostore;
	}
	public String getUservalue() {
		return uservalue;
	}
	public void setUservalue(String uservalue) {
		this.uservalue = uservalue;
	}
	public String getKjvalue() {
		return kjvalue;
	}
	public void setKjvalue(String kjvalue) {
		this.kjvalue = kjvalue;
	}
	public String getKjtxvalue() {
		return kjtxvalue;
	}
	public void setKjtxvalue(String kjtxvalue) {
		this.kjtxvalue = kjtxvalue;
	}
	public String getKjjzvalue() {
		return kjjzvalue;
	}
	public void setKjjzvalue(String kjjzvalue) {
		this.kjjzvalue = kjjzvalue;
	}
	public String getLlzvalue() {
		return llzvalue;
	}
	public void setLlzvalue(String llzvalue) {
		this.llzvalue = llzvalue;
	}
	public String getLlkyvalue() {
		return llkyvalue;
	}
	public void setLlkyvalue(String llkyvalue) {
		this.llkyvalue = llkyvalue;
	}
	public String getLldjvalue() {
		return lldjvalue;
	}
	public void setLldjvalue(String lldjvalue) {
		this.lldjvalue = lldjvalue;
	}
	public String getYjvalue() {
		return yjvalue;
	}
	public void setYjvalue(String yjvalue) {
		this.yjvalue = yjvalue;
	}
	   

}
