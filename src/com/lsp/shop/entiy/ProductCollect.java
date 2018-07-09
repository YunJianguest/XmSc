package com.lsp.shop.entiy;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/**
 * 商品收藏
 * @author Administrator
 *
 */
public class ProductCollect  extends ReflectionDBObject  {
	
	private String custid;//平台id
	
	private String fromUserid;//用户ID
	
	private String productId;//商品id

	private Date createdate;
	
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	

}
