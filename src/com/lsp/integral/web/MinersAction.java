package com.lsp.integral.web;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.integral.entity.InteProstore;
import com.lsp.integral.entity.Miner;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Code;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.website.service.WwzService;
import com.lsp.weixin.entity.WxUser;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;

/**
 * 矿机设置
 * 
 * @author lsp
 * 
 */
@Namespace("/integral")
@Results({ @Result(name = MinersAction.RELOAD, location = "miners.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class MinersAction extends GeneralAction<Miner> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private MongoSequence mongoSequence;
	private Miner entity = new Miner();
	private Long _id;
	@Autowired
	private WwzService wwzService;
	
	public void set_id(Long _id) {
		this._id = _id;
	}
	@Autowired
	  public void setMongoSequence(MongoSequence mongoSequence)
	  {
	    this.mongoSequence = mongoSequence;
	  } 
	
	@Override
	public Miner getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		sortMap.put("sort", -1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		
		String  title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title))
		{
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("ptitle", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_MINER,null,fypage,10,sortMap);
		Struts2Utils.getRequest().setAttribute("list", list);
		
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_MINER,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", this.fycount);
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
	
		try {
			if (_id == null ) {
				_id = mongoSequence.currval(PubConstants.INTEGRAL_MINER);
			}
			entity.set_id(_id); 
			entity.setCreatedate(new Date());
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			baseDao.insert(PubConstants.INTEGRAL_MINER, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		SpringSecurityUtils.getCurrentUser().getId();
		baseDao.delete(PubConstants.INTEGRAL_MINER, _id);
		return RELOAD;
	}

	@Override
	public String input() throws Exception { 
		return "add";
	}

	@Override
	public String update() throws Exception { 

		return "add";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) { 
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_MINER, _id);
			this.entity = ((Miner)UniObject.DBObjectToObject(db, 
					Miner.class));
		} else {
			entity = new Miner();
		}
	}
	
	public String list() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		return "list";
	}
	
	/**
	 * 移动端矿机列表
	 * @throws Exception
	 */
	public void showall() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>();
		sub_map.put("state", 1);
		sortMap.put("price", 1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_MINER,whereMap,fypage,10,sortMap);
		if(list.size()>0){
			sub_map.put("list", list);
			sub_map.put("state", 0);
		}
		
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 我的兑换
	 * @throws Exception
	 */
	public void saveMiner() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode); 
		Map<String, Object> sub_map = new HashMap<String, Object>();
		String id = Struts2Utils.getParameter("id");
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_MINER, Long.parseLong(id)); 
		if(db != null){
			if(db.get("price")!=null){  
					boolean fag=wwzService.deljf(db.get("price").toString(), fromUserid, "shop_bmzt", custid, 1, 1, 1);
					if(fag) {
						Miner miner=(Miner) UniObject.DBObjectToObject(db, Miner.class);
						InteProstore prostore = new InteProstore();
						prostore.setCid(Long.parseLong(id));
						prostore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
						prostore.setCreatedate(new Date());
						prostore.setCustid(custid);
						prostore.setFromUserid(fromUserid); 
						prostore.setMoney(miner.getPrice()); 
						prostore.setTime(miner.getTime());
						prostore.setEnddate(DateUtil.addDay(new Date(),miner.getTime()));
						prostore.setType("shop_bmzt");
						prostore.setState(0);
						prostore.setKj(db);
						baseDao.insert(PubConstants.INTEGRAL_PROSTORE, prostore);
						sub_map.put("state", 0); 
					}else {
						sub_map.put("state", 2);//积分不足
					}
				  
			}
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/*public void prostore(String custid,String fromUserid,String type,String price){
		
	}*/
	
	/**
	 * 我的商城收益页面
	 */
	public String ownminer(){
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		return "ownminer";
	}
	
	/**
	 * 我的商城收益列表
	 * @throws Exception
	 */
	public  void myMiner() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>();
		sub_map.put("state", 1);
		sortMap.put("createdate", -1); 
        whereMap.put("fromUserid", fromUserid);
        //type为shop_bmzt是商城收益
        //whereMap.put("type", "shop_bmzt"); 
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_PROSTORE,whereMap,fypage,10,sortMap);
		if(list.size()>0){
			sub_map.put("list", list);
			sub_map.put("state", 0);
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	public String detail() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.INTEGRAL_PROSTORE, Long.parseLong(id));
		Struts2Utils.getRequest().setAttribute("db", dbObject);
		return "detail";
	} 
	
	/***
	   * 移动端登录页面
	   * @return
	   */
	  public String signin(){
		  return "signin";
	  }
	  /***
	   * 移动端注册页面
	   * @return
	   */
	  public String signup(){
		  return "signup";
	  }
	  
	  /***
	   * 移动端忘记密码
	   * @return
	   */
	  public String forgetpw(){

		  return "forgetpw";
	  }
	  
	  /***
		 * 注册
		 * @throws Exception
		 */
		public void ajaxsignup() throws Exception{
			Map<String,Object>sub_map = new HashMap<>();
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			sub_map.put("state", 1);
			String tel=Struts2Utils.getParameter("tel");
			String yzcode=Struts2Utils.getParameter("yzcode"); 
			String password=Struts2Utils.getParameter("password"); 
			whereMap.put("tel", tel);
			Long count =baseDao.getCount(PubConstants.DATA_WXUSER, whereMap);
			
			Code code=GetAllFunc.telcode.get(tel); 
			if(count == 0){
				if (code!=null&&code.getCode().equals(yzcode)) {
					//验证时间
					if(DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(),10))) {
						WxUser user = new WxUser();
						user.set_id(UUID.randomUUID().toString());
						user.setTel(tel);
						user.setPassword(password);
						baseDao.insert(PubConstants.DATA_WXUSER, user);
						String lscode=wwzService.createcode(user.get_id().toString());
						sub_map.put("lscode", lscode);//注册成功
						sub_map.put("state", 0);//注册成功
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
	    public void ajaxsignin() throws Exception{
	    	 HashMap<String, Object> whereMap = new HashMap<String, Object>();
	   	     Map<String, Object> sub_map = new HashMap<String, Object>(); 
	   	     sub_map.put("state", 1);//操作失败
	   	     String tel =Struts2Utils.getParameter("tel"); 
	   	     String password =Struts2Utils.getParameter("password");
	   	     
	   	     whereMap.put("tel", tel);
	  	     if (StringUtils.isNotEmpty(tel)&&StringUtils.isNotEmpty(password)) {
	  		     DBObject user = baseDao.getMessage(PubConstants.DATA_WXUSER, whereMap);
	  		     if(user != null){
	 			   if(user.get("password").toString().equals(password)) {
	                   String lscode=wwzService.createcode(user.get("_id").toString()); 
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
	  	  DBObject dbObject =baseDao.getMessage(PubConstants.DATA_WXUSER, whereMap);
	  	  if(dbObject!=null){
	  		  WxUser user = (WxUser) UniObject.DBObjectToObject(dbObject, WxUser.class);
	  			  Code code=GetAllFunc.telcode.get(tel); 
	  				if (code!=null&&code.getCode().equals(yzcode)) { 
	  					 //验证时间
	  					if(DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(),10))) {
	  						user.setPassword(password);
	  						baseDao.insert(PubConstants.DATA_WXUSER, user);
	  						sub_map.put("state", 0);//修改成功
	  					}else{
	  						sub_map.put("state", 5);//验证码超时
	  					}
	  				}else{
	  					sub_map.put("state", 4);//验证码错误
	  				} 
	  	  }else{
	  		  sub_map.put("state", 2);//该账户不存在
	  	  }
	  	  
	  		String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    }

}
