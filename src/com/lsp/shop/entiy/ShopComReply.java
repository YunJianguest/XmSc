package com.lsp.shop.entiy;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/***
 * 评论回复
 * 
 * @author lsp
 *
 */
public class ShopComReply extends ReflectionDBObject {
	/**
	 * 平台ID
	 */
	private String custid;
	/**
	 * 评论人ID
	 */
	private String fromid;  
	/**
	 * 评论ID（shopcomments里面的ID）
	 */
	private Long comid; 
	/**
	 * 父ID
	 */
	private Long parentid;
	/**
	 * 回复类型（0用户，1商家）
	 */
	private int hflx;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 图片
	 */
	private String picurl;
	private Date createdate;
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public Long getComid() {
		return comid;
	}
	public void setComid(Long comid) {
		this.comid = comid;
	}
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	public int getHflx() {
		return hflx;
	}
	public void setHflx(int hflx) {
		this.hflx = hflx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	 
}
