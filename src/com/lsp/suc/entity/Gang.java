package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * @author lsp
 *
 */
public class Gang extends ReflectionDBObject{

	private String  custid;
	private String  fromUserid;
	private String  sort;
	private Date    createdate;
	private String  title;
	private int     pcount;
	private int     level;
	private int  experience;
	private int  needExperience;
	private int  getExperience;
	private String summary;
	private float  money;
	private float whmoney;
	
	public float getWhmoney() {
		return whmoney;
	}
	public void setWhmoney(float whmoney) {
		this.whmoney = whmoney;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getNeedExperience() {
		return needExperience;
	}
	public void setNeedExperience(int needExperience) {
		this.needExperience = needExperience;
	}
	public int getGetExperience() {
		return getExperience;
	}
	public void setGetExperience(int getExperience) {
		this.getExperience = getExperience;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	
	
}
