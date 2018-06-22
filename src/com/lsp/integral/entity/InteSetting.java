package com.lsp.integral.entity;

import java.math.BigDecimal;
import java.util.Date;


import com.mongodb.ReflectionDBObject;
/**
 * 设置
 * @author lsp
 *
 */
public class InteSetting extends ReflectionDBObject {
    
	private String custid;
	
	/**
	 * 币种设置
	 */
	private String name;//名称 
	private String remark;//备注
	
	/**
	 * 提成设置
	 */
	private double any;//任意推荐
	private double direct;//直推
	private double between;//二级推荐
	
	private double diffProvince;//异地推荐省级
	private double diffCity;//异地推荐市级
	private double diffCounty;//异地推荐县级
	
	/**
	 * 预返金额设置
	 */
	private BigDecimal returnProvince;//省级预返值
	private BigDecimal returnCity;//市级预返值
	private BigDecimal returnCounty;//县级预返值
	
	private Date createdate;
	
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public double getAny() {
		return any;
	}
	public void setAny(double any) {
		this.any = any;
	}
	public double getDirect() {
		return direct;
	}
	public void setDirect(double direct) {
		this.direct = direct;
	}
	public double getBetween() {
		return between;
	}
	public void setBetween(double between) {
		this.between = between;
	}
	public double getDiffProvince() {
		return diffProvince;
	}
	public void setDiffProvince(double diffProvince) {
		this.diffProvince = diffProvince;
	}
	public double getDiffCity() {
		return diffCity;
	}
	public void setDiffCity(double diffCity) {
		this.diffCity = diffCity;
	}
	public double getDiffCounty() {
		return diffCounty;
	}
	public void setDiffCounty(double diffCounty) {
		this.diffCounty = diffCounty;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getReturnProvince() {
		return returnProvince;
	}
	public void setReturnProvince(BigDecimal returnProvince) {
		this.returnProvince = returnProvince;
	}
	public BigDecimal getReturnCity() {
		return returnCity;
	}
	public void setReturnCity(BigDecimal returnCity) {
		this.returnCity = returnCity;
	}
	public BigDecimal getReturnCounty() {
		return returnCounty;
	}
	public void setReturnCounty(BigDecimal returnCounty) {
		this.returnCounty = returnCounty;
	}
	 
}
