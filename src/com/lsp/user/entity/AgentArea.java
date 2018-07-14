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
	private Long parentId;//父id
	private Long agentId;//代理商id
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
	 
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	} 
	
	
}
