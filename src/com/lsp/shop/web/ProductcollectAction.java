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
import com.lsp.shop.entiy.ProductCollect;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;

/**
 * 商品收藏
 * @author Administrator
 *
 */
@Namespace("/shop")
@Results({ @Result(name = ProductcollectAction.RELOAD, location = "productcollect.action", params={"fypage", "%{fypage}"}, type = "redirect") })
public class ProductcollectAction  extends GeneralAction<ProductCollect> {
	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private Long _id;
	
	private MongoSequence mongoSequence;
	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}
	ProductCollect entity = new ProductCollect();//商品收藏
	public void set_id(Long _id) {
		this._id = _id;
	}

	/**
	 * 商品收藏 
	 * @param productId： 商品id
	 */
	public void ajaxsave() {
		getLscode();
		Map<String, Object> sub_map = new HashMap<String, Object>(); 
		try {
			if(_id == null){
				_id=mongoSequence.currval(PubConstants.SHOP_PRODUCTCOLLET);	
			}
			String productId = Struts2Utils.getParameter("productId");
			entity.set_id(_id);
			entity.setFromUserid(fromUserid);
			entity.setProductId(productId);
			entity.setCreatedate(new Date());
			baseDao.insert(PubConstants.SHOP_PRODUCTCOLLET,entity);
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
	 * 商品收藏取消 根据商品id
	 */
	public void ajaxdelete() {
		getLscode();
		Map<String, Object> sub_map = new HashMap<String, Object>(); 
		try {
			
			String productId = Struts2Utils.getParameter("productId");
			HashMap<String, Object>whereMap=new HashMap<String, Object>();
			HashMap<String, Object>sortMap=new HashMap<String, Object>();
			whereMap.put("productId", productId);
			whereMap.put("fromUserid", fromUserid);
			List<DBObject> plist=baseDao.getList(PubConstants.SHOP_PRODUCTCOLLET, whereMap, sortMap);
			if(plist!=null) {
				for(DBObject dbObject : plist) {
					Long id = Long.parseLong(dbObject.get("_id").toString());
					baseDao.delete(PubConstants.SHOP_PRODUCTCOLLET,id);
					sub_map.put("state", "1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,删除过程中出现异常!");
			sub_map.put("state", "0");
		}
		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}
	
	
	/**
	 * 根据收藏商品id 取消收藏
	 */
	public void ajaxdeletecollect() 
	{
		getLscode();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("state", "0");
		try {
			String proCollectIds = Struts2Utils.getParameter("proCollectIds");//收藏商品id
			String[] idArr= proCollectIds.split(",");
			for(String id :idArr) 
			{
				baseDao.delete(PubConstants.SHOP_PRODUCTCOLLET,Long.parseLong(id));
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
	 * 判断商品是否已经收藏
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
		List<DBObject> plist=baseDao.getList(PubConstants.SHOP_PRODUCTCOLLET, whereMap, sortMap);
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
	 * 获取商品收藏数量
	 */
	public void ajaxproductcollectncount() {
		getLscode();
		HashMap<String, Object> whereMap=new HashMap<String, Object>();
		HashMap<String, Object> sortMap=new HashMap<String, Object>();
//		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
		whereMap.put("fromUserid", fromUserid);
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_PRODUCTCOLLET, whereMap);
		Struts2Utils.getRequest().setAttribute("fycount",fycount); 
	}
	
	/**
	 * 商品收藏列表
	 * @return
	 */
	public String productcollectList() 
	{
		getLscode();
		Map<String, Object> map = new HashMap<String, Object>(); 
		HashMap<String, Object> whereMap=new HashMap<String, Object>();
		HashMap<String, Object> sortMap=new HashMap<String, Object>();
//		whereMap.put("custid", SpringSecurityUtils.getCurrentUser().getId());
		whereMap.put("fromUserid", fromUserid);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		//关注商品列表
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		fycount=baseDao.getCount(PubConstants.SHOP_PRODUCTCOLLET, whereMap);
		List<DBObject> lists = baseDao.getList(PubConstants.SHOP_PRODUCTCOLLET,whereMap,fypage,10,sortMap);
		if(lists!=null) {
			for(DBObject dbObject:lists) {
				Long productId = Long.parseLong(dbObject.get("productId").toString());
				HashMap<String, Object> wheresMap=new HashMap<String, Object>();
				wheresMap.put("_id", productId);
				DBObject obj= baseDao.getMessage(PubConstants.DATA_PRODUCT, wheresMap);
				Map<String,Object> mapObj = new HashMap<String,Object>();
				mapObj.put("productcollect", dbObject);
				mapObj.put("productobj", obj);
				list.add(mapObj);
			}
		}
		Struts2Utils.getRequest().setAttribute("fycount", fycount);
		Struts2Utils.getRequest().setAttribute("list", list);
		return "collect";
	}
	
	
	

	@Override
	public ProductCollect getModel() {
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
