package com.lsp.shop.entiy;

import java.util.Date;

import com.mongodb.DBObject;
import com.mongodb.ReflectionDBObject;
/**
 * 售后
 * @author lsp
 *
 */
public class AfterService extends ReflectionDBObject {
	/**
	  * 卖家id
	  */
	 private String custid;
	 /**
	  * 买家id
	  */
	 private String fromUserid;
	 /**
	  * 订单ID
	  */
	 private String oid;
	 /**
	  * 订单详情ID
	  */
	 private Long orderproId; 
	 /**
	  * 店铺ID
	  */
	 private Long comid; 
	/**
	 * 商品 
	 */
	 private DBObject product; 
	 /**
	  * 商品数量
	  */
	 private int num;
	 /**
	  * 售后类型   1-退货   2-换货
	  */
	 private  int type;
     /**
      * 售后原因
      * 
      */ 
	 private String resource;
	 /**
	  * 备注
	  */
	 private String remark;
	 /**
	  * 创建时间
	  */
	 private Date   createdate;
	 /**
	  * 0-申请  1-同意   2-拒绝    3-取消
	  */
	 private int state;
	 /**
	  * 退款金额
	  */
	 private double price;
	 
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
	public Long getComid() {
		return comid;
	}
	public void setComid(Long comid) {
		this.comid = comid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public DBObject getProduct() {
		return product;
	}
	public void setProduct(DBObject product) {
		this.product = product;
	}
	public Long getOrderproId() {
		return orderproId;
	}
	public void setOrderproId(Long orderproId) {
		this.orderproId = orderproId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	 
}
