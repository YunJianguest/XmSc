package com.lsp.pub.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * 汇率
 * @author lsp
 *
 */
public class Exchangerate extends ReflectionDBObject{
 
	/**
	 * 类型    USD_CNY美元转人民币
	 */
	private String type;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 更新时间
	 */
	private Date  upddate;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	
}
