package com.lsp.suc.entity;

import com.mongodb.ReflectionDBObject;
/**
 * @author lsp
 *
 */
public class Signinmonth extends ReflectionDBObject{

	private String toUser;
	private int sort;
	private String month;
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
