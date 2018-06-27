package com.lsp.integral.entity;

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
	private String name;//币种一设置
	private String remark;//备注
	private String names;//币种二设置
	
	/**
	 * 提成设置
	 */
	private double any;//任意推荐
	private double direct;//直推
	private double between;//二级推荐
	

	private double sameProvince;//同地推荐省级
	private double sameCity;//同地推荐市级
	private double sameCounty;//同地推荐县级
	private double sameDepartment;//部门提成
	
	
	private double diffProvince;//异地推荐省级
	private double diffCity;//异地推荐市级
	private double diffCounty;//异地推荐县级
	
	/**
	 * 预返金额设置
	 */
	private double returnProvince;//省级预返值
	private double returnCity;//市级预返值
	private double returnCounty;//县级预返值
	private double returnDept;//县级预返值
	
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
	public double getReturnProvince() {
		return returnProvince;
	}
	public void setReturnProvince(double returnProvince) {
		this.returnProvince = returnProvince;
	}
	public double getReturnCity() {
		return returnCity;
	}
	public void setReturnCity(double returnCity) {
		this.returnCity = returnCity;
	}
	public double getReturnCounty() {
		return returnCounty;
	}
	public void setReturnCounty(double returnCounty) {
		this.returnCounty = returnCounty;
	}
	public double getReturnDept() {
		return returnDept;
	}
	public void setReturnDept(double returnDept) {
		this.returnDept = returnDept;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public double getSameProvince() {
		return sameProvince;
	}
	public void setSameProvince(double sameProvince) {
		this.sameProvince = sameProvince;
	}
	public double getSameCity() {
		return sameCity;
	}
	public void setSameCity(double sameCity) {
		this.sameCity = sameCity;
	}
	public double getSameCounty() {
		return sameCounty;
	}
	public void setSameCounty(double sameCounty) {
		this.sameCounty = sameCounty;
	}
	public double getSameDepartment() {
		return sameDepartment;
	}
	public void setSameDepartment(double sameDepartment) {
		this.sameDepartment = sameDepartment;
	}
	
}
