package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;
/**
 * @author lsp
 *
 */
public class MemorialCircle extends ReflectionDBObject{

	private String custid;
	private String fromUserid;
	private String relashipbetween;
	private Long   wid;
	private String nickname;
	private String headimgurl;
	private Date   createdate;
	/**
	 * 1为未审核2为已通过3为已拒绝
	 */
	private int    state;
	private String content;
  
 
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRelashipbetween() {
		return relashipbetween;
	}
	public void setRelashipbetween(String relashipbetween) {
		this.relashipbetween = relashipbetween;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	 
}
