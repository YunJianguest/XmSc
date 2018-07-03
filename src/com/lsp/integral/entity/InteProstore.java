package com.lsp.integral.entity;

import java.util.Date;

import com.mongodb.DBObject;
import com.mongodb.ReflectionDBObject;
/**
 * 预存账单
 * @author lsp
 *
 */
public class InteProstore extends ReflectionDBObject {
    
	private String custid;
	private String  fromUserid;  
	private double money;
	/**
	 * 预存类型   
	 * ps_account 开通账户
	 * ps_recovery 回本后待返
	 * shop_bmzt 商城收益
	 */
	private String type;
	/**
	 * 0-返金额中
	 * 1-已返完
	 * 2-冻结
	 */
	private int state;
	private Date createdate;
	private Date enddate;
	/**
	 * 矿机Id
	 */
	private Long cid;
	/**
	 * 矿机运行时间
	 */
	private int time;
	/**
	 * 矿机
	 */
	private DBObject kj;

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
	public double getMoney() {
		return money;
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
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public DBObject getKj() {
		return kj;
	}
	public void setKj(DBObject kj) {
		this.kj = kj;
	} 
	 
	
}
