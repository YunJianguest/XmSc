package com.lsp.suc.entity;

import java.util.Date;

import com.mongodb.ReflectionDBObject;

/**
 * 除大众区买东西返乐乐币，都返还此币种盼盼币
 * 
 * @author lsp
 * 
 */
public class IntegralInfo extends ReflectionDBObject {
	private String toUser;
	private String fromUser; 
	private String fromUserid;
	private String custid;
	/**
	 * ps_account 开通账户
	 * tj_account 推荐管理员
	 * ps_recovery 回本后待返
	 * shop_bmzt 商城收益
	 * shop_jfdh 下单使用
	 * jfcz 充值
	 * jf_withdraw 提现
	 * shop_order 订单收益
	 * shop_zz 转账
	 */
	private String type;
	
	private double value;
	 
    private Date createdate;
    private String summary;
    /**
     * 0为收入，1为支出
     */
    private int    state;
    /**
     * 0-待返
     * 1-可使用
     * 2-冻结
     **/
    private int  jfstate;
    private String fid;
    /**
     * 种类（0为普通1为PP币，2为LL币）
     */
    private int jflx;
    /**
     * 0未冻结
     * 1冻结
     */
    private int isfreeze;
	public int getJflx() {
		return jflx;
	}

	public void setJflx(int jflx) {
		this.jflx = jflx;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setValue(float value) {
		this.value = value;
	}
 
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFromUserid() {
		return fromUserid;
	}

	public void setFromUserid(String fromUserid) {
		this.fromUserid = fromUserid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}

	public int getJfstate() {
		return jfstate;
	}

	public void setJfstate(int jfstate) {
		this.jfstate = jfstate;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public int getIsfreeze() {
		return isfreeze;
	}

	public void setIsfreeze(int isfreeze) {
		this.isfreeze = isfreeze;
	}
	

}
