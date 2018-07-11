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
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DictionaryUtil;
import com.lsp.pub.util.ExportExcel;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderForm;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.website.service.WwzService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
 
/**
 * 订单
 * @author lsp
 *
 */
@Namespace("/shop")
@Results( { @Result(name = OrderformAction.RELOAD, location = "orderform.action",params={"fypage", "%{fypage}","state","%{state}","comid","%{comid}"}, type = "redirect") })
public class OrderformAction extends GeneralAction<OrderForm> {

	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private WwzService wwzService;
	@Autowired
	private DictionaryUtil dictionaryUtil;
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
			BasicDBList dbList = new BasicDBList();
			dbList.add(Long.parseLong(comid));
			whereMap.put("comids",Long.parseLong(comid));
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
				 if(user.get("nickname") != null){
					 db.put("nickname", user.get("nickname"));
				 }else{
					 db.put("nickname", user.get("tel")); 
				 }
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
	
	public void delall(){
		baseDao.delete(PubConstants.WX_ORDERFORM);
		baseDao.delete(PubConstants.SHOP_ODERFORMPRO);
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
	public String fahuo() throws Exception {
		//注册业务逻辑
		try {
			prepareModel();
			entity.set_id(_id);
			entity.setState(3);
			baseDao.insert(PubConstants.WX_ORDERFORM, entity);
			addActionMessage("成功添加!");
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		
		return "orderform";
	}
	

	
	@Override
	public OrderForm getModel() {
		return entity;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void wxpay() throws Exception {
		Long payid=Long.parseLong(Struts2Utils.getParameter("payid"));
		DBObject db = baseDao.getMessage(PubConstants.SHOP_WXPAY, payid);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	/*public void orderinfo() throws Exception {
		Long orderid=Long.parseLong(Struts2Utils.getParameter("orderid"));
		HashMap<String, Object> whereMap =new HashMap<String, Object>();
		whereMap.put("orderid", orderid);
		Map<String, Object> sub_map = new HashMap<String, Object>();
		List<DBObject> orderList = baseDao.getList(PubConstants.WX_ORDERBUY, whereMap,null);
		sub_map.put("list", orderList);
		String json = JSONArray.fromObject(sub_map).toString();
		
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}*/
	public void orderinfo() throws Exception {
		String  orderid=Struts2Utils.getParameter("orderid");
		HashMap<String, Object> whereMap =new HashMap<String, Object>();
		whereMap.put("orderid", orderid);
		Map<String, Object> sub_map = new HashMap<String, Object>();
		List<DBObject> orderList = baseDao.getList(PubConstants.SHOP_ODERFORMPRO, whereMap,null);
		sub_map.put("list", orderList);
		String json = JSONArray.fromObject(sub_map).toString();
		
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	public void orderfromexp() throws Exception {

		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();  
		String  comid=Struts2Utils.getParameter("comid");
		if(StringUtils.isNotEmpty(comid)){
			BasicDBList dbList = new BasicDBList();
			dbList.add(Long.parseLong(comid));
			whereMap.put("comids",Long.parseLong(comid));
		} 
		String sel_state = Struts2Utils.getParameter("sel_state");
		if (StringUtils.isNotEmpty(sel_state)) {
			whereMap.put("state", Integer.parseInt(sel_state));
		}
		String sel_insdate = Struts2Utils.getParameter("sel_insdate");
		System.out.println("sel_insdate---->"+sel_insdate);
		String sel_enddate = Struts2Utils.getParameter("sel_enddate");
		BasicDBObject dateCondition = new BasicDBObject();
		if (StringUtils.isNotEmpty(sel_insdate)) {			
			dateCondition.append("$gte", DateFormat.getFormat(sel_insdate));
			whereMap.put("insDate", dateCondition);
		}
		if (StringUtils.isNotEmpty(sel_enddate)) {
			dateCondition.append("$lte", DateFormat.getFormat(sel_enddate));
			whereMap.put("insDate", dateCondition);
		}
		sortMap.put("insDate", -1);
		List<DBObject> list = baseDao.getList(PubConstants.WX_ORDERFORM,
				whereMap, 0, 3000, sortMap);
		for (DBObject dbObject : list) {
			if (dbObject.get("fromUserid") != null) {			 
			 DBObject  user=wwzService.getWxUser(dbObject.get("fromUserid").toString());
			 dbObject.put("nickname", user.get("nickname"));
			 dbObject.put("headimgurl", user.get("headimgurl"));
				 
			 dbObject.put("insDate",DateFormat.getDate(DateFormat.getFormat(dbObject.get("insDate").toString())));
			 if(dbObject.get("fhdate")!=null){
				 dbObject.put("fhdate",DateFormat.getDate(DateFormat.getFormat(dbObject.get("fhdate").toString())));
			 }			 
			} 
			dbObject.put("total",
					BaseDecimal.round(dbObject.get("total").toString(), 2));
			if (dbObject.get("zfmoney") == null) {
				dbObject.put("zfmoney", "0");
			} else {
				dbObject.put("zfmoney", BaseDecimal.round(
						dbObject.get("zfmoney").toString(), 2));
			} 
			Double public_money = 0.0;
			Double contri_money = 0.0;
			Double members_money = 0.0;
			Double other_money = 0.0;
			
			//whereMap.put("orderid", dbObject.get("_id").toString());
			System.out.println("----orderid--->"+dbObject.get("_id").toString());
			/*if(StringUtils.isNotEmpty(comid)){
				whereMap.put("pro.comid", comid);
			}*/
			System.out.println("----comid--->"+comid);
			List<DBObject> lists = baseDao.getList(PubConstants.SHOP_ODERFORMPRO, whereMap,sortMap);
			System.out.println("lists.size-->"+lists);
			for (DBObject dbObject2 : lists) {
				
				OrderFormpro pro = (OrderFormpro) UniObject.DBObjectToObject(dbObject2, OrderFormpro.class);
				System.out.println("---->"+pro);
				DBObject dbObject3 = pro.getPro();
				if(dbObject3!=null){
					System.out.println("---->"+dbObject3.get("goodstype"));
					if(dbObject3.get("goodstype")!=null){
						if(dbObject3.get("goodstype").toString().equals("3")){//商品为大众区商品
							public_money +=Double.parseDouble(dbObject3.get("price").toString());
						}
						if(dbObject3.get("goodstype").toString().equals("4")){//商品为特约区商品
							contri_money +=Double.parseDouble(dbObject3.get("price").toString());		
						}
						if(dbObject3.get("goodstype").toString().equals("5")){//商品为会员区商品
							members_money +=Double.parseDouble(dbObject3.get("price").toString());
						}
					}
				}
				if(pro.getOther_money()!=null){
					other_money+=pro.getOther_money();
				}
				
			}
			dbObject.put("public_money", BaseDecimal.round(public_money+"", 2));
			dbObject.put("contri_money", BaseDecimal.round(contri_money+"", 2));
			dbObject.put("members_money", BaseDecimal.round(members_money+"", 2));
			dbObject.put("other_money", BaseDecimal.round(other_money+"", 2));

			if(dbObject.get("kdcom")!=null){
				dbObject.put("kdcom",wwzService.getKdName(dbObject.get("kdcom").toString()));
			} 
			switch (Integer.parseInt(dbObject.get("state").toString())) {
			case 1:
				dbObject.put("state", "下单");
				break;
			case 2:
				dbObject.put("state", "已购买");
				break;
			case 3:
				dbObject.put("state", "已发货");
				break;
			case 4:
				dbObject.put("state", "已收货");
				break;

			default:
				break;
			}
		}
		String[] header = { "微信名", "订单号", "快递单位", "快递号", "大众区金额", "特约区金额","会员区金额","其他费用（如存在退款，为退款手续费）","实付款",
				 "备注", "姓名", "电话", "地址", "订货日期", "发货日期", "状态" };
		String[] body = { "nickname", "_id", "kdcom", "kdno", "public_money","contri_money","members_money","other_money","zfmoney",
				"remark", "count", "name", "tel",
				"address", "insDate", "fhdate", "state" };
		String newtime = "orderfrom" + ".xls";

		HSSFWorkbook wb = ExportExcel
				.exportByMongo(list, header, body, newtime);
		Struts2Utils.getResponse().setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(newtime, "utf-8"));
		OutputStream ouputStream = Struts2Utils.getResponse().getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	/***
	 * 订单详情
	 * @return
	 * @throws Exception
	 * @author CuiJing
	 * @version 
	 * @date 2018年7月4日 下午4:56:51
	 */
	public String orderDetailsById() throws Exception{
		getLscode();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortsMap = new HashMap<String, Object>();
		String orderId = Struts2Utils.getParameter("orderId");
		if(StringUtils.isNotEmpty(orderId)){
			whereMap.put("orderid", orderId);
			Struts2Utils.getRequest().setAttribute("orderId", orderId);
		}
		String title = Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title)){
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("pro.ptitle", pattern);
			Struts2Utils.getRequest().setAttribute("title", title);
		}
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_ODERFORMPRO, whereMap);
		List<DBObject> lists = baseDao.getList(PubConstants.SHOP_ODERFORMPRO,whereMap,fypage,10,sortsMap);
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		Struts2Utils.getRequest().setAttribute("list", lists);
		
		return "sjreply";
	}
	/***
	 * 评论列表
	 * @return
	 * @throws Exception
	 * @author CuiJing
	 * @version 
	 * @date 2018年7月4日 下午4:56:51
	 */
	public String ordercom() throws Exception{
		getLscode();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortsMap = new HashMap<String, Object>();
		sortsMap.put("createdate", -1);
		String gid = Struts2Utils.getParameter("gid");
		 if(StringUtils.isNotEmpty(gid)){
			 whereMap.put("gid",Long.parseLong(gid));
		 }
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_SHOPCOMMENTS, whereMap);
		List<DBObject> lists = baseDao.getList(PubConstants.SHOP_SHOPCOMMENTS,whereMap,fypage,10,sortsMap);
		System.out.println("lists---->"+lists);
		
		for (DBObject dbObject : lists) {

			if (dbObject.get("fromid") != null) {
			 
			 DBObject  user=wwzService.getWxUser(dbObject.get("fromid").toString());
			 dbObject.put("nickname", user.get("nickname"));
			 dbObject.put("headimgurl", user.get("headimgurl"));
				 

			} 
		 
		}
		Struts2Utils.getRequest().setAttribute("list", lists);
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		return "ordercom";
	}
	
	/***
	 * 回复列表
	 * @return
	 * @throws Exception
	 * @author CuiJing
	 * @version 
	 * @date 2018年7月4日 下午4:56:51
	 */
	public String orderreply() throws Exception{
		getLscode();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortsMap = new HashMap<String, Object>();
		String parentid = Struts2Utils.getParameter("parentid");
		whereMap.put("parentid",Long.parseLong(parentid)); 
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_SHOPCOMREPLY, whereMap);
		List<DBObject> lists = baseDao.getList(PubConstants.SHOP_SHOPCOMREPLY,whereMap,fypage,10,sortsMap);
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		for (DBObject dbObject : lists) {

			if (dbObject.get("fromid") != null) {
			 
			 DBObject  user=wwzService.getWxUser(dbObject.get("fromid").toString());
			 dbObject.put("nickname", user.get("nickname"));
			 dbObject.put("headimgurl", user.get("headimgurl"));
				 
			 dbObject.put("createdate",DateFormat.getDate(DateFormat.getFormat(dbObject.get("createdate").toString())));
			 
			 
			} 
		 
		}
		Struts2Utils.getRequest().setAttribute("list", lists);
		
		return "orderreply";
	}
	
}
