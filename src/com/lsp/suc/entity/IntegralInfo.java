package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/**
 * 积分
 * 
 * @author lsp
 * 
 */
public class IntegralInfo extends ReflectionDBObject {
	private String toUser;
	private String fromUser; 
	private String fromUserid;
	private String custid;
	/**
	 * 积分类型
	 * ps_account 推荐账户
	 * ps_recovery 回本后待返
	 * ps_shop 商城收益
	 */
	private String type;
	/**
	 * 积分
	 */
	private float value;
	 
    private Date createdate;
    private String summary;
    /**
     * 0为收入，1为支出
     */
    private int    state;
    /**
     * 0-待返积分
     * 1-可使用积分
     * 2-冻结积分
     **/
    private int  jfstate;
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
 
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFromUserid() {
		return fromUserid;
	}

	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}

	public int getJfstate() {
		return jfstate;
	}

	public void setJfstate(int jfstate) {
		this.jfstate = jfstate;
	}
	

}
