package com.lsp.integral.web;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.lsp.integral.entity.Miner;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence; 
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.web.GeneralAction;
import com.mongodb.DBObject;

/**
 * 设置
 * 
 * @author lsp
 * 
 */
@Namespace("/integral")
@Results({ @Result(name = MinersAction.RELOAD, location = "miners.action", params = {"fypage", "%{fypage}" }, type = "redirect") })
public class MinersAction extends GeneralAction<Miner> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private MongoSequence mongoSequence;
	private Miner entity = new Miner();
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
	public Miner getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		sortMap.put("sort", -1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		
		String  title=Struts2Utils.getParameter("title");
		if(StringUtils.isNotEmpty(title))
		{
			Pattern pattern = Pattern.compile("^.*" + title + ".*$",
					Pattern.CASE_INSENSITIVE);
			whereMap.put("ptitle", pattern);
			Struts2Utils.getRequest().setAttribute("title",  title);
		}
		
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_MINER,null,fypage,10,sortMap);
		Struts2Utils.getRequest().setAttribute("list", list);
		
		this.fycount = this.baseDao.getCount(PubConstants.INTEGRAL_MINER,whereMap);
		Struts2Utils.getRequest().setAttribute("fycount", this.fycount);
		return SUCCESS;
	}
	
	@Override
	public String save() throws Exception {
	
		try {
			if (_id == null ) {
				_id = mongoSequence.currval(PubConstants.INTEGRAL_MINER);
			}
			entity.set_id(_id); 
			entity.setCreatedate(new Date());
			entity.setCustid(SpringSecurityUtils.getCurrentUser().getId());
			baseDao.insert(PubConstants.INTEGRAL_MINER, entity); 
			addActionMessage("成功添加!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		SpringSecurityUtils.getCurrentUser().getId();
		baseDao.delete(PubConstants.INTEGRAL_MINER, _id);
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
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_MINER, _id);
			this.entity = ((Miner)UniObject.DBObjectToObject(db, 
					Miner.class));
		} else {
			entity = new Miner();
		}
	}
	
	public String list() throws Exception{
		return "list";
	}
	
	/**
	 * 移动端矿机列表
	 * @throws Exception
	 */
	public void showall() throws Exception{
		getLscode();
		Struts2Utils.getRequest().setAttribute("custid", custid);
		Struts2Utils.getRequest().setAttribute("lscode", lscode);
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		
		sortMap.put("sort", -1);   
		whereMap.put("custid", SysConfig.getProperty("custid"));
		
		//分页
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<DBObject> list = baseDao.getList(PubConstants.INTEGRAL_MINER,null,fypage,10,sortMap);
	}

}
