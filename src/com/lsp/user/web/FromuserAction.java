package com.lsp.user.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.activemq.filter.function.splitFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Code;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.WxToken;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.JmsService;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.util.UserUtil;
import com.lsp.pub.util.WeiXinUtil;
import com.lsp.pub.web.GeneralAction;
import com.lsp.suc.entity.Comunit;
import com.lsp.user.entity.CustomerInfo;
import com.lsp.user.entity.UserInfo;
import com.lsp.website.service.WwzService;
import com.lsp.websocket.ChatServer;
import com.lsp.websocket.service.WebsoketListen;
import com.lsp.weixin.entity.WxUser;
import com.lsp.weixin.entity.WxUserToken;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
/**
 * 手机用户管理
 * @author lsp
 *
 */
@Namespace("/user")
@Results({@org.apache.struts2.convention.annotation.Result(name="reload", location="fromuser.action",params={"fypage", "%{fypage}","nickname", "%{nickname}"}, type="redirect")})
public class FromuserAction extends GeneralAction<WxUser>{
	 private static final long serialVersionUID = -6784469775589971579L;

	  @Autowired
	  private BaseDao basedao;
	  private String _id;
	  private WxUser entity = new WxUser();
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
		    custid=SpringSecurityUtils.getCurrentUser().getId();
		    
