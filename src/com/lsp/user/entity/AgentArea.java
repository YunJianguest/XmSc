package com.lsp.user.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/**
 * 代理地区
 * @author lsp
 *
 */
public class AgentArea extends ReflectionDBObject{ 
	private String area;//地区
	private String custid;
	private Long parentId;//父id
	private String agentId;//代理商id
	/**
	 * 代理商   类型
	 * 1-省  2-市  3-县   4-部门 
	 * 2018/6/20
	 */
	private int agentLevel;
	private int  sort;
	private Date createdate; 
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	} 
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	 
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(int agentLevel) {
		this.agentLevel = agentLevel;
	} 
	
	
}
