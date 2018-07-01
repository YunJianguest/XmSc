package com.lsp.integral.entity;

import java.util.Date;


import com.mongodb.ReflectionDBObject;
/**
 * 矿机
 * @author lsp
 *
 */
public class Miner extends ReflectionDBObject {
    
	/**
	 * 矿机标题
	 */															
	private String ptitle;
	
	private String custid;
	/**
	 * 矿机编码
	 */															
	private String no;
	
	/**
	 * 数量
	 */															
	private int num;
	/**
	 * 矿机数量
	 */															
	private int gmnum;
	/**
	 * 价格
	 */															
	private Double price;
	/**
	 * 图片。  
	 */
	private String logo;
	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。  
	 */
	private String picUrl;
    /**
     * 矿机运行周期
     */
	private int time;
	/**
     * 创建更新时间
     */
	private Date createdate;
	
	private int sort;
	
	private String remark;
	/**
	 * 提成比例
	 */
	private Double percent;
    
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getGmnum() {
		return gmnum;
	}
	public void setGmnum(int gmnum) {
		this.gmnum = gmnum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	
	 
}
