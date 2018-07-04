package com.lsp.shop.entiy;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/***
 * 店铺评论
 * 
 * @author lsp
 *
 */
public class ShopComments extends ReflectionDBObject {
	/**
	 * 平台ID
	 */
	private String custid;
	/**
	 * 评论人ID
	 */
	private String fromid;
	/**
	 * 店铺ID
	 */
	private Long sid;
	/**
	 * 订单ID
	 */
	private String oid;
	/**
	 * 商品id
	 */
	private Long gid; 
	/**
	 * 评论标题
	 */
	private String title;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 图片
	 */
	private String picurl;
	/**
	 * 描述相符1非常差2差3一般4好5非常好
	 */
	private int desIscon;
	/**
	 * 物流服务1非常差2差3一般4好5非常好
	 */
	private int logisService;
	/**
	 * 服务态度1非常差2差3一般4好5非常好
	 */
	private int serviceAtt;
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
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	 
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
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
	public int getDesIscon() {
		return desIscon;
	}
	public void setDesIscon(int desIscon) {
		this.desIscon = desIscon;
	}
	public int getLogisService() {
		return logisService;
	}
	public void setLogisService(int logisService) {
		this.logisService = logisService;
	}
	public int getServiceAtt() {
		return serviceAtt;
	}
	public void setServiceAtt(int serviceAtt) {
		this.serviceAtt = serviceAtt;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	

}
