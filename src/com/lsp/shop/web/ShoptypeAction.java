package com.lsp.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
import com.lsp.shop.entiy.ShopType;
import com.mongodb.DBObject;
 

/**
 * 资源管理
 * 
 * @author lsp
 * 
 */
@Namespace("/shop")
@Results({ @Result(name = ShoptypeAction.RELOAD, location = "shoptype.action", params = {"parentid", "%{parentid}","fypage", "%{fypage}" }, type = "redirect") })
public class ShoptypeAction extends GeneralAction<ShopType> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private ShopType entity = new ShopType();;
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
		
		String title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title)){
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		String parentid=Struts2Utils.getParameter("parentid");
		if(StringUtils.isNotEmpty(parentid)){
			whereMap.put("parentid", Long.parseLong(parentid));
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
	    
		List<DBObject> list = baseDao.getList(PubConstants.SHOP_SHOPTYPE,whereMap,fypage,10,sortMap);
		fycount=baseDao.getCount(PubConstants.SHOP_SHOPTYPE,whereMap);
		Struts2Utils.getRequest().setAttribute("funcList", list);
		Struts2Utils.getRequest().setAttribute("parentid",whereMap.get("parentid"));
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		try {
			custid=SpringSecurityUtils.getCurrentUser().getId();
			Struts2Utils.getRequest().setAttribute("parentid",
					Struts2Utils.getParameter("parentid"));
			baseDao.delete(PubConstants.SHOP_SHOPTYPE, _id);
			addActionMessage("成功删除!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		Struts2Utils.getRequest().setAttribute("parentid",
				Struts2Utils.getParameter("parentid"));
		return "add";
	}

	@Override
	public String update() throws Exception {
		Struts2Utils.getRequest().setAttribute("parentid",
				Struts2Utils.getParameter("parentid"));
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPTYPE, _id);

		entity = JSON.parseObject(db.toString(), ShopType.class);
		entity.set_id((Long) db.get("_id"));
		return "add";
	}
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPTYPE, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) {
			
			// 有custId查出来 用户信息
			DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPTYPE, _id);

			entity = JSON.parseObject(db.toString(), ShopType.class);
			entity.set_id((Long) db.get("_id"));
		} else {
			entity = new ShopType();
		}
	}

	@Override
	public String save() throws Exception {
		// 注册业务逻辑
		try {
			if (_id == null) {
				_id = mongoSequence.currval(PubConstants.SHOP_SHOPTYPE);
			}
			entity.set_id(_id);
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			entity.setType(entity.get_id()+"");
			baseDao.insert(PubConstants.SHOP_SHOPTYPE, entity);
			Struts2Utils.getRequest().setAttribute("parentid",
					Struts2Utils.getParameter("parentid"));
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}

	@Override
	public ShopType getModel() {
		return entity;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
}
