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
import com.lsp.integral.entity.InteSetting;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence; 
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
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
@Results({ @Result(name = IntesettingAction.RELOAD, location = "intesetting.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class IntesettingAction extends GeneralAction<InteSetting> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private InteSetting entity = new InteSetting();
	private String _id;
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	@Override
	public InteSetting getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, SysConfig.getProperty("custid"));
		System.out.println("db--->"+db);
		Struts2Utils.getRequest().setAttribute("entity", db);
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
		
		try {
			if (_id == null || _id.equals("")) {
				_id = SysConfig.getProperty("custid");
			}
			entity.set_id(_id); 
			entity.setCreatedate(new Date());
			if(entity.getNum() == null){
				entity.setNum("0");
			}
			if(entity.getNums() == null){
				entity.setNums("0");
			}
			if(entity.getNownum() == null){
				entity.setNownum("0");
			}
			if(entity.getNownums() == null){
				entity.setNownums("0");
			}
			baseDao.insert(PubConstants.INTEGRAL_INTESETTING, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		
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
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, _id);

			entity = JSON.parseObject(db.toString(), InteSetting.class);
			entity.set_id((Long) db.get("_id"));
		} else {
			entity = new InteSetting();
		}
	}

}
