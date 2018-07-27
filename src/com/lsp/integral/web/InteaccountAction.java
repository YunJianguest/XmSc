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
import com.lsp.integral.entity.InteAccount;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence; 
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.RoleInfo;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
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
@Results({ @Result(name = InteaccountAction.RELOAD, location = "inteaccount.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class InteaccountAction extends GeneralAction<InteAccount> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	//主键自增
	private MongoSequence mongoSequence;
	private InteAccount entity = new InteAccount();
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
	public InteAccount getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		gsCustid();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		sortMap.put("sort", -1);   
		//验证custid
		if(custid.equals(SysConfig.getProperty("gsid"))||custid.equals(SysConfig.getProperty("custid"))) {
			whereMap.put("custid", SysConfig.getProperty("custid"));
		} else {
			whereMap.put("custid", custid);
		}
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
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_INTEACCOUNT,whereMap,fypage,10,sortMap);
		for (DBObject dbObject : list) {
			if(dbObject.get("cid")!=null) {
				DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTECURRENCY, Long.parseLong(dbObject.get("cid").toString()));
				if(db!=null) {
					if(db.get("name")!=null) {
						dbObject.put("cname", db.get("name").toString());
					}
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("list", list);
		System.out.println(list);
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_INTEACCOUNT,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", Long.valueOf(this.fycount));
		
		whereMap.clear();
		sortMap.put("sort", -1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		List<DBObject> clist = baseDao.getList(PubConstants.INTEGRAL_INTECURRENCY,whereMap, sortMap);
		Struts2Utils.getRequest().setAttribute("clist", clist);
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
		gsCustid();
		try {
			if (_id == null) {
				_id = mongoSequence.currval(PubConstants.INTEGRAL_INTEACCOUNT);
			}
			entity.set_id(_id); 
			if(custid.equals(SysConfig.getProperty("gsid"))||custid.equals(SysConfig.getProperty("custid"))) {
				entity.setCustid(SysConfig.getProperty("custid"));
			}else{
				entity.setCustid(custid);
			}
			entity.setCreatedate(new Date());
			baseDao.insert(PubConstants.INTEGRAL_INTEACCOUNT, entity); 
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
			
			baseDao.delete(PubConstants.INTEGRAL_INTEACCOUNT, _id);
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
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTEACCOUNT, _id);
			this.entity = ((InteAccount)UniObject.DBObjectToObject(db, 
					InteAccount.class));
		} else {
			entity = new InteAccount();
		}
	}
	
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTEACCOUNT, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	
	public void  selintecurrency(){
		Map<String, Object> sub_map = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();

		sortMap.put("sort", -1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_INTECURRENCY,whereMap, sortMap);
		sub_map.put("list", list);
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
    }
	

}