		    if(StringUtils.isNotEmpty(custid))
			{
		    	if(custid.equals(SysConfig.getProperty("custid"))){
			    	
			    }else{
			    	Pattern pattern = Pattern.compile("^.*" + custid + ".*$",
							Pattern.CASE_INSENSITIVE);
					whereMap.put("custid", pattern); 
			    }
				
			} 
			String  nickname=Struts2Utils.getParameter("nickname");
			if(StringUtils.isNotEmpty(nickname))
			{
				Pattern pattern = Pattern.compile("^.*" + nickname + ".*$",
						Pattern.CASE_INSENSITIVE);
				whereMap.put("nickname", pattern);
				Struts2Utils.getRequest().setAttribute("nickname",  nickname);
			}
			String  isline=Struts2Utils.getParameter("isline");
			if(StringUtils.isNotEmpty(isline))
			{  
				if(Integer.parseInt(isline)==1){
					whereMap.put("online", Integer.parseInt(isline));
				}else if (Integer.parseInt(isline)==0) {
					BasicDBList   dblist=new BasicDBList(); 
					dblist.add(new BasicDBObject("online",null));
					dblist.add(new BasicDBObject("online",0));
					//or判断
					whereMap.put("$or", dblist); 
				}
				
				Struts2Utils.getRequest().setAttribute("isline",  isline);
			}
		    sortMap.put("createdate", Integer.valueOf(-1));
			if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
				fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
			} 
		    List<DBObject> list = this.basedao.getList(PubConstants.DATA_WXUSER, whereMap,fypage,10,sortMap);
		    Struts2Utils.getRequest().setAttribute("userList", list);
		    for (DBObject dbObject : list) { 
		    	if(dbObject.get("_id")!=null){
		    		DBObject  code=basedao.getMessage(PubConstants.USER_AUTHCODE,dbObject.get("_id").toString());
		    		if(code!=null){
			    		dbObject.put("activitydate", code.get("activitydate"));
			    	}
		    		dbObject.put("logindate",wwzservice.getlogin(custid, dbObject.get("_id").toString()));
		    	}
		    	
		    	
			}
		    this.fycount = this.basedao.getCount(PubConstants.DATA_WXUSER,whereMap); 
		    Struts2Utils.getRequest().setAttribute("custid",SpringSecurityUtils.getCurrentUser().getId());
		  
		  return SUCCESS;
	  }
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void upd() throws Exception {
		DBObject db = basedao.getMessage(PubConstants.DATA_WXUSER, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub  
	     DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, _id);
	     if(db!=null){
	    	 WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
	    	 user.setPassword(Struts2Utils.getParameter("password"));
	    	 basedao.insert(PubConstants.DATA_WXUSER, user); 
	     }
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		try { 
			custid=SpringSecurityUtils.getCurrentUser().getId();
			basedao.delete(PubConstants.DATA_WXUSER,_id);
			/*HashMap<String, Object>whereMap=new HashMap<String, Object>(); 
			whereMap.put("_id",_id);
			if(StringUtils.isNotEmpty(custid)){
				Pattern pattern = Pattern.compile("^.*" + custid + ".*$",
						Pattern.CASE_INSENSITIVE);
				whereMap.put("custid", pattern); 	
			}
			DBObject db=basedao.getMessage(PubConstants.DATA_WXUSER, whereMap);
			if(db!=null){
				WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
				user.setCustid(user.getCustid().replace(custid+",", ""));
				basedao.insert(PubConstants.DATA_WXUSER, user);
			}*/ 
			addActionMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		try {
			if(_id!=null){
				DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, _id);
				entity=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
			}else{
				
				entity=new WxUser();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addActionMessage("删除失败");
		}
		
	}
	@Override
	public WxUser getModel() {
		// TODO Auto-generated method stub
		return entity;
	}
	 public String UserDetail(){ 
		  getLscode();  
		  Struts2Utils.getRequest().setAttribute("custid",custid );
		  System.out.println(custid);
		  WxToken token=GetAllFunc.wxtoken.get(custid);
		  System.out.println(token);
			 if(token.getSqlx()>0){
				 token=GetAllFunc.wxtoken.get(wwzservice.getparentcustid(custid)); 
			 } 
			Struts2Utils.getRequest().setAttribute("token",WeiXinUtil.getSignature(token,Struts2Utils.getRequest()));
			token=WeiXinUtil.getSignature(token,Struts2Utils.getRequest()); 
			String  url=SysConfig.getProperty("ip")+"/user/fromuser!UserDetail.action?custid="+custid;  
			if(StringUtils.isEmpty(fromUserid)){ 
				String inspection="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+token.getAppid()+"&redirect_uri="+URLEncoder.encode(url)+"&response_type=code&scope=snsapi_base&state=c1c2j3h4#wechat_redirect";
				Struts2Utils.getRequest().setAttribute("inspection",inspection);  
				return "refresh";
			}else if(fromUserid.equals("register")){ 
				String inspection="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+token.getAppid()+"&redirect_uri="+URLEncoder.encode(url)+"&response_type=code&scope=snsapi_userinfo&state=register#wechat_redirect";
				Struts2Utils.getRequest().setAttribute("inspection",inspection);  
				return "refresh";
			}  
		  
		  //优先使用用户ID查询
		  DBObject wxUser=new BasicDBObject();
		  if(StringUtils.isNotEmpty(fromUserid)){
			  HashMap<String, Object>whereMap=new HashMap<String, Object>();
			  whereMap.put("_id", fromUserid);
			  wxUser=wwzservice.getWxUser(whereMap);
			  whereMap.clear();
			  whereMap.put("fromUserid",fromUserid);
			  whereMap.put("custid", custid);
			  Long bbscount=basedao.getCount(PubConstants.BBS_INFO, whereMap);
			  whereMap.clear();
			  whereMap.put("fromUserid",fromUserid);
			  Long tackcount=basedao.getCount(PubConstants.SUC_TASK);
			  //wxUser.put("bbscount",bbscount); 
			  wxUser.put("tackcount",tackcount); 
			  if(wxUser.get("getExperience")!=null&&wxUser.get("needExperience")!=null) {
				  double bl= Double.parseDouble(wxUser.get("getExperience").toString())/Double.parseDouble(wxUser.get("needExperience").toString());   
				  wxUser.put("expbl",new java.text.DecimalFormat("#").format(bl*100)); 
			  } 
			  //积分 
			  if (wwzservice.getJfOBJ(SysConfig.getProperty("custid"), fromUserid)!=null) {
				  Struts2Utils.getRequest().setAttribute("jf",wwzservice.getJfOBJ(SysConfig.getProperty("custid"), fromUserid).get("uservalue") );
			  }
			
			  Struts2Utils.getRequest().setAttribute("entity", wxUser);
		  }else{
			 
			  if(StringUtils.isEmpty(qqfromUser)){
				  //未登录
				  return "fromlogin";  
			  }else{
				  //根据QQ登录信息查询  
				  HashMap<String, Object>whereMap=new HashMap<String, Object>();
				  whereMap.put("qqfromUser", qqfromUser);
				  wxUser=wwzservice.getWxUser(whereMap);
				  whereMap.put("custid", custid);
				  Long count=basedao.getCount(PubConstants.BBS_INFO, whereMap);
				  wxUser.put("bbscount",count);
				  Struts2Utils.getRequest().setAttribute("entity", wxUser);
				  
				  
			  }
			  
		  }
		  //加载菜单   
		  DBObject  mb=wwzservice.getfromusermbs(custid);
		  Struts2Utils.getRequest().setAttribute("func",mb);  
		  
		  DBObject share=wwzservice.getShareFx(custid,"fromuser_share"); 
		  if(share==null){
			  share=new BasicDBObject();
		  }
		  if(GetAllFunc.wxTouser.get(custid)!=null){
			  share.put("fximg",GetAllFunc.wxTouser.get(custid).getLogo());
		  }
		  
		  share.put("fxurl",url);  
		  Struts2Utils.getRequest().setAttribute("share", share);
		  //加载佣金
		  DBObject  agent=wwzservice.getAgentPrice(custid, fromUserid);
		  Struts2Utils.getRequest().setAttribute("agent",agent);
		  //检测代理 
		  if(wwzservice.checkAgent(custid,fromUserid)){
			 Struts2Utils.getRequest().setAttribute("isAgent","ok");
		  }
		  
		  /**获取商品关注、收藏， 店铺关注、收藏数量***/
		  HashMap<String, Object> whereMap=new HashMap<String, Object>();
		 whereMap.put("fromUserid", fromUserid);
		 Long productattentionCount=basedao.getCount(PubConstants.SHOP_PRODUCTATTENTION, whereMap);
		 Long productcollectCount=basedao.getCount(PubConstants.SHOP_PRODUCTCOLLET, whereMap);
		 //店铺关注 收藏
		 Long shopattentionCount=basedao.getCount(PubConstants.SHOP_SHOPATTENTION, whereMap);
		 Long shopcollectCount=basedao.getCount(PubConstants.SHOP_SHOPCOLLECT, whereMap);
		 Struts2Utils.getRequest().setAttribute("productattentionCount",productattentionCount);
		 Struts2Utils.getRequest().setAttribute("productcollectCount",productcollectCount);
		 
		 Struts2Utils.getRequest().setAttribute("shopattentionCount",shopattentionCount);
		 Struts2Utils.getRequest().setAttribute("shopcollectCount",shopcollectCount);
		 
		 /*********2018/7/14 获取当前用户代理************************/
		 String daili="";
		 UserInfo user = (UserInfo) UniObject.DBObjectToObject(wxUser,UserInfo.class);
		 if(user!=null) {
			 //1-省  2-市  3-县   4-部门  5-会员  6-会员的下级会员
			 int agentLevel = user.getAgentLevel();
			 
			 String agentprovince=user.getAgentprovince();
			 String agentcity = user.getAgentcity();
			 String agentcounty =user.getAgentcounty();
			 if(agentLevel==1) {
				 daili=agentprovince+"代理";
			 }else if(agentLevel==2) {
				 daili=agentprovince+agentcity+"代理";
			 }else if(agentLevel==3) {
				 daili=agentprovince+agentcity+agentcounty+"代理";
			 }else if(agentLevel==4) {
				 daili="报单中心";
			 }else if(agentLevel==5) {
				 daili="会员";
			 }else if(agentLevel==6) {
				 daili="商家";
			 }
		 }
		 Struts2Utils.getRequest().setAttribute("daili", daili);
		 
		 
		 if(mb!=null&&mb.get("mb")!=null){
			  return "detail"+mb.get("mb");  
		  }
		return "detail2";   
	  }
	 public String register(){
		  
		return "register";   
	 }
	 /**
	  * ajax注册
	  */
	 public void  ajaxregister(){
		//邮箱用作登录账户方便邮箱验证
		String  name=Struts2Utils.getParameter("name");
		String  toUser=Struts2Utils.getParameter("toUser");
		String  custid=Struts2Utils.getParameter("custid");
		String  password=Struts2Utils.getParameter("password"); 
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		//验证用户名是否唯一
		if(wwzservice.checkName(name)){
			try {
				if(StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(password)){
					 WxUser user=new WxUser();
					 user.set_id(UUID.randomUUID().toString());
					 user.setLoginname(name); 
					 //user.setNo(wwzservice.getVipNo());
					 user.setLoginpasswd(password);
					 user.setCreatedate(new Date());
					 user.setNickname("江湖大虾!");
					 user.setEmail(name);
					 user.setToUser(toUser);
					 user.setCustid(custid);
					 basedao.insert(PubConstants.DATA_WXUSER, user);
					 sub_Map.put("state",0);
					 sub_Map.put("value", user.get_id().toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				sub_Map.put("state", 1);
				e.printStackTrace();
			}
		}else{
			sub_Map.put("state", 2); 
		}
		
		String json = JSONArray.fromObject(sub_Map).toString();
		 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		
		 
	 }
	 /**
	  * ajax登录
	  */
	 public void  ajaxlongin(){
		 String  name=Struts2Utils.getParameter("name");
		 String  password=Struts2Utils.getParameter("password");
		 HashMap<String, Object>whereMap=new HashMap<String, Object>();
		 Map<String, Object>sub_Map=new HashMap<String, Object>();
		 try {
			
			 whereMap.put("loginname", name);
			 DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, whereMap);
			 if(db!=null){
				 if(db.get("loginpasswd").equals(password)){
					sub_Map.put("state", 0);
					sub_Map.put("value", db.get("_id").toString());
				 }
			 }
		} catch (Exception e) {
			sub_Map.put("state", 1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = JSONArray.fromObject(sub_Map).toString();
		 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		 
	 }
	 /**
	  * 微信授权登录
	  */
	 public  String   wxlogin(){
		 getLscode();
		 String number = Struts2Utils.getParameter("number");
		 WxUserToken token=GetAllFunc.usertoken.get(fromUser);
		 //fromUserid=wwzservice.register(fromUser,token,custid);
		 fromUserid=wwzservice.registerCommend(fromUser,token,custid,number); 
		 if(StringUtils.isNotEmpty(fromUserid)){
			 lscode=wwzservice.createcode(fromUserid);	 
		 }
		 Struts2Utils.getRequest().setAttribute("lscode", lscode);
		 Struts2Utils.getRequest().setAttribute("custid", custid);
		 return "wxlogin";
	 }
	 /**
	  * ajax返回登录url
	  */
	 public void  getloginurl(){
		 custid=getCustid();
		 Map<String, Object> submap = new HashMap<String, Object>();
		try {
			 WxToken token=GetAllFunc.wxtoken.get(custid); 
			 if(token.getSqlx()>0){
				 token=GetAllFunc.wxtoken.get(wwzservice.getparentcustid(custid)); 
			 } 
			 
			 String surl=SysConfig.getProperty("ip")+"/user/fromuser!wxlogin.action?custid="+custid;
			 String ddurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+token.getAppid()+"&redirect_uri="+URLEncoder.encode(surl)+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		     submap.put("state", 0);
		     submap.put("value", ddurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			submap.put("state",1);
			e.printStackTrace();
		}
		 String json = JSONArray.fromObject(submap).toString(); 
		 Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	 }
	public String  qqlogin(){
		  fromUserid=getFromUserid();
		  custid=getCustid(); 
		  Struts2Utils.getRequest().setAttribute("fromUserid", fromUserid);
		  Struts2Utils.getRequest().setAttribute("custid",custid );
		return "qqlogin";
		
	}
	/**
	 * 用户信息
	 * @return
	 */
	public String  detail(){ 
		 getLscode();
		 DBObject  user=wwzservice.getWxUser(fromUserid);
		 Struts2Utils.getRequest().setAttribute("user",user);
		 Struts2Utils.getRequest().setAttribute("custid",custid); 
		return "add";
		
	}
	public void  savedetatil(){
		Map<String, Object> submap = new HashMap<String, Object>();
		 getLscode();
		 String  headimgurl=Struts2Utils.getParameter("headimgurl");
		 String  email=Struts2Utils.getParameter("email");
		 String  nickname=Struts2Utils.getParameter("nickname");
		 String  qq=Struts2Utils.getParameter("qq");
		 String  wxid=Struts2Utils.getParameter("wxid");
		 String  tel=Struts2Utils.getParameter("tel");
		 String  password=Struts2Utils.getParameter("password");
		 DBObject db=wwzservice.getWxUser(fromUserid);
		 if(db.get("_id").equals("notlogin")){
			 submap.put("state", 3);
			 return;
		 }else{
			 WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class); 
			 if(StringUtils.isNotEmpty(headimgurl)){
				 user.setHeadimgurl(headimgurl);
			 }
			 if(StringUtils.isNotEmpty(nickname)){
				 user.setNickname(nickname); 
			 }
			 if(StringUtils.isNotEmpty(email)){
				 user.setEmail(email); 
			 }
			 if(StringUtils.isNotEmpty(qq)){
				 user.setQq(qq);
			 }
			 if(StringUtils.isNotEmpty(wxid)){
				 user.setWxid(wxid); 
			 }
			 if(StringUtils.isNotEmpty(tel)){
				 user.setTel(tel);	 
			 }
			 if(StringUtils.isNotEmpty(password)){
				 user.setPassword(password);
			 }
			  
			 basedao.insert(PubConstants.DATA_WXUSER, user);
			 submap.put("state", 0);
		 } 
		 String json = JSONArray.fromObject(submap).toString(); 
		 Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	/**
	 * 设置安卓管理员
	 */
	public  void   setAndroidAdmin(){
		Map<String, Object> submap = new HashMap<String, Object>();
		custid=SpringSecurityUtils.getCurrentUser().getId();
		String id=Struts2Utils.getParameter("id");
		DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, id);
		if(db!=null){
			WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
			user.setAndroidAdmin(1);
			basedao.insert(PubConstants.DATA_WXUSER, user);
			submap.put("state", 0);
		}
		String json = JSONArray.fromObject(submap).toString(); 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	/**
	 * 取消安卓管理员
	 */
	public  void   cancelAndroidAdmin(){
		Map<String, Object> submap = new HashMap<String, Object>();
		custid=SpringSecurityUtils.getCurrentUser().getId();
		String id=Struts2Utils.getParameter("id");
		DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, id);
		if(db!=null){
			WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
			user.setAndroidAdmin(0);
			basedao.insert(PubConstants.DATA_WXUSER, user);
			submap.put("state", 0);
		}
		String json = JSONArray.fromObject(submap).toString(); 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	} 
	/**
	 * 设置用户密码
	 */
	public  void  setfromUserpasswd(){
		Map<String, Object> submap = new HashMap<String, Object>();
		custid=SpringSecurityUtils.getCurrentUser().getId();
		String  password=Struts2Utils.getParameter("password");
		String  id=Struts2Utils.getParameter("id");
		if(StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(id)){
			DBObject  db=basedao.getMessage(PubConstants.DATA_WXUSER, id);
			if(db!=null){
				WxUser  user=(WxUser) UniObject.DBObjectToObject(db, WxUser.class);
				user.setPassword(password);
				basedao.insert(PubConstants.DATA_WXUSER, user);
				submap.put("state", 0);
			}
		}
		String json = JSONArray.fromObject(submap).toString(); 
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	public String test(){
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid",custid);
		return "test";
	} 
	/**
	 * 测试账号创建
	 * @throws Exception
	 */
	public void ajaxsave() throws Exception{

		System.err.println();
	}
	
	/**
	 * 推荐用户
	 */
	public String share() throws Exception{
		getLscode();  
		DBObject  user= basedao.getMessage(PubConstants.DATA_WXUSER, fromUserid);
		System.out.println("user---->"+user);
		Struts2Utils.getRequest().setAttribute("user",user);
		Struts2Utils.getRequest().setAttribute("custid",custid);
		return "share";
	}
	
	
	
	/**
	 * 生成验证码
	 */
	public void   createTelCode() {
		HashMap<String, Object>sub_map=new HashMap<>();
		sub_map.put("state",1);
		String tel=Struts2Utils.getParameter("tel");
		String code=UserUtil.createVipNo(6);
		if (code!=null&&tel!=null) {
			System.out.println(code);
			Code code2=new Code();
			code2.setCode(code);
			code2.setCreatedate(new Date());
			code2.setType(0);
			code2.setValue(tel);
			GetAllFunc.telcode.put(tel, code2);
			boolean bl=wwzservice.sendSMS(tel, "您的验证码为"+code+"，有效时间10分钟。");
			if (bl) {
				sub_map.put("state",0);
			} 
		}
		
		String json = JSONArray.fromObject(sub_map).toString();
	    Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
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
	
	/***
	 * 注册
	 * @throws Exception
	 */
	public void signup() throws Exception{
		Map<String,Object>sub_map = new HashMap<>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sub_map.put("state", 1);
		String tel=Struts2Utils.getParameter("tel");
		String yzcode=Struts2Utils.getParameter("verCode"); 
		String password=Struts2Utils.getParameter("password"); 
		System.out.println("手机验证码-verCode--》"+yzcode);
		String userName = Struts2Utils.getParameter("username");//真实姓名
		String id_card = Struts2Utils.getParameter("id_card");//身份证号码
		String id_card_front = Struts2Utils.getParameter("id_card_front");//身份证正面照
		String id_card_reverse = Struts2Utils.getParameter("id_card_recverse");//身份证反面照
		String company_name = Struts2Utils.getParameter("company_name"); //公司名称
		String lisense_number= Struts2Utils.getParameter("lisense_number"); //营业证号码
		String lisense_photo = Struts2Utils.getParameter("lisense_photo"); //营业证照片
		String status = Struts2Utils.getParameter("status"); //状态
		
		String province = Struts2Utils.getParameter("province");//省
		String city = Struts2Utils.getParameter("city");//市
		String county = Struts2Utils.getParameter("county");//区
		
		whereMap.put("tel", tel);
		Long count =basedao.getCount(PubConstants.USER_INFO, whereMap);
		
		Code code=GetAllFunc.telcode.get(tel); 
		System.out.println("手机验证码---》"+code.getCode());
		if(count == 0){
			if (code!=null&&code.getCode().equals(yzcode)) {
				//验证时间
				if(DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(),10))) {
					if(status.equals("1")) {//个人注册
						UserInfo user = new UserInfo();
						user.set_id(UUID.randomUUID().toString());
						user.setAccount(tel);
						user.setTel(tel);
						user.setPassword(password);
						user.setCustid(SysConfig.getProperty("custid"));
						user.setRoleid(Long.parseLong(SysConfig.getProperty("sjRoleid")));
						user.setProvince(province);
						user.setCity(city);
						user.setCounty(county);
						user.setUserName(userName);
						user.setId_card_front(id_card_front);
						user.setId_card_reverse(id_card_reverse);
						user.setNumber(Long.parseLong(getVipNo()));
						basedao.insert(PubConstants.USER_INFO, user);
						String lscode=wwzservice.createcode(user.get_id().toString());
						sub_map.put("lscode", lscode);//注册成功
						sub_map.put("state", 0);//注册成功
					}else if(status.equals("2")) {//商家注册
						UserInfo user = new UserInfo();
						user.set_id(UUID.randomUUID().toString());
						user.setAccount(tel);
						user.setTel(tel);
						user.setPassword(password);
						user.setCustid(SysConfig.getProperty("custid"));
						user.setRoleid(Long.parseLong(SysConfig.getProperty("sjRoleid")));
						user.setUserName(userName);
						user.setId_card(id_card);
						user.setId_card_front(id_card_front);
						user.setId_card_reverse(id_card_reverse);
						user.setCompany_name(company_name);
						user.setLisense_number(lisense_number);
						user.setLisense_photo(lisense_photo);
						user.setProvince(province);
						user.setCity(city);
						user.setCounty(county);
						user.setNumber(Long.parseLong(getVipNo()));
						basedao.insert(PubConstants.USER_INFO, user);
						String lscode=wwzservice.createcode(user.get_id().toString());
						sub_map.put("lscode", lscode);//注册成功
						sub_map.put("state", 0);//注册成功
					}
					
				}else{
					sub_map.put("state", 4);//验证码超时
				}
			}else{
				sub_map.put("state", 3);//验证码输入错误
			}
		}else{
			sub_map.put("state", 2);//用户名已存在
		}
		
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	/**
	 * 登录
	 * @throws Exception
	 */
    public void signin() throws Exception{
    	 HashMap<String, Object> whereMap = new HashMap<String, Object>();
   	     Map<String, Object> sub_map = new HashMap<String, Object>(); 
   	     sub_map.put("state", 1);//操作失败
   	     String tel =Struts2Utils.getParameter("tel"); 
   	     String password =Struts2Utils.getParameter("password");
   	     System.err.println("--->"+tel);
   	     whereMap.put("account", tel);
  	     if (StringUtils.isNotEmpty(tel)&&StringUtils.isNotEmpty(password)) {
  		     DBObject user = basedao.getMessage(PubConstants.USER_INFO, whereMap);
  		     if(user != null){
 			   if(user.get("password").toString().equals(password)) {
                   String lscode=wwzservice.createcode(user.get("_id").toString()); 
                   sub_map.put("state", 0);//登陆成功
                   sub_map.put("lscode", lscode);
 			   }else{
 				  sub_map.put("state", 3);//密码错误
 			   }
  		    }else{
  		    	sub_map.put("state", 2);//用户不存在
  		    }
	    }
  	    String json = JSONArray.fromObject(sub_map).toString();
	    Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
    }
    
    /**
     * 忘记密码
     * @throws Exception
     */
    public void changepw() throws Exception{
  	  Map<String,Object>sub_map = new HashMap<>();
  	  sub_map.put("state", 1);
  	  HashMap<String,Object>whereMap = new HashMap<>();
  	  String tel=Struts2Utils.getParameter("tel");
  	  String yzcode=Struts2Utils.getParameter("yzcode"); 
  	  String password=Struts2Utils.getParameter("password"); 
  	  whereMap.put("tel", tel);
  	  DBObject dbObject =basedao.getMessage(PubConstants.DATA_WXUSER, whereMap);
  	  if(dbObject!=null){
  		  WxUser user = (WxUser) UniObject.DBObjectToObject(dbObject, WxUser.class);
  		  if(user.getPassword().equals(password)){
  			  Code code=GetAllFunc.telcode.get(tel); 
  				if (code!=null&&code.getCode().equals(yzcode)) { 
  					 //验证时间
  					if(DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(),10))) {
  						user.setPassword(password);
  						basedao.insert(PubConstants.DATA_WXUSER, user);
  						sub_map.put("state", 0);//修改成功
  					}else{
  						sub_map.put("state", 5);//验证码超时
  					}
  				}else{
  					sub_map.put("state", 4);//验证码错误
  				} 
  		  }else{
  			  sub_map.put("state", 3);//密码错误
  		  }
  	  }else{
  		  sub_map.put("state", 2);//该账户不存在
  	  }
  	  
  		String json = JSONArray.fromObject(sub_map).toString();
  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
    }
	 
    /**
	 * ajax用户信息完善
	 */
	public void  ajaxuserupdate(){
		getLscode();
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		try {
			
			String name =  Struts2Utils.getParameter("name");//姓名
			String tel =  Struts2Utils.getParameter("tel");//电话
			String uskd =  Struts2Utils.getParameter("uskd");//uskd账号
			String password =  Struts2Utils.getParameter("password");//密码
			String province =  Struts2Utils.getParameter("province");//省
			String city =  Struts2Utils.getParameter("city");//市
			String county =  Struts2Utils.getParameter("county");//区
			String up_picture_front =  Struts2Utils.getParameter("up_picture_front");//身份证正面照
			String up_picture_reverse =  Struts2Utils.getParameter("up_picture_reverse");//身份证反面照
			DBObject db = basedao.getMessage(PubConstants.USER_INFO, lscode);
			UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
			user.setUserName(name);
			user.setTel(tel);
			user.setAccount(tel);
			user.setUskd(uskd);
			user.setPassword(password);
			user.setProvince(province);
			user.setCity(city);
			user.setCounty(county);
			user.setId_card_front(up_picture_front);
			user.setId_card_reverse(up_picture_reverse);
			basedao.insert(PubConstants.USER_INFO, user);
			sub_Map.put("state", 0);//修改成功
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			sub_Map.put("state", 1);
			e.printStackTrace();
		}
	  	String json = JSONArray.fromObject(sub_Map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 个人信息完善
	 */
	public String basemsg()throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		HashMap<String, Object> whereMap=new HashMap<>();
		whereMap.put("_id", lscode);
		DBObject db = basedao.getMessage(PubConstants.USER_INFO, whereMap);
		UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
		Struts2Utils.getRequest().setAttribute("user", user);
		return "basemsg";
	}
	
	/**
	 * ajax用户信息完善
	 */
	public void  ajaxUserUpdate(){
		getLscode();
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		try {
			
			String name =  Struts2Utils.getParameter("name");//姓名
			String tel =  Struts2Utils.getParameter("tel");//电话
			String uskd =  Struts2Utils.getParameter("uskd");//uskd账号
			String password =  Struts2Utils.getParameter("password");//一级密码
			String paypassword = Struts2Utils.getParameter("paypassword");//二级密码
			String province =  Struts2Utils.getParameter("province");//省
			String city =  Struts2Utils.getParameter("city");//市
			String county =  Struts2Utils.getParameter("county");//区
			String up_picture_front =  Struts2Utils.getParameter("up_picture_front");//身份证正面照
			String up_picture_reverse =  Struts2Utils.getParameter("up_picture_reverse");//身份证反面照
			DBObject db = basedao.getMessage(PubConstants.USER_INFO, lscode);
			UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
			user.setUserName(name);
			user.setTel(tel);
			user.setAccount(tel);
			user.setUskd(uskd);
			user.setPassword(password);
			user.setPaypassword(paypassword);
			user.setProvince(province);
			user.setCity(city);
			user.setCounty(county);
			user.setId_card_front(up_picture_front);
			user.setId_card_reverse(up_picture_reverse);
			basedao.insert(PubConstants.USER_INFO, user);
			sub_Map.put("state", 0);//修改成功
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			sub_Map.put("state", 1);
			e.printStackTrace();
		}
	  	String json = JSONArray.fromObject(sub_Map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 个人安全
	 */
	public String safePwd()throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		
		HashMap<String, Object> whereMap=new HashMap<>();
		whereMap.put("_id", fromUserid);
		DBObject db = basedao.getMessage(PubConstants.USER_INFO, whereMap);
		UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
		Struts2Utils.getRequest().setAttribute("user", user);
		return "safePwd";
	}
	
	/**
	 * ajax 个人安全  密码修改
	 */
	public void ajaxsafePwd()throws Exception{
		getLscode();
		Map<String, Object> sub_Map=new HashMap<String, Object>();
		String oldPwd = Struts2Utils.getParameter("oldPwd");
		String newPwd = Struts2Utils.getParameter("newPwd");
		HashMap<String, Object> whereMap=new HashMap<>();
		whereMap.put("_id", lscode);
		DBObject db = basedao.getMessage(PubConstants.USER_INFO, whereMap);
		UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
		if(user!=null) {
			String loginpasswd =user.getPassword();
			//判断旧密码是否输入正确
			if(oldPwd.equals(loginpasswd)) {
				user.setPassword(newPwd);
				basedao.insert(PubConstants.USER_INFO, user);
				sub_Map.put("state", 1);//修改成功
			}else {
				sub_Map.put("state", 0);//旧密码错误
			}
			
		}
		String json = JSONArray.fromObject(sub_Map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 用户关系  2层
	 */
	public String nexus()throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		return "nexus";
	}
	
	/**
	 * 用户关系  2层
	 */
	public void ajaxnexus()throws Exception{
		getLscode();
		Map<String, Object>sub_Map=new HashMap<String, Object>();
		HashMap<String, Object>sortMap = new HashMap<>();
		sortMap.put("createdate", -1);
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<Object> userInfoList = new ArrayList<Object>();
		try {
			    DBObject   user=wwzservice.getWxUser(fromUserid);
			    HashMap<String, Object>whereMap=new HashMap<>(); 
				Map<String, Object>sub_Map1=new HashMap<String, Object>();
				UserInfo  info=(UserInfo) UniObject.DBObjectToObject(user, UserInfo.class);
				Long number = info.getNumber();//用户的编号
				
				sub_Map1.put("user", info);
				whereMap=new HashMap<>();
				whereMap.put("renumber", number);
				
				List<DBObject>list1=basedao.getList(PubConstants.USER_INFO, whereMap,fypage,10, sortMap);
				List<Object> userInfoList1 = new ArrayList<Object>();
				for(int j=0;j<list1.size();j++) {
					Map<String, Object>sub_Map2=new HashMap<String, Object>();
					UserInfo  info1=(UserInfo) UniObject.DBObjectToObject(list1.get(j), UserInfo.class);
					Long number1 = info1.getNumber();//用户的编号
					sub_Map2.put("user", info1);
					whereMap=new HashMap<>();
					whereMap.put("renumber", number1);
					
					List<DBObject>list2=basedao.getList(PubConstants.USER_INFO, whereMap,fypage,10, sortMap);
					
					sub_Map2.put("son", list2);
					userInfoList1.add(sub_Map2);
				}
				sub_Map1.put("son", userInfoList1);
				
				userInfoList.add(sub_Map1);
				//sub_Map.put("userinfo", sub_Map1);
		 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	String json = JSONArray.fromObject(userInfoList).toString();
	  	Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		//Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
}
