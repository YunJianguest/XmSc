package com.lsp.shop.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.ProductAttention;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;


/**
 * 商品关注
 * @author Administrator
 *
 */
@Namespace("/shop")
@Results({ @Result(name = ProductattentionAction.RELOAD, location = "productattention.action", params={"fypage", "%{fypage}"}, type = "redirect") })
public class ProductattentionAction  extends GeneralAction<ProductAttention> {
	
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private Long _id;
	
	private MongoSequence mongoSequence;
	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	ProductAttention entity = new ProductAttention();//商品关注

	/**
	 * 商品关注 
	 * @param productId： 商品id
	 */
	public void ajaxsave() {
		getLscode();
		Map<String, Object> sub_map = new HashMap<String, Object>(); 
		try {
			String productId = Struts2Utils.getParameter("productId");
			if(_id == null){
				_id=mongoSequence.currval(PubConstants.SHOP_PRODUCTATTENTION);	
			}
			entity.set_id(_id);
			entity.setFromUserid(fromUserid);
			entity.setProductId(productId);
			entity.setCreatedate(new Date());
			baseDao.insert(PubConstants.SHOP_PRODUCTATTENTION,entity);
			sub_map.put("state", "1");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
			sub_map.put("state", "0");
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	/**
	 * 商品关注取消 根据商品id
	 */
	public void ajaxdelete() {
		getLscode();
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		HashMap<String, Object>sortMap=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("state", "0");
		try {
			String productId = Struts2Utils.getParameter("productId");//商品id
			whereMap.put("productId", productId);
			whereMap.put("fromUserid", fromUserid);
			List<DBObject> plist=baseDao.getList(PubConstants.SHOP_PRODUCTATTENTION, whereMap, sortMap);
			if(plist!=null) {
				for(DBObject dbObject : plist) {
					Long id = Long.parseLong(dbObject.get("_id").toString());
					baseDao.delete(PubConstants.SHOP_PRODUCTATTENTION,id);
					map.put("state", "1");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
			map.put("state", "0");
		}
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	
	/**
	 * 根据关注商品id 取消收藏
	 */
	public void ajaxdeletepro() {
		getLscode();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("state", "0");
		try {
			String proAttentionIds = Struts2Utils.getParameter("proAttentionIds");//关注商品id
			String[] idArr= proAttentionIds.split(",");
			for(String id :idArr) 
			{
				baseDao.delete(PubConstants.SHOP_PRODUCTATTENTION,Long.parseLong(id));
				map.put("state", "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
			map.put("state", "0");
		}
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	
	/**
	 * 判断商品是否已经关注
	 */
	public void ajaxproductattenById()
	{
		getLscode();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("state", "0");
		String productId = Struts2Utils.getParameter("productId");
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		HashMap<String, Object>sortMap=new HashMap<String, Object>();
		whereMap.put("productId", productId);
		whereMap.put("fromUserid", fromUserid);
		List<DBObject> plist=baseDao.getList(PubConstants.SHOP_PRODUCTATTENTION, whereMap, sortMap);
		if(plist!=null) 
		{
			if(plist.size() >0) {
				map.put("state", "1");
			}
			
		}
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	

	/**
	 * 获取商品关注数量
	 */
	public void ajaxproductattentioncount() {
		getLscode();
		HashMap<String, Object> whereMap=new HashMap<String, Object>();
		HashMap<String, Object> sortMap=new HashMap<String, Object>();
//		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
		whereMap.put("fromUserid", fromUserid);
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_PRODUCTATTENTION, whereMap);
		Struts2Utils.getRequest().setAttribute("fycount",fycount); 
	}
	
	
	
	/**
	 * 商品关注 列表
	 * @return
	 */
	public String productattentionList() 
	{
		getLscode();
		HashMap<String, Object> whereMap=new HashMap<String, Object>();
		HashMap<String, Object> sortMap=new HashMap<String, Object>();
//		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
		whereMap.put("fromUserid", fromUserid);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_PRODUCTATTENTION, whereMap);
		List<DBObject> lists = baseDao.getList(PubConstants.SHOP_PRODUCTATTENTION,whereMap,fypage,10,sortMap);
		if(lists!=null) {
			for(DBObject dbObject:lists) {
				Long productId = Long.parseLong(dbObject.get("productId").toString());
				HashMap<String, Object> wheresMap=new HashMap<String, Object>();
				wheresMap.put("_id", productId);
				DBObject obj= baseDao.getMessage(PubConstants.DATA_PRODUCT, wheresMap);
				Map<String,Object> mapObj = new HashMap<String,Object>();
				mapObj.put("productattention", dbObject);
				mapObj.put("productobj", obj);
				list.add(mapObj);
			}
		}
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		Struts2Utils.getRequest().setAttribute("list", list);
		/*String json = JSONArray.fromObject(list).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);*/
		
		return "attention";
	}
	
	@Override
	public ProductAttention getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
