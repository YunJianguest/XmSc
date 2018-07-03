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
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.ProType;
import com.lsp.shop.entiy.ShopComReply;
import com.lsp.shop.entiy.ShopComments;
import com.lsp.shop.entiy.ShopType;
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
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sortMap.put("sort", 1);
		 
		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId()); 
		//商品id
		String gid=Struts2Utils.getParameter("gid");
		if(StringUtils.isNotEmpty(gid)) {
			whereMap.put("gid", Long.parseLong(gid));
		}
		//店铺id
		String sid=Struts2Utils.getParameter("sid");
		if(StringUtils.isNotEmpty(sid)) {
			whereMap.put("sid", Long.parseLong(sid));
		}
		//订单id
		String oid=Struts2Utils.getParameter("oid");
		if(StringUtils.isNotEmpty(oid)) {
			whereMap.put("oid", Long.parseLong(oid));
		}
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
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		String sid = Struts2Utils.getParameter("sid");
		String oid = Struts2Utils.getParameter("oid");
		String gid = Struts2Utils.getParameter("gid");
		
		Struts2Utils.getRequest().setAttribute("sid", sid);
		Struts2Utils.getRequest().setAttribute("oid", oid);
		Struts2Utils.getRequest().setAttribute("gid", gid);
		//DBObject dbObject = baseDao.getMessage(PubConstants.SHOP_ODERFORMPRO, Long.parseLong(orderproId));
		//Struts2Utils.getRequest().setAttribute("db", dbObject);
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
			reply.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			reply.setFromid(SpringSecurityUtils.getCurrentUser().getId());
			reply.setHflx(1);
			if (StringUtils.isNotEmpty(parentid)) {
				reply.setParentid(Long.parseLong(parentid));
			} 
			reply.setPicurl(picurl);
			reply.setTitle(title);
			baseDao.insert(PubConstants.SHOP_SHOPCOMREPLY, reply);
		}
	}
	
	/**
	 * ajax回复评论(用户)
	 */
	public void ajaxReplayUser() {
		getLscode();
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
			baseDao.insert(PubConstants.SHOP_SHOPCOMREPLY, reply);
		}
	}
	
	/**
	 * ajax添加评论单商品
	 */
	public void ajaxSaveCom() {
		getLscode();
		Map<String,Object>sub_map = new HashMap<>();
		sub_map.put("state", 1);
		//评论id  
		//sid和gid在传值的时候以逗号隔开
		String sid=Struts2Utils.getParameter("sid"); 
		String oid=Struts2Utils.getParameter("oid"); 
		String gid=Struts2Utils.getParameter("gid");
		String desIscon=Struts2Utils.getParameter("desIscon"); 
		String logisService=Struts2Utils.getParameter("logisService"); 
		String serviceAtt=Struts2Utils.getParameter("serviceAtt"); 
		String content=Struts2Utils.getParameter("content"); 
		String picurl=Struts2Utils.getParameter("picurl");
		String title=Struts2Utils.getParameter("title");
		
		ShopComments comments=new ShopComments();
		if(StringUtils.isEmpty(gid)||StringUtils.isEmpty(sid)||StringUtils.isEmpty(oid)||StringUtils.isEmpty(content)) {
			return;
		}
		/*//店铺id
		String[] idp = sid.split(",");
		//商品id
		String[] isp = gid.split(",");*/
		
		
			comments.set_id(mongoSequence.currval(PubConstants.SHOP_SHOPCOMMENTS));
			comments.setContent(content);
			comments.setCreatedate(new Date());
			comments.setCustid(custid);
			comments.setFromid(fromUserid);
			comments.setGid(Long.parseLong(sid));
			comments.setSid(Long.parseLong(gid));
			comments.setOid(Long.parseLong(oid));
			comments.setCreatedate(new Date());
			comments.setTitle(title);
			comments.setPicurl(picurl);
			comments.setDesIscon(Integer.parseInt(desIscon));
			comments.setLogisService(Integer.parseInt(logisService));
			comments.setServiceAtt(Integer.parseInt(serviceAtt));
			baseDao.insert(PubConstants.SHOP_SHOPCOMMENTS, comments);
		
		sub_map.put("state", 0);
		
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

}
