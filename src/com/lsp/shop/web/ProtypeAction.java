package com.lsp.shop.web;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

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
import com.lsp.shop.entiy.ShopType;
import com.mongodb.DBObject;
 

/**
 * 商品分类管理（平台）
 * 
 * @author lsp
 * 
 */
@Namespace("/shop")
@Results({ @Result(name = ProtypeAction.RELOAD, location = "protype.action", params = {"parentid", "%{parentid}","fypage", "%{fypage}" }, type = "redirect") })
public class ProtypeAction extends GeneralAction<ProType> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private ProType entity = new ProType();;
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
		String  parentid=Struts2Utils.getParameter("parentid");
		if(StringUtils.isNotEmpty(parentid)){
			whereMap.put("parentid", Long.parseLong(parentid));
		}else {
			whereMap.put("parentid",0L);
		}
		List<DBObject> list = baseDao.getList(PubConstants.SHOP_PROTYPE,whereMap, sortMap);
		Struts2Utils.getRequest().setAttribute("funcList", list); 
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		try {
			custid=SpringSecurityUtils.getCurrentUser().getId(); 
			baseDao.delete(PubConstants.SHOP_PROTYPE, _id);
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
		DBObject db = baseDao.getMessage(PubConstants.SHOP_PROTYPE, _id);

		entity = JSON.parseObject(db.toString(), ProType.class);
		entity.set_id((Long) db.get("_id"));
		return "add";
	}
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.SHOP_PROTYPE, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) {
			// 有custId查出来 用户信息
			DBObject db = baseDao.getMessage(PubConstants.SHOP_PROTYPE, _id);

			entity = JSON.parseObject(db.toString(), ProType.class);
			entity.set_id((Long) db.get("_id"));
		} else {
			entity = new ProType();
		}
	}

	@Override
	public String save() throws Exception {
		// 注册业务逻辑
		try {
			if (_id == null) {
				_id = mongoSequence.currval(PubConstants.SHOP_PROTYPE);
			}
			entity.set_id(_id);
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			entity.setType(entity.get_id()+"");
			if (StringUtils.isEmpty(Struts2Utils.getParameter("parentid"))) {
				entity.setParentid(0L);
			}
			baseDao.insert(PubConstants.SHOP_PROTYPE, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}

	@Override
	public ProType getModel() {
		return entity;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
	/**
	 * 获取分类
	 * @throws Exception
	 */
	public void gettype() throws Exception{
		HashMap<String, Object>whereMap=new HashMap<>();
		HashMap<String, Object>sortMap=new HashMap<>();
		Map<String,Object>sub_map=new HashMap<>();
		sub_map.put("state", 1);
		String parentId=Struts2Utils.getParameter("parentId");
		whereMap.put("custid", SysConfig.getProperty("custid"));
		if(StringUtils.isNotEmpty(parentId)){
			whereMap.put("parentid", Long.parseLong(parentId));
		}else{
			whereMap.put("parentid", 0L);
		}
		sortMap.put("sort", Long.valueOf(1));
		List<DBObject>list=baseDao.getList(PubConstants.SHOP_PROTYPE, whereMap,  sortMap);
		System.out.println("进入这个方法---->"+list.size());
		if(list.size()>0){
			for (DBObject dbObject : list) {
				whereMap.clear();
				whereMap.put("parentid", Long.parseLong(dbObject.get("_id").toString()));
				List<DBObject>clist=baseDao.getList(PubConstants.SHOP_PROTYPE, whereMap, sortMap);
				if(clist.size()>0) {
					dbObject.put("list", clist);
				}else {
					dbObject.put("list", null);
				}
			}
			sub_map.put("state", 0);
			sub_map.put("list", list);
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	public String classme()throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		return "classme";
	}
	

}
