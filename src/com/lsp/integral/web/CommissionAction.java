package com.lsp.integral.web;
import java.net.URLEncoder;
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
import com.lsp.integral.entity.Commission;
import com.lsp.integral.entity.TopupOrder;
import com.lsp.integral.entity.TransferOrder;
import com.lsp.integral.entity.WithdrawalOrder;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Code;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.HttpClient;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.WxToken;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.PBKDF2Util;
import com.lsp.pub.util.PayCommonUtil;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.TenpayUtil;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.util.WeiXinUtil;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.suc.entity.IntegralRecord;
import com.lsp.user.entity.UserInfo;
import com.lsp.website.service.WwzService;
import com.lsp.weixin.entity.WxUser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
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
@Results({ @Result(name = CommissionAction.RELOAD, location = "commission.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class CommissionAction extends GeneralAction<Commission> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private MongoSequence mongoSequence;
	private Commission entity = new Commission();
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
	public Commission getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		gsCustid();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		sortMap.put("createdate", -1);   
		System.out.println("====>"+SysConfig.getProperty("gsid"));
		System.out.println("====>"+SysConfig.getProperty("custid"));
		if(custid.equals(SysConfig.getProperty("gsid"))||custid.equals(SysConfig.getProperty("custid"))) {

		}else{
			whereMap.put("fromid", custid);
		}
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_COMMISSION,whereMap,fypage,10,sortMap);
		for (DBObject dbObject : list) {
			if(dbObject.get("fromid") != null){
				DBObject dbObject2 =baseDao.getMessage(PubConstants.USER_INFO, dbObject.get("fromid").toString());
				if(dbObject2.get("account") != null){
					dbObject.put("acc", dbObject2.get("account").toString());
				}
				if(dbObject2.get("nickname") != null){
					dbObject.put("nickname", dbObject2.get("nickname").toString());
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("list", list);
		
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_COMMISSION,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", this.fycount);
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
	
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {

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
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_COMMISSION, _id);
			this.entity = ((Commission)UniObject.DBObjectToObject(db, 
					Commission.class));
		} else {
			entity = new Commission();
		}
	}
	    /**
	     * 提现申请
	     * @return
	     */
	    public String  apply() throws Exception{
	    	getLscode(); 
	    	Struts2Utils.getRequest().setAttribute("custid", custid);
			Struts2Utils.getRequest().setAttribute("lscode", lscode);
			return "apply"; 
	    }
	    
	    /**
	     * 提现详情
	     * @return
	     */
	    public String  detail() throws Exception{
	    	getLscode(); 
	    	Struts2Utils.getRequest().setAttribute("custid", custid);
			Struts2Utils.getRequest().setAttribute("lscode", lscode);
			String id = Struts2Utils.getParameter("id");
			System.out.println("id0000"+id);
			DBObject dbObject = baseDao.getMessage(PubConstants.INTEGRAL_COMMISSION, id);
			Struts2Utils.getRequest().setAttribute("dbObject", dbObject);
			return "detail"; 
	    }
	    
	    /**
	     * 提现列表
	     * @return
	     */
	    public String  list() throws Exception{
	    	getLscode(); 
	    	Struts2Utils.getRequest().setAttribute("custid", custid);
			Struts2Utils.getRequest().setAttribute("lscode", lscode);
			return "list"; 
	    }
	    
	    /**
	     * 提现申请
	     */
	    public void ajaxsave() throws Exception{
	    	getLscode(); 
	    	Map<String,Object>sub_map = new HashMap<>();
		  	sub_map.put("state", 1);
	    	String account = Struts2Utils.getParameter("account");
	    	String type = Struts2Utils.getParameter("type");
	    	String price = Struts2Utils.getParameter("price");
	    	String yname = Struts2Utils.getParameter("yname");
	    	String remark = Struts2Utils.getParameter("remark");
           
	    	if(StringUtils.isEmpty(account) || StringUtils.isEmpty(price) ||StringUtils.isEmpty(type) ){
	    		sub_map.put("state", 2);//请填写完整信息
	    		String json = JSONArray.fromObject(sub_map).toString();
		  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    		return ;
	    	}
	    	
	    	 if(StringUtils.isEmpty(fromUserid) ){
		    		sub_map.put("state", 3);//登录过期
		    		String json = JSONArray.fromObject(sub_map).toString();
			  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
		    		return ;
		    	}
	    	 
	    	Commission commission=new Commission();
	    	// 四位随机数
			String strRandom = TenpayUtil.buildRandom(4) + "";
			// 10位序列号,可以自行调整。
			String orderno = DateFormat.getDate() + strRandom + mongoSequence.currval(PubConstants.INTEGRAL_COMMISSION);
			commission.set_id(orderno);
			commission.setCreatedate(new Date());
			commission.setState(0);
			commission.setPrice(Double.parseDouble(price));
	    	commission.setCost(Double.parseDouble(price)*0.1);
	    	commission.setType(Integer.parseInt(type));
	    	if(StringUtils.isNotEmpty(remark)){
	    		commission.setRemark(remark);
	    	}
	    	if(StringUtils.isNotEmpty(yname)){
	    		commission.setYname(yname);
	    	}
	    	commission.setAccount(account.trim());
	    	commission.setFromid(fromUserid);
	    	commission.setCustid(custid);
	    	try {
	    		if(wwzService.delYjjf(price, fromUserid, "shop_tx", custid, 1, 1, 0,orderno)){
	    			baseDao.insert(PubConstants.INTEGRAL_COMMISSION, commission);
					
					sub_map.put("id", commission.get_id().toString());
					sub_map.put("state", 0);//操作成功
	    		}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    	
	    	
	    }
	  
	    /**
	     * 列表
	     * @throws Exception
	     */
	    public void ajaxlist() throws Exception{
	    	getLscode();
	    	HashMap<String, Object> sortMap = new HashMap<String, Object>();
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			Map<String, Object> sub_map = new HashMap<String, Object>();
			sub_map.put("state", 1);
			sortMap.put("createdate", -1);   
			whereMap.put("fromid", fromUserid);
			//分页
			if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
				fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
			}
			List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_COMMISSION,whereMap,fypage,20,sortMap);
			if(list.size() > 0){
				sub_map.put("state", 0);
				sub_map.put("list",list);
			}
			String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    }
	    
	    /**
	     * 提现审批
	     * 
	     */
	    public void appro() throws Exception{
			Map<String, Object> sub_map = new HashMap<String, Object>();
			sub_map.put("state", 1);
			String id = Struts2Utils.getParameter("id");
			String state = Struts2Utils.getParameter("state");
			DBObject  dbObject = baseDao.getMessage(PubConstants.INTEGRAL_COMMISSION, id);
			if(dbObject != null){
				Commission commission = (Commission) UniObject.DBObjectToObject(dbObject, Commission.class);
				commission.set_id(id);
				if(commission.getState() == 0){
					commission.setState(Integer.parseInt(state));
					commission.setUpdatedate(new Date());
                    if(commission.getState() == 2){ //申请拒绝
                    	boolean flag = wwzService.addjfoid(commission.getPrice()+"", commission.getFromid(), "fail_tx", commission.getCustid(), 1, 1,0,null);
						if(!flag){
							String json = JSONArray.fromObject(sub_map).toString();
					  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
							return ;
						}
					}
                    try {
						baseDao.insert(PubConstants.INTEGRAL_COMMISSION, commission);
						sub_map.put("state", 0);//操作成功
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						sub_map.put("state", 1);//添加失败
					}
				}else{
					sub_map.put("state", 3);//已审批请勿重复提交
				}
			}else{
				sub_map.put("state", 2);//该提现申请不存在
			}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    }
	    
	    /**
	     * 确认打款
	     * 
	     */
	    public void resureMoney() throws Exception{
	    	Map<String, Object> sub_map = new HashMap<String, Object>();
			sub_map.put("state", 1);
			String id = Struts2Utils.getParameter("id");
			DBObject  dbObject = baseDao.getMessage(PubConstants.INTEGRAL_COMMISSION, id);
			if(dbObject != null){
				Commission commission = (Commission) UniObject.DBObjectToObject(dbObject, Commission.class);
				commission.set_id(id);
				if(commission.getState() == 1){
					commission.setState(4);
					commission.setConfirmdate(new Date());
                    try {
						baseDao.insert(PubConstants.INTEGRAL_COMMISSION, commission);
						sub_map.put("state", 0);//操作成功
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						sub_map.put("state", 1);//添加失败
					}
				}else{
					sub_map.put("state", 3);//已审批请勿重复提交
				}
			}else{
				sub_map.put("state", 2);//该提现申请不存在
			}
	    	String json = JSONArray.fromObject(sub_map).toString();
	  		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	    }
	  
}
