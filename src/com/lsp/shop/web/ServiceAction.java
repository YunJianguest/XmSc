package com.lsp.shop.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

 
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DictionaryUtil;
import com.lsp.pub.util.ExportExcel;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.TenpayUtil;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.AfterService;
import com.lsp.shop.entiy.OrderForm;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.website.service.WwzService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
 
/**
 * 订单
 * @author lsp
 *
 */
@Namespace("/shop")
@Results( { @Result(name = ServiceAction.RELOAD, location = "service.action",params={"fypage", "%{fypage}","state","%{state}","comid","%{comid}"}, type = "redirect") })
public class ServiceAction extends GeneralAction<OrderForm> {

	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private WwzService wwzService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	private MongoSequence mongoSequence;
	private OrderForm entity=new OrderForm();
	private String _id;


	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap =new HashMap<String, Object>();
		HashMap<String, Object> whereMap =new HashMap<String, Object>();
		custid=SpringSecurityUtils.getCurrentUser().getId(); 
		String  comid=Struts2Utils.getParameter("comid");
		if(StringUtils.isNotEmpty(comid))
		{  
			whereMap.put("comid", Long.parseLong(comid));
			Struts2Utils.getRequest().setAttribute("comid",  comid);
		} 
		String  name=Struts2Utils.getParameter("name");
		if(StringUtils.isNotEmpty(name))
		{
			Pattern pattern = Pattern.compile("^.*" + name + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("name",  name);
		}
		String  no=Struts2Utils.getParameter("no");
		if(StringUtils.isNotEmpty(no))
		{
			whereMap.put("_id", no);
			Struts2Utils.getRequest().setAttribute("no",  no);
		}
		String  tel=Struts2Utils.getParameter("tel");
		if(StringUtils.isNotEmpty(tel))
		{
			whereMap.put("tel", tel);
			Struts2Utils.getRequest().setAttribute("tel",  tel);
		}
		String state=Struts2Utils.getParameter("state");
		if(StringUtils.isNotEmpty(state)){
			whereMap.put("state", Integer.parseInt(state)); 
		}
		String  sel_insdate=Struts2Utils.getParameter("sel_insdate");
		String  sel_enddate=Struts2Utils.getParameter("sel_enddate");
		if (StringUtils.isNotEmpty(sel_enddate)) {
			BasicDBObject dateCondition = new BasicDBObject();
			dateCondition.append("$gte", DateFormat.getFormat(sel_insdate));
			dateCondition.append("$lte", DateFormat.getFormat(sel_enddate));
			whereMap.put("insDate", dateCondition);
			Struts2Utils.getRequest().setAttribute("sel_enddate", sel_enddate);
			Struts2Utils.getRequest().setAttribute("sel_insdate", sel_insdate);

		}
		
		sortMap.put("insDate", -1);
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.WX_ORDERFORM, whereMap);
		List<DBObject> list=baseDao.getList(PubConstants.WX_ORDERFORM,whereMap,fypage,10, sortMap);
		for(DBObject db:list){
			if(db.get("fromUserid")!=null){
				 DBObject  user=wwzService.getWxUser(db.get("fromUserid").toString());
				 db.put("nickname", user.get("nickname"));
				 db.put("headimgurl", user.get("headimgurl"));
			}
		 
		} 
		Struts2Utils.getRequest().setAttribute("OrderFormList", list);
		whereMap.clear();
		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
		sortMap.clear();
		sortMap.put("createdate",-1);
		List<DBObject>lskd=baseDao.getList(PubConstants.SET_COURIER, whereMap, sortMap);
		Struts2Utils.getRequest().setAttribute("lskd", lskd);
 
