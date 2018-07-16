package com.lsp.shop.entiy;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.DoubleListUIBean;

import com.mongodb.DBObject;
import com.mongodb.ReflectionDBObject;

/**
 * 订单商品
 * 
 * @author lsp
 *
 */
public class OrderFormpro extends ReflectionDBObject {
	private String orderid;
	private int count;
	private String spec;
	private DBObject pro;
	private Long pid;
	private String fromUserid;

	// 1订单 2 确认 3 发货 4收货 5退货 6 取消
	private int goodstate;

	/**
	 * 0-正常 1-退货申请 2-换货申请 3-退货 4-换货
	 */
	private int state;
	/**
	 * 售后申请id
	 */
	private String sid;
	private Double other_money;
	/**
	 * 大众区支付金额
	 */
	private Double public_money;
	/**
	 * 特约区支付金额
	 */
	private Double contri_money;
	/**
	 * 会员区支付金额
	 */
	private Double members_money;
	/**
	 * 第三方支付的金额
	 */
	private Double money;
	/**
	 * 支付类型0人民币，1比特币2以太坊3PP币
	 */
	private int  zflx;
	private Long deptCode; 
	/**
	 * 快递公司
	 */															
	private String kdcom;
	/**
	 * 快递单号
	 */															
	private String kdno;
	/**
	 * 快递价格
	 */
	private Double kdprice;
	/**
	 * 发货
	 */
	private Date deliveryDate;
	/**
	 * 确认收货时间
	 */
	private Date resureDate;
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public int getZflx() {
		return zflx;
	}

	public void setZflx(int zflx) {
		this.zflx = zflx;
	}

	public Long getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Long deptCode) {
		this.deptCode = deptCode;
	}

	public Double getPublic_money() {
		return public_money;
	}

	public void setPublic_money(Double public_money) {
		this.public_money = public_money;
	}

	public Double getContri_money() {
		return contri_money;
	}

	public void setContri_money(Double contri_money) {
		this.contri_money = contri_money;
	}

	public Double getMembers_money() {
		return members_money;
	}

	public void setMembers_money(Double members_money) {
		this.members_money = members_money;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getGoodstate() {
		return goodstate;
	}

	public void setGoodstate(int goodstate) {
		this.goodstate = goodstate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DBObject getPro() {
		return pro;
	}

	public void setPro(DBObject pro) {
		this.pro = pro;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getFromUserid() {
		return fromUserid;
	}

	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Double getOther_money() {
		return other_money;
	}

	public void setOther_money(Double other_money) {
		this.other_money = other_money;
	}

	public String getKdcom() {
		return kdcom;
	}

	public void setKdcom(String kdcom) {
		this.kdcom = kdcom;
	}

	public String getKdno() {
		return kdno;
	}

	public void setKdno(String kdno) {
		this.kdno = kdno;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getResureDate() {
		return resureDate;
	}

	public void setResureDate(Date resureDate) {
		this.resureDate = resureDate;
	}

	public Double getKdprice() {
		return kdprice;
	}

	public void setKdprice(Double kdprice) {
		this.kdprice = kdprice;
	} 

}