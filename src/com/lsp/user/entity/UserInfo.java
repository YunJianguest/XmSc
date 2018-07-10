package com.lsp.user.entity;

import com.mongodb.DBObject;
import com.mongodb.ReflectionDBObject;
import java.util.Date;
import java.util.List;
/***
 * 资源管理
 * @author lsp
 *
 */
public class UserInfo extends ReflectionDBObject {
	private String custid;
	private String parentId;
	private String toUser;
	private String fromUser;
	private String openid;
	private String account; 
	private String password;
	private String nickname;
	private String email;
	private String tel;
	private Date createdate;
	private Long createcustid;
	private Long role;
	private String qq_id;
	private String wx_id;
	private String address;
	private String shop;
	private String pic;
	private String signature;
	private String instructions;
	private String hometown;
	private String home;
	private String age;
	private String birthday;
	private String Record_schooling;
	private String school;
	private String company;
	private String professional;
	private Long orgid;
	private String orgname;
	private int type;
	private Date startdate;
	private Date enddate;
	private String status;
	private Long comid;
	private String remark;
	private String appid;
	private int jf;
	private String appsecret;
	private Long roleid;
	private String grade;
	private String classes;
	private String qqfromUser;
	private Object other;
	private int css;
	private List<DBObject> funclist;
	private int mb;
	private String area;
	private String province;
	private String city;
	/**
	 * 用户所在县
	 */
	private String county;
	/**
	 * 代理商   类型
	 * 1-省  2-市  3-县   4-部门  5-会员  6-会员的下级会员
	 * 2018/6/20
	 */
	private int agentLevel;
	
	private Long number;
	//推荐人编号
	private Long renumber;
	//推荐部门编号
	private Long deptnumber;
	private String rolename; 
	private String country;
	private String sex;
	private String headimgurl;
	private String language; 
	private String no; 
	private String reno;
	
	/**
	 * 是否为管理员
	 * 
	 */
	private boolean  isadmin; 
	/**
	 * 绑定号
	 */
	private String sno; 
	/**
	 * 微信id
	 */
	private String wxid; 
	
	private String comname;
	private String name;
	private String qq; 
	/**
	 * 用户照片
	 */
	private String userimg;
	private String sfz; 
	private String sfzpic; 
	/**
	 * 经纬度，经度在前，纬度在后
	 */
	private List<Double> loc; 
	
	/**
	 * 0 默认 
	 */
	private int lx;
	
	private Long wid;
	
	private String comUser;
	/**
	 * 来源
	 */
	private int ly;
	/**
	 * 其他来源同步用户
	 */
	private String qtUser;
	private int groupid;
	 
	/**
	 * 序号
	 */
	private int xh; 
	/**
	 * 等级
	 * @return
	 */
	private int   level;
	/**
	 * 论坛帖子
	 */
	private Long   bbscount;
	/**
	 * 徽章
	 */
	private String levelimgurl;
	/**
	 * 累计总经验
	 * @return
	 */
	private int  experience;
	/**
	 * 升级需要经验
	 */
	private int  needExperience;
	/**
	 * 当前获得经验
	 */
	private int  getExperience;
	/**
	 * 心情
	 * @return
	 */
	private String humor;
	private boolean isqqvip;
	private boolean isqqhz;
	private int     qqHzdj;
	private String loginname;
	private String loginpasswd;
	private String    expbl; 
	private Long    tackcount;
	/**
	 * 是否是安卓管理员
	 */
	private int     androidAdmin; 
	/**
	 * 是否在线0不在线1在线
	 */
	private int     online; 
	/**
	 * 推荐类型（0默认1推荐id为管理员id）
	 */
	private int    tjlx; 
	
	public String getReno() {
		return reno;
	}

	public void setReno(String reno) {
		this.reno = reno;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getComid() {
		return this.comid;
	}

	public void setComid(Long comid) {
		this.comid = comid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getJf() {
		return this.jf;
	}

	public void setJf(int jf) {
		this.jf = jf;
	}

	public Object getOther() {
		return this.other;
	}

	public void setOther(Object other) {
		this.other = other;
	}

	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Long getCreatecustid() {
		return this.createcustid;
	}

	public void setCreatecustid(Long createcustid) {
		this.createcustid = createcustid;
	}

	public String getQq_id() {
		return this.qq_id;
	}

	public void setQq_id(String qq_id) {
		this.qq_id = qq_id;
	}

	public String getWx_id() {
		return this.wx_id;
	}

	public void setWx_id(String wx_id) {
		this.wx_id = wx_id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getHometown() {
		return this.hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRecord_schooling() {
		return this.Record_schooling;
	}

	public void setRecord_schooling(String record_schooling) {
		this.Record_schooling = record_schooling;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public Long getRole() {
		return this.role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return this.appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasses() {
		return this.classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public int getCss() {
		return css;
	}

	public void setCss(int css) {
		this.css = css;
	}

	public String getQqfromUser() {
		return qqfromUser;
	}

	public void setQqfromUser(String qqfromUser) {
		this.qqfromUser = qqfromUser;
	}

	public List<DBObject> getFunclist() {
		return funclist;
	}

	public void setFunclist(List<DBObject> funclist) {
		this.funclist = funclist;
	}

	public int getMb() {
		return mb;
	}

	public void setMb(int mb) {
		this.mb = mb;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(int agentLevel) {
		this.agentLevel = agentLevel;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getRenumber() {
		return renumber;
	}

	public void setRenumber(Long renumber) {
		this.renumber = renumber;
	}

	public Long getDeptnumber() {
		return deptnumber;
	}

	public void setDeptnumber(Long deptnumber) {
		this.deptnumber = deptnumber;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	

}