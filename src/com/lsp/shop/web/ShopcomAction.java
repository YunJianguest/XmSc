package com.lsp.shop.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.alibaba.fastjson.JSON;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.OrderForm;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.shop.entiy.ProType;
import com.lsp.shop.entiy.ProductInfo;
import com.lsp.shop.entiy.ShopComReply;
import com.lsp.shop.entiy.ShopComments;
import com.lsp.shop.entiy.ShopType;
import com.lsp.shop.entiy.Useraddress;
import com.lsp.website.entity.WwzFlowInfo;
import com.lsp.website.service.WwzService;
import com.lsp.weixin.entity.WxUser;
import com.mongodb.DBObject;
 

/**
 * 店铺评论
 * 
 * @author lsp
 * 
 */
@Namespace("/shop")
@Results({ @Result(name = ShopcomAction.RELOAD, location = "shopcom.action", params = {"oid", "%{oid}" ,"sid", "%{sid}" ,"gid", "%{gid}" ,"fypage", "%{fypage}" }, type = "redirect") })
public class ShopcomAction extends GeneralAction<ShopComments> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private ShopComments entity = new ShopComments();;
	private Long _id;

	private MongoSequence mongoSequence;
	@Autowired
	private WwzService wwzService;

	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		//sortMap.put("sort", 1);
		 
		//whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId()); 
		//商品id
		/*String gid=Struts2Utils.getParameter("gid");
		if(StringUtils.isNotEmpty(gid)) {
			whereMap.put("gid", Long.parseLong(gid));
		}*/
		/*//店铺id
		String sid=Struts2Utils.getParameter("sid");
		if(StringUtils.isNotEmpty(sid)) {
			whereMap.put("sid", Long.parseLong(sid));
		}
		//订单id
		String oid=Struts2Utils.getParameter("oid");
		if(StringUtils.isNotEmpty(oid)) {
			whereMap.put("oid", Long.parseLong(oid));
		}*/
		List<DBObject> list = baseDao.getList(PubConstants.SHOP_SHOPCOMMENTS,whereMap, sortMap);
		Struts2Utils.getRequest().setAttribute("list", list); 
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		try {
			custid=SpringSecurityUtils.getCurrentUser().getId(); 
			baseDao.delete(PubConstants.SHOP_SHOPCOMMENTS, _id);
			addActionMessage("成功删除!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception { 
		return "add";
	}

	@Override
	public String update() throws Exception { 
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPCOMMENTS, _id);

		entity = JSON.parseObject(db.toString(), ShopComments.class);
		entity.set_id((Long) db.get("_id"));
		return "add";
	}
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPCOMMENTS, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) {
			// 有custId查出来 用户信息
			DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPCOMMENTS, _id);

			entity = JSON.parseObject(db.toString(), ShopComments.class);
			entity.set_id((Long) db.get("_id"));
		} else {
			entity = new ShopComments();
		}
	}

	@Override
	public String save() throws Exception {
		// 注册业务逻辑
		try {
			if (_id == null) {
				_id = mongoSequence.currval(PubConstants.SHOP_SHOPCOMMENTS);
			}
			entity.set_id(_id);
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId()); 
			baseDao.insert(PubConstants.SHOP_SHOPCOMMENTS, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}

	@Override
	public ShopComments getModel() {
		return entity;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
	/***
	 * 评论初始化页面
	 * @return
	 * @throws Exception
	 * @author CuiJing
	 * @version 
	 * @date 2018年7月3日 下午5:39:47
	 */
	public String shopcomadd() throws Exception{
		getLscode();  
		String oid = Struts2Utils.getParameter("oid");
		String gid = Struts2Utils.getParameter("gid");
		if(StringUtils.isNotEmpty(gid)) {
			DBObject dbObject = baseDao.getMessage(PubConstants.DATA_PRODUCT,Long.parseLong(gid));
			if(dbObject != null){
				if(dbObject.get("comid") !=null){
					Struts2Utils.getRequest().setAttribute("sid", dbObject.get("comid").toString());
				}
			}
		}
		
		Struts2Utils.getRequest().setAttribute("oid", oid);
		Struts2Utils.getRequest().setAttribute("gid", gid);
		
		return "shopcomadd";
	}
	
	/**
	 * 评论回复
	 * @return
	 * @throws Exception
	 */
	public String replay() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sortMap.put("sort", 1);
		 
		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId()); 
		//回复类型
		String hflx=Struts2Utils.getParameter("hflx");
		if(StringUtils.isNotEmpty(hflx)) {
			whereMap.put("hflx", Long.parseLong(hflx));
			Struts2Utils.getRequest().setAttribute("hflx", "hflx");
		}
		//父ID
		String parentid=Struts2Utils.getParameter("parentid");
		if(StringUtils.isNotEmpty(parentid)) {
			whereMap.put("parentid", Long.parseLong(parentid));
			Struts2Utils.getRequest().setAttribute("parentid", "parentid");
		}
		//评论id
		String comid=Struts2Utils.getParameter("comid");
		if(StringUtils.isNotEmpty(comid)) {
			whereMap.put("comid", Long.parseLong(comid));
			Struts2Utils.getRequest().setAttribute("comid", "comid");
		}
		List<DBObject> list = baseDao.getList(PubConstants.SHOP_SHOPCOMREPLY,whereMap, sortMap);
		Struts2Utils.getRequest().setAttribute("list", list); 
		return "replay";
	}
	/**
	 * ajax回复评论(商家)
	 */
	public void ajaxReplayAdm() {
		getLscode(); 
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);
		//评论id
		String comid=Struts2Utils.getParameter("comid");
		
		//String parentid=Struts2Utils.getParameter("parentid");
		String content=Struts2Utils.getParameter("content"); 
		String picurl=Struts2Utils.getParameter("picurl");
		String title=Struts2Utils.getParameter("title");
		//父ID
		String parentid="";
		if(StringUtils.isNotEmpty(comid)) {
			DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_SHOPCOMREPLY,Long.parseLong(comid));
			if(dbObject!=null) {
				if(dbObject.get("_id") !=null) {
					parentid=dbObject.get("_id").toString();
				}
			}
		}
		if(StringUtils.isNotEmpty(comid)) {
			ShopComReply reply=new ShopComReply();
			reply.set_id(mongoSequence.currval(PubConstants.SHOP_SHOPCOMREPLY));
			reply.setComid(Long.parseLong(comid));
			reply.setContent(content);
			reply.setCreatedate(new Date());
			reply.setCustid(custid);
			reply.setFromid(fromUserid);
			reply.setHflx(1);
			if (StringUtils.isNotEmpty(parentid)) {
				reply.setParentid(Long.parseLong(parentid));
			} 
			reply.setPicurl(picurl);
			reply.setTitle(title);
			int i=baseDao.insert(PubConstants.SHOP_SHOPCOMREPLY, reply);
			if (i>0) {
				
				sub_map.put("state", 0);
			}
			String json = JSONArray.fromObject(sub_map).toString();
			Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
			System.out.println(json);
		}
	}
	
	/**
	 * ajax回复评论(用户)
	 */
	public void ajaxReplayUser() {
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);
		//评论id
		String comid=Struts2Utils.getParameter("comid");
		//父ID
		String parentid=Struts2Utils.getParameter("parentid");
		String content=Struts2Utils.getParameter("content"); 
		String picurl=Struts2Utils.getParameter("picurl");
		String title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(comid)) {
			ShopComReply reply=new ShopComReply();
			reply.set_id(mongoSequence.currval(PubConstants.SHOP_SHOPCOMREPLY));
			reply.setComid(Long.parseLong(comid));
			reply.setContent(content);
			reply.setCreatedate(new Date());
			reply.setCustid(custid);
			reply.setFromid(fromUserid);
			reply.setHflx(0);
			if (StringUtils.isNotEmpty(parentid)) {
				reply.setParentid(Long.parseLong(parentid));
			} 
			reply.setPicurl(picurl);
			reply.setTitle(title);
			int i =baseDao.insert(PubConstants.SHOP_SHOPCOMREPLY, reply);
			if (i>0) {	
				sub_map.put("state", 0);
			}
			String json = JSONArray.fromObject(sub_map).toString();
			Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
			System.out.println(json);
		}
	}
	
	/**
	 * ajax添加评论单商品
	 */
	public void ajaxSaveCom() {
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sub_map.put("state", 1);
		//评论id
		String sid=Struts2Utils.getParameter("sid"); 
		String oid=Struts2Utils.getParameter("oid"); 
		String gid=Struts2Utils.getParameter("gid");
		String desIscon=Struts2Utils.getParameter("goodsevalulen"); 
		String logisService=Struts2Utils.getParameter("logisticsEvalu"); 
		String serviceAtt=Struts2Utils.getParameter("serviceEvalu"); 
		String content=Struts2Utils.getParameter("cause"); 
		ShopComments comments=new ShopComments();
		if(StringUtils.isEmpty(gid)||StringUtils.isEmpty(sid)||StringUtils.isEmpty(oid)) {
			return;
		}
		
		comments.set_id(mongoSequence.currval(PubConstants.SHOP_SHOPCOMMENTS));
		comments.setContent(content);
		comments.setCreatedate(new Date());
		comments.setCustid(custid);
		comments.setFromid(fromUserid);
		comments.setGid(Long.parseLong(gid));
		comments.setSid(Long.parseLong(sid));
		comments.setOid(oid);
		comments.setCreatedate(new Date());
		comments.setDesIscon(Integer.parseInt(desIscon));
		comments.setLogisService(Integer.parseInt(logisService));
		comments.setServiceAtt(Integer.parseInt(serviceAtt));
		int i=baseDao.insert(PubConstants.SHOP_SHOPCOMMENTS, comments);
		if (i>0) {
			
			sub_map.put("state", 0);
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	/**
	 * 获取评论列表
	 */
	public void getCom() {
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("createdate",-1);
		sub_map.put("state", 1);
		//评论id
		String gid=Struts2Utils.getParameter("gid");
		if (StringUtils.isNotEmpty(gid)) {
			whereMap.put("gid", Long.parseLong(gid));
		}
		List<DBObject>list=baseDao.getList(PubConstants.SHOP_SHOPCOMMENTS, whereMap, sortMap);
		if(list.size()>0) {
			sub_map.put("state",0);
			for (DBObject dbObject : list) {
				if(dbObject.get("fromid")!=null) {
					dbObject.put("nickname",wwzService.getWxUser(dbObject.get("fromid").toString()).get("nickname"));
				}
				if(dbObject.get("_id")!=null) {
					whereMap.clear();
					whereMap.put("comid", Long.parseLong(dbObject.get("_id").toString()));
					DBObject obj = baseDao.getMessage(PubConstants.SHOP_SHOPCOMREPLY,whereMap);
					if(obj!=null) {
						dbObject.put("sjreply",obj.get("content"));
						dbObject.put("reply_id",obj.get("_id"));
						if(obj.get("_id")!=null) {
							whereMap.clear();
							whereMap.put("parentid", Long.parseLong(obj.get("_id").toString()));
							DBObject objs = baseDao.getMessage(PubConstants.SHOP_SHOPCOMREPLY,whereMap);
							System.out.println(objs);
							System.out.println(objs.get("content"));
							if(objs!=null) {
								if(objs.get("content")!=null) {
									dbObject.put("yhreply",objs.get("content"));
								}
								
							}
						}
					}				
				}
			}
			sub_map.put("state", 0);
			sub_map.put("list",list);
		}
		String json = JSONArray.fromObject(sub_map).toString();
		System.out.println(json);
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
}
