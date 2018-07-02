package com.lsp.integral.web;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.website.service.WwzService;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;

/**
 * 设置
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
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>();
		String id = Struts2Utils.getParameter("id");
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_MINER, id);
		
		if(db != null){
			if(db.get("price")!=null){
				boolean flag = wwzService.checkTotalIntegral(1,db.get("price").toString());
				if(flag){
					
					whereMap.put("fromUserid", fromUserid);
			        //type为shop_bmzt是商城收益
			        whereMap.put("type", "shop_bmzt");
			        whereMap.put("isfreeze", 1);
			        DBObject dbObject = baseDao.getMessage(PubConstants.INTEGRAL_INFO, whereMap);
			       
			        
			        if(dbObject !=null){
			        	IntegralInfo info = (IntegralInfo) UniObject.DBObjectToObject(dbObject, IntegralInfo.class);
			        		if(info.getValue()>Double.parseDouble(db.get("price").toString())){
			        			//添加预付积分
			        			InteProstore prostore = new InteProstore();
			        			prostore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
			        			prostore.setFromUserid(fromUserid);
			        			//prostore.set
			        			
			        			//减少冻结积分IntegralInfo
			        			wwzService.delbmtz(db.get("price").toString(), fromUserid, "shop_bmzt", custid, null, 1);
			        			sub_map.put("state", 1);//兑换成功
			        		}else{
			        			sub_map.put("state", 2);//积分不足,无法兑换
			        		}

			        	
			        }
				}else{
					sub_map.put("state", 3);//无法兑换，pp币发行量已完
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
		
		sortMap.put("createdate", -1); 
        whereMap.put("fromUserid", fromUserid);
        //type为shop_bmzt是商城收益
        whereMap.put("type", "shop_bmzt");
        whereMap.put("isfreeze", 0);
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

}
