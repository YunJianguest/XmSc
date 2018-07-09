package com.lsp.shop.entiy;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/***
 * 店铺关注
 * @author ysh
 *
 */
public class ShopAttention  extends ReflectionDBObject{
	
	//id
	private Long id;
  	
	//平台id
	private String custid;
		
	//店铺id
	private String shopId;
	
	//用户id
	private String fromUserid;
	
	//添加时间
	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
