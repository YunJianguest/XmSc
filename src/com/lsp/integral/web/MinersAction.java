package com.lsp.integral.web;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.integral.entity.InteProstore;
import com.lsp.integral.entity.InteSetting;
import com.lsp.integral.entity.Miner;
import com.lsp.integral.entity.TransferOrder;
import com.lsp.integral.entity.WithdrawalOrder;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Code;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.HttpClient;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.PayCommonUtil;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.TenpayUtil;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.user.entity.UserInfo;
import com.lsp.website.service.WwzService;
import com.lsp.weixin.entity.WxUser;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("fromUserid", fromUserid);
		DBObject dbObject =baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		Struts2Utils.getRequest().setAttribute("dbObject", dbObject);
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
		if(dbObject != null){
			InteProstore prostore = (InteProstore) UniObject.DBObjectToObject(dbObject, InteProstore.class);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String end = formatter.format(prostore.getEnddate());
			dbObject.put("end", end);
		}
		Struts2Utils.getRequest().setAttribute("db", dbObject);
		DBObject setting =baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		Struts2Utils.getRequest().setAttribute("setting", setting);
		return "detail";
	} 
	
	public String pcdetail() throws Exception{
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.INTEGRAL_PROSTORE, Long.parseLong(id));
		if(dbObject != null){
			InteProstore prostore = (InteProstore) UniObject.DBObjectToObject(dbObject, InteProstore.class);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String end = formatter.format(prostore.getEnddate());
			dbObject.put("end", end);
		}
		Struts2Utils.getRequest().setAttribute("db", dbObject);
		DBObject setting =baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		Struts2Utils.getRequest().setAttribute("setting", setting);
		return "pcdetail";
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
	   * 个人中心
	   * @return
	   */
	  public String ownperson(){
		  getLscode();
		  Struts2Utils.getRequest().setAttribute("custid", custid);
		  Struts2Utils.getRequest().setAttribute("lscode", lscode);
		  return "ownperson";
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
	    /**
	     * 转账
	     */
	    public void   transfer() {
	    	getLscode();
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
	    	String toid=Struts2Utils.getParameter("toid");
	    	String price=Struts2Utils.getParameter("price");
	    	String remark=Struts2Utils.getParameter("remark");
	    	if(StringUtils.isNotEmpty(price)) {
	    		TransferOrder transferOrder=new TransferOrder();
		    	// 四位随机数
				String strRandom = TenpayUtil.buildRandom(4) + "";
				// 10位序列号,可以自行调整。
				String orderno = DateFormat.getDate() + strRandom + mongoSequence.currval(PubConstants.INTEGRAL_TRANSFERORDER);
		    	transferOrder.set_id(orderno);
		    	transferOrder.setCreatedate(new Date());
		    	transferOrder.setCustid(custid);
		    	transferOrder.setFromid(fromUserid);
		    	transferOrder.setPrice(Double.parseDouble(price));
		    	transferOrder.setToid(toid);
		    	transferOrder.setRemark(remark);
		    	transferOrder.setState(0);
		    	baseDao.insert(PubConstants.INTEGRAL_TRANSFERORDER, transferOrder);
		    	
		    	//开始转账 
		    	if(wwzService.deljf(price, fromUserid, "shop_zz", custid, 0, 1, 0)) {  
		    		String pro=BaseDecimal.subtract(price, BaseDecimal.multiplication(BaseDecimal.division(price, "100",6),"0.2"));
		    		if(wwzService.addjf(pro, toid, "shop_zz", custid, 0, 1, 0)) {
		    			sub_map.put("state", 0);
		    			transferOrder.setState(1);
		    			transferOrder.setUpdatedate(new Date());
			    		baseDao.insert(PubConstants.INTEGRAL_TRANSFERORDER, transferOrder);
		    		}else {
		    			//转账失败
		    			transferOrder.setState(2);
		    			transferOrder.setUpdatedate(new Date());
			    		baseDao.insert(PubConstants.INTEGRAL_TRANSFERORDER, transferOrder);
		    			sub_map.put("state", 3);
		    		} 
		    	}else {
		    		//余额不足
		    		transferOrder.setState(2);
		    		transferOrder.setUpdatedate(new Date());
		    		baseDao.insert(PubConstants.INTEGRAL_TRANSFERORDER, transferOrder);
		    		sub_map.put("state", 2);
		    	}  
	    	}
	    
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    	
	    }
	    /**
	     * 提现
	     */
	    public void   withdrawal() {
	    	getLscode();
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
	    	String eth=Struts2Utils.getParameter("eth");
	    	String price=Struts2Utils.getParameter("price");
	    	String remark=Struts2Utils.getParameter("remark");
	    	SortedMap<Object, Object> parameters = new TreeMap<Object, Object>(); 
	    	if(StringUtils.isNotEmpty(eth)&&StringUtils.isNotEmpty(price)&&StringUtils.isNotEmpty(remark)) {
	    		WithdrawalOrder tx=new WithdrawalOrder();
		    	// 四位随机数
				String strRandom = TenpayUtil.buildRandom(4) + "";
				// 10位序列号,可以自行调整。
				String orderno = DateFormat.getDate() + strRandom + mongoSequence.currval(PubConstants.INTEGRAL_WITHDRAWALORDER);
		    	tx.set_id(orderno);
		    	tx.setCreatedate(new Date());
		    	tx.setCustid(custid);
		    	tx.setFromid(fromUserid);
		    	tx.setPrice(Double.parseDouble(price));
		    	tx.setRemark(remark);
		    	tx.setState(0);
		    	baseDao.insert(PubConstants.INTEGRAL_WITHDRAWALORDER, tx);
		    	//提现
		    	if(wwzService.deljf(price, fromUserid, "shop_tx", custid, 0, 1, 0)) {
		    		parameters.put("eth", eth);
			    	parameters.put("num",price);
			    	parameters.put("username",wwzService.getUserName(fromUserid));
			    	parameters.put("orderid",orderno);
			    	String sign = PayCommonUtil.createKey("UTF-8",eth+price+wwzService.getUserName(fromUserid)+orderno, SysConfig.getProperty("jyskey"));
			    	parameters.put("key", sign);
			    	HashMap<String,Object>map=new HashMap<>();
			    	map.put("data", parameters);
		            String result =HttpClient.doHttpPost(SysConfig.getProperty("jysurl"),JSONObject.fromObject(parameters).toString());
		            JSONObject obj=JSONObject.fromObject(result);
		            if(obj.getString("code").equals("1000")) {
		            	//提现成功；
		            	tx.setState(1);
		            	tx.setUpdatedate(new Date());
				    	baseDao.insert(PubConstants.INTEGRAL_WITHDRAWALORDER, tx);
				    	sub_map.put("state", 0);
		            }else {
		            	//提现失败开始返回
		            	tx.setState(2);
		            	tx.setUpdatedate(new Date());
				    	baseDao.insert(PubConstants.INTEGRAL_WITHDRAWALORDER, tx);
				    	wwzService.addjf(price, fromUserid, "shop_tx", custid, 0, 1, 0);
				    	sub_map.put("state", 3);
		            }
		    	}else {
		    		//余额不足
		    		sub_map.put("state", 2);
		    	} 
	    	}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    			 
	    }
	    /**
	     * 充值
	     */
	    public void   topup() {
	    	
	    }
	    /**
	     * 获取可提现金额
	     */
	    public void   getTxje() {
	    	double price=wwzService.getSprice();
	    	if(price>0) {
	    		//获取
	    		
	    	}
	    }
	    /**
	     * 兑换招商部
	     */
	    public void  dhZsb() {
	    	getLscode();
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
	    	String address=Struts2Utils.getParameter("address");
	    	HashMap<String, Object> whereMap = new HashMap<>();
	    	whereMap.put("custid",SysConfig.getProperty("custid"));
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, whereMap);
			if(StringUtils.isEmpty(address)) {
				//未选择所属县域
				sub_map.put("state",4);
				String json = JSONArray.fromObject(sub_map).toString();
		  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]); 
		  		return;
			}
			if(db!=null) { 
				//获取部门价格
				InteSetting sett = (InteSetting) UniObject.DBObjectToObject(db, InteSetting.class);
				double bl=sett.getReturnDept()/3;
				if(wwzService.deljf(bl+"", fromUserid,"jfdh", custid, 0, 1, 0)) {
					//购买成功
					DBObject dbuser=wwzService.getWxUser(fromUserid);
					UserInfo user=(UserInfo) UniObject.DBObjectToObject(dbuser, UserInfo.class);
					user.set_id(fromUserid);
					user.setAgentLevel(4);
					user.setAgentedate(new Date());
					user.setAgentcounty(address);
					baseDao.insert(PubConstants.USER_INFO, user);
					//购买成功
					sub_map.put("state",0);
				}else {
					//余额不足
					sub_map.put("state",3);
				}
				
			}else {
				//服务器未开通该功能
				sub_map.put("state",2);
			}
			
			String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]); 
	    }
	    /**
	     * 获取比特币实时价格
	     */
	    public void  getBTCSrice() {
	    	getLscode();
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
		  	double  dou=wwzService.getBTCSprice();
		  	if(dou>0) {
		  		sub_map.put("state", 0);
		  		sub_map.put("data", dou);
		  	}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]); 
	    };
	    /**
	     * 获取以太坊实时价格
	     */
        public void  getETHSrice() {
        	getLscode();
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
		  	double  dou=wwzService.getBTCSprice();
		  	if(dou>0) {
		  		sub_map.put("state", 0);
		  		sub_map.put("data", dou);
		  	}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]); 
	    };
	    /**
	     * 提现
	     * @return
	     */
	    public String  withweb() {
	    	getLscode(); 
			return "withweb"; 
	    }
	    /**
	     * 兑换
	     * @return
	     */
	    public String  exchangeweb() {
	    	getLscode();
	    	DBObject dbuser=wwzService.getWxUser(fromUserid);
	    	//获取当前登录人所在市
			UserInfo user=(UserInfo) UniObject.DBObjectToObject(dbuser, UserInfo.class);
			Struts2Utils.getRequest().setAttribute("address", user.getAgentcounty());
			return "exchangeweb"; 
	    }
	    /**
	     * 转账
	     * @return
	     */
	    public String  transferweb() {
	    	getLscode();
			return "transferweb"; 
	    }
	    
}
