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
import com.lsp.integral.entity.InteProstore;
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
 * 预存账单
 * 
 * @author lsp
 * 
 */
@Namespace("/integral")
@Results({ @Result(name = ProstoreAction.RELOAD, location = "prostore.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class ProstoreAction extends GeneralAction<InteProstore> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	//主键自增
	private MongoSequence mongoSequence;
	private InteProstore entity = new InteProstore();
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
	public InteProstore getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		gsCustid();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sortMap.put("createdate", -1);  
		if(custid.equals(SysConfig.getProperty("gsid"))||custid.equals(SysConfig.getProperty("custid"))) {
		}else{
			whereMap.put("fromUserid", custid);
		}
		String  title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title))
		{
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		//查询全部带分页的
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_PROSTORE,whereMap,fypage,10,sortMap);
		Struts2Utils.getRequest().setAttribute("list", list);
		for (DBObject dbObject : list) {
			if(dbObject.get("fromUserid")!=null){
				DBObject db = baseDao.getMessage(PubConstants.USER_INFO, dbObject.get("fromUserid").toString());
				if(db!=null){
					if(db.get("account")!=null){
						dbObject.put("account", db.get("account").toString());
					}
					if(db.get("no")!=null){
						dbObject.put("vip_no", db.get("no").toString());
					}
				}
			}
		} 
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", Long.valueOf(this.fycount));
		DBObject dbs = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		if(dbs!=null){
			if(dbs.get("name")!=null){
				Struts2Utils.getRequest().setAttribute("jfname", dbs.get("name").toString());
			}
		}
		return SUCCESS;
	}
	

	public String list() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		//custid=SpringSecurityUtils.getCurrentUser().getId();
		sortMap.put("createdate", -1);   
		/*whereMap.put("fromUserid", custid);*/
		String  title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title))
		{
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("name", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		//查询全部带分页的
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_PROSTORE,whereMap,fypage,10,sortMap);
		Struts2Utils.getRequest().setAttribute("list", list);
		for (DBObject dbObject : list) {
			if(dbObject.get("fromUserid")!=null){
				DBObject db = baseDao.getMessage(PubConstants.USER_INFO, dbObject.get("fromUserid").toString());
				if(db!=null){
					if(db.get("account")!=null){
						dbObject.put("account", db.get("account").toString());
					}
					if(db.get("no")!=null){
						dbObject.put("vip_no", db.get("no").toString());
					}
				}
			}
		} 
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", Long.valueOf(this.fycount));
		DBObject dbs = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		if(dbs!=null){
			if(dbs.get("name")!=null){
				Struts2Utils.getRequest().setAttribute("jfname", dbs.get("name").toString());
			}
		}
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
		
		
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		try {
			
			baseDao.delete(PubConstants.INTEGRAL_PROSTORE, _id);
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
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_PROSTORE, _id);
			this.entity = ((InteProstore)UniObject.DBObjectToObject(db, 
					InteProstore.class));
		} else {
			entity = new InteProstore();
		}
	}
	
	public void upd() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_PROSTORE, _id);
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
}
