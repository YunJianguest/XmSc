package com.lsp.integral.web;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.lsp.hou.entity.ServeType;
import com.lsp.integral.entity.InteCurrency;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence; 
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.RoleInfo;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.Useraddress;
import com.mongodb.DBObject;

/**
 * 设置
 * 
 * @author lsp
 * 
 */
@Namespace("/integral")
@Results({ @Result(name = IntecurrencyAction.RELOAD, location = "intecurrency.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class IntecurrencyAction extends GeneralAction<InteCurrency> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	//主键自增
	private MongoSequence mongoSequence;
	private InteCurrency entity = new InteCurrency();
	private Long _id;
	
	public void set_id(Long _id) {
		this._id = _id;
	}
	@Autowired
	  public void setMongoSequence(MongoSequence mongoSequence)
	  {
	    this.mongoSequence = mongoSequence;
	  } 
	@Override
	public InteCurrency getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		custid=SpringSecurityUtils.getCurrentUser().getId();
		sortMap.put("sort", -1);   
		whereMap.put("custid", custid);
		
		String  title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title))
		{
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		//查询不带分页的List<DBObject> list = baseDao.getList(PubConstants.SHOP_SHOPMB,whereMap, sortMap);
		//查询全部带分页的
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_INTECURRENCY,whereMap,fypage,10,sortMap);
		Struts2Utils.getRequest().setAttribute("List", list);
		
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_INTECURRENCY,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", Long.valueOf(this.fycount));
		
		return SUCCESS;
		
		
	}
	
	@Override
	public String save() throws Exception {
		
		try {
			if (_id == null) {
				_id = mongoSequence.currval(PubConstants.INTEGRAL_INTECURRENCY);
			}
			entity.set_id(_id); 
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			entity.setCreatedate(new Date());
			baseDao.insert(PubConstants.INTEGRAL_INTECURRENCY, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		try {
			
			baseDao.delete(PubConstants.INTEGRAL_INTECURRENCY, _id);
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

		return "add";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (_id != null) { 
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTECURRENCY, _id);
			this.entity = ((InteCurrency)UniObject.DBObjectToObject(db, 
					InteCurrency.class));
		} else {
			entity = new InteCurrency();
		}
	}
	
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTECURRENCY, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	

	

}
