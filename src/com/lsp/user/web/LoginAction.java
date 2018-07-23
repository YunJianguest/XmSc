package com.lsp.user.web;

import com.lsp.pub.dao.BaseDao;

import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Code;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.SpringSecurityUtils;

import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.shop.entiy.OrderFormpro;
import com.lsp.user.entity.UserInfo;
import com.lsp.user.service.LoginService;
import com.lsp.website.service.WwzService;
import com.lsp.weixin.entity.WxUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 登录管理
 * 
 * @author lsp
 *
 */
@Namespace("/")
@Results({
		@org.apache.struts2.convention.annotation.Result(name = "reload", location = "login.action", type = "redirect") })
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public static final String RELOAD = "reload";

	@Autowired
	private LoginService login;
	@Autowired
	private BaseDao basedao;
	private UserInfo info = new UserInfo();
	private String _id;
	private MongoSequence mongoSequence;
	@Autowired
	private WwzService wwzservice;

	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String execute() throws Exception {
		Object qqfromUser = Struts2Utils.getRequest().getSession().getAttribute("qqfromUser");
		if (qqfromUser != null) {
			// 用户使用QQ一键登录后台，
			SpringSecurityUtils.saveUserDetailsToContext(login.getUserDeatil(qqfromUser.toString()),
					Struts2Utils.getRequest());
			Struts2Utils.getRequest().getRequestDispatcher("/index.action").forward(Struts2Utils.getRequest(),
					Struts2Utils.getResponse());
		}
		return "success";
	}

	public String error() throws Exception {
		addActionMessage("账号或密码错误");
		return "reload";
	}

	public String logout() throws Exception {
		try {
			Struts2Utils.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}

	public String expired() throws Exception {
		return "expired";
	}

	public String chek() {
		/*
		 * String name = Struts2Utils.getParameter("name"); String pwd =
		 * Struts2Utils.getParameter("pwd"); HashMap<String, Object> sortMap =
		 * new HashMap<String, Object>(); HashMap<String, Object> whereMap = new
		 * HashMap<String, Object>();
		 * 
		 * whereMap.put("account", name); sortMap.put("createdate",
		 * Integer.valueOf(-1)); List<DBObject> list =
		 * this.baseDao.getList(PubConstants.USER_INFO, whereMap, sortMap);
		 * 
		 * if ((list.size() > 0) && (list.get(0) != null)) { DBObject emDbObject
		 * = (DBObject)list.get(0); UserInfo entity =
		 * (UserInfo)UniObject.DBObjectToObject(emDbObject, UserInfo.class);
		 * 
		 * if (entity.getPassword().equals(pwd)) {
		 * Struts2Utils.getSession().setAttribute("nickname",
		 * entity.getNickname()); } }
		 */

		return "ok";
	}

	public String move() {

		return "move";

	}

	/***
	 * 移动端登录页面
	 * 
	 * @return
	 */
	public String signin() {
		return "signin";
	}

	/***
	 * 移动端注册页面
	 * 
	 * @return
	 */
	public String signup() {
		return "signup";
	}

	/***
	 * 移动端商家注册页面
	 * 
	 * @return
	 */
	public String sjsignup() {
		return "sjsignup";
	}

	/***
	 * 移动端忘记密码
	 * 
	 * @return
	 */
	public String forgetpw() {

		return "forgetpw";
	}

	/***
	 * PC端忘记密码
	 * 
	 * @return
	 */
	public String pcforgetpw() {

		return "pcforgetpw";
	}

	public void ajaxsave() throws Exception {
		Map<String, Object> sub_map = new HashMap<>();
		sub_map.put("state", 1);
		HashMap<String, Object> whereMap = new HashMap<>();
		String tel = Struts2Utils.getParameter("tel");
		String yzcode = Struts2Utils.getParameter("yzcode");
		String password = Struts2Utils.getParameter("password");
		String nickname = Struts2Utils.getParameter("nickname");
		whereMap.put("account", tel);
		long count = basedao.getCount(PubConstants.USER_INFO, whereMap);

		if (count == 0) {
			Code code = GetAllFunc.telcode.get(tel);
			if (code != null && code.getCode().equals(yzcode)) {
				// 验证时间
				if (DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(), 10))) {
					UserInfo user = new UserInfo();
					user.set_id(UUID.randomUUID().toString());
					user.setAccount(tel);
					user.setTel(tel);
					user.setPassword(password);
					user.setNickname(nickname);
					user.setCustid(SysConfig.getProperty("custid"));
					user.setRoleid(Long.parseLong(SysConfig.getProperty("sjRoleid")));
					basedao.insert(PubConstants.USER_INFO, user);
					sub_map.put("state", 0);// 注册成功
				} else {
					sub_map.put("state", 4);// 验证码过期
				}
			} else {
				sub_map.put("state", 3);// 验证码输入错误
			}
		} else {
			sub_map.put("state", 2);// 用户名存在
		}

		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	public void del() {
		String tel = Struts2Utils.getParameter("tel");
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("tel", tel);
		basedao.delete(PubConstants.USER_INFO, whereMap);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void changepw() throws Exception {
		Map<String, Object> sub_map = new HashMap<>();
		sub_map.put("state", 1);
		HashMap<String, Object> whereMap = new HashMap<>();
		String tel = Struts2Utils.getParameter("tel");
		String yzcode = Struts2Utils.getParameter("yzcode");
		String password = Struts2Utils.getParameter("password");
		whereMap.put("account", tel);
		DBObject dbObject = basedao.getMessage(PubConstants.USER_INFO, whereMap);
		if (dbObject != null) {
			UserInfo user = (UserInfo) UniObject.DBObjectToObject(dbObject, UserInfo.class);
			if (user.getPassword().equals(password)) {
				Code code = GetAllFunc.telcode.get(tel);
				if (code != null && code.getCode().equals(yzcode)) {
					// 验证时间
					if (DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(), 10))) {
						user.setPassword(password);
						basedao.insert(PubConstants.USER_INFO, user);
						sub_map.put("state", 0);// 修改成功
					} else {
						sub_map.put("state", 5);// 验证码超时
					}
				} else {
					sub_map.put("state", 4);// 验证码错误
				}
			} else {
				sub_map.put("state", 3);// 密码错误
			}
		} else {
			sub_map.put("state", 2);// 该账户不存在
		}

		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	/**
	 * 商家注册
	 * 
	 * @throws Exception
	 */
	public void ajaxsave1() throws Exception {
		Map<String, Object> sub_map = new HashMap<>();
		sub_map.put("state", 1);
		HashMap<String, Object> whereMap = new HashMap<>();
		String tel = Struts2Utils.getParameter("tel");
		String yzcode = Struts2Utils.getParameter("yzcode");
		String password = Struts2Utils.getParameter("password");
		String nickname = Struts2Utils.getParameter("nickname");
		String userName = Struts2Utils.getParameter("username");// 真实姓名
		String id_card = Struts2Utils.getParameter("id_card");// 身份证号码
		String id_card_front = Struts2Utils.getParameter("id_card_front");// 身份证正面照
		String id_card_reverse = Struts2Utils.getParameter("id_card_recverse");// 身份证反面照
		String company_name = Struts2Utils.getParameter("company_name"); // 公司名称
		String lisense_number = Struts2Utils.getParameter("lisense_number"); // 营业证号码
		String lisense_photo = Struts2Utils.getParameter("lisense_photo"); // 营业证照片
		whereMap.put("account", tel);
		long count = basedao.getCount(PubConstants.USER_INFO, whereMap);

		if (count == 0) {
			Code code = GetAllFunc.telcode.get(tel);
			if (code != null && code.getCode().equals(yzcode)) {
				// 验证时间
				if (DateUtil.checkbig(DateUtil.addMinute(code.getCreatedate(), 10))) {
					UserInfo user = new UserInfo();
					user.set_id(UUID.randomUUID().toString());
					user.setAccount(tel);
					user.setTel(tel);
					user.setPassword(password);
					user.setNickname(nickname);
					user.setCustid(SysConfig.getProperty("custid"));
					user.setRoleid(Long.parseLong(SysConfig.getProperty("sjRoleid")));
					user.setUserName(userName);
					user.setId_card(id_card);
					user.setId_card_front(id_card_front);
					user.setId_card_reverse(id_card_reverse);
					user.setCompany_name(company_name);
					user.setLisense_number(lisense_number);
					user.setLisense_photo(lisense_photo);
					basedao.insert(PubConstants.USER_INFO, user);
					sub_map.put("state", 0);// 注册成功
				} else {
					sub_map.put("state", 4);// 验证码过期
				}
			} else {
				sub_map.put("state", 3);// 验证码输入错误
			}
		} else {
			sub_map.put("state", 2);// 用户名存在
		}

		String json = JSONArray.fromObject(sub_map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	/**
	 * 代理
	 * 
	 * @return
	 */
	public String agent() {
		return "agent";

	}

	/**
	 * 商家
	 * 
	 * @return
	 */
	public String merchants() {
		return "merchants";
	}

	public void delAll() {
		HashMap<String, Object> map = new HashMap<>();
		
		basedao.delete(PubConstants.WX_ORDERFORM);
		basedao.delete(PubConstants.SHOP_ODERFORMPRO);
		basedao.delete(PubConstants.SUC_SHOPPINGCART);
		basedao.delete(PubConstants.SHOP_SHOPCOMMENTS);
	    basedao.delete(PubConstants.SHOP_SHOPCOMREPLY);
		basedao.delete(PubConstants.SHOP_AFTERSERVICE);
		basedao.delete(PubConstants.INTEGRAL_INFO);
		basedao.delete(PubConstants.INTEGRALLL_INFO);
		basedao.delete(PubConstants.SUC_INTEGRALRECORD);
		basedao.delete(PubConstants.INTEGRALM);
		basedao.delete(PubConstants.JF_TOTAL);
		basedao.delete(PubConstants.INTEGRAL_TOPUPORDER);
		
		basedao.delete(PubConstants.INTEGRAL_TRANSFERORDER);
		basedao.delete(PubConstants.INTEGRAL_WITHDRAWALORDER);
		
		HashMap<String,Object>whereMap=new HashMap<>();
		BasicDBObject dateCondition = new BasicDBObject(); 
		whereMap.put("money", 0);
		basedao.delete(PubConstants.INTEGRAL_PROSTORE,whereMap);
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
	}

	/**
	 * ajax微信登录
	 */
	public void wxLogin() {
		String  redirectURL=Struts2Utils.getParameter("redirectURL");
		 

	}

}