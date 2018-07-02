package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * @author lsp
 *
 */
public class FarmYd extends ReflectionDBObject{

	private String custid;
	private String fromUserid;
	private Date   createdate;  
	private FarmProduct obj;
	private Long   wid;
	private int    growth;
	private int    state;
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
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public FarmProduct getObj() {
		return obj;
	}
	public void setObj(FarmProduct obj) {
		this.obj = obj;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public int getGrowth() {
		return growth;
	}
	public void setGrowth(int growth) {
		this.growth = growth;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