		return SUCCESS;
	}
	
	
	@Override
	public String delete() throws Exception {
		try {
			custid=SpringSecurityUtils.getCurrentUser().getId();
			baseDao.delete(PubConstants.WX_ORDERFORM,_id);
			HashMap<String, Object> whereMap =new HashMap<String, Object>();
			whereMap.put("_id", _id);
			baseDao.delete(PubConstants.WX_ORDERBUY,whereMap);
			addActionMessage("成功删除!");
			 
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
		}
		return RELOAD;
	}
 
	@Override
	public String input() throws Exception {
		String type=Struts2Utils.getParameter("type");
		Struts2Utils.getRequest().setAttribute("type", type);
		return "add";
	}
	
	@Override
	public String update() throws Exception {	
		String type=Struts2Utils.getParameter("type");
		Struts2Utils.getRequest().setAttribute("type", type);
		if(Struts2Utils.getParameter("fypage")!=null){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
	
		}
		return "add";
	}
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.WX_ORDERFORM, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) {
			//有custId查出来 用户信息
			entity = (OrderForm)UniObject.DBObjectToObject(baseDao.getMessage(PubConstants.WX_ORDERFORM,_id),OrderForm.class);
		} else {
			entity = new OrderForm();
		}
	}
	
	

	@Override
	public String save() throws Exception {
		//注册业务逻辑
		try {
			entity = (OrderForm)UniObject.DBObjectToObject(baseDao.getMessage(PubConstants.WX_ORDERFORM,_id),OrderForm.class);
			custid=SpringSecurityUtils.getCurrentUser().getId();
			entity.set_id(_id);
			entity.setKdno(Struts2Utils.getParameter("kdno"));
			entity.setKdcom(Struts2Utils.getParameter("kdcom"));
			int state=Integer.parseInt(Struts2Utils.getParameter("state")); 
			entity.setState(state);
			baseDao.insert(PubConstants.WX_ORDERFORM, entity); 
			addActionMessage("成功添加!");
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		
		return RELOAD;
	}
	
	

	
	@Override
	public OrderForm getModel() {
		return entity;
	}
	public void set_id(String _id) {
		this._id = _id; 
	}
	/**
	 * 退换货申请
	 * @throws Exception
	 */
	public void ajaxsave() throws Exception{
		getLscode();
		String pid = Struts2Utils.getParameter("pid");
		String custid = Struts2Utils.getParameter("custid");
		String fromUserid = Struts2Utils.getParameter("fromUserid");
		String oid = Struts2Utils.getParameter("oid");
		String num = Struts2Utils.getParameter("num");
		String resource = Struts2Utils.getParameter("resource");
		String remark = Struts2Utils.getParameter("remark");
		String orderproId = Struts2Utils.getParameter("orderproId");
		
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String serviceno = DateFormat.getDate() + strRandom + mongoSequence.currval("serviceno");
		AfterService info = new AfterService();
		info.set_id(serviceno);
		if(StringUtils.isNotEmpty(pid)){
			info.setProduct(baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, Long.parseLong(pid)));
		}
		info.setOid(oid);
		info.setCustid(custid);
		info.setRemark(remark);
		info.setNum(Integer.parseInt(num));
		info.setFromUserid(fromUserid);
		info.setResource(resource);
		info.setCreatedate(new Date());
		info.setType(Integer.parseInt(type));
		double price = 0;
		if(Integer.parseInt(type) == 1){
			DBObject  pro =  info.getProduct();
			if(pro != null){
				if(pro.get("price") != null){
					price = Double.parseDouble(pro.get("price").toString())*Integer.parseInt(num);
				}
			}
		}
		info.setPrice(price);
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, Long.parseLong(orderproId));
		if(dbObject != null){
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("pro", Map.class);
			OrderFormpro pro=(OrderFormpro) JSONObject.toBean(JSONObject.fromObject(dbObject),OrderFormpro.class,classMap);
			pro.setState(1);//异常订单
			pro.setSid(info.get_id().toString());
			baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
		}
		baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
	}
	
	/**
	 * 退换货审批
	 */
	public void approval() throws Exception{
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);//操作失败
		String id = Struts2Utils.getParameter("id");
		String state =Struts2Utils.getParameter("state");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, Long.parseLong(id));
		if(dbObject !=null){
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("pro", Map.class);
			AfterService info=(AfterService) JSONObject.toBean(JSONObject.fromObject(dbObject),AfterService.class,classMap);
			if(info.getState() == 0){
				info.setState(Integer.parseInt(state));
				if(Integer.parseInt(state) == 1){//同意
					//判断是否为退货，退货则退积分
					if(info.getType() == 2){
						//卖家增加积分
						wwzService.addjf(info.getPrice()+"", info.getFromUserid(), "shop_afterservice", null, null);
						
						//卖家减少积分
						wwzService.deljf(info.getPrice()+"", info.getCustid(), "shop_afterservice", null, null);
					}
				}else{
					DBObject dbObjects = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, info.getOrderproId());
					if(dbObjects != null){
						classMap.clear();
						classMap.put("pro", Map.class);
						OrderFormpro pro=(OrderFormpro) JSONObject.toBean(JSONObject.fromObject(dbObjects),OrderFormpro.class,classMap);
						pro.setState(0);//将订单变成正常订单
						baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
					}
				}
				baseDao.insert(PubConstants.SHOP_ODERFORMPRO, info);
			}else{
				sub_map.put("state", 2);//重复操作
			}
			
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 取消售后申请
	 */
	public void cancel () throws Exception{
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);//操作失败
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, Long.parseLong(id));
		if(dbObject !=null){
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("pro", Map.class);
			AfterService info=(AfterService) JSONObject.toBean(JSONObject.fromObject(dbObject),AfterService.class,classMap);
			if(info.getState()==0){
				info.setState(3);
				DBObject dbObjects = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, info.getOrderproId());
				if(dbObjects != null){
					classMap.clear();
					classMap.put("pro", Map.class);
					OrderFormpro pro=(OrderFormpro) JSONObject.toBean(JSONObject.fromObject(dbObjects),OrderFormpro.class,classMap);
					pro.setState(0);//将订单变成正常订单
					baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
				}
				baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
				sub_map.put("state", 0);//操作成功
			}else{
				sub_map.put("state", 2);//操作失败
			}
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 退换货详情
	 */
	public void detail() throws Exception{
		getLscode();
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, Long.parseLong(id));
		String json = JSONObject.fromObject(dbObject).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	
	
	
}
