package com.lsp.shop.entiy;

import com.mongodb.ReflectionDBObject;
/**
 * 提醒配置
 * @author lsp
 *
 */
public class ShopRemind extends ReflectionDBObject{

	private String custid;
	/**
	 * 支付提醒
	 */
	private String  zftxTel;
	/**
	 * 支付提醒内容
	 */
	private String  zftxContent;
	/**
	 * 发货提醒
	 */
	private String  fhtxTel;
	private String  fhtxContent;
	
	
	public String getZftxContent() {
		return zftxContent;
	}
	public void setZftxContent(String zftxContent) {
		this.zftxContent = zftxContent;
	}
	public String getFhtxContent() {
		return fhtxContent;
	}
	public void setFhtxContent(String fhtxContent) {
		this.fhtxContent = fhtxContent;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getZftxTel() {
		return zftxTel;
	}
	public void setZftxTel(String zftxTel) {
		this.zftxTel = zftxTel;
	}
	public String getFhtxTel() {
		return fhtxTel;
	}
	public void setFhtxTel(String fhtxTel) {
		this.fhtxTel = fhtxTel;
	}
	
	
}
