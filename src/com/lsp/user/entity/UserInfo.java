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
	
	private String userName;//真实姓名
	private String id_card;//身份证号码
	private String id_card_front;//身份证正面照
	private String id_card_reverse;//身份证反面照
	private String company_name;//公司名称
	private String lisense_number;//营业证号码
	private String lisense_photo;//营业证照片
	private int audit_status;//审核状态  1为通过  2为不通过
	
	private String uskd;//uskd账号
	
	private String paypassword;//支付密码
	/**
	 * 用户所在县
	 */
	private String county;
	/**
	 * 代理的省
	 */
	private Long agentprovinceid;
	/**
	 * 代理的市
	 */
	private Long agentcityid;
	/**
	 * 代理的县id
	 */
	private Long agentcountyid;
	/**
	 * 部门的县id
	 */
	private Long deptcountyid;
	/**
	 * 代理的省
	 */
	private String agentprovince;
	/**
	 * 代理的市
	 */
	private String agentcity;
	/**
	 * 代理的县
	 */
	private String agentcounty;
	/**
	 * 部门的县
	 */
	private String deptcounty;
	/**
	 * 代理商   类型
	 * 1-省  2-市  3-县   4-部门 
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
	/**
	 * 代理时间
	 */
	private Date   agentedate; 
	private  int isfull;//1-已补全  其余-未补全
	
	private String salt;
	/**
	 * 销售业绩
	 */
	private String xsyj;
	

	public String getXsyj() {
		return xsyj;
	}

	public void setXsyj(String xsyj) {
		this.xsyj = xsyj;
	}

	public Date getAgentedate() {
		return agentedate;
	}

	public void setAgentedate(Date agentedate) {
		this.agentedate = agentedate;
	}

	public String getAgentcounty() {
		return agentcounty;
	}

	public void setAgentcounty(String agentcounty) {
		this.agentcounty = agentcounty;
	}

	public String getAgentcity() {
		return agentcity;
	}

	public void setAgentcity(String agentcity) {
		this.agentcity = agentcity;
	}

	public String getAgentprovince() {
		return agentprovince;
	}

	public void setAgentprovince(String agentprovince) {
		this.agentprovince = agentprovince;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getSfzpic() {
		return sfzpic;
	}

	public void setSfzpic(String sfzpic) {
		this.sfzpic = sfzpic;
	}

	public List<Double> getLoc() {
		return loc;
	}

	public void setLoc(List<Double> loc) {
		this.loc = loc;
	}

	public int getLx() {
		return lx;
	}

	public void setLx(int lx) {
		this.lx = lx;
	}

	public Long getWid() {
		return wid;
	}

	public void setWid(Long wid) {
		this.wid = wid;
	}

	public String getComUser() {
		return comUser;
	}

	public void setComUser(String comUser) {
		this.comUser = comUser;
	}

	public int getLy() {
		return ly;
	}

	public void setLy(int ly) {
		this.ly = ly;
	}

	public String getQtUser() {
		return qtUser;
	}

	public void setQtUser(String qtUser) {
		this.qtUser = qtUser;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getBbscount() {
		return bbscount;
	}

	public void setBbscount(Long bbscount) {
		this.bbscount = bbscount;
	}

	public String getLevelimgurl() {
		return levelimgurl;
	}

	public void setLevelimgurl(String levelimgurl) {
		this.levelimgurl = levelimgurl;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getNeedExperience() {
		return needExperience;
	}

	public void setNeedExperience(int needExperience) {
		this.needExperience = needExperience;
	}

	public int getGetExperience() {
		return getExperience;
	}

	public void setGetExperience(int getExperience) {
		this.getExperience = getExperience;
	}

	public String getHumor() {
		return humor;
	}

	public void setHumor(String humor) {
		this.humor = humor;
	}

	public boolean isIsqqvip() {
		return isqqvip;
	}

	public void setIsqqvip(boolean isqqvip) {
		this.isqqvip = isqqvip;
	}

	public boolean isIsqqhz() {
		return isqqhz;
	}

	public void setIsqqhz(boolean isqqhz) {
		this.isqqhz = isqqhz;
	}

	public int getQqHzdj() {
		return qqHzdj;
	}

	public void setQqHzdj(int qqHzdj) {
		this.qqHzdj = qqHzdj;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpasswd() {
		return loginpasswd;
	}

	public void setLoginpasswd(String loginpasswd) {
		this.loginpasswd = loginpasswd;
	}

	public String getExpbl() {
		return expbl;
	}

	public void setExpbl(String expbl) {
		this.expbl = expbl;
	}

	public Long getTackcount() {
		return tackcount;
	}

	public void setTackcount(Long tackcount) {
		this.tackcount = tackcount;
	}

	public int getAndroidAdmin() {
		return androidAdmin;
	}

	public void setAndroidAdmin(int androidAdmin) {
		this.androidAdmin = androidAdmin;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public int getTjlx() {
		return tjlx;
	}

	public void setTjlx(int tjlx) {
		this.tjlx = tjlx;
	}

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

	public Long getAgentprovinceid() {
		return agentprovinceid;
	}

	public void setAgentprovinceid(Long agentprovinceid) {
		this.agentprovinceid = agentprovinceid;
	}

	public Long getAgentcityid() {
		return agentcityid;
	}

	public void setAgentcityid(Long agentcityid) {
		this.agentcityid = agentcityid;
	}

	public Long getAgentcountyid() {
		return agentcountyid;
	}

	public void setAgentcountyid(Long agentcountyid) {
		this.agentcountyid = agentcountyid;
	}

	public Long getDeptcountyid() {
		return deptcountyid;
	}

	public void setDeptcountyid(Long deptcountyid) {
		this.deptcountyid = deptcountyid;
	}

	public String getDeptcounty() {
		return deptcounty;
	}

	public void setDeptcounty(String deptcounty) {
		this.deptcounty = deptcounty;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getId_card_front() {
		return id_card_front;
	}

	public void setId_card_front(String id_card_front) {
		this.id_card_front = id_card_front;
	}

	public String getId_card_reverse() {
		return id_card_reverse;
	}

	public void setId_card_reverse(String id_card_reverse) {
		this.id_card_reverse = id_card_reverse;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getLisense_number() {
		return lisense_number;
	}

	public void setLisense_number(String lisense_number) {
		this.lisense_number = lisense_number;
	}

	public String getLisense_photo() {
		return lisense_photo;
	}

	public void setLisense_photo(String lisense_photo) {
		this.lisense_photo = lisense_photo;
	}

	public int getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}

	public String getUskd() {
		return uskd;
	}

	public void setUskd(String uskd) {
		this.uskd = uskd;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public int getIsfull() {
		return isfull;
	}

	public void setIsfull(int isfull) {
		this.isfull = isfull;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}