package com.lsp.user.web;


import com.lsp.email.entity.Email; 
import com.lsp.email.util.EmailUtils;
import com.lsp.integral.entity.InteProstore;
import com.lsp.integral.entity.InteSetting;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Fromusermb;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.RoleInfo;
import com.lsp.pub.util.BaseDate;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.ListUtil;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.util.UserUtil;
import com.lsp.pub.web.GeneralAction; 
import com.lsp.user.entity.UserInfo;
import com.lsp.website.service.WwzService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject; 

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern; 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.activemq.store.jdbc.adapter.DB2JDBCAdapter;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
/***
 * 资源管理
 * @author lsp
 *
 */
@Namespace("/user")
@Results({@org.apache.struts2.convention.annotation.Result(name="reload", location="user.action", type="redirect")})
public class UserAction extends GeneralAction<UserInfo>
{
  private static final long serialVersionUID = -6784469775589971579L;

  @Autowired
  private BaseDao basedao;
  private String _id;
  private UserInfo entity = new UserInfo();
  private MongoSequence mongoSequence;
  @Autowired
  private WwzService  wwzservice; 
  @Autowired
  public void setMongoSequence(MongoSequence mongoSequence)
  {
    this.mongoSequence = mongoSequence;
  }
  public void set_id(String _id) {
    this._id = _id;
  }
  public String execute() throws Exception {
	  
    HashMap<String, Object> sortMap = new HashMap<String, Object>();
    HashMap<String, Object> whereMap = new HashMap<String, Object>();
    sortMap.put("createdate",-1);
    if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
		fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
	} 
    String title = Struts2Utils.getParameter("title");
    if (StringUtils.isNotEmpty(title)) {
      Pattern pattern = Pattern.compile("^.*" + title + ".*$", 
        2);
      whereMap.put("account", pattern);
      Struts2Utils.getRequest().setAttribute("title", title);
    }
    String name = Struts2Utils.getParameter("name");
    if (StringUtils.isNotEmpty(name)) {
      Pattern pattern = Pattern.compile("^.*" + name + ".*$", 
        2);
      whereMap.put("nickname", pattern);
      Struts2Utils.getRequest().setAttribute("name", name);
    }
    whereMap.put("custid",SpringSecurityUtils.getCurrentUser().getId());
    List<DBObject> list = this.basedao.getList(PubConstants.USER_INFO, whereMap, sortMap); 
    for (DBObject dbObject : list) {
		dbObject.put("nickname", wwzservice.getCustName(dbObject.get("custid").toString()));
		if(dbObject.get("roleid") != null){
			DBObject dbObject2 =basedao.getMessage(PubConstants.ROLE_INFO, Long.parseLong(dbObject.get("roleid").toString()));
		    if(dbObject2 != null){
		    	if(dbObject2.get("rolename") != null){
		    		System.out.println(dbObject2.get("rolename"));
		    		dbObject.put("rolename", dbObject2.get("rolename").toString());
		    	}
		    }
		}else{
			dbObject.put("rolename", "无");
		}
		dbObject.put("nickname", wwzservice.getCustName(dbObject.get("custid").toString()));
	}
    Struts2Utils.getRequest().setAttribute("userList", list);
    this.fycount = this.basedao.getCount(PubConstants.USER_INFO,whereMap);
    Struts2Utils.getRequest().setAttribute("fycount", Long.valueOf(this.fycount));
    whereMap.clear();
    whereMap.put("custid",SpringSecurityUtils.getCurrentUser().getId());
    List<DBObject> rolelist = this.basedao.getList(PubConstants.ROLE_INFO, whereMap, sortMap);
    HashMap<Long, Object> map = new HashMap<Long,Object>();
    map.put(Long.valueOf(0L), "请选择");
    List<Map<Long,Object>> lsrole = new ArrayList<Map<Long,Object>>();
    for (int i = 0; i < rolelist.size(); i++) {
      RoleInfo entity = (RoleInfo)UniObject.DBObjectToObject((DBObject)rolelist.get(i), RoleInfo.class);
      HashMap<Long,Object> roleMap = new HashMap<Long,Object>();
      map.put(Long.valueOf(Long.parseLong(entity.get_id().toString())), entity.getRolename().toString());

      lsrole.add(roleMap);
    } 
    Struts2Utils.getRequest().setAttribute("custid", SpringSecurityUtils.getCurrentUser().getId());
    Struts2Utils.getRequest().setAttribute("map", map); 
    Struts2Utils.getRequest().setAttribute("rolelist", rolelist);
    sortMap.clear();
    sortMap.put("sort", -1);  
    Struts2Utils.getRequest().setAttribute("fromfunc", basedao.getList(PubConstants.PUB_FROMUSERFUNC, null, sortMap));
    return SUCCESS;
  }

  public String input()
    throws Exception
  {
    return "add";
  }

  public String update()
    throws Exception
  {
    return "add";
  }

  public String save() throws Exception
  {
    try
    { 
    	
      if(StringUtils.isEmpty(_id)){
    	  _id=UUID.randomUUID().toString(); 
      }
      this.entity.set_id(_id);
      this.entity.setCreatedate(new Date());
      this.entity.setCustid(SpringSecurityUtils.getCurrentUser().getId()); 
     
      this.basedao.insert(PubConstants.USER_INFO, this.entity);
      String funcs=Struts2Utils.getParameter("funcs");
      List<DBObject>list=new ArrayList<DBObject>(); 
      if(StringUtils.isNotEmpty(funcs)){
    	  String[]lsfunc=funcs.split(",");
    	  for (String string : lsfunc) {
			if(StringUtils.isNotEmpty(string)){
				DBObject  db=basedao.getMessage(PubConstants.PUB_FROMUSERFUNC, Long.parseLong(string));
				list.add(db);
			}
		}
    	  
      }
      list=ListUtil.sort(list, "sort");
      String  fxmb=Struts2Utils.getParameter("mb"); 
      Fromusermb   mb=new Fromusermb();
      mb.set_id(_id);
      mb.setCustid(_id);
      mb.setLsfunc(list);
      if(StringUtils.isNotEmpty(fxmb)){
    	  mb.setMb(Integer.parseInt(fxmb));
      }
      basedao.insert(PubConstants.PUB_FROMUSERMB, mb);
      addActionMessage("添加成功");
    }
    catch (Exception e) {
      e.printStackTrace();
      addActionMessage("添加失败");
    }
    return RELOAD;
  }

  public String delete() throws Exception
  {
    try
    {   HashMap<String, Object>whereMap=new HashMap<String, Object>();
        whereMap.put("_id", _id);
        whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
    	
        this.basedao.delete(PubConstants.USER_INFO,whereMap);
        addActionMessage("删除失败");
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
      addActionMessage("删除成功");
    }
    return RELOAD;
  }
   
  protected void prepareModel()
    throws Exception
  {
    if (this._id != null)
    {
      DBObject emDbObject = this.basedao.getMessage(PubConstants.USER_INFO, this._id);
      this.entity = ((UserInfo)UniObject.DBObjectToObject(emDbObject, UserInfo.class));
    } else {
      this.entity = new UserInfo();
    }
  }
 
  public UserInfo getModel()
  {
    return this.entity;
  }
  public void upd() throws Exception {
	    
		DBObject db = basedao.getMessage(PubConstants.USER_INFO, _id);
		List<DBObject>list=wwzservice.getfromusermb(_id);
		db.put("funclist", list);
		db.put("mb",wwzservice.getfromusermbs(_id).get("mb"));
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
  } 

  public String main() {
		//qqfromUser = getQqfromUser();
		//HashMap<String, Object> whereMap = new HashMap<String, Object>();
		//whereMap.put("qqfromUser", qqfromUser);
		//DBObject db = wwzservice.getWxUser(whereMap);
		//Struts2Utils.getRequest().setAttribute("entity", db);
 
		return "main";
 }
  
  public void  ajaxsave() throws Exception{
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		HashMap<String,Object>whereMap = new HashMap<>();
		try {
			String  id=Struts2Utils.getParameter("id");
			String  account=Struts2Utils.getParameter("account");
			String  password=Struts2Utils.getParameter("password"); 
			String  nickname=Struts2Utils.getParameter("nickname"); 
			String  toUser=Struts2Utils.getParameter("toUser");
			String  roleid=Struts2Utils.getParameter("roleid");
			String  type=Struts2Utils.getParameter("type");
			String  funcs=Struts2Utils.getParameter("funcs");
			String  fxmb=Struts2Utils.getParameter("mb");
			String  area=Struts2Utils.getParameter("area");
			String  province=Struts2Utils.getParameter("province");
			String  city=Struts2Utils.getParameter("city");
			String  agentLevel=Struts2Utils.getParameter("agentLevel");
			String  number=Struts2Utils.getParameter("number");
			String  renumber=Struts2Utils.getParameter("renumber");
			UserInfo  user=new UserInfo();
			if(StringUtils.isEmpty(id)){
				id=UUID.randomUUID().toString();
				user.set_id(id);
				if(StringUtils.isNotEmpty(agentLevel)){
					if(StringUtils.isNotEmpty(renumber)){
						this.commend(Integer.parseInt(agentLevel), user.get_id().toString(), Long.parseLong(renumber));
					}else{
						this.commend(Integer.parseInt(agentLevel), user.get_id().toString(), null);
					}
				}
			}
			user.set_id(id);
			user.setAccount(account);
			user.setPassword(password);
			user.setToUser(toUser);
			user.setNickname(nickname);
			user.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			user.setArea(area);
			user.setProvince(province);
			user.setCity(city);
			if(StringUtils.isNotEmpty(roleid)){
				user.setRoleid(Long.parseLong(roleid));	
			}
			if(StringUtils.isNotEmpty(renumber)){
				user.setRenumber(Long.parseLong(renumber));
				whereMap.put("number", Long.parseLong(renumber));
				DBObject dbObject = basedao.getMessage(PubConstants.USER_INFO, whereMap);
				if(dbObject != null){
					user.setParentId(dbObject.get("_id").toString());
				}
			}
			if(StringUtils.isNotEmpty(agentLevel)){
				user.setAgentLevel(Integer.parseInt(agentLevel));
			}
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(type)){
				user.setType(Integer.parseInt(type));	
			}
			if(number == null||number.equals("")){
				user.setNumber(Long.parseLong(getVipNo()));
			}else{
				user.setNumber(Long.parseLong(number));
			}
			user.setCreatedate(new Date());
			
			basedao.insert(PubConstants.USER_INFO, user);
			
			
			List<DBObject>list=new ArrayList<DBObject>();
			  if(StringUtils.isNotEmpty(funcs)){
				  String[]lsfunc=funcs.split(",");
				  System.out.println("funcs--->"+funcs);
				  for (String string : lsfunc) {
					if(StringUtils.isNotEmpty(string)){
						DBObject  db=basedao.getMessage(PubConstants.PUB_FROMUSERFUNC, Long.parseLong(string));
						list.add(db);
					}
				}
				  
			  }
			  Fromusermb   mb=new Fromusermb();
			  mb.set_id(id);
			  mb.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			  mb.setLsfunc(list); 
			  if(StringUtils.isNotEmpty(fxmb)){
				  mb.setMb(Integer.parseInt(fxmb));
			  }
			  basedao.insert(PubConstants.PUB_FROMUSERMB, mb);
			  sub_Map.put("state", 0); 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			sub_Map.put("state", 1);
			e.printStackTrace();
		}
	  	String json = JSONArray.fromObject(sub_Map).toString();
		 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	  
	}
	/**
	 * ajax保存
	 */
	public void  ajaxwebsave(){
		
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		try {
			String  id=Struts2Utils.getParameter("id");
			String  account=Struts2Utils.getParameter("account");
			String  password=Struts2Utils.getParameter("password"); 
			String  nickname=Struts2Utils.getParameter("nickname"); 
			String  toUser=Struts2Utils.getParameter("toUser");
			String  roleid=Struts2Utils.getParameter("roleid");
			String  type=Struts2Utils.getParameter("type");
			String  email=Struts2Utils.getParameter("email");
			String  tel=Struts2Utils.getParameter("tel");
			String  cid=Struts2Utils.getParameter("cid");
			String  funcs=Struts2Utils.getParameter("funcs");
			String  qq=Struts2Utils.getParameter("qq"); 
			if(StringUtils.isNotEmpty(cid)&&StringUtils.isNotEmpty(account)&&StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(email)){
				HashMap<String,Object>whereMap= new HashMap<String, Object>();
				whereMap.put("account", account);
				List<DBObject>list=basedao.getList(PubConstants.USER_INFO, whereMap, null);
				if(list.size()==0){
					if(StringUtils.isEmpty(id)){
						id=UUID.randomUUID().toString();
					}
					UserInfo  user=new UserInfo();
					user.set_id(id);
					user.setAccount(account);
					user.setPassword(password);
					user.setToUser(toUser);
					user.setNickname(nickname);
					if(StringUtils.isEmpty(nickname)){
						user.setNickname(account);	
					}
					user.setTel(tel);
					user.setCustid(cid);
					if(StringUtils.isNotEmpty(roleid)){
						user.setRoleid(Long.parseLong(roleid));	
					}
					if(StringUtils.isNotEmpty(type)){
						user.setType(Integer.parseInt(type));	
					}
					user.setQq_id(qq);
					basedao.insert(PubConstants.USER_INFO, user);
					/**List<DBObject>list=new ArrayList<DBObject>();
					  if(StringUtils.isNotEmpty(funcs)){
						  String[]lsfunc=funcs.split(",");
						  for (String string : lsfunc) {
							if(StringUtils.isNotEmpty(string)){
								DBObject  db=basedao.getMessage(PubConstants.PUB_FROMUSERFUNC, Long.parseLong(string));
								list.add(db);
							}
						}
						  
					  }
					  Fromusermb   mb=new Fromusermb();
					  mb.set_id(id);
					  mb.setCustid(SpringSecurityUtils.getCurrentUser().getId());
					  mb.setLsfunc(list);
					  basedao.insert(PubConstants.PUB_FROMUSERMB, mb);*/
					  sendemail(email,account);
					  sub_Map.put("state", 0); 
				}else{
					 sub_Map.put("state", 2);
					 String json = JSONArray.fromObject(sub_Map).toString();
					 
					 Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
					 return;
					
				}
				
			}else{
				 sub_Map.put("state", 1);
				 String json = JSONArray.fromObject(sub_Map).toString();
				 
				 Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
				 return;
			}
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			sub_Map.put("state", 1);
			e.printStackTrace();
		}
	  	String json = JSONArray.fromObject(sub_Map).toString();
		 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	  
	}
	/**
	 * 获取用户数据
	 */
	public  void   getusercount(){
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>();
		int usertype=SpringSecurityUtils.getCurrentUser().getType();
		if(usertype==2){
		custid=SpringSecurityUtils.getCurrentUser().getId();
		if(StringUtils.isNotEmpty(custid))
		 {
		 Pattern pattern = Pattern.compile("^.*" + custid + ".*$",
		  Pattern.CASE_INSENSITIVE);
			whereMap.put("custid", pattern); 
		 } 
		 Long count=basedao.getCount(PubConstants.DATA_WXUSER, whereMap);
		 sub_map.put("count", count);
		 String rq=BaseDate.RelativeDate(BaseDate.getDate(),-1);
		 whereMap.put("createdate",  new BasicDBObject("$lte",DateFormat.getFormat(rq + " 23:59:59")).append("$gte", DateFormat.getFormat(rq + " 00:00:00")));
		 long zrcount=basedao.getCount(PubConstants.DATA_WXUSER, whereMap);
		 sub_map.put("zcount", zrcount);
		 whereMap.put("createdate", new BasicDBObject("$gt", BaseDate.getDay(new Date(), -30)));
	     long bycount=basedao.getCount(PubConstants.DATA_WXUSER, whereMap);
		 sub_map.put("ycount", bycount);
		 
		 String json = JSONArray.fromObject(sub_map).toString();
	     Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		 
		} 
		
	}
	
	/**
	 * 获取商城数据
	 */
	public  void   getshopcount(){
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>();
		int usertype=SpringSecurityUtils.getCurrentUser().getType();
		custid=SpringSecurityUtils.getCurrentUser().getId();
		if(usertype==2){
		 whereMap.put("custid",custid );
		 Long count=basedao.getCount(PubConstants.WX_ORDERFORM, whereMap);
		 sub_map.put("count", count);
		 String rq=BaseDate.RelativeDate(BaseDate.getDate(),-1);
		 whereMap.put("createdate",  new BasicDBObject("$lte",DateFormat.getFormat(rq + " 23:59:59")).append("$gte", DateFormat.getFormat(rq + " 00:00:00")));
		 long zrcount=basedao.getCount(PubConstants.WX_ORDERFORM, whereMap);
		 sub_map.put("zcount", zrcount);
		 whereMap.put("createdate", new BasicDBObject("$gt", BaseDate.getDay(new Date(), -30)));
	     long bycount=basedao.getCount(PubConstants.WX_ORDERFORM, whereMap);
		 sub_map.put("ycount", bycount);
		 sub_map.put("reading", wwzservice.getFlow(custid, "shop"));
		 String json = JSONArray.fromObject(sub_map).toString();
	     Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		 
		} 
		
	}
	/**
	 * 网站模块流量统计
	 * @throws Exception
	 */
	public void getHmenuReading() throws Exception {
		Map<String, Object> sub_map = new HashMap<String, Object>();
		custid=SpringSecurityUtils.getCurrentUser().getId();
		HashMap<String, Object> sortMap =new HashMap<String, Object>();
		HashMap<String, Object> whereMap =new HashMap<String, Object>();
		HashMap<String, Object> backMap =new HashMap<String, Object>();
		
		backMap.put("_id", 1);
		backMap.put("name", 1);
		whereMap.put("custid", custid);
		Pattern pattern = Pattern.compile("^.*wxmenu-.*$",Pattern.CASE_INSENSITIVE);
		whereMap.put("type", pattern);
		sortMap.put("count", -1);
		
		List<DBObject> list=basedao.getList(PubConstants.WWZ_FLOW,whereMap,0,20, sortMap);
		
		List<HashMap<String, Object>> list1=new ArrayList<HashMap<String, Object>>();
		
		for(DBObject db:list){
			HashMap<String, Object> m=new HashMap<String, Object>();
			
			String type=db.get("type").toString().replace("wxmenu-", "");
			String title=GetAllFunc.wxtitle.get(custid + type);
			if(StringUtils.isEmpty(type)||StringUtils.isEmpty(title)){
				m.put("y", type);
			}else{
				m.put("y", title);
			}
			
			m.put("a", (Integer)db.get("count"));
			list1.add(m);
		}
		sub_map.put("list", list1);
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	/**
	 * 发送邮件
	 * @param email
	 * @param id
	 */
	public  void   sendemail(String email,String id){
		Email  emailInfo=new Email();
		try {
			emailInfo.setFromAddress(javax.mail.internet.MimeUtility.encodeText("熊猫商城")+" "+SysConfig.getProperty("eaddress"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		emailInfo.setToAddress(email); 
		emailInfo.setUserName(SysConfig.getProperty("eusername"));
		emailInfo.setPassword(SysConfig.getProperty("epassword"));
		emailInfo.setType("text/html;charset=UTF-8");
		emailInfo.setHost(SysConfig.getProperty("ehost"));
		emailInfo.setHostType("smtp");
		emailInfo.setSubject("恭喜您成为熊猫商城的会员");
		String content="";
		content+="<div style='padding: 20px;'><font size='3'><div>亲爱的用户"+id+":</div></font>" 
		       +"<div style='padding-top: 10px;padding-bottom: 10px;'>您好！欢迎您加入熊猫，请立即点击下列按钮（链接）激活您的帐号。</div>"
			   +"<a href='"+SysConfig.getProperty("ip")+"/user/user!activate.action?id="+id+"'><div style='padding-bottom: 10px;'>此处是验证连接</div></a>"
		       +"<div style='padding-bottom: 10px;'>此信息由系统自动发送，请勿回复！</div></div>";
		emailInfo.setContent(content);
		try {
			EmailUtils.sendHtmlEmail(emailInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 激活账号
	 */
	public String activate(){
		String  account=Struts2Utils.getParameter("id"); 
		HashMap<String,Object>whereMap=new HashMap<String, Object>();
		whereMap.put("account",account);
		List<DBObject>list=basedao.getList(PubConstants.USER_INFO, whereMap,null);
		if(list.size()==1&&list.get(0).get("createdate")==null){
			whereMap.clear();
			whereMap.put("rolename","免费用户");
			List<DBObject> lsrl=basedao.getList(PubConstants.ROLE_INFO, whereMap, null);
			
			UserInfo  info=(UserInfo) UniObject.DBObjectToObject(list.get(0), UserInfo.class);
			info.setCreatedate(new Date());
			if(lsrl.size()>0){
				info.setRoleid(Long.parseLong(lsrl.get(0).get("_id").toString()));
			}
			basedao.insert(PubConstants.USER_INFO, info); 
		}
		return "activate"; 
	}
	/**
	 * 激活账号
	 */
	public void   ajaxactivate(){
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		String  account=Struts2Utils.getParameter("account");
		HashMap<String,Object>whereMap=new HashMap<String, Object>();
		whereMap.put("account",account);
		List<DBObject>list=basedao.getList(PubConstants.USER_INFO, whereMap,null);
		if(list.size()==1){
			UserInfo  info=(UserInfo) UniObject.DBObjectToObject(list.get(1), UserInfo.class);
			info.setCreatedate(new Date());
			basedao.insert(PubConstants.USER_INFO, info);
			sub_Map.put("state",0);
		}
		String json = JSONArray.fromObject(sub_Map).toString();
	    Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	/**
	 * 注册
	 * @return
	 */
	public String  register(){
		//加载区域（默认）
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		whereMap.put("account", "admin");
		DBObject  db=basedao.getMessage(PubConstants.USER_INFO, whereMap);
		if(db!=null){
			Struts2Utils.getRequest().setAttribute("id", db.get("_id"));
		} 
		return "register"; 
	}
	/**
	 * 生成会员编号
	 * @return
	 */
	public  String  getVipNo(){
		String vipno=null;
		Long  count=basedao.getCount(PubConstants.USER_INFO);
		while (true) { 
			if(count.toString().length()>=5&&Double.parseDouble(count.toString())>=Math.pow(10,Double.parseDouble(count.toString())+1)-10000){
				vipno=UserUtil.createVipNo(count.toString().length()+1);
			}else{
				vipno=UserUtil.createVipNo(5);
			}
			//鎺掓煡棣栦綅涓�0锛�
			if(!vipno.startsWith("0")){
				//妫�鏌ユ槸鍚﹀敮涓�
				HashMap<String, Object>whereMap=new HashMap<String, Object>();
				whereMap.put("no", vipno);
				Long num=basedao.getCount(PubConstants.USER_INFO,whereMap);
				if(num==0){
					break;
				} 
			}
		}
		return vipno;
	}
	
	/**
	 * 开通代理商账号  
	 * @param type 角色类型
	 * @param custid 当前用户id
	 * @param number 用户编号
	 * @throws Exception
	 */
	public void commend(int type,String custid,Long number) throws Exception{
		HashMap<String,Object> whereMap = new HashMap<>();
		whereMap.put("number", number);
		DBObject user = basedao.getMessage(PubConstants.USER_INFO, whereMap);
		
		System.out.println("user---->"+user.get("_id").toString());
		
		DBObject db = basedao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		InteSetting sett = (InteSetting) UniObject.DBObjectToObject(db, InteSetting.class);
		if(db!=null){
			//预付
			InteProstore info = new InteProstore();
			
			info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
			info.setType("ps_account");//开通账户 
	        info.setCreatedate(new Date()); 
			info.setFromUserid(custid);
			info.setState(0); 
			//1-省  2-市  3-县   4-部门  5-会员  6-会员的下级会员
			String any = "";
			if(db.get("any")!=null){
				any = BaseDecimal.division(db.get("any").toString(), "100", 6);
			}
			if(type == 1){
				if(db.get("returnProvince")!=null){
					info.setMoney(Float.valueOf(BaseDecimal.multiplication(db.get("returnProvince").toString(), "3")));
					System.out.println("钱数----》"+info.getMoney());
					info.setTime(Integer.parseInt(sett.getProvinceTime()+""));
					info.setEnddate(DateUtil.addDay(new Date(), Integer.parseInt(sett.getProvinceTime()+"")));
					if(user!=null){
						//推荐收益
						String total = BaseDecimal.multiplication(db.get("returnProvince").toString(), any);
						wwzservice.addjf(total, user.get("_id").toString(), "tj_account", custid, 1, 1, 0);
					}
				}
			}else if(type == 2){
				if(db.get("returnCity")!=null){
					info.setMoney(Float.valueOf(BaseDecimal.multiplication(db.get("returnCity").toString(), "3")));
					info.setTime(Integer.parseInt(sett.getCityTime()+""));
					info.setEnddate(DateUtil.addDay(new Date(), Integer.parseInt(sett.getCityTime()+"")));
					if(user!=null){
						//推荐收益
						String total = BaseDecimal.multiplication(db.get("returnCity").toString(), any);
						wwzservice.addjf(total, user.get("_id").toString(), "tj_account", custid, 1, 1, 0);
					} 
				}
			}else if(type == 3){
				if(db.get("returnCounty")!=null){
					info.setMoney(Float.valueOf(Long.parseLong(db.get("returnCounty").toString())*3));
					info.setTime(Integer.parseInt(sett.getCountyTime()+""));
					info.setEnddate(DateUtil.addDay(new Date(), Integer.parseInt(sett.getCountyTime()+"")));
					if(user!=null){
						//推荐收益
						String total = BaseDecimal.multiplication(db.get("returnCounty").toString(), any);
						wwzservice.addjf(total, user.get("_id").toString(), "tj_account", custid, 1, 1, 0);
					}
					
				}
			}else if(type == 4){
				if(db.get("returnDept")!=null){
					info.setMoney(Float.valueOf(Long.parseLong(db.get("returnDept").toString())*3));
					info.setTime(Integer.parseInt(sett.getDeptTime()+""));
					info.setEnddate(DateUtil.addDay(new Date(), Integer.parseInt(sett.getDeptTime()+"")));
					if(user!=null){
						//推荐收益
						String total = BaseDecimal.multiplication(db.get("returnDept").toString(), any);
						wwzservice.addjf(total, user.get("_id").toString(), "tj_account", custid, 1, 1, 0);
					}
				}
			}
			basedao.insert(PubConstants.INTEGRAL_PROSTORE, info);
		}
		
	}
   
	public void deleteall(){
		basedao.delete(PubConstants.INTEGRAL_INFO);
		
		basedao.delete(PubConstants.INTEGRAL_PROSTORE);
	}
	public void ceshi(){
		System.out.println("---->"+DateUtil.getTimesnight());
	}
	/**
	 * 手动确认回本state（1.其他异常2.回本ID不存在3.回本账户为空0.冻结回本账户成功）
	 */
	public void confirmBack() {
		HashMap<String, Object>sub_Map=new HashMap<>();
		sub_Map.put("state",1);
		SpringSecurityUtils.getCurrentUser().getId();
		//回本ID
		String selfromid=Struts2Utils.getParameter("selfromid");
		if(StringUtils.isNotEmpty(selfromid)) {
			HashMap<String, Object>whereMap=new HashMap<>();
			whereMap.put("fromUserid",selfromid);
			whereMap.put("type","ps_account");
			DBObject db=basedao.getMessage(PubConstants.INTEGRAL_PROSTORE, whereMap);
			if(db!=null) {
				//冻结回本账户
				InteProstore inteProstore=(InteProstore) UniObject.DBObjectToObject(db, InteProstore.class);
				inteProstore.setState(2);
				inteProstore.setEnddate(new Date());
				basedao.insert(PubConstants.INTEGRAL_PROSTORE, inteProstore); 
				//冻结成功
				sub_Map.put("state",0);
				
			}else {
				//回本账户不存在
				sub_Map.put("state",3);
			}
		}else {
			//回本ID为空
			sub_Map.put("state",2);
		}
		 
		String json = JSONArray.fromObject(sub_Map).toString();
	    Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		
	}
	
}