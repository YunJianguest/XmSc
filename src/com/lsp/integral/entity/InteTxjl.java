package com.lsp.integral.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * 提现记录(记录每天是否提币)
 * @author lsp
 *
 */
public class InteTxjl extends ReflectionDBObject{

	private String fromUserid;
	private Date createdate;
	private int state;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
