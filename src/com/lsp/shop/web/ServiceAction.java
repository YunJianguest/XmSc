package com.lsp.shop.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

import com.lsp.integral.entity.InteProstore;
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
import com.lsp.shop.entiy.AfterService;
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
public class ServiceAction extends GeneralAction<AfterService> {

	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private WwzService wwzService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
	private MongoSequence mongoSequence;
	private AfterService entity=new AfterService();
	private String _id;
    
	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap =new HashMap<String, Object>();
		HashMap<String, Object> whereMap =new HashMap<String, Object>();
		sortMap.put("createdate", -1);
		custid=SpringSecurityUtils.getCurrentUser().getId(); 
		String  comid=Struts2Utils.getParameter("comid");
		if(StringUtils.isNotEmpty(comid))
		{  
			whereMap.put("comid", Long.parseLong(comid));
			Struts2Utils.getRequest().setAttribute("comid",  comid);
		} 
		String  no=Struts2Utils.getParameter("no");
		if(StringUtils.isNotEmpty(no))
		{
			Pattern pattern = Pattern.compile("^.*" + no + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("oid", pattern);
			Struts2Utils.getRequest().setAttribute("no",  no);
		}
		String  type=Struts2Utils.getParameter("type");
		if(StringUtils.isNotEmpty(type)){
			if(!type.equals("0")){
				whereMap.put("type", Integer.parseInt(type));
			}
			
			Struts2Utils.getRequest().setAttribute("type",  type);
		}
		String state=Struts2Utils.getParameter("state");
		if(StringUtils.isNotEmpty(state)){
			if(!state.equals("4")){
				whereMap.put("state", Integer.parseInt(state)); 
			}
			Struts2Utils.getRequest().setAttribute("state",  state);
		}else{
			Struts2Utils.getRequest().setAttribute("state",  "4");
		}
		
		String  sel_insdate=Struts2Utils.getParameter("sel_insdate");
		String  sel_enddate=Struts2Utils.getParameter("sel_enddate");
		BasicDBObject dateCondition = new BasicDBObject();
		if (StringUtils.isNotEmpty(sel_insdate)) {
			dateCondition.append("$gte", DateFormat.getFormat(sel_insdate));
			Struts2Utils.getRequest().setAttribute("sel_insdate", sel_insdate);
			whereMap.put("createdate", dateCondition);
		}
		if (StringUtils.isNotEmpty(sel_enddate)) {
			dateCondition.append("$lte", DateFormat.getFormat(sel_enddate));
			Struts2Utils.getRequest().setAttribute("sel_enddate", sel_enddate);
			whereMap.put("createdate", dateCondition);
		}
		

		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_AFTERSERVICE, whereMap);
		List<DBObject> list=baseDao.getList(PubConstants.SHOP_AFTERSERVICE,whereMap,fypage,10, sortMap);
		for(DBObject db:list){
			if(db.get("fromUserid")!=null){
				 DBObject  user=wwzService.getWxUser(db.get("fromUserid").toString());
				 if(user.get("nickname")!=null){
					 db.put("nickname", user.get("nickname"));
				 }else{
					 db.put("nickname", user.get("tel"));
				 }
				
				 db.put("headimgurl", user.get("headimgurl"));
			}
		 
		} 
		Struts2Utils.getRequest().setAttribute("list", list);
 
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
			entity = (AfterService)UniObject.DBObjectToObject(baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE,_id),AfterService.class);
		} else {
			entity = new AfterService();
		}
	}
	
	

	@Override
	public String save() throws Exception {
		
		
		return RELOAD;
	}
	
	

	
	@Override
	public AfterService getModel() {
		return entity;
	}
	public void set_id(String _id) {
		this._id = _id; 
	}
	public String serviceadd() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		String orderproId = Struts2Utils.getParameter("orderproId");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, Long.parseLong(orderproId));
		Struts2Utils.getRequest().setAttribute("db", dbObject);
		return "serviceadd";
	}
	
	/**
	 * 退换货申请
	 * @throws Exception
	 */
	public void ajaxsave() throws Exception{
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		String custid = Struts2Utils.getParameter("custid");
		String num = Struts2Utils.getParameter("num");
		String resource = Struts2Utils.getParameter("resource");
		String remark = Struts2Utils.getParameter("remark");
		String orderproId = Struts2Utils.getParameter("orderproId");
		
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String serviceno = DateFormat.getDate() + strRandom + mongoSequence.currval(PubConstants.SHOP_AFTERSERVICE);
		AfterService info = new AfterService();
		info.set_id(serviceno);
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, Long.parseLong(orderproId));
		if(dbObject != null){
			OrderFormpro pro = (OrderFormpro) UniObject.DBObjectToObject(dbObject, OrderFormpro.class);
			DBObject pros = pro.getPro();
			pro.set_id(Long.parseLong(orderproId));
			pro.setState(Integer.parseInt(type));//异常订单  1-退货   2-换货
			pro.setSid(info.get_id().toString());
			baseDao.insert(PubConstants.SHOP_ODERFORMPRO, pro);
			
			info.setProduct(pro.getPro());
			if(pro.getPro() != null){
				DBObject dbObject2 =pro.getPro();
				if(dbObject2.get("comid")!=null){
					info.setComid(Long.parseLong(dbObject2.get("comid").toString()));
				}
			}
		    info.setOid(pro.getOrderid());
			info.setCustid(custid);
			info.setRemark(remark);
			if(StringUtils.isNotEmpty(num)){
				info.setNum(Integer.parseInt(num));	
			}else{
				info.setNum(1);	
			}
			info.setFromUserid(fromUserid);
			info.setResource(resource);
			info.setCreatedate(new Date());
			info.setType(Integer.parseInt(type));
			double price = 0;
			double charge = 0;
			double total = 0;
			if(Integer.parseInt(type) == 1){
				if(pros != null){
					if(pros.get("price") != null){
						price = Double.parseDouble(pros.get("price").toString())*info.getNum();
						charge = price * 0.002;
						total = price - charge;
					}
				}
			}
			info.setPrice(price);
			info.setCharge(charge);
			info.setTotal(total);
			baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
			sub_map.put("state", 0);
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	public String find() throws Exception{
		getLscode();
		String sid = Struts2Utils.getParameter("sid");
		String orderid = Struts2Utils.getParameter("orderid");
        DBObject dbObject =baseDao.getMessage(PubConstants.WX_ORDERFORM, orderid);
        DBObject dbObject2 =baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, sid);
        if(dbObject2 != null){
        	AfterService service = (AfterService) UniObject.DBObjectToObject(dbObject2, AfterService.class);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = formatter.format(service.getCreatedate());
			dbObject2.put("date", date);
        }
        Struts2Utils.getRequest().setAttribute("order", dbObject);
        Struts2Utils.getRequest().setAttribute("service", dbObject2);
        return "find";
	}
	
	/**
	 * 退换货审批
	 */
	public void approval() throws Exception{
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);//操作失败
		String id = Struts2Utils.getParameter("id");
		String state =Struts2Utils.getParameter("state");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, id);
		OrderFormpro pro = new OrderFormpro();
		if(dbObject !=null){
			AfterService info = (AfterService) UniObject.DBObjectToObject(dbObject, AfterService.class);
			DBObject dbObjects = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, info.getOrderproId());
			if(info.getState() == 0){
				info.set_id(id);
				info.setState(Integer.parseInt(state));
				//判断是否为退货，退货则退积分
				if(Integer.parseInt(state) == 1){//同意
					//根据订单号查询订单
					DBObject db = baseDao.getMessage(PubConstants.WX_ORDERFORM, info.getOid());
					if(db!= null){
						OrderForm order = (OrderForm) UniObject.DBObjectToObject(db, OrderForm.class);
						order.set_id(info.getOid());
						DBObject dbObject2 = info.getProduct();
						if(dbObject2!=null){
							if(dbObject2.get("goodstype")!=null){
								if(dbObject2.get("goodstype").toString().equals("3")){//商品为大众区商品
									order.setPublic_money(order.getPublic_money()-info.getPrice());
								}
								if(dbObject2.get("goodstype").toString().equals("4")){//商品为特约区商品
									order.setContri_money(order.getContri_money()-info.getPrice());									
								}
								if(dbObject2.get("goodstype").toString().equals("5")){//商品为会员区商品
									order.setMembers_money(order.getMembers_money()-info.getPrice());
								}
								//订单退货手续费
								order.setOther_money(order.getOther_money()+info.getCharge());
								System.out.println("订单--退货手续费-->"+order.getOther_money());
								if(order.getPublic_money()==0&&order.getContri_money()==0&&order.getMembers_money()==0){
									order.setState(5);//退货完成
								}
								baseDao.insert(PubConstants.WX_ORDERFORM, order);
							}
						}
					 }
					if(dbObjects != null){
						pro = (OrderFormpro) UniObject.DBObjectToObject(dbObjects, OrderFormpro.class);
						pro.set_id(info.getOrderproId());
						if(info.getType() == 1){//为退货
							pro.setState(3);//订单详情状态变成退货完成
						}
                        if(info.getType() == 2){//为换货
                        	pro.setState(4);//订单详情状态变成换货完成
						}
                        pro.setOther_money(info.getCharge());
                        System.out.println("订单详情手续费---》"+pro.getOther_money());
					}
				}else{
					if(dbObjects != null){
						pro = (OrderFormpro) UniObject.DBObjectToObject(dbObjects, OrderFormpro.class);
						pro.set_id(info.getOrderproId());
						pro.set_id(id);
						pro.setState(0);//将订单详情变成正常订单
					}
				}
				baseDao.insert(PubConstants.SHOP_ODERFORMPRO, pro);
				baseDao.insert(PubConstants.SHOP_AFTERSERVICE, info);
				sub_map.put("state", 0);//提交成功
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
	public void cancel() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);//操作失败
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, id);
		if(dbObject !=null){
			AfterService info = (AfterService) UniObject.DBObjectToObject(dbObject, AfterService.class);
			if(info.getState()==0){
				info.set_id(id);
				info.setState(3);
				DBObject dbObjects = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, info.getOrderproId());
				if(dbObjects != null){
					OrderFormpro pro = (OrderFormpro) UniObject.DBObjectToObject(dbObjects, OrderFormpro.class);
					pro.set_id(info.getOrderproId());
					pro.setState(0);//将订单变成正常订单
					baseDao.insert(PubConstants.SHOP_ODERFORMPRO, pro);
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
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		String id = Struts2Utils.getParameter("id");
		DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_AFTERSERVICE, Long.parseLong(id));
		Struts2Utils.getRequest().setAttribute("dbObject", dbObject);
	}
	
	
	
}
