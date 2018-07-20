package com.lsp.user.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Fromusermb;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.lsp.user.entity.AgentArea;
import com.lsp.user.entity.UserInfo;
import com.lsp.website.entity.RollInfo;
import com.lsp.website.service.WwzService;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 好友管理
 * @author lsp
 *
 */
@Namespace("/user")
@Results({@org.apache.struts2.convention.annotation.Result(name="reload", params = {"parentId", "%{parentId}" ,"fypage", "%{fypage}"},location="area.action", type="redirect")})
public class AreaAction extends GeneralAction<AgentArea>{
	 private static final long serialVersionUID = -6784469775589971579L;

	  @Autowired
	  private BaseDao basedao;
	  private Long _id;
	  private MongoSequence mongoSequence;
	  @Autowired
	  private WwzService  wwzservice; 
	  
	  private AgentArea entity = new AgentArea();
	  
	  @Autowired
	  public void setMongoSequence(MongoSequence mongoSequence)
	  {
	    this.mongoSequence = mongoSequence;
	  }
	  public void set_id(Long _id) {
	    this._id = _id;
	  }
	  @Override
	  public String execute() throws Exception {
	  	HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		sortMap.put("sort", 1);
		String  parentid =Struts2Utils.getParameter("parentId");
		if (StringUtils.isNotEmpty(parentid)) {
			parentid =Struts2Utils.getParameter("parentId");
			whereMap.put("parentId", Long.parseLong(parentid));
		}else {
			whereMap.put("parentId", 0L); 
		} 
        Struts2Utils.getRequest().setAttribute("parentId", parentid);
		sortMap.put("createdate", Integer.valueOf(-1));
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		} 
		List<DBObject> list = basedao.getList(PubConstants.USER_AGENTAREA, whereMap,fypage,10,sortMap);
		for (DBObject dbObject : list) {
			if(dbObject.get("agentId") != null){
				DBObject dbObject2 = basedao.getMessage(PubConstants.USER_INFO, dbObject.get("agentId").toString());
				if(dbObject2 != null){
					if(dbObject2.get("account") != null){
						dbObject.put("agentId", dbObject2.get("account").toString());
					}
				}
			}
		}
		fycount=basedao.getCount(PubConstants.USER_AGENTAREA,whereMap);
		Struts2Utils.getRequest().setAttribute("list", list); 
		Struts2Utils.getRequest().setAttribute("fycount", fycount); 
	  	 return SUCCESS;
	  }
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "add";
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return "add";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		try {
			if(_id==null){
				_id=mongoSequence.currval(PubConstants.USER_AGENTAREA);
			}
			entity.set_id(_id);
			if (StringUtils.isEmpty(Struts2Utils.getParameter("parentId"))) {
				entity.setParentId(0L);
			}
			entity.setCreatedate(new Date());   
			entity.setCustid(SysConfig.getProperty("custid"));
			basedao.insert(PubConstants.USER_AGENTAREA, entity);
			addActionMessage("添加成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addActionMessage("添加失败!");
		}
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			basedao.delete(PubConstants.USER_AGENTAREA, _id);
			addActionMessage("添加成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addActionMessage("添加失败!");
		} 
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if(_id!=null){
			  DBObject emDbObject = this.basedao.getMessage(PubConstants.USER_AGENTAREA, 
				        this._id);
				        this.entity = ((AgentArea)UniObject.DBObjectToObject(emDbObject, 
				        		AgentArea.class));
		}else{
			entity=new AgentArea();
		}
		
	}
	public AgentArea getModel()
	  {
	    return this.entity;
	  }
	public void upd() throws Exception { 
		DBObject db = basedao.getMessage(PubConstants.USER_AGENTAREA, _id); 
		String json = JSONObject.fromObject(db).toString();
		Struts2Utils.renderJson(json, new String[0]);
	}
	/**
	 * 获取下一级地址
	 * @throws Exception
	 */
	public void getchild() throws Exception{
		HashMap<String, Object>whereMap=new HashMap<>();
		HashMap<String, Object>sortMap=new HashMap<>();
		Map<String,Object>sub_map=new HashMap<>();
		sub_map.put("state", 1);
		whereMap.put("custid", SysConfig.getProperty("custid"));
		String parentId = Struts2Utils.getParameter("id");
		
		if(StringUtils.isNotEmpty(parentId)){
			whereMap.put("parentId", Long.parseLong(parentId));
		}else{
			whereMap.put("parentId", 0L);
		}
		sortMap.put("sort", Long.valueOf(-1));
		List<DBObject>list=basedao.getList(PubConstants.USER_AGENTAREA, whereMap,  sortMap);
		if(list.size()>0){
			sub_map.put("state", 0);
			sub_map.put("list", list);			
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	 
}
