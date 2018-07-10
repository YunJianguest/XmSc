package com.lsp.shop.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.web.GeneralAction;
import com.lsp.shop.entiy.ShopCollect;
import com.lsp.website.service.WwzService;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;

/**
* 店铺收藏管理
* @author ysh
*
*/
@Namespace("/shop")
@Results( { @Result(name = ShopcollectAction.RELOAD, location = "shopcollect.action", type = "redirect") })
public class ShopcollectAction extends GeneralAction<ShopCollect>{

	private static final long serialVersionUID = -6784469775589971579L;
	@Autowired
	private BaseDao baseDao;
	private MongoSequence mongoSequence;
	private Long _id;
	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}
	@Autowired
	private WwzService wwzService;
	
	@Override
	public ShopCollect getModel() {
		// TODO Auto-generated method stub
		return null;
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
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * ajax获取店铺收藏
	 */
	public  String  ajaxkj(){
		getLscode();
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		HashMap<String, Object>sortMap=new HashMap<String, Object>();
		Map<String, Object> sub_map = new HashMap<String, Object>(); 
		whereMap.put("fromUserid", fromUserid);
		sortMap.put("createdate",-1);
		if(StringUtils.isNotEmpty(Struts2Utils.getParameter("fypage"))){
			fypage=Integer.parseInt(Struts2Utils.getParameter("fypage"));
		}
		List<Object> list = new ArrayList<Object>();
		List<DBObject>collect_list=baseDao.getList(PubConstants.SHOP_SHOPCOLLECT, whereMap,fypage,10,sortMap);
		if(collect_list.size()>0){
			Map<String, Object> sub_map1;
			for(int i=0;i<collect_list.size();i++){
				whereMap=new HashMap<String, Object>();
				String shopid = (String)collect_list.get(i).get("shopId");
				whereMap.put("_id", Long.parseLong(shopid));
				DBObject object = baseDao.getMessage(PubConstants.SHOP_SHOPMB, whereMap);
				
				sub_map1 = new HashMap<String, Object>();
				sub_map1.put("list", collect_list.get(i));
				sub_map1.put("shop", object);
				
				list.add(sub_map1);
			}
		}
		sub_map.put("list",list);
		sub_map.put("state", collect_list.size());
		//String json = JSONArray.fromObject(sub_map).toString();
		
		//Struts2Utils.renderJson(json.substring(1, json.length()-1), new String[0]);
		Struts2Utils.getRequest().setAttribute("obj", sub_map);
		return "collect";
	}
	
	/**
	 * ajax添加店铺收藏
	 */
	public  String  save(){
		getLscode();
		ShopCollect collect = new ShopCollect();
		String shopId=Struts2Utils.getParameter("shopId");//店铺id
		try {
			HashMap<String, Object>whereMap=new HashMap<String, Object>();
			HashMap<String, Object>sortMap=new HashMap<String, Object>();
			whereMap.put("fromUserid", fromUserid);
			whereMap.put("shopId", shopId);
			sortMap.put("createdate",-1);
			List<DBObject>list=baseDao.getList(PubConstants.SHOP_SHOPCOLLECT, whereMap,sortMap);
			if(list.size()>0){
				addActionMessage("已添加!");
			}else{
				if(_id == null){
					_id=mongoSequence.currval(PubConstants.SHOP_SHOPCOLLECT);	
				}
				collect.setId(_id);
				collect.setShopId(shopId);
				collect.setCreateDate(new Date());
				collect.setFromUserid(fromUserid);
				baseDao.insert(PubConstants.SHOP_SHOPCOLLECT, collect); 
				addActionMessage("成功添加!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,添加过程中出现异常!");
		}
		return RELOAD;
		
	}
	
	/**
	 * 取消店铺收藏
	 */
	public String delete() throws Exception {
		getLscode();
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		
		try {
			String shopId=Struts2Utils.getParameter("shopId");//店铺id
			shopId = shopId.substring(0,shopId.length()-1);
			String[] shopIds = shopId.split(",");
			for(int i=0;i<shopIds.length;i++){
				whereMap.put("shopId", shopIds[i]);
				whereMap.put("fromUserid", fromUserid);
				baseDao.delete(PubConstants.SHOP_SHOPCOLLECT, whereMap);
			}
			
			addActionMessage("成功取消!");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("抱歉,取消过程中出现异常!");
		}
		
		return RELOAD;
	} 
}
