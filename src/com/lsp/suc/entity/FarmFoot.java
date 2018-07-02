package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * @author lsp
 *
 */
public class FarmFoot extends ReflectionDBObject{

	private String custid;
	private String fromUserid;
	private String fid;
	private Date   createdate;  
	private String summary;
	private String   wid; 
	private Long   pid;
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
	 
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	 
	
	
}
