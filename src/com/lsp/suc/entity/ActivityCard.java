package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/***
 * 资源管理
 * @author lsp
 *
 */
public class ActivityCard extends ReflectionDBObject{ 
	private String fromUserid; 
	private String custid;  
															
	private Date createdate; 
	
	private int state; 
		
	private Long hdid; 
	
	private String yhj; 
	
	private String jp;
	
	private Date djenddate;
	
	private String hdtitle;
	public String getFromUserid() {
		return fromUserid;
	}
	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
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
	public Long getHdid() {
		return hdid;
	}
	public void setHdid(Long hdid) {
		this.hdid = hdid;
	}
	public String getYhj() {
		return yhj;
	}
	public void setYhj(String yhj) {
		this.yhj = yhj;
	}
	public String getJp() {
		return jp;
	}
	public void setJp(String jp) {
		this.jp = jp;
	}
	public Date getDjenddate() {
		return djenddate;
	}
	public void setDjenddate(Date djenddate) {
		this.djenddate = djenddate;
	}
	public String getHdtitle() {
		return hdtitle;
	}
	public void setHdtitle(String hdtitle) {
		this.hdtitle = hdtitle;
	}
	
	
}
