package com.lsp.shop.entiy;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.DoubleListUIBean;

import com.mongodb.DBObject;
import com.mongodb.ReflectionDBObject;
/**
 * 订单商品
 * @author lsp
 *
 */
public class OrderFormpro extends ReflectionDBObject{
	 private String  orderid;
	 private int count;
	 private String  spec;
	 private DBObject pro;
	 private Long  pid;
	 private String fromUserid;
	 /**
	  * 0-正常 1-退货申请   2-换货申请    3-退货   4-换货
	  */
	 private int state;
	 /**
	  * 售后申请id
	  */
	 private String sid;
	 private Double other_money;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public DBObject getPro() {
		return pro;
	}
	public void setPro(DBObject pro) {
		this.pro = pro;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getFromUserid() {
		return fromUserid;
	}
	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Double getOther_money() {
		return other_money;
	}
	public void setOther_money(Double other_money) {
		this.other_money = other_money;
	}
	 
	
}