package com.lsp.shop.web;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.ProType;
import com.lsp.shop.entiy.ShopRemind;
import com.lsp.shop.entiy.ShopType;
import com.mongodb.DBObject;
 

/**
 * 商品分类管理（平台）
 * 
 * @author lsp
 * 
 */
@Namespace("/shop")
@Results({ @Result(name = ShopremindAction.RELOAD, location = "shopremind.action", params = {"parentid", "%{parentid}","fypage", "%{fypage}" }, type = "redirect") })
public class ShopremindAction extends GeneralAction<ShopRemind> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private ShopRemind entity = new ShopRemind();;
	private Long _id;

	private MongoSequence mongoSequence;

	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	@Override
	public String execute() throws Exception {
		gsCustid();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sortMap.put("sort", 1);
		 
		//验证custid
		if(custid.equals(SysConfig.getProperty("gsid"))||custid.equals(SysConfig.getProperty("custid"))) {
			whereMap.put("custid", SysConfig.getProperty("custid"));
		} else {
			whereMap.put("custid", custid);
		}
		String  parentid=Struts2Utils.getParameter("parentid");
		if(StringUtils.isNotEmpty(parentid)){
			whereMap.put("parentid", Long.parseLong(parentid));
		}else {
			whereMap.put("parentid",0L);
		}
		String title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title)){
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.SHOP_REMIND,whereMap,fypage,10, sortMap);
		Struts2Utils.getRequest().setAttribute("funcList", list);
		fycount=baseDao.getCount(PubConstants.SHOP_REMIND,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", fycount); 
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		try {
			custid=SpringSecurityUtils.getCurrentUser().getId(); 
			baseDao.delete(PubConstants.SHOP_REMIND, _id);
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
		DBObject db = baseDao.getMessage(PubConstants.SHOP_REMIND, SysConfig.getProperty("custid")); 
		if(db!=null){
			entity = JSON.parseObject(db.toString(), ShopRemind.class); 
			Struts2Utils.getRequest().setAttribute("entity", entity);
		}
		return "add";
	}
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.SHOP_REMIND, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	protected void prepareModel() throws Exception { 
			DBObject db = baseDao.getMessage(PubConstants.SHOP_REMIND, SysConfig.getProperty("custid"));
            if(db!=null){
            	entity = JSON.parseObject(db.toString(), ShopRemind.class);
            }else{
            	entity=new ShopRemind();
            }
            
			
		 
	}

	@Override
	public String save() throws Exception {
		// 注册业务逻辑
		try { 
			entity.set_id(SysConfig.getProperty("custid")); 
			entity.setCustid(SysConfig.getProperty("custid"));  
			baseDao.insert(PubConstants.SHOP_REMIND, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return update();
	}

	@Override
	public ShopRemind getModel() {
		return entity;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
 

}
