package com.lsp.website.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpRequest;
import org.bson.util.StringRangeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chuanglan.sms.request.SmsSendRequest;
import com.chuanglan.sms.response.SmsSendResponse;
import com.chuanglan.sms.util.ChuangLanSmsUtil;
import com.lsp.integral.entity.InteProstore;
import com.lsp.integral.entity.InteSetting;
import com.lsp.integral.entity.InteTxjl;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.Exchangerate;
import com.lsp.pub.entity.Fromusermb;
import com.lsp.pub.entity.GetAllFunc;
import com.lsp.pub.entity.HttpClient;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.entity.WxToken;
import com.lsp.pub.upload.FtpUtils;
import com.lsp.pub.upload.JsonUtil;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateFormat;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.DownloadImage;
import com.lsp.pub.util.EncodeUtils;
import com.lsp.pub.util.LevelUtitls;
import com.lsp.pub.util.QRCodeUtil;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.pub.util.UserUtil;
import com.lsp.pub.util.WeiXinUtil;
import com.lsp.shop.entiy.AgentDetail;
import com.lsp.shop.entiy.AgentPrice;
import com.lsp.shop.entiy.ComMain;
import com.lsp.shop.entiy.ShopAgent;
import com.lsp.suc.entity.Areward;
import com.lsp.suc.entity.BbsInfo;
import com.lsp.suc.entity.Bbsstick;
import com.lsp.suc.entity.DatingInfo;
import com.lsp.suc.entity.DatingMember;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.suc.entity.IntegralLlInfo;
import com.lsp.suc.entity.IntegralRecord;
import com.lsp.suc.entity.IntegralYjInfo;
import com.lsp.suc.entity.KjAreaRecord;
import com.lsp.suc.entity.Taskresults;
import com.lsp.suc.entity.Comunit;
import com.lsp.user.entity.Authcode;
import com.lsp.user.entity.FriendsInfo;
import com.lsp.user.entity.UserInfo;
import com.lsp.user.entity.UserLoginDetail;
import com.lsp.website.entity.WwzFlowInfo;
import com.lsp.weixin.entity.Experience;
import com.lsp.weixin.entity.WxUser;
import com.lsp.weixin.entity.WxUserToken;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * 总服务
 * 
 * @author lsp
 * 
 */

@Component
@Transactional
public class WwzService {

	@Autowired
	private BaseDao baseDao;
	private MongoSequence mongoSequence;

	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}

	/**
	 * 网站流量
	 * 
	 * @param toUser
	 * @param type
	 */
	public int flow(String toUser, String type) {
		DBObject wwz = baseDao.getMessage(PubConstants.WWZ_FLOW, toUser + type);
		int count = 1;
		if (wwz != null) {
			count = Integer.parseInt(wwz.get("count").toString()) + 1;
		}
		WwzFlowInfo flow = new WwzFlowInfo();
		flow.set_id(toUser + type);
		flow.setToUser(toUser);
		flow.setType(type);
		flow.setCount(count);
		baseDao.insert(PubConstants.WWZ_FLOW, flow);
		return count;
	}

	/**
	 * 获取模块流量
	 * 
	 * @param toUser
	 * @param type
	 */
	public int getFlow(String toUser, String type) {
		DBObject wwz = baseDao.getMessage(PubConstants.WWZ_FLOW, toUser + type);
		int count = 1;
		if (wwz != null) {
			count = Integer.parseInt(wwz.get("count").toString());
		}
		return count;
	}

	/**
	 * 广告位显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public List<DBObject> slide(String custid, String type) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);

		whereMap.put("type", type);

		sortMap.put("sort", 1);
		List<DBObject> list = baseDao.getList(PubConstants.SUC_SLIDE, whereMap, sortMap);

		return list;
	}

	/**
	 * 广告位显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public List<DBObject> advertisement(String custid, String type) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);

		whereMap.put("type", type);

		sortMap.put("sort", 1);
		List<DBObject> list = baseDao.getList(PubConstants.ADVERTISEMENT, whereMap, sortMap);

		return list;
	}

	/**
	 * 广告位显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public List<DBObject> noadvertisement(String toUser, String type) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("toUser", toUser);

		whereMap.put("type", type);

		sortMap.put("sort", 1);
		List<DBObject> list = baseDao.getList(PubConstants.ADVERTISEMENT, whereMap, sortMap);

		return list;
	}

	/**
	 * 滚动显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public List<DBObject> roll(String toUser, String type) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("toUser", toUser);

		whereMap.put("type", type);

		sortMap.put("sort", 1);
		List<DBObject> list = baseDao.getList(PubConstants.NEW_ROLL, whereMap, sortMap);

		return list;
	}

	/**
	 * 滚动显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getUserdb(String fromUser) {
		if (StringUtils.isEmpty(fromUser)) {
			return null;
		}
		Object ob = Struts2Utils.getSession().getAttribute("fromUserDb");
		if (ob == null) {
			DBObject user = baseDao.getMessage(PubConstants.USER_INFO, fromUser);
			Struts2Utils.getSession().setAttribute("fromUserDb", user);
			return user;
		} else {
			DBObject user = (DBObject) ob;
			return user;
		}

	}

	/**
	 * 广告位显示
	 * 
	 * @param toUser
	 * @param type
	 */
	public List<DBObject> advertisement(String toUser, String type, int num) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("toUser", toUser);

		whereMap.put("type", type);

		sortMap.put("sort", 1);
		List<DBObject> list = baseDao.getList(PubConstants.ADVERTISEMENT, whereMap, 0, num, sortMap);
		if (list.size() == 0) {
			whereMap.clear();
			whereMap.put("toUser", null);
			list = baseDao.getList(PubConstants.ADVERTISEMENT, whereMap, sortMap);
		}
		return list;
	}

	/**
	 * 获取分享说明
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getShareFx(String toUser, WxToken token, String type) {

		DBObject fx = baseDao.getMessage(PubConstants.WEIXIN_SHAREFX, toUser + "-" + type);
		if (fx == null) {
			fx = new BasicDBObject();
			DBObject userdb = baseDao.getMessage(PubConstants.DATA_WXTOUSER, toUser);
			if (userdb == null) {
				fx.put("fxtitle", "");
				fx.put("fxsummary", "");
				fx.put("fximg", "");
				if (token != null) {
					fx.put("fxurl", token.getUrl());
				}

				return fx;
			}
			if (userdb.get("title") == null) {
				fx.put("fxtitle", "");
			} else {
				fx.put("fxtitle", userdb.get("title").toString());
			}
			if (userdb.get("summary") == null) {
				fx.put("fxsummary", "");
			} else {
				fx.put("fxsummary", userdb.get("summary").toString());
			}
			if (userdb.get("bj3") == null) {
				fx.put("fximg", "");
			} else {
				fx.put("fximg", userdb.get("bj3").toString());
			}

			if (token != null) {
				fx.put("fxurl", token.getUrl());
			}
			return fx;
		}
		if (fx.get("fxurl") == null || StringUtils.isEmpty(fx.get("fxurl").toString())) {
			if (token == null || token.getUrl() == null) {
				fx.put("fxurl", "");
			} else {
				fx.put("fxurl", token.getUrl());
			}

		}
		if (fx.get("fxtitle") == null) {
			fx.put("fxtitle", "");

		}
		return fx;

	}

	/**
	 * 获取分享说明
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getShareFx(String toUser, String type) {

		DBObject fx = baseDao.getMessage(PubConstants.WEIXIN_SHAREFX, toUser + "-" + type);

		return fx;

	}

	/**
	 * 获取代码
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getCmpno(String toUser, int price, int sort) {
		ComMain commain = GetAllFunc.comToUser.get(toUser);
		if (commain != null) {
			toUser = commain.getToUser();
		}
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("price", 0);
		// whereMap.put("toUser", toUser);
		sortMap.clear();
		sortMap.put("sort", sort);
		Random rand = new Random();
		int num = rand.nextInt(9999);
		List<DBObject> notuse = baseDao.getList(PubConstants.CMP_NOTUSENO, whereMap, num, 1, sortMap);
		baseDao.delete(PubConstants.CMP_NOTUSENO, notuse.get(0).get("_id").toString());
		return notuse.get(0);

	}

	/**
	 * 获取用户昵称
	 * 
	 * @param toUser
	 * @param type
	 */
	public String getUserName(String fromUser) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, fromUser);
		String name = "游客";
		if (db != null && db.get("nickname") != null) {
			name = db.get("nickname").toString();
		}
		return name;
	}

	/**
	 * 获取用户
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getWxUser(String fromUserid) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, fromUserid);
		if (db == null) {
			db = new UserInfo();
			db.put("nickname", "游客");
			db.put("no", "未注册");
			db.put("humor", "暂无心情");
			db.put("_id", "notlogin");
		}

		return db;
	}

	/**
	 * 绑定推荐关系
	 * 
	 * @param toUser
	 * @param type
	 */
	public boolean  addRenumber(String fromUserid,String agid) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, fromUserid);
		if(db!=null){
			UserInfo info=(UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
			info.set_id(fromUserid);
			if(info.getRenumber()==0&&StringUtils.isNotEmpty(agid)&&agid!=null&&!agid.equals("null")&&Long.parseLong(agid)>1000){
				info.setRenumber(Long.parseLong(agid));
				baseDao.insert(PubConstants.USER_INFO, info);
				return true;
			}
			 
		}

		return false;
	}
	/**
	 * 获取用户
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getWxUserSession(String fromUserid) {
		HashMap<String, Object>whereMap=new HashMap<>();
		whereMap.put("_id", fromUserid);
		HashMap<String, Object>backMap=new HashMap<>();
		backMap.put("no", 1);
		backMap.put("reno", 1);
		backMap.put("number", 1);
		backMap.put("renumber", 1);
		backMap.put("nickname", 1);
		backMap.put("headimgurl", 1); 
		backMap.put("agentLevel", 1);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap,backMap);
		if (db == null) {
			db = new UserInfo();
			db.put("nickname", "游客");
			db.put("no", "未注册");
			db.put("humor", "暂无心情");
			db.put("_id", "notlogin");
			
		}
		if(db !=null){
			if(db.get("_id").toString().equals(SysConfig.getProperty("custid"))){
				db.put("_id", "notlogin");
			}
			if(db.get("roleid") != null){
				if(db.get("roleid").toString().equals(SysConfig.getProperty("sjRoleid"))){
					db.put("_id", "notlogin");
				}
			}
		}
		return db;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param toUser
	 * @param type
	 */
	public String getWxUsertype(String fromUserid, String type) {
		if (StringUtils.isEmpty(fromUserid)) {
			return "";
		}
		HashMap<String, Object> backMap = new HashMap<>();
		backMap.put(type, 1);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, fromUserid, backMap);
		if (db != null && db.get(type) != null) {
			return db.get(type).toString();

		}
		return "";
	}

	/**
	 * 获取用户信息
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getWxUser(String fromUser, String custid) {
		if (StringUtils.isEmpty(fromUser)) {
			return null;
		}
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("fromUser", fromUser);
		whereMap.put("custid", custid);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
		if (db != null) {
			return db;
		}
		return null;
	}

	/**
	 * 生成会员卡号
	 */
	public String getVipNo() {
		String vipno = null;
		Long count = baseDao.getCount(PubConstants.USER_INFO);
		while (true) {
			if (count.toString().length() >= 5 && Double
					.parseDouble(count.toString()) >= Math.pow(10, Double.parseDouble(count.toString()) + 1) - 10000) {
				vipno = UserUtil.createVipNo(count.toString().length() + 1);
			} else {
				vipno = UserUtil.createVipNo(5);
			}
			// 排查首位为0；
			if (!vipno.startsWith("0")) {
				// 检查是否唯一
				HashMap<String, Object> whereMap = new HashMap<String, Object>();
				whereMap.put("no", vipno);
				Long num = baseDao.getCount(PubConstants.USER_INFO, whereMap);
				if (num == 0) {
					break;
				}
			}

		}

		return vipno;

	}

	/**
	 * 获取用户
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getWxUser(HashMap<String, Object> whereMap) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
		if (db == null) {
			db = new UserInfo();
			db.put("nickname", "游客");
			db.put("no", "未注册");
			db.put("humor", "暂无心情");
			db.put("fromUser", "notlogin");
			db.put("level", 1);
		}

		return db;
	}

	/**
	 * 获取用户
	 * 
	 * @param toUser
	 * @param type
	 */
	public DBObject getWxUsers(String fromUser) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(fromUser)){
			whereMap.put("fromUser", fromUser);
			DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
			if (db == null) {
				db = new UserInfo();
				db.put("nickname", "游客");
				db.put("no", "未注册");
				db.put("humor", "暂无心情");
				db.put("fromUser", "notlogin");
				db.put("level", 1);
			}

			return db;
		}
		return null;
		
	}

	/**
	 * 
	 */
	public boolean checkName(String name) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("loginname", name);
		Long count = baseDao.getCount(PubConstants.USER_INFO, whereMap);
		if (count == 0L) {
			return true;
		}
		return false;

	}

	/**
	 * 获取是否管理员
	 * 
	 * @param toUser
	 * @param type
	 */
	public boolean isAdmin(String fromUser, String wid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		boolean re = false;
		DBObject user = baseDao.getMessage(PubConstants.USER_INFO, fromUser);
		BasicDBList dbList = new BasicDBList(); // 翻译数组对象
		if (user == null || user.get("comUser") == null) {

		} else {
			dbList.add(user.get("comUser").toString());
		}

		dbList.add(fromUser);
		whereMap.put("wid", wid);
		whereMap.put("fromUser", new BasicDBObject("$in", dbList));

		Long count = baseDao.getCount(PubConstants.RF_FROMADMIN, whereMap);
		if (count > 0) {
			re = true;
		}
		return re;
	}

	/**
	 * 获取是否管理员
	 * 
	 * @param toUser
	 * @param type
	 */
	public boolean getComUser(String fromUser, String toUser, String url) {

		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		ComMain commain = GetAllFunc.comToUser.get(toUser);
		if (commain == null) {// 主账号
			return false;
		}
		whereMap.put("comUser", fromUser);
		whereMap.put("toUser", toUser);
		if (baseDao.getCount(PubConstants.USER_INFO, whereMap) > 0) {
			return false;
		}
		Comunit wxtoUser = GetAllFunc.wxTouser.get(toUser);
		if (wxtoUser.getZhlx() != 2) {
			return false;
		}
		String dlurl = SysConfig.getProperty("ip") + "/wwz/wwz!combd.action?fromUser=" + fromUser + "&toUser=" + toUser
				+ "&method=" + URLEncoder.encode(url);
		String tzurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxtoUser.getAppid()
				+ "&redirect_uri=" + URLEncoder.encode(dlurl)
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		Struts2Utils.getRequest().setAttribute("method", tzurl);
		return true;
	}

	public String getmb(String id) {
		DBObject db = baseDao.getMessage(PubConstants.SUC_SHARECONFIG, id);
		if (db != null) {
			return db.get("mb").toString();
		} else {
			return null;
		}
	}

	public DBObject getshare(String id) {
		DBObject db = baseDao.getMessage(PubConstants.WEIXIN_SHAREFX, id);
		if (db != null) {
			return db;
		} else {
			return null;
		}
	}

	/**
	 * 通过平台ID获取微信toUser
	 * 
	 * @param custid
	 * @return
	 */
	public String gettoUser(String custid) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, custid);

		return db.get("toUser").toString();

	}

	/**
	 * 获取管理员
	 * 
	 * @param custid
	 * @return
	 */
	public DBObject getCustUser(String custid) {
		if (StringUtils.isEmpty(custid)) {
			return null;
		}
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, custid);
		if (db != null) {
			return db;
		}
		return null;

	}

	/**
	 * 获取父账号的custid
	 * 
	 * @param custid
	 * @return
	 */
	public String getparentcustid(String custid) {
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, custid);

		return db.get("custid").toString();

	}

	/**
	 * 通过toUser 获取平台ID
	 */
	public String getCustid(String toUser) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("toUser", toUser);
		sortMap.put("sort", -1);
		List<DBObject> db = baseDao.getList(PubConstants.USER_INFO, whereMap, sortMap);
		if (db.size() > 0) {
			return db.get(0).get("_id").toString();
		}

		return null;

	}

	/**
	 * 通过fromUser 获取平台fromUserid
	 * 
	 */
	public String getfromUserid(String fromUser, String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("fromUser", fromUser);
		if (StringUtils.isNotEmpty(custid)) {
			Pattern pattern = Pattern.compile("^.*" + custid + ".*$", Pattern.CASE_INSENSITIVE);
			whereMap.put("custid", pattern);
		}
		sortMap.put("sort", -1);
		
		DBObject db = getWxUser(whereMap);
		if(db !=null){
			if (db.get("fromUser") != null && db.get("fromUser").equals("notlogin") && StringUtils.isNotEmpty(fromUser)
					&& StringUtils.isNotEmpty(custid)) {
				// 注册新用户

				WxUserToken token = GetAllFunc.usertoken.get(fromUser);
				return register(fromUser, token, custid);

			}
			
		}
		

		if (db != null && StringUtils.isNotEmpty(fromUser)) {
			if (db.get("headimgurl") == null || db.get("nickname") == null || db.get("nickname").equals("游客")) {
				// 更新用户
				WxUserToken token = GetAllFunc.usertoken.get(fromUser);
				updateUser(token, db);

			}
			return db.get("_id").toString();

		} else {

		}

		return null;

	}

	/**
	 * 通过fromUser 获取平台fromUserid
	 * 
	 */
	public String getcodefromUserid(String fromUser, String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("fromUser", fromUser);
		if (StringUtils.isNotEmpty(custid)) {
			Pattern pattern = Pattern.compile("^.*" + custid + ".*$", Pattern.CASE_INSENSITIVE);
			whereMap.put("custid", pattern);
		}
		sortMap.put("sort", -1);
		DBObject db = getWxUser(whereMap);
		// 如何没有会员号则生成
		if (db.get("no") == null || StringUtils.isEmpty(db.get("no").toString())) {
			if(!db.get("no").toString().equals("未注册")){
				createVipNo(db);
			} 
		}
		if(db.get("number")==null||db.get("number").toString().equals("0")){
			createNumber(db);
		}
		if (!db.get("fromUser").equals("notlogin") && db.get("nickname") != null && StringUtils.isNotEmpty(fromUser)
				&& StringUtils.isNotEmpty(custid)) {

			return db.get("_id").toString();
		}

		return "register";

	}

	public void createNumber(DBObject user){
	    if(user!=null){
	    	UserInfo us=(UserInfo) UniObject.DBObjectToObject(user, UserInfo.class);
	    	if(StringUtils.isNotEmpty(us.getNo())&&!us.getNo().equals("未注册")){
	    		us.setNumber(Long.parseLong(us.getNo()));
		    	baseDao.insert(PubConstants.USER_INFO, us);
	    	}
	    	
	    }	
	}
	
	public boolean updateUser(WxToken token, DBObject db) {
		try {
			JSONObject map = WeiXinUtil.getUserInfo(token.getAccess_token(), db.get("fromUser").toString());

			if (map == null) {// 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
				return false;
			}
			if (map.get("errcode") != null) {// 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:

				return false;
			}
			if (map.get("subscribe").equals("0")) {

				return false;
			}
			UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
			if (StringUtils.isEmpty(user.getProvince())) {
				user.setCity(map.getString("city"));
				user.setCountry(map.getString("country"));
				user.setProvince(map.getString("province"));
				user.setSex(map.getString("sex"));
			}

			if (map.get("headimgurl") == null || map.get("headimgurl").toString().length() < 5) {

			} else {
				user.setHeadimgurl(map.getString("headimgurl"));
				if (StringUtils.isNotEmpty(user.getHeadimgurl())) {
					String path = "logo_" + user.getFromUser() + ".jpg";
					String savePath = SysConfig.getProperty("imgpath") + "/" + path;

					DownloadImage.download(user.getHeadimgurl(), savePath);
					File file = new File(savePath);

					if (SysConfig.getProperty("isossup").equals("1")) {

					} else if (SysConfig.getProperty("isossup").equals("2")) {
						FileInputStream localObject1 = new FileInputStream(file);

						FTPClient ftp = FtpUtils.getFtpClient(SysConfig.getProperty("ftp"),
								SysConfig.getProperty("ftpname"), SysConfig.getProperty("ftppwd"),
								Integer.parseInt(SysConfig.getProperty("ftpport")));
						FtpUtils.uploadImageToFTP(localObject1, ftp, "/" + path);
						FtpUtils.closeFtp(ftp);
						user.setHeadimgurl(SysConfig.getProperty("osshttp") + path);
					} else if (SysConfig.getProperty("isossup").endsWith("3")) {
						// 跨域
						// 添加文件到缓存
						FileInputStream localObject1 = new FileInputStream(file);

						JsonUtil.Add(new String[] { "file", "FileName", "ContentType" }, new Object[] {
								EncodeUtils.base64Encode(JsonUtil.readBytes(localObject1)), path, "img" });
						// 返回保存的路径
						JsonUtil.UploadFile();

						user.setHeadimgurl(SysConfig.getProperty("osshttp") + path);
					} else {

						user.setHeadimgurl(path);
					}
				}
			}

			user.setLanguage(map.getString("language"));
			user.setCreatedate(new Date());
			user.setNickname(map.getString("nickname"));
			baseDao.insert(PubConstants.USER_INFO, user);
			return true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean updateUser(WxUserToken token, DBObject db) {
		try {
			JSONObject map = WeiXinUtil.getUserinfo(token.getToken(), db.get("fromUser").toString());
			if (map == null) {// 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
				return false;
			}
			if (map.get("errcode") != null) {// 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:

				return false;
			}

			UserInfo user = (UserInfo) UniObject.DBObjectToObject(db, UserInfo.class);
			if (StringUtils.isEmpty(user.getProvince())) {
				user.setCity(map.getString("city"));
				user.setCountry(map.getString("country"));
				user.setProvince(map.getString("province"));
				user.setSex(map.getString("sex"));
			}

			if (map.get("headimgurl") == null || map.get("headimgurl").toString().length() < 5) {

			} else {
				user.setHeadimgurl(map.getString("headimgurl"));
				if (StringUtils.isNotEmpty(user.getHeadimgurl())) {
					String path = "logo_" + user.getFromUser() + ".jpg";
					String savePath = SysConfig.getProperty("imgpath") + "/" + path;

					DownloadImage.download(user.getHeadimgurl(), savePath);
					File file = new File(savePath);

					if (SysConfig.getProperty("isossup").equals("1")) {

					} else if (SysConfig.getProperty("isossup").equals("2")) {
						FileInputStream localObject1 = new FileInputStream(file);

						FTPClient ftp = FtpUtils.getFtpClient(SysConfig.getProperty("ftp"),
								SysConfig.getProperty("ftpname"), SysConfig.getProperty("ftppwd"),
								Integer.parseInt(SysConfig.getProperty("ftpport")));
						FtpUtils.uploadImageToFTP(localObject1, ftp, "/" + path);
						FtpUtils.closeFtp(ftp);
						user.setHeadimgurl(SysConfig.getProperty("osshttp") + path);
					} else if (SysConfig.getProperty("isossup").endsWith("3")) {
						// 跨域
						// 添加文件到缓存
						FileInputStream localObject1 = new FileInputStream(file);

						JsonUtil.Add(new String[] { "file", "FileName", "ContentType" }, new Object[] {
								EncodeUtils.base64Encode(JsonUtil.readBytes(localObject1)), path, "img" });
						// 返回保存的路径
						JsonUtil.UploadFile();

						user.setHeadimgurl(SysConfig.getProperty("osshttp") + path);
					} else {

						user.setHeadimgurl(path);
					}
				}
			}

			user.setLanguage(map.getString("language"));
			user.setCreatedate(new Date());
			user.setNickname(map.getString("nickname"));
			user.setNumber(Long.parseLong(user.getNo()));
			baseDao.insert(PubConstants.USER_INFO, user);
			return true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 微信注册
	 */
	public String register(String fromUser, WxUserToken token, String custid) {
		if (StringUtils.isNotEmpty(fromUser)) {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("fromUser", fromUser);
			List<DBObject> list = baseDao.getList(PubConstants.USER_INFO, whereMap, null);
			if (list.size() == 0) {
				UserInfo user = new UserInfo();
				String id = UUID.randomUUID().toString();
				user.set_id(id);
				user.setCustid(user.getCustid() + "," + custid);
				user.setFromUser(fromUser);
				user.setNo(getVipNo());
				user.setNumber(Long.parseLong(user.getNo()));
				baseDao.insert(PubConstants.USER_INFO, user); 
				updateUser(token, baseDao.getMessage(PubConstants.USER_INFO, id));

				return id;
			} else {
				/**
				 * 返回查到的用户
				 */ 
				UserInfo user = (UserInfo) UniObject.DBObjectToObject(list.get(0), UserInfo.class);
				user.setCustid(user.getCustid() + "," + custid);
				user.setFromUser(fromUser);
				baseDao.insert(PubConstants.USER_INFO, user);
				if (list.get(0).get("headimgurl") == null || list.get(0).get("nickname") == null
						|| list.get(0).get("nickname").equals("游客")) {

					updateUser(token, list.get(0));
				}
				return list.get(0).get("_id").toString();
			}
		}
		return null;
	}

	/**
	 * 微信推荐邀请注册
	 */
	public String registerCommend(String fromUser, WxUserToken token, String custid, String number) {
		if (StringUtils.isNotEmpty(fromUser)) {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("fromUser", fromUser);
			List<DBObject> list = baseDao.getList(PubConstants.USER_INFO, whereMap, null);
			if (list.size() == 0) {
				UserInfo user = new UserInfo();
				String id = UUID.randomUUID().toString();
				user.set_id(id);
				user.setCustid(user.getCustid() + "," + custid);
				user.setFromUser(fromUser);
				user.setNo(getVipNo());
				// 推荐人推荐号码保存
				user.setReno(number);
				if(StringUtils.isNotEmpty(number)){
					user.setRenumber(Long.parseLong(number));;
				} 
				baseDao.insert(PubConstants.USER_INFO, user);
				updateUser(token, baseDao.getMessage(PubConstants.USER_INFO, id));

				return id;
			} else {
				/**
				 * 返回查到的用户
				 */
				UserInfo user = (UserInfo) UniObject.DBObjectToObject(list.get(0), UserInfo.class);
				user.setCustid(user.getCustid() + "," + custid);
				user.setFromUser(fromUser);
				baseDao.insert(PubConstants.USER_INFO, user);
				if (list.get(0).get("headimgurl") == null || list.get(0).get("nickname") == null
						|| list.get(0).get("nickname").equals("游客")) {

					updateUser(token, list.get(0));
				}
				return list.get(0).get("_id").toString();
			}
		}
		return null;
	}

	/**
	 * 验证任务
	 * 
	 * @param type
	 * @return
	 */
	public Long checkTask(String type, String fromUserid, String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("sort", -1);
		whereMap.put("type", type);
		whereMap.put("custid", custid);
		List<DBObject> list = baseDao.getList(PubConstants.SUC_TASK, whereMap, sortMap);
		for (DBObject dbObject : list) {
			whereMap.clear();
			whereMap.put("parentid", Long.parseLong(dbObject.get("_id").toString()));
			whereMap.put("fromUserid", fromUserid);
			// 每日时间验证
			BasicDBObject dateCondition = new BasicDBObject();

			dateCondition.append("$gte", com.lsp.pub.util.DateUtil.getTimesmorning());
			dateCondition.append("$lt", com.lsp.pub.util.DateUtil.getTimesnight());
			whereMap.put("createdate", dateCondition);

			Long cou = baseDao.getCount(PubConstants.SUC_TASKRESULT, whereMap);

			if (Integer.parseInt(dbObject.get("count").toString()) > cou) {
				// 未完成,返回当前任务
				return Long.parseLong(dbObject.get("_id").toString());
			}
		}
		return -1L;
	}

	/**
	 * 完成任务
	 * 
	 * @param id
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public boolean completeTask(Long id, String custid, String fromUserid, String type) {
		// 任务完成
		Taskresults taskresults = new Taskresults();
		taskresults.set_id(mongoSequence.currval(PubConstants.SUC_TASKRESULT));
		taskresults.setCreatedate(new Date());
		taskresults.setCustid(custid);
		taskresults.setFromUserid(fromUserid);
		taskresults.setParentid(id);
		baseDao.insert(PubConstants.SUC_TASKRESULT, taskresults);

		DBObject task = baseDao.getMessage(PubConstants.SUC_TASK, id);
		WxUser wxuser = (WxUser) UniObject.DBObjectToObject(getWxUser(fromUserid), WxUser.class);
		// 增加经验奖励
		if (Integer.parseInt(task.get("expreward").toString()) > 0) {

			Experience exp = new Experience();
			exp.set_id(mongoSequence.currval(PubConstants.WX_EXPERIENCE));
			exp.setFromUserid(fromUserid);
			exp.setCreatedate(new Date());
			exp.setType("task");
			exp.setCustid(custid);
			exp.setExperience(Integer.parseInt(task.get("expreward").toString()));
			baseDao.insert(PubConstants.WX_EXPERIENCE, exp);

			wxuser.setExperience(wxuser.getExperience() + Integer.parseInt(task.get("expreward").toString()));
			wxuser.setGetExperience(wxuser.getGetExperience() + Integer.parseInt(task.get("expreward").toString()));
			wxuser.setNeedExperience(LevelUtitls.getNeedExp(wxuser.getLevel()));
			if (LevelUtitls.isUpLevel(wxuser.getGetExperience(), wxuser.getLevel())) {
				// 升级
				wxuser.setLevel(wxuser.getLevel() + 1);
				wxuser.setGetExperience(wxuser.getExperience() - wxuser.getNeedExperience());
				wxuser.setNeedExperience(LevelUtitls.getNeedExp(wxuser.getLevel()));
			}

		}

		if (Integer.parseInt(task.get("jfreward").toString()) > 0) {
			// 增加积分奖励
			addjf(task.get("jfreward").toString(), fromUserid, type, custid, wxuser);

		}
		baseDao.insert(PubConstants.USER_INFO, wxuser);

		return false;

	}

	/**
	 * 悬赏
	 */
	public boolean areward(String fromUserid, String custid, Long bmtid, String price, DBObject user) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bmtid", bmtid);
		Long count = baseDao.getCount(PubConstants.BBS_AREWARD, whereMap);
		if (count == 0L) {
			// 开始悬赏
			if (deljf(price, fromUserid, "bbsareward", custid, user)) {
				Areward obj = new Areward();
				obj.set_id(mongoSequence.currval(PubConstants.BBS_AREWARD));
				obj.setBmtid(bmtid);
				obj.setCustid(custid);
				obj.setFromUserid(fromUserid);
				obj.setState(0);
				obj.setPrice(Integer.parseInt(price));
				obj.setCreatedate(new Date());
				baseDao.insert(PubConstants.BBS_AREWARD, obj);

				// 同步到帖子
				DBObject db = baseDao.getMessage(PubConstants.BBS_INFO, bmtid);
				if (db != null) {
					BbsInfo bbs = (BbsInfo) UniObject.DBObjectToObject(db, BbsInfo.class);
					bbs.setActivity(1);
					baseDao.insert(PubConstants.BBS_INFO, bbs);
					return true;
				}

			}
		}
		return false;

	}

	/**
	 * 置顶
	 */
	public boolean stick(String fromUserid, String custid, Long bmtid, String price, String time, DBObject user) {
		// 验证是否已经置顶
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bmtid", bmtid);
		Long count = baseDao.getCount(PubConstants.BBS_STICK, whereMap);
		if (count == 0L) {
			// 开始置顶
			if (deljf(price, fromUserid, "bbsstick", custid, user)) {
				Bbsstick obj = new Bbsstick();
				obj.set_id(mongoSequence.currval(PubConstants.BBS_STICK));
				obj.setBmtid(bmtid);
				obj.setCustid(custid);
				obj.setFromUserid(fromUserid);
				obj.setState(0);
				obj.setPrice(Double.parseDouble(price));
				obj.setTime(Long.parseLong(time));
				obj.setCreatedate(new Date());
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR_OF_DAY, +Integer.parseInt(obj.getTime().toString()));
				obj.setStartdate(new Date());
				obj.setEnddate(cal.getTime());
				baseDao.insert(PubConstants.BBS_STICK, obj);
				// 同步到帖子
				DBObject db = baseDao.getMessage(PubConstants.BBS_INFO, bmtid);
				if (db != null) {
					BbsInfo bbs = (BbsInfo) UniObject.DBObjectToObject(db, BbsInfo.class);
					bbs.setStick(1);
					baseDao.insert(PubConstants.BBS_INFO, bbs);
					return true;
				}
			}
			;

		}
		return false;

	}

	/**
	 * 增加积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param wxuser
	 * @return
	 */
	public boolean addjf(String price, String fromUserid, String type, String custid, DBObject wxuser) {
		try {
			if (Double.parseDouble(price) > 0) {
				IntegralInfo info = new IntegralInfo();
				info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
				info.setCreatedate(new Date());
				info.setFromUserid(fromUserid);
				info.setValue(price);
				info.setType(type);
				info.setState(0);
				info.setCustid(custid);
				baseDao.insert(PubConstants.INTEGRAL_INFO, info);
				if (changeJf(custid, fromUserid, price, 0, 0)) {
					return true;
				} else {
					return false;
				}

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 预返积分 每日返还
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param wxuser
	 * @return
	 * @throws Exception 
	 */
	public boolean addyfjf(String price, String fromUserid, String type, String custid, int jfstate, String fid,
			String jzprice,String ppbprice) throws Exception {
		try {
			if (Double.parseDouble(price) > 0) {
				if(checkTotalIntegral(1, price)) {
					IntegralInfo info = new IntegralInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(0);
					info.setCustid(custid);
					info.setFid(fid);
					info.setPpbprice(ppbprice);
					info.setInsdate(DateFormat.getDate());
					baseDao.insert(PubConstants.INTEGRAL_INFO, info);
					if (jfstate == 3) {//
						if (changeKjJf(custid, fromUserid, price, 0,jzprice)) { 
							return true; 
						}
					}if (jfstate == 2) {//
						if (changeKjJf(custid, fromUserid, price, 0,jzprice)) {
							if (updateTotalIntegral(1, price)) {
								//判断是否已经返完
								checkFh(fromUserid,custid,fid,type);
								return true;
							}
						}
					} else {// 可用积分
						if (changeKjJf(custid, fromUserid, price, 0,jzprice)) {
							if (updateTotalIntegral(1, price)) {
								//判断是否已经返完
								checkFh(fromUserid,custid,fid,type);
								return true;
							}
						}
					}
				}
				

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 矿机提现
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param wxuser
	 * @return
	 * @throws Exception 
	 */
	public boolean delyfjf(String price, String fromUserid, String type, String custid,String jzprice,String oid) throws Exception {
		try {
			if (Double.parseDouble(price) > 0) {
				if(changeKjJf(custid, fromUserid, price, 1,jzprice)) {
					IntegralInfo info = new IntegralInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(1);
					info.setOid(oid);
					info.setCustid(custid); 
					baseDao.insert(PubConstants.INTEGRAL_INFO, info);
					return true;
				}
				

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 验证账户情况
	 * @param fromid
	 * @param custid 
	 * @param fid（预返账户）
	 * @throws Exception 
	 */
	public void  checkFh(String fromid,String custid,String fid,String type) throws Exception {
		//查询冻结积分
		HashMap<String, Object> whereMap = new HashMap<String, Object>(); 
		whereMap.put("fromUserid", fromid);
		whereMap.put("custid", custid);
		IntegralRecord ir = null;
		DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		DBObject yf=baseDao.getMessage(PubConstants.INTEGRAL_PROSTORE, Long.parseLong(fid));
		DBObject jfsz = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, whereMap); 
		InteSetting sett = (InteSetting) UniObject.DBObjectToObject(jfsz, InteSetting.class);
		//查询总返积分
		if(db!=null) {
			ir=(IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			//获取下级代理数量
			DBObject user=getCustUser(fromid);
			HashMap<String, Object>whereUserMap=new HashMap<>();
			whereUserMap.put("renumber", Long.parseLong(user.get("number").toString()));
			Long count=baseDao.getCount(PubConstants.USER_INFO, whereUserMap);
			double userprice=0;
			if(user.get("agentLevel").toString().equals("1")) {
				//省
				userprice=count*(sett.getReturnCity()/3);
			}else if(user.get("agentLevel").toString().equals("2")){
				//市 
				userprice=count*(sett.getReturnCounty()/3);
			}else if(user.get("agentLevel").toString().equals("3")) {
				//县 
				userprice=count*(sett.getReturnDept()/3);
			}else if(user.get("agentLevel").toString().equals("4")) {
				//部 
				userprice=count*(sett.getReturnDept()/3);
			} 
			//获取订单利润
			whereMap.clear();
			whereMap.put("fromUserid",fromid);
			whereMap.put("type","shop_bmzt");
			List<DBObject>list=baseDao.getList(PubConstants.INTEGRAL_INFO, whereMap, null);
			double orderprice=0;
			for (DBObject dbObject : list) {
				if(Double.parseDouble(dbObject.get("value").toString())>0&&dbObject.get("state").toString().equals("0")) {
					orderprice+=Double.parseDouble(dbObject.get("value").toString());
				}
			}
			
			//获取矿机利润
			whereMap.clear();
			whereMap.put("fromUserid",fromid);
			whereMap.put("type",type);
			List<DBObject>kjlist=baseDao.getList(PubConstants.INTEGRAL_INFO, whereMap, null);
			double kjprice=0;
			for (DBObject dbObject : kjlist) {
				if(Double.parseDouble(dbObject.get("value").toString())>0&&dbObject.get("state").toString().equals("0")) {
					kjprice+=Double.parseDouble(dbObject.get("value").toString());
				}
			}
			
			if(kjprice+userprice+orderprice>=Double.parseDouble(yf.get("money").toString())) { 
				//已经返完，开始解冻
				InteProstore dd=(InteProstore) UniObject.DBObjectToObject(yf, InteProstore.class);
				dd.setState(1);
				baseDao.insert(PubConstants.INTEGRAL_PROSTORE, dd); 
				if(dd.getType().equals("ps_account")) {
					changeFreezeJf(custid,fromid);
					//创建新的预返账户
					whereMap.clear();
					
					if(jfsz!=null) { 
						if(user.get("agentLevel").equals("1")) {
							//省
							whereMap.clear();
							whereMap.put("type", "ps_recovery");
							whereMap.put("fromUserid", fromid);
							Long icount=baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
							if(icount==0) {
								InteProstore inteProstore=new InteProstore();
								inteProstore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
								inteProstore.setCreatedate(new Date());
								inteProstore.setFromUserid(fromid);
								inteProstore.setMoney(sett.getReturnProvince()*0.1);
								inteProstore.setTime(365*3);
								inteProstore.setEnddate(DateUtil.addDay(new Date(), 365*3));
								inteProstore.setType("ps_recovery");
								baseDao.insert(PubConstants.INTEGRAL_PROSTORE, inteProstore);
							}
							
							
						}else if(user.get("agentLevel").equals("2")) {
							//市 
							whereMap.clear();
							whereMap.put("type", "ps_recovery");
							whereMap.put("fromUserid", fromid);
							Long icount=baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
							if(icount==0) {
								InteProstore inteProstore=new InteProstore();
								inteProstore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
								inteProstore.setCreatedate(new Date());
								inteProstore.setFromUserid(fromid);
								inteProstore.setMoney(sett.getReturnCity()*0.1);
								inteProstore.setTime(365*3);
								inteProstore.setEnddate(DateUtil.addDay(new Date(), 365*3));
								inteProstore.setType("ps_recovery");
								baseDao.insert(PubConstants.INTEGRAL_PROSTORE, inteProstore);
							}
							
						}else if(user.get("agentLevel").equals("3")) {
							//县 
							whereMap.clear();
							whereMap.put("type", "ps_recovery");
							whereMap.put("fromUserid", fromid);
							Long icount=baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
							if(icount==0) {
								InteProstore inteProstore=new InteProstore();
								inteProstore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
								inteProstore.setCreatedate(new Date());
								inteProstore.setFromUserid(fromid);
								inteProstore.setMoney(sett.getReturnCounty()*0.1);
								inteProstore.setTime(365*3);
								inteProstore.setEnddate(DateUtil.addDay(new Date(), 365*3));
								inteProstore.setType("ps_recovery");
								baseDao.insert(PubConstants.INTEGRAL_PROSTORE, inteProstore);
							}
							
						}else if(user.get("agentLevel").equals("4")) {
							//部 
							whereMap.clear();
							whereMap.put("type", "ps_recovery");
							whereMap.put("fromUserid", fromid);
							Long icount=baseDao.getCount(PubConstants.INTEGRAL_PROSTORE,whereMap);
							if(icount==0) {
								InteProstore inteProstore=new InteProstore();
								inteProstore.set_id(mongoSequence.currval(PubConstants.INTEGRAL_PROSTORE));
								inteProstore.setCreatedate(new Date());
								inteProstore.setFromUserid(fromid);
								inteProstore.setMoney(sett.getReturnDept()*0.1);
								inteProstore.setTime(365*3);
								inteProstore.setEnddate(DateUtil.addDay(new Date(), 365*3));
								inteProstore.setType("ps_recovery");
								baseDao.insert(PubConstants.INTEGRAL_PROSTORE, inteProstore);
							}
							
						}
					}
				}
				
			} 
		}   
		
	}


	/**
	 * 减少积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param wxuser
	 * @return
	 */
	public boolean deljf(String price, String fromUserid, String type, String custid, DBObject wxuser) {
		try {
			if (Double.parseDouble(price) > 0) {
				if (changeJf(custid, fromUserid, price, 1, 0)) {
					IntegralInfo info = new IntegralInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(1);
					info.setCustid(custid);
					baseDao.insert(PubConstants.INTEGRAL_INFO, info);
					return true;
				} else {
					return false;
				}

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
//	/**
//	 * 
//	 * 减少冻结积分
//	 */
//	public boolean  delbmtz(String price, String fromUserid, String type, String custid, DBObject wxuser,int isfreeze) throws Exception{
//		try {
//			
//			HashMap<String, Object> whereMap = new HashMap<String, Object>();
//			if(StringUtils.isNotEmpty(custid)){
//				whereMap.put("custid", custid);
//			}
//			whereMap.put("fromUserid", fromUserid);
//			whereMap.put("type", type);
//			whereMap.put("isfreeze", isfreeze);
//			
//			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INFO, whereMap);
//			if (db != null) {
//				IntegralInfo  info = (IntegralInfo) UniObject.DBObjectToObject(db, IntegralInfo.class);
//				info.setValue(info.getValue()-price);
//				baseDao.insert(PubConstants.INTEGRAL_INFO, info);
//				return true;
//			} else {
//				return false;
//			}
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	
	

	/**
	 * 根据类型获取价格
	 * 
	 * @return
	 */
	public List<DBObject> getprice(String custid, String type) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("type", type);
		sortMap.put("createdate", -1);
		List<DBObject> list = baseDao.getList(PubConstants.SET_PRICE, whereMap, sortMap);
		if (list.size() > 0) {
			return list;
		}
		return null;

	}

	/**
	 * 获取已经支付的广告位
	 * 
	 * @return
	 */
	public List<DBObject> getadvertispay(String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("state", 3);
		BasicDBObject dateCondition = new BasicDBObject();

		dateCondition.append("$gte", new Date());
		whereMap.put("enddate", dateCondition);
		sortMap.put("createdate", -1);
		List<DBObject> list = baseDao.getList(PubConstants.ADVERTISEMENT, whereMap, sortMap);
		if (list.size() > 0) {
			return list;
		}
		return null;

	}

	/**
	 * 获取幻灯片
	 */
	public List<DBObject> getslide(String custid, String type) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("type", type);
		sortMap.put("sort", -1);
		List<DBObject> list = baseDao.getList(PubConstants.SUC_SLIDE, whereMap, sortMap);
		if (list.size() > 0) {
			return list;
		}

		return null;
	}

	/**
	 * 生成验证码
	 * 
	 * @param fromUserid
	 */
	public String createcode(String fromUserid) {
		if (StringUtils.isEmpty(fromUserid)) {
			return "";
		}
		DBObject db = baseDao.getMessage(PubConstants.USER_AUTHCODE, fromUserid);
		if (db == null) {
			Authcode code = new Authcode();
			code.set_id(fromUserid);
			code.setActivitydate(new Date());
			code.setCreatedate(new Date());
			code.setCode(UUID.randomUUID().toString());
			baseDao.insert(PubConstants.USER_AUTHCODE, code);
			return code.getCode();
		} else {
			Authcode obj = (Authcode) UniObject.DBObjectToObject(db, Authcode.class);
			if (DateUtil.getTimeDifference(DateUtil.getBeforeAnHours(), obj.getActivitydate()) >= 0) {
				// 验证码失效
				return updatecode(db.get("_id").toString());
			} else {
				// 记录活动时间
				obj.set_id(db.get("_id").toString());
				obj.setActivitydate(new Date());
				baseDao.insert(PubConstants.USER_AUTHCODE, obj);
			}
			return db.get("code").toString();
		}

	}

	/**
	 * 获取验证码
	 */
	public String getcode(String fromUserid) {
		if (StringUtils.isNotEmpty(fromUserid)) {
			DBObject db = baseDao.getMessage(PubConstants.USER_AUTHCODE, fromUserid);
			return db.get("code").toString();
		}
		return "";
	}

	/**
	 * 通过code获取用户id
	 * 
	 * @return
	 */
	public String getfromUseridfromcode(String code) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("code", code);
		DBObject db = baseDao.getMessage(PubConstants.USER_AUTHCODE, whereMap);
		if (db != null) {
			Authcode obj = (Authcode) UniObject.DBObjectToObject(db, Authcode.class);
			if (DateUtil.getTimeDifference(DateUtil.getBeforeAnHours(), obj.getActivitydate()) >= 0) {
				// 验证码失效
				updatecode(db.get("_id").toString());
			} else {
				// 记录活动时间
				obj.set_id(db.get("_id").toString());
				obj.setActivitydate(new Date());
				baseDao.insert(PubConstants.USER_AUTHCODE, obj);
			}
			return db.get("_id").toString();
		}
		return null;
	}

	/**
	 * 更新验证码
	 * 
	 * @return
	 */
	public String updatecode(String fromUserid) {
		if (StringUtils.isEmpty(fromUserid)) {
			return "";
		}
		DBObject db = baseDao.getMessage(PubConstants.USER_AUTHCODE, fromUserid);
		if (db != null) {
			Authcode code = (Authcode) UniObject.DBObjectToObject(db, Authcode.class);
			code.set_id(fromUserid);
			code.setCode(UUID.randomUUID().toString());
			code.setActivitydate(new Date());
			baseDao.insert(PubConstants.USER_AUTHCODE, code);
			return code.getCode();
		} else {
			return "";
		}
	}

	/**
	 * 获取个人中心模板
	 * 
	 * @param custid
	 * @return
	 */
	public List<DBObject> getfromusermb(String custid) {
		DBObject db = baseDao.getMessage(PubConstants.PUB_FROMUSERMB, custid);
		if (db != null) {
			Fromusermb mb = (Fromusermb) UniObject.DBObjectToObject(db, Fromusermb.class);
			return mb.getLsfunc();
		} else {
			return null;
		}

	}

	/**
	 * 获取个人中心模板
	 * 
	 * @param custid
	 * @return
	 */
	public DBObject getfromusermbs(String custid) {
		DBObject db = baseDao.getMessage(PubConstants.PUB_FROMUSERMB, custid);
		if (db != null) {
			return db;
		} else {
			return null;
		}

	}

	/**
	 * 获取分享url
	 * 
	 * @param custid
	 * @param url
	 * @return
	 */
	public String getshareurl(String custid, String url) {
		String toUser = "";
		WxToken wxtoken = GetAllFunc.wxtoken.get(custid);
		if (wxtoken.getSqlx() == 1) {
			toUser = GetAllFunc.wxtoken.get(getparentcustid(custid)).getToUser();
		}
		if (StringUtils.isNotEmpty(toUser)) {
			return WeiXinUtil.getinqurl(toUser, url);
		} else {
			return "";
		}

	}

	public DBObject getWxTouser(String custid) {
		HashMap<String, Object> backMap = new HashMap<String, Object>();
		backMap.put("title", 1);
		backMap.put("summary", 1);
		DBObject db = baseDao.getMessage(PubConstants.DATA_WXTOUSER, custid, backMap);
		if (db != null) {
			return db;
		} else {
			return null;
		}

	}

	public String getKdName(String id) {
		if (StringUtils.isEmpty(id)) {
			return "";
		}
		DBObject db = baseDao.getMessage(PubConstants.SET_COURIER, Long.parseLong(id));
		if (db != null) {
			return db.get("title").toString();
		} else {
			return "";
		}

	}

	public String getCustName(String custid) {
		if (StringUtils.isEmpty(custid)) {
			return "";
		}
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, custid);
		if (db != null) {
			return db.get("nickname").toString();
		} else {
			return "";
		}

	}

	public List<DBObject> getRoll(String custid, String type) {
		HashMap<String, Object> sortMap = new HashMap<String, Object>();
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("type", type);
		sortMap.put("sort", -1);
		List<DBObject> list = baseDao.getList(PubConstants.SUC_ROLL, whereMap, sortMap);
		if (list.size() > 0) {
			return list;
		}
		return null;

	}

	/**
	 * 积分更新
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param value
	 * @param type
	 * @param isfreeze
	 *            0--冻结积分增加 1--可使用积分增加
	 */
	public boolean changeJf(String custid, String fromUserid, String value, int type, int isfreeze) {
		try {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("custid", custid);
			whereMap.put("fromUserid", fromUserid);
			IntegralRecord ir = null;
			DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
			if (db == null) {
				ir = new IntegralRecord();
				ir.set_id(mongoSequence.currval(PubConstants.SUC_INTEGRALRECORD));
			} else {
				ir = (IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			}
			if(StringUtils.isEmpty(ir.getKjvalue())){
				ir.setKjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjtxvalue())){
				ir.setKjtxvalue("0");
			}
			if(StringUtils.isEmpty(ir.getProstore())){
				ir.setProstore("0");
			}
			if(StringUtils.isEmpty(ir.getValue())){
				ir.setValue("0");
			}
			if(StringUtils.isEmpty(ir.getUservalue())){
				ir.setUservalue("0");
			}
			if(StringUtils.isEmpty(ir.getLldjvalue())){
				ir.setLldjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlkyvalue())){
				ir.setLlkyvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlzvalue())){
				ir.setLlzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getYjvalue())){
				ir.setYjvalue("0");
			}
			ir.setCustid(custid);
			ir.setFromUserid(fromUserid);
			if (type == 0) {
				if (isfreeze == 1) {// 冻结积分增加
					ir.setProstore(BaseDecimal.add(ir.getProstore(), value));
					ir.setValue(BaseDecimal.add(ir.getValue(), value));
				} else if (isfreeze == 0) {// 可使用积分增加
					ir.setUservalue(ir.getUservalue() + value);
					ir.setValue(ir.getValue() + value);
				}
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 1 && Double.parseDouble(ir.getValue()) >=Double.parseDouble(value) ) {
				ir.setValue(BaseDecimal.subtract(ir.getValue(), value));
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 2) {
				ir.setValue(value);
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 积分更新（矿机）
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param value
	 * @param type
	 * @param isfreeze
	 * jzprice(价值)
	 * 0--冻结积分增加 1--可使用积分增加
	 */
	public boolean changeKjJf(String custid, String fromUserid, String value, int type,String jzprice) {
		try {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("custid", custid);
			whereMap.put("fromUserid", fromUserid);
			IntegralRecord ir = null;
			DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
			if (db == null) {
				ir = new IntegralRecord();
				ir.set_id(mongoSequence.currval(PubConstants.SUC_INTEGRALRECORD));
			} else {
				ir = (IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			}
			if(StringUtils.isEmpty(ir.getKjvalue())){
				ir.setKjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjtxvalue())){
				ir.setKjtxvalue("0");
			}
			if(StringUtils.isEmpty(ir.getProstore())){
				ir.setProstore("0");
			}
			if(StringUtils.isEmpty(ir.getValue())){
				ir.setValue("0");
			}
			if(StringUtils.isEmpty(ir.getUservalue())){
				ir.setUservalue("0");
			}
			if(StringUtils.isEmpty(ir.getLldjvalue())){
				ir.setLldjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlkyvalue())){
				ir.setLlkyvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlzvalue())){
				ir.setLlzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getYjvalue())){
				ir.setYjvalue("0");
			}
			ir.setCustid(custid);
			ir.setFromUserid(fromUserid);
			if (type == 0) { 
				ir.setKjvalue(BaseDecimal.add(ir.getKjvalue(), value));
				ir.setKjjzvalue(BaseDecimal.add(ir.getKjjzvalue(),jzprice));
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 1 &&Double.parseDouble(ir.getKjvalue())>=Double.parseDouble(value)&&Double.parseDouble(ir.getKjjzvalue())>=Double.parseDouble(jzprice)) {
				ir.setKjvalue(BaseDecimal.subtract(ir.getKjvalue(), value));
				ir.setKjjzvalue(BaseDecimal.subtract(ir.getKjjzvalue(), jzprice));
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 2) { 
				return false;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 积分更新
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param value
	 * @param type  0增加1减少
	 * @param isfreeze
	 *            0--未冻结 1--已冻结
	 * @param lx
	 *            0--PP 1--LL
	 */
	public boolean changeJf(String custid, String fromUserid, String value, int type, int isfreeze, int lx) {
		try {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("custid", custid);
			whereMap.put("fromUserid", fromUserid);
			IntegralRecord ir = null;
			DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
			if (db == null) {
				ir = new IntegralRecord();
				ir.set_id(mongoSequence.currval(PubConstants.SUC_INTEGRALRECORD));
			} else {
				ir = (IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			}
			if(StringUtils.isEmpty(ir.getKjvalue())){
				ir.setKjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjtxvalue())){
				ir.setKjtxvalue("0");
			}
			if(StringUtils.isEmpty(ir.getProstore())){
				ir.setProstore("0");
			}
			if(StringUtils.isEmpty(ir.getValue())){
				ir.setValue("0");
			}
			if(StringUtils.isEmpty(ir.getUservalue())){
				ir.setUservalue("0");
			}
			if(StringUtils.isEmpty(ir.getLldjvalue())){
				ir.setLldjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlkyvalue())){
				ir.setLlkyvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlzvalue())){
				ir.setLlzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getYjvalue())){
				ir.setYjvalue("0");
			}
			ir.setCustid(custid);
			ir.setFromUserid(fromUserid);
			if (type == 0) {
				//增加操作
				if (lx == 0) {
					//PP币种计算
					if (isfreeze == 1) {// 冻结增加
						System.out.println("冻结增加");
						ir.setProstore(BaseDecimal.add(ir.getKjvalue(), value)); 
					} else if (isfreeze == 0) {// 可使用增加
						System.out.println("可用增加");
						ir.setUservalue(BaseDecimal.add(ir.getUservalue(), value)); 
					}
					ir.setValue(BaseDecimal.add(ir.getValue(), value));
					baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
					return true;
				} else if (lx == 1) {
					//乐乐币种计算
					if (isfreeze == 1) {// 冻结增加
						ir.setLldjvalue(BaseDecimal.add(ir.getLldjvalue(), value));
						
					} else if (isfreeze == 0) {// 可用增加
						System.out.println("llz");
						ir.setLlkyvalue(BaseDecimal.add(ir.getLlkyvalue(), value)); 
					}
					ir.setLlzvalue(BaseDecimal.add(ir.getLlzvalue(), value));
					baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
					return true;
				}

			} else if (type == 1) {
				//减少操作
				if(lx==0) {
					//PP盼盼币种计算
					if(isfreeze == 1) {
						if(Double.parseDouble(ir.getProstore())>=Double.parseDouble(value)) {
							// 冻结减少
							ir.setProstore(BaseDecimal.subtract(ir.getProstore(), value));
						}else {
							return false;
						}
					}else if(isfreeze == 0) {
						if(Double.parseDouble(ir.getUservalue())>=Double.parseDouble(value)) {
							// 可以使用减少
							ir.setUservalue(BaseDecimal.subtract(ir.getUservalue(), value));
						}else {
							return false;
						}
					}
					
					
					if(Double.parseDouble(ir.getValue()) >=Double.parseDouble(value)) {
						ir.setValue(BaseDecimal.subtract(ir.getValue(), value));
					}
				}else if(lx==1) {
					//乐乐币种计算
					if(isfreeze == 1) {
						if(Double.parseDouble(ir.getLldjvalue())>=Double.parseDouble(value)) {
							// 冻结减少
							ir.setLldjvalue(BaseDecimal.subtract(ir.getLldjvalue(), value));
						}else {
							return false;
						}
					}else if(isfreeze == 0) {
						if(Double.parseDouble(ir.getLlkyvalue())>=Double.parseDouble(value)) {
							// 可以使用减少
							ir.setLlkyvalue(BaseDecimal.subtract(ir.getUservalue(), value));
						}else {
							return false;
						}
					}
					 
					if(Double.parseDouble(ir.getLlzvalue()) >=Double.parseDouble(value)) {
						ir.setLlzvalue(BaseDecimal.subtract(ir.getLlzvalue(), value));
					}
				}
				
				
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 2) {
				ir.setValue(value);
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 积分更新(佣金)
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param value
	 * @param type  0增加1减少
	 * @param isfreeze
	 *            0--未冻结 1--已冻结
	 * @param lx
	 *            0--PP 1--LL
	 */
	public boolean changeYjJf(String custid, String fromUserid, String value, int type, int lx) {
		try {
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("custid", custid);
			whereMap.put("fromUserid", fromUserid);
			IntegralRecord ir = null;
			DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
			if (db == null) {
				ir = new IntegralRecord();
				ir.set_id(mongoSequence.currval(PubConstants.SUC_INTEGRALRECORD));
			} else {
				ir = (IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			}
			if(StringUtils.isEmpty(ir.getKjvalue())){
				ir.setKjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjjzvalue())){
				ir.setKjjzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getKjtxvalue())){
				ir.setKjtxvalue("0");
			}
			if(StringUtils.isEmpty(ir.getProstore())){
				ir.setProstore("0");
			}
			if(StringUtils.isEmpty(ir.getValue())){
				ir.setValue("0");
			}
			if(StringUtils.isEmpty(ir.getUservalue())){
				ir.setUservalue("0");
			}
			if(StringUtils.isEmpty(ir.getLldjvalue())){
				ir.setLldjvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlkyvalue())){
				ir.setLlkyvalue("0");
			}
			if(StringUtils.isEmpty(ir.getLlzvalue())){
				ir.setLlzvalue("0");
			}
			if(StringUtils.isEmpty(ir.getYjvalue())){
				ir.setYjvalue("0");
			}
			ir.setCustid(custid);
			ir.setFromUserid(fromUserid);
			if(type == 0) { 
				ir.setYjvalue(BaseDecimal.add(ir.getYjvalue(), value)); 
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
				 
			}else if (type == 1) { 
				if(Double.parseDouble(ir.getYjvalue())>=Double.parseDouble(value)) {
				   ir.setYjvalue(BaseDecimal.subtract(ir.getYjvalue(), value));
				} 
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else if (type == 2) {
				ir.setValue(value);
				baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	

	/**
	 * 将冻结积分变成可用积分
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param value
	 * @param type
	 */
	public boolean changeFreezeJf(String custid, String fromUserid) throws Exception {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		IntegralRecord ir = null;
		DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		if (db != null) {
			ir = (IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
			ir.setUservalue(ir.getUservalue() + ir.getProstore());
			ir.setProstore("0");
			baseDao.insert(PubConstants.SUC_INTEGRALRECORD, ir);
			return true;
		}
		return false;
	}

	/**
	 * 获取用户积分
	 * 
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public float getJf(String custid, String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		if (db != null) {
			return Float.parseFloat(db.get("value").toString());
		}
		return 0;
	}
	/**
	 * 获取用户积分
	 * 
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public DBObject getJfOBJ(String custid, String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		if (db != null) {
			return db;
		}
		return null;
	}
	
	/**
	 * 获取用户积分
	 * 
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public DBObject getJfObj(String custid, String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, whereMap);
		if (db != null) {
			return db;
		}
		return null;
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码内容
	 * @param lPath
	 *            logo地址
	 * @param fPath
	 *            二维码储存地址
	 * @param bl
	 *            是否压缩logo
	 * @param lwidth
	 *            logo宽度
	 * @param fwidth
	 *            二维码宽度
	 * @return
	 */
	public String recode(String type, String content, String lPath, boolean bl, int lwidth, int fwidth) {
		try {
			if (!QRCodeUtil.recode(content, SysConfig.getProperty("imgpath") + "/" + lPath,
					SysConfig.getProperty("imgpath") + "/bcode/" + type + ".jpg", bl, lwidth, fwidth)) {
				return "";
			}
			;
			return "/bcode/" + type + ".jpg";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 记录登录时间
	 * 
	 * @return
	 */
	public boolean recordlogin(String custid, String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		UserLoginDetail ul;
		DBObject db = baseDao.getMessage(PubConstants.USER_USERLOGINDETAIL, whereMap);
		if (db == null) {
			ul = new UserLoginDetail();
			ul.set_id(mongoSequence.currval(PubConstants.USER_USERLOGINDETAIL));
		} else {
			ul = (UserLoginDetail) UniObject.DBObjectToObject(db, UserLoginDetail.class);
		}
		ul.setCustid(custid);
		ul.setCreatedate(new Date());
		ul.setFromUserid(fromUserid);
		baseDao.insert(PubConstants.USER_USERLOGINDETAIL, ul);
		return false;
	}

	/**
	 * 获取用户最后登录时间
	 * 
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public Object getlogin(String custid, String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		DBObject db = baseDao.getMessage(PubConstants.USER_USERLOGINDETAIL, whereMap);
		if (db != null) {
			if (db.get("createdate") != null) {
				return db.get("createdate");
			}
		}
		return "";
	}

	/**
	 * 生成会员号
	 * 
	 * @param fromUserid
	 * @return
	 */
	public boolean createVipNo(DBObject db) {
		if (db != null) {
			WxUser wxuser = (WxUser) UniObject.DBObjectToObject(db, WxUser.class);
			wxuser.setNo(getVipNo());
			baseDao.insert(PubConstants.USER_INFO, wxuser);
			return true;
		}
		return false;
	}

	/**
	 * 根据会员号获取用户Id
	 * 
	 * @param VipNo
	 * @return
	 */
	public String getfromUseridVipNo(String VipNo) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("no", VipNo);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
		if (db != null) {
			return db.get("_id").toString();
		}
		return "";

	}

	/**
	 * 根据会员号获取用户
	 * 
	 * @param VipNo
	 * @return
	 */
	public DBObject getWXuserVipNo(String VipNo) {
		if (StringUtils.isEmpty(VipNo)) {
			return null;
		}
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("no", VipNo);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
		if (db != null) {
			return db;
		}
		return null;

	}

	/**
	 * 获取会员号
	 * 
	 * @param FromUserid
	 * @return
	 */
	public String getVipNo(String fromUserid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("_id", fromUserid);
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
		if (db != null && db.get("no") != null) {
			return db.get("no").toString();
		}
		return "";

	}

	/**
	 * 添加好友
	 * 
	 * @param fromUserid
	 * @param friendsid
	 * @return
	 */
	public boolean addFriends(String fromUserid, String friendsid) {
		try {
			if (fromUserid.equals(friendsid)) {
				return false;
			}
			// 检查是否是好友
			HashMap<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("fromUserid", fromUserid);
			whereMap.put("friendsid", friendsid);
			Long count = baseDao.getCount(PubConstants.USER_FRIEDSINFO, whereMap);
			if (count == 0) {
				FriendsInfo fr = new FriendsInfo();
				fr.set_id(mongoSequence.currval(PubConstants.USER_FRIEDSINFO));
				fr.setCreatedate(new Date());
				fr.setFriendsid(friendsid);
				fr.setFromUserid(fromUserid);
				fr.setState(1);
				baseDao.insert(PubConstants.USER_FRIEDSINFO, fr);

				whereMap.clear();
				whereMap.put("fromUserid", friendsid);
				whereMap.put("friendsid", fromUserid);
				count = baseDao.getCount(PubConstants.USER_FRIEDSINFO, whereMap);
				if (count == 0) {
					fr = new FriendsInfo();
					fr.set_id(mongoSequence.currval(PubConstants.USER_FRIEDSINFO));
					fr.setCreatedate(new Date());
					fr.setFriendsid(fromUserid);
					fr.setFromUserid(friendsid);
					fr.setState(1);
					baseDao.insert(PubConstants.USER_FRIEDSINFO, fr);
				}
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 积分验证
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param type
	 */
	public boolean chekjf(String custid, String fromUserid, String type) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		whereMap.put("type", type);
		Long count = baseDao.getCount(PubConstants.INTEGRAL_INFO, whereMap);
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证未读邮件
	 * 
	 * @return
	 */
	public Long chekEmail(String fromUserid, String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("toUserid", fromUserid);
		whereMap.put("custid", custid);
		whereMap.put("state", 1);
		Long count = baseDao.getCount(PubConstants.EMAIL_EMALIINFO, whereMap);
		if (count > 0) {
			return count;
		}
		return null;
	}

	/**
	 * 验证未审核好友
	 * 
	 * @return
	 */
	public Long chekfireds(String fromUserid, String custid) {
		HashMap<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("fromUserid", fromUserid);
		whereMap.put("state", 1);
		whereMap.put("custid", custid);
		Long count = baseDao.getCount(PubConstants.USER_FRIEDSINFO, whereMap);
		if (count > 0) {
			return count;
		}
		return null;
	}

	public String fromUserbyid(String custid, String fromUserid) {
		if (StringUtils.isNotEmpty(fromUserid)) {
			DBObject db = baseDao.getMessage(PubConstants.USER_INFO, fromUserid);
			WxToken token = GetAllFunc.wxtoken.get(custid);
			if (token.getSqlx() > 0) {
				token = GetAllFunc.wxtoken.get(getparentcustid(custid));
			}
			JSONObject map = WeiXinUtil.getUserinfo(token.getAccess_token(), db.get("fromUser").toString());
			return map.get("subscribe").toString();
		}
		return "";
	}

	public boolean getUser(String custid) {
		if (StringUtils.isEmpty(custid)) {
			return false;
		}
		DBObject db = baseDao.getMessage(PubConstants.USER_INFO, custid);
		if (db != null) {
			return true;
		}
		return false;

	}

	/**
	 * 增加佣金
	 * 
	 * @param id
	 * @param price
	 * @return
	 */
	public boolean addAgent(String id, double price, String oid, String fromUserid, String custid) {
		if (StringUtils.isEmpty(oid)) {
			return false;
		}
		if (StringUtils.isNotEmpty(id) && price > 0) {
			DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPAGENT, id);
			if (db != null) {
				// 结算到店铺
				ShopAgent agent = (ShopAgent) UniObject.DBObjectToObject(db, ShopAgent.class);
				agent.setSales(agent.getSales() + price);
				agent.setPrice(agent.getPrice() + price);
				baseDao.insert(PubConstants.SHOP_SHOPAGENT, agent);
				// 记录
				AgentDetail detail = new AgentDetail();
				detail.set_id(mongoSequence.currval(PubConstants.SHOP_AGENTDETAIL));
				detail.setWid(id);
				detail.setOid(oid);
				detail.setPrice(price);
				detail.setFromUserid(fromUserid);
				detail.setType(1);
				detail.setCreatedate(new Date());
				baseDao.insert(PubConstants.SHOP_AGENTDETAIL, detail);

				// 结算到账户
				if (addAgentPrice(custid, fromUserid, price)) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * 佣金提现
	 * 
	 * @param id
	 * @param price
	 * @return
	 */
	public boolean delAgent(String id, double price, String oid, String fromUserid, String custid) {
		if (StringUtils.isNotEmpty(id) && price > 0) {
			// 记录
			AgentDetail detail = new AgentDetail();
			detail.set_id(mongoSequence.currval(PubConstants.SHOP_AGENTDETAIL));
			// detail.setWid(id);
			detail.setOid(oid);
			detail.setFromUserid(fromUserid);
			detail.setPrice(price);
			detail.setType(2);
			detail.setCreatedate(new Date());
			baseDao.insert(PubConstants.SHOP_AGENTDETAIL, detail);
			// 结算到账户
			if (addAgentPrice(custid, fromUserid, price)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 获取代理ID
	 * 
	 * @param comid
	 * @param vid
	 * @return
	 */
	public String getAgid(String comid, String vid) {
		if (StringUtils.isNotEmpty(vid) && StringUtils.isNotEmpty(comid)) {
			DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPAGENT, comid + "-" + vid);
			if (db != null && db.get("state").toString().equals("2")) {
				return comid + "-" + vid;
			}
		}
		return "";

	}

	/**
	 * 获取代理
	 * 
	 * @param agid
	 * @return
	 */
	public DBObject getAgent(String agid) {
		if (StringUtils.isEmpty(agid) || agid.equals("null")) {
			return null;
		}
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPAGENT, agid);
		if (db != null && db.get("state").toString().equals("2")) {
			return db;
		}
		return null;
	}

	/**
	 * 验证代理(是否是某平台的代理)
	 * 
	 * @param agid
	 * @param fromUserid
	 * @return
	 */
	public boolean checkAgent(String custid, String fromUserid) {

		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("custid", custid);
		whereMap.put("fromUserid", fromUserid);
		whereMap.put("state", 2);
		Long count = baseDao.getCount(PubConstants.SHOP_SHOPAGENT, whereMap);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 验证代理（是否是当前代理本人）
	 * 
	 * @param agid
	 * @param fromUserid
	 * @return
	 */
	public boolean checkAgent(String agid, String custid, String fromUserid) {

		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("_id", agid);
		whereMap.put("custid", custid);
		whereMap.put("state", 2);
		DBObject db = baseDao.getMessage(PubConstants.SHOP_SHOPAGENT, whereMap);
		if (db != null && db.get("fromUserid") != null && db.get("fromUserid").toString().equals(fromUserid)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取佣金
	 * 
	 * @return
	 */
	public double getAgent(String custid, String fromUserid) {
		DBObject db = getAgentPrice(custid, fromUserid);
		if (db.get("price") != null) {
			return Double.parseDouble(db.get("price").toString());
		}
		return 0;
	}

	/**
	 * 获取佣金账户
	 * 
	 * @param custid
	 * @param fromUserid
	 * @return
	 */
	public DBObject getAgentPrice(String custid, String fromUserid) {
		if (StringUtils.isEmpty(fromUserid) || StringUtils.isEmpty(custid)) {
			return null;
		}
		DBObject db = baseDao.getMessage(PubConstants.SHOP_AGENTPRICE, custid + "-" + fromUserid);
		if (db != null) {
			return db;
		} else {
			AgentPrice agentPrice = new AgentPrice();
			agentPrice.set_id(custid + "-" + fromUserid);
			agentPrice.setCustid(custid);
			agentPrice.setFromUserid(fromUserid);
			baseDao.insert(PubConstants.SHOP_AGENTPRICE, agentPrice);
			return baseDao.getMessage(PubConstants.SHOP_AGENTPRICE, custid + "-" + fromUserid);
		}

	}

	/**
	 * 结算到佣金账户
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param price
	 * @return
	 */
	public boolean addAgentPrice(String custid, String fromUserid, double price) {
		DBObject agp = getAgentPrice(custid, fromUserid);
		if (agp != null) {
			AgentPrice agentPrice = (AgentPrice) UniObject.DBObjectToObject(agp, AgentPrice.class);
			agentPrice.setCustid(custid);
			agentPrice.setFromUserid(fromUserid);
			agentPrice.setPrice(agentPrice.getPrice() + price);
			baseDao.insert(PubConstants.SHOP_AGENTPRICE, agentPrice);
			return true;
		}
		return false;
	}

	/**
	 * 结算到佣金账户
	 * 
	 * @param custid
	 * @param fromUserid
	 * @param price
	 * @return
	 */
	public boolean delAgentPrice(String custid, String fromUserid, double price) {
		DBObject agp = getAgentPrice(custid, fromUserid);
		if (agp != null) {
			AgentPrice agentPrice = (AgentPrice) UniObject.DBObjectToObject(agp, AgentPrice.class);
			agentPrice.setCustid(custid);
			agentPrice.setFromUserid(fromUserid);
			agentPrice.setPrice(agentPrice.getPrice() - price);
			baseDao.insert(PubConstants.SHOP_AGENTPRICE, agentPrice);
			return true;
		}
		return false;
	}

	/**
	 * 验证客户端是否是微信浏览器
	 * 
	 * @param request
	 * @return
	 */
	public boolean isWXAgent(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent").toLowerCase();
		if (agent.indexOf("micromessenger") > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 手动注册用户
	 * 
	 * @param custid
	 * @param nickname
	 * @param headimgurl
	 * @param sex
	 * @param age
	 * @param city
	 * @param province
	 * @return 用户ID
	 */
	public WxUser registerUser(String custid, String nickname, String headimgurl, String sex, String age, String city,
			String province) {
		WxUser user;
		try {
			user = new WxUser();
			user.set_id(UUID.randomUUID().toString());
			user.setCustid(custid);
			user.setCity(city);
			user.setSex(sex);
			user.setProvince(province);
			user.setNickname(nickname);
			user.setHeadimgurl(headimgurl);
			user.setNo(getVipNo());
			user.setCreatedate(new Date());
			baseDao.insert(PubConstants.USER_INFO, user);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 验证是否是兼职管理员
	 * 
	 * @param fromid
	 * @param custid
	 * @return
	 */
	public boolean CheckEmployee(String fromid, String custid) {
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("custid", custid);
		whereMap.put("fromid", fromid);
		DBObject dbObject = baseDao.getMessage(PubConstants.PARTTIME_EMPLOYEE, custid + "-" + fromid);

		if (dbObject != null && dbObject.get("type") != null) {
			int type = Integer.parseInt(dbObject.get("type").toString());
			if (type > 0) {
				return true;
			}
		}
		return false;
	}
 

	/**
	 * 获取资金
	 * 
	 * @param custid
	 * @param fromid
	 * @return
	 */
	private DBObject getAssetMiss(String custid, String fromid) {
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("custid", custid);
		whereMap.put("fromid", fromid);
		DBObject dbObject = baseDao.getMessage(PubConstants.PARTTIME_ASSETS, whereMap);
		if (dbObject != null) {
			return dbObject;
		}
		return null;
	}

	/**
	 * 获取资金
	 * 
	 * @return
	 */
	public double getAsset(String custid, String fromUserid) {
		DBObject db = getAssetMiss(custid, fromUserid);
		if (db != null && db.get("value") != null) {
			return Double.parseDouble(db.get("value").toString());
		}
		return 0;
	}

	/**
	 * 验证资金
	 * 
	 * @param custid
	 * @param fromid
	 * @return
	 */
	public boolean checkAssetMiss(String custid, String fromid) {
		double d = 0;
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("custid", custid);
		whereMap.put("fromid", fromid);
		List<DBObject> list = baseDao.getList(PubConstants.PARTTIME_ASSETSRECORD, whereMap, null);
		for (DBObject dbObject : list) {
			if (Integer.parseInt(dbObject.get("type").toString()) == 1) {
				d -= Double.parseDouble(dbObject.get("value").toString());
			} else {
				d += Double.parseDouble(dbObject.get("value").toString());
			}
		}
		DBObject dbObject = getAssetMiss(custid, fromid);
		if (dbObject != null && Double.parseDouble(dbObject.get("value").toString()) == d) {
			return true;
		}
		return false;

	}

	/**
	 * 推荐开户人 积分增加
	 * 
	 * @param custid
	 */
	public void profit(String price, String fromUserid, String type, String custid, DBObject wxuser) {

	}

	/**
	 * 验证总积分
	 * 
	 * @param type积分类型
	 * @return
	 */
	public boolean checkTotalIntegral(int type, String value) {
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("_id",SysConfig.getProperty("custid"));
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING,SysConfig.getProperty("custid"));
		if (db != null) {
			InteSetting inteSetting = (InteSetting) UniObject.DBObjectToObject(db, InteSetting.class);
			 
			String now="0";
			String nows="0";
			if(StringUtils.isNotEmpty(inteSetting.getNownum())) {
				now=inteSetting.getNownum();
			}
			if(StringUtils.isNotEmpty(inteSetting.getNownums())) {
				now=inteSetting.getNownums();
			}
			if (type == 1) {
				if ((Double.parseDouble(inteSetting.getNum())
						- Double.parseDouble(BaseDecimal.add(now, value))) > 0) {
					return true;
				}
			} else if (type == 2) {
				if ((Double.parseDouble(inteSetting.getNums())
						- Double.parseDouble(BaseDecimal.add(nows, value))) > 0) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * 更新总积分
	 * 
	 * @param type积分类型
	 * @return
	 */
	public boolean updateTotalIntegral(int type, String value) {
		HashMap<String, Object> whereMap = new HashMap<>();
		DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, whereMap);
		if (db != null) {
			InteSetting inteSetting = (InteSetting) UniObject.DBObjectToObject(db, InteSetting.class);
			if (type == 1) {
				if(inteSetting.getNownum() == null){
					inteSetting.setNownum(BaseDecimal.add("0", value));
				}else{
					inteSetting.setNownum(BaseDecimal.add(inteSetting.getNownum(), value));
				}
				
				baseDao.insert(PubConstants.INTEGRAL_INTESETTING, inteSetting);
				return true;
			} else if (type == 2) {
				if(inteSetting.getNownums() == null){
					inteSetting.setNownums(BaseDecimal.add("0", value));
				}else{
					inteSetting.setNownums(BaseDecimal.add(inteSetting.getNownums(), value));
				} 
				baseDao.insert(PubConstants.INTEGRAL_INTESETTING, inteSetting);
				return true;
			}

		}
		return false;
	}

	/**
	 * 增加积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean addjf(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze) {
		try {
			if (Double.parseDouble(price) > 0) {
				if (lx == 0) {
					//默认积分
					if (jflx == 1) {
						IntegralInfo info = new IntegralInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setState(0);
						info.setCustid(custid);
						info.setIsfreeze(isfreeze);
						baseDao.insert(PubConstants.INTEGRAL_INFO, info);
						if (changeJf(custid, fromUserid,price, 0, isfreeze, 0)) {
							return true;
						} else {
							return false;
						}
					} else if (jflx == 2) {
						IntegralLlInfo info = new IntegralLlInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setState(0);
						info.setCustid(custid);
						baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
						if (changeJf(custid, fromUserid,price, 0, isfreeze, 1)) {
							return true;
						} else {
							return false;
						}
					}

				} else if (lx == 1) {
					// 系统发放（验证库存）
					if (checkTotalIntegral(jflx, price)) {
						//默认积分
						if (jflx == 1) {
							IntegralInfo info = new IntegralInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setState(0);
							info.setCustid(custid);
							info.setIsfreeze(isfreeze);
							baseDao.insert(PubConstants.INTEGRAL_INFO, info);
							if (changeJf(custid, fromUserid,price, 0, isfreeze, 0)) {
								if (updateTotalIntegral(jflx, price)) {
									return true;
								}
							} else {
								return false;
							}
						} else if (jflx == 2) {
							IntegralLlInfo info = new IntegralLlInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setState(0);
							info.setCustid(custid);
							baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
							if (changeJf(custid, fromUserid,price, 0, isfreeze, 1)) {
								if (updateTotalIntegral(jflx, price)) {
									return true;
								}
							} else {
								return false;
							}
						}
					}

				}

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * 增加积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean addjfoid(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze,String oid) {
		try {
			if (Double.parseDouble(price) > 0) {
				if (lx == 0) {
					//默认积分
					if (jflx == 1) {
						IntegralInfo info = new IntegralInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setState(0);
						info.setOid(oid);
						info.setCustid(custid);
						info.setIsfreeze(isfreeze);
						baseDao.insert(PubConstants.INTEGRAL_INFO, info);
						if (changeJf(custid, fromUserid, price, 0, isfreeze, 0)) {
							return true;
						} else {
							return false;
						}
					} else if (jflx == 2) {
						IntegralLlInfo info = new IntegralLlInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setOid(oid);
						info.setState(0);
						info.setCustid(custid);
						baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
						if (changeJf(custid, fromUserid,price, 0, isfreeze, 1)) {
							return true;
						} else {
							return false;
						}
					}

				} else if (lx == 1) { 
						//默认积分
						if (jflx == 1) {
							IntegralYjInfo info = new IntegralYjInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRALYJ_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setState(0);
							info.setOid(oid);
							info.setCustid(custid);
							info.setIsfreeze(isfreeze);
							baseDao.insert(PubConstants.INTEGRALYJ_INFO, info);
							if (changeYjJf(custid, fromUserid, price, 0,0)) { 
								return true; 
							} else {
								return false;
							}
						} else if (jflx == 2) {
							// 系统发放（验证库存）
							if (checkTotalIntegral(jflx, price)) {
							IntegralLlInfo info = new IntegralLlInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setOid(oid);
							info.setState(0);
							info.setCustid(custid);
							baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
							if (changeJf(custid, fromUserid, price, 0, isfreeze, 1)) {
								if (updateTotalIntegral(jflx, price)) {
									return true;
								}
							} else {
								return false;
							}
						  }
						}
					}
 

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * 增加积分(佣金)
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean addYjjfoid(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze,String oid) {
		try {
			if (Double.parseDouble(price) > 0) {
				if (lx == 0) {
					//默认积分
					if (jflx == 1) {
						IntegralInfo info = new IntegralInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setState(0);
						info.setOid(oid);
						info.setCustid(custid);
						info.setIsfreeze(isfreeze);
						baseDao.insert(PubConstants.INTEGRAL_INFO, info);
						if (changeJf(custid, fromUserid, price, 0, isfreeze, 0)) {
							return true;
						} else {
							return false;
						}
					} else if (jflx == 2) {
						IntegralLlInfo info = new IntegralLlInfo();
						info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
						info.setCreatedate(new Date());
						info.setFromUserid(fromUserid);
						info.setValue(price);
						info.setType(type);
						info.setOid(oid);
						info.setState(0);
						info.setCustid(custid);
						baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
						if (changeJf(custid, fromUserid,price, 0, isfreeze, 1)) {
							return true;
						} else {
							return false;
						}
					}

				} else if (lx == 1) {
					// 系统发放（验证库存）
					if (checkTotalIntegral(jflx, price)) {
						//默认积分
						if (jflx == 1) {
							IntegralInfo info = new IntegralInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setState(0);
							info.setOid(oid);
							info.setCustid(custid);
							info.setIsfreeze(isfreeze);
							baseDao.insert(PubConstants.INTEGRAL_INFO, info);
							if (changeJf(custid, fromUserid, price, 0, isfreeze, 0)) {
								if (updateTotalIntegral(jflx, price)) {
									return true;
								}
							} else {
								return false;
							}
						} else if (jflx == 2) {
							IntegralLlInfo info = new IntegralLlInfo();
							info.set_id(mongoSequence.currval(PubConstants.INTEGRALLL_INFO));
							info.setCreatedate(new Date());
							info.setFromUserid(fromUserid);
							info.setValue(price);
							info.setType(type);
							info.setOid(oid);
							info.setState(0);
							info.setCustid(custid);
							baseDao.insert(PubConstants.INTEGRALLL_INFO, info);
							if (changeJf(custid, fromUserid, price, 0, isfreeze, 1)) {
								if (updateTotalIntegral(jflx, price)) {
									return true;
								}
							} else {
								return false;
							}
						}
					}

				}

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	
	
	/**
	 * 减少积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean deljf(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze) {
		try {
			//减少冻结积分
			if(Double.parseDouble(price)>0) {
				if(changeJf(custid, fromUserid, price,1,isfreeze,0)) {
					IntegralInfo info = new IntegralInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(1);
					info.setCustid(custid);
					baseDao.insert(PubConstants.INTEGRAL_INFO, info);
					return true;
				}
			} 

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * 减少积分
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean deljfoid(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze,String oid) {
		try {
			//减少冻结积分
			if(Double.parseDouble(price)>0) {
				if(changeJf(custid, fromUserid, price,1,isfreeze,0)) {
					IntegralInfo info = new IntegralInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRAL_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(1);
					info.setOid(oid);
					info.setCustid(custid);
					baseDao.insert(PubConstants.INTEGRAL_INFO, info);
					return true;
				}
			} 

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * 减少积分(佣金)
	 * 
	 * @param price
	 * @param fromUserid
	 * @param type
	 * @param custid
	 * @param lx(0为正常交易，1为系统赠送)
	 * @param jflx(0普通积分，1为PP，2为LL)
	 * @param isfreeze(0-未冻结   1-已冻结)
	 * @return
	 */
	public boolean delYjjf(String price, String fromUserid, String type, String custid, int lx, int jflx,int isfreeze,String oid) {
		try {
			//减少冻结积分
			if(Double.parseDouble(price)>0) {
				if(changeYjJf(custid, fromUserid, price,1,0)) {
					IntegralYjInfo info = new IntegralYjInfo();
					info.set_id(mongoSequence.currval(PubConstants.INTEGRALYJ_INFO));
					info.setCreatedate(new Date());
					info.setFromUserid(fromUserid);
					info.setValue(price);
					info.setType(type);
					info.setState(1);
					info.setCustid(custid);
					info.setOid(oid);
					baseDao.insert(PubConstants.INTEGRALYJ_INFO, info);
					return true;
				}
			} 

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 获取当前LL赠送数量
	 * 
	 * @return
	 */
	public String getGivingPro(double price) {
		if (price > 0) {
			HashMap<String, Object> whereMap = new HashMap<>();
			whereMap.put("_id",SysConfig.getProperty("custid"));
			DBObject db = baseDao.getMessage(PubConstants.INTEGRAL_INTESETTING, whereMap);
			if (db != null) {
				InteSetting inteSetting = (InteSetting) UniObject.DBObjectToObject(db, InteSetting.class);
				// 获取当前赠送数量
				double now =0;
				if(inteSetting.getNownums()!=null){
					
					now=Double.parseDouble(inteSetting.getNownums());
				}
				
				if (now >= 0 && now < 50000000) {
					// 1倍
					return BaseDecimal.multiplication(price + "", "1");
				} else if (now >= 50000000 && now < 75000000) {
					// 0.5倍
					return BaseDecimal.multiplication(price + "", "0.5");
				} else if (now >= 75000000 && now < 87500000) {
					// 0.25倍
					return BaseDecimal.multiplication(price + "", "0.25");
				} else if (now >= 87500000 && now <= 100000000) {
					// 0.125倍
					return BaseDecimal.multiplication(price + "", "0.125");
				}

			}
		}
		return "0";
	}
	
	/**
     * 发送短信验证码
     * @param tel
     * @param content
     * @return https://IP+Port/ws/BatchSend2.aspx?CorpID=*&Pwd=*&Mobile=*&Content=*&SendTime=* 
     */

    public boolean sendSMS_ts(String tel,String content) {
    	String account ="N4103166";
    	// 用户平台API密码(非登录密码)
    	String pswd ="eDqwluLbWSe971";
    	String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
    	//状态报告
    	String report= "true"; 
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, content, tel,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		if(Integer.parseInt(smsSingleResponse.getCode().trim())==0) {
			return true;
		} 
		return false; 
    }
    /**
     * 发送短信验证码
     * @param tel
     * @param content
     * @return https://IP+Port/ws/BatchSend2.aspx?CorpID=*&Pwd=*&Mobile=*&Content=*&SendTime=* 
     */

    public boolean sendSMS(String tel,String content) {
    	//https://sdk2.028lk.com/sdk2/  
    	try {
			String send_content=URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");//发送内容
			String url="https://sdk2.028lk.com/sdk2/BatchSend2.aspx?CorpID="+SysConfig.getProperty("lk_sms_id")+"&Pwd="+SysConfig.getProperty("lk_sms_pwd")+"&Mobile="+tel+"&Content="+send_content;  
			String str=HttpClient.sendGet(url); 
			System.out.println(str);
			if (Integer.parseInt(str.trim())>0) {
				return true; 
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
    }
    /**
     * 获取实时价格
     * @return
     */
    public double getSprice() { 
		return 0; 
    }
    /**
     * 获取比特币实时价格(USD美元CNY人民币)
     * @return
     */
    public double getBTCSprice() { 
    	String str=HttpClient.sendGet("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=CNY");
    	JSONObject json=JSONObject.parseObject(str);
    	if(json.get("CNY")!=null) {
    		return Double.parseDouble(json.get("CNY").toString());
    	};
		return 0; 
    }
    /**
     * 获取以太坊实时价格
     * @return
     */
    public double getETHSprice() { 
    	String str=HttpClient.sendGet("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=CNY");
    	JSONObject json=JSONObject.parseObject(str);
    	if(json.get("CNY")!=null) {
    		return Double.parseDouble(json.get("CNY").toString());
    	};
		return 0; 
    }
    /**
     * 获取盼盼币实时价格
     * @return
     */
    public double getPPBSprice() { 
    	String str=HttpClient.sendGet(SysConfig.getProperty("uskd_price_api"));
    	if(StringUtils.isNotEmpty(str)){
    		JSONObject json=JSONObject.parseObject(str);
        	if(json.get("new_price")!=null) {
        		return Double.parseDouble(BaseDecimal.multiplication(getUSD_CNY(), json.get("new_price").toString()));
        	};
    	}
    	
		return 0; 
    } 
    /**
     * 获取美元兑换人民币的比例
     * @return
     */
    public  String  getUSD_CNY(){
    	HashMap<String, Object>whereMap=new HashMap<>();
    	whereMap.put("type","USD_CNY");
    	DBObject  db=baseDao.getMessage(PubConstants.PUB_EXCHANGERATE, whereMap);  
    	if(db!=null&&DateUtil.getTimeDifference(new Date(),DateFormat.getFormat(db.get("upddate").toString()))<=1800000){
    		System.out.println(111);
    		return db.get("value").toString();
    	} else if(db!=null&&DateUtil.getTimeDifference(new Date(),DateFormat.getFormat(db.get("upddate").toString()))>1800000){
    		//更新汇率 
    		String str=HttpClient.sendGet(SysConfig.getProperty("cny_price_api")); 
    		if(StringUtils.isNotEmpty(str)){
        		JSONObject json=JSONObject.parseObject(str);
        		if(json.get("result")!=null){
        			json=JSONObject.parseObject(json.get("result").toString());
        		} 
            	if(json.get("USD")!=null) { 
            		JSONObject usd=JSONObject.parseObject(json.get("USD").toString());
            		if(usd.get("BOC")!=null){ 
            			JSONObject boc=JSONObject.parseObject(usd.get("BOC").toString());
            			if(boc.get("middle")!=null){ 
            				Exchangerate  exchangerate=(Exchangerate) UniObject.DBObjectToObject(db, Exchangerate.class);
            	    		exchangerate.set_id(Long.parseLong(db.get("_id").toString())); 
            	    		exchangerate.setValue(BaseDecimal.division(boc.get("se_buy").toString(), "100", 6));
            	    		exchangerate.setUpddate(new Date());
            	    		baseDao.insert(PubConstants.PUB_EXCHANGERATE, exchangerate);
            	    		return BaseDecimal.division(boc.get("middle").toString(), "100", 6);
            			}
            		}
            	};
        	}
    		
    	}else{
    		//更新汇率 
    		String str=HttpClient.sendGet(SysConfig.getProperty("cny_price_api")); 
    		if(StringUtils.isNotEmpty(str)){
        		JSONObject json=JSONObject.parseObject(str);
        		if(json.get("result")!=null){
        			json=JSONObject.parseObject(json.get("result").toString());
        		} 
            	if(json.get("USD")!=null) { 
            		JSONObject usd=JSONObject.parseObject(json.get("USD").toString());
            		if(usd.get("BOC")!=null){ 
            			JSONObject boc=JSONObject.parseObject(usd.get("BOC").toString());
            			if(boc.get("middle")!=null){ 
            				Exchangerate  exchangerate=new Exchangerate();
            				exchangerate.set_id(mongoSequence.currval(PubConstants.PUB_EXCHANGERATE)); 
            				exchangerate.setType("USD_CNY");
            	    		exchangerate.setValue(BaseDecimal.division(boc.get("se_buy").toString(), "100", 6));
            	    		exchangerate.setUpddate(new Date());
            	    		baseDao.insert(PubConstants.PUB_EXCHANGERATE, exchangerate);
            	    		return BaseDecimal.division(boc.get("middle").toString(), "100", 6);
            			}
            		}
            	};
        	}
    		
    	} 
    	
		return "6.5"; 
    }
    
    /**
     * 修改当前用户小区兑换矿机量
     * @param custid
     * @param fromUserid
     * @param num
     * @return
     * @throws Exception
     */
    public boolean changeKjArea(String custid,String fromUserid,int num) throws Exception {
    	if(num > 0){
    		HashMap<String, Object>whereMap = new HashMap<>();
        	whereMap.put("custid", custid);
        	whereMap.put("fromUserid", fromUserid);
        	DBObject dbObject = baseDao.getMessage(PubConstants.SUC_KJAREARECORD, whereMap);
        	String total = BaseDecimal.multiplication(num+"", "200000");
        	KjAreaRecord record = new KjAreaRecord();
        	if(dbObject != null){
        		record=(KjAreaRecord) UniObject.DBObjectToObject(dbObject, KjAreaRecord.class); 
        		record.setValue(BaseDecimal.add(record.getValue(), total));
        	}else{
        		record.set_id(mongoSequence.currval(PubConstants.SUC_KJAREARECORD));
        		record.setCustid(custid);
        		record.setFromUserid(fromUserid);
        		record.setValue(total);
        	}
        	try {
				baseDao.insert(PubConstants.SUC_KJAREARECORD, record);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
		return false;
    }
    /**
     * 查询当前小区已经兑换了多少矿机
     * @param custid
     * @param fromUserid
     * @return
     */
    public String ownerKjAreaRecord(String custid,String fromUserid){
    	HashMap<String, Object>whereMap = new HashMap<>();
    	whereMap.put("custid", custid);
    	whereMap.put("fromUserid", fromUserid);
    	DBObject dbObject = baseDao.getMessage(PubConstants.SUC_KJAREARECORD, whereMap);
    	if(dbObject != null){
    		if(dbObject.get("value") != null){
    			return dbObject.get("value").toString();
    		}
    	}
    	return "0";
    }
    /**
     * 添加提币记录
     */
    public boolean  addTbjl(String fromUserid){
    	String date=DateFormat.getDate();
    	DBObject db=baseDao.getMessage(PubConstants.INTE_TBJL, fromUserid+"-"+date);
    	if(db!=null){
    		InteTxjl inteTxjl=new InteTxjl();
    		inteTxjl.set_id(fromUserid+"-"+date);
    		inteTxjl.setCreatedate(new Date());
    		inteTxjl.setState(1);
    		inteTxjl.setFromUserid(fromUserid);
    		baseDao.insert(PubConstants.INTE_TBJL, inteTxjl);
    		return true;
    	}
    	
		return false;
    	
    }
    /**
     * 获取当天是否提币
     * @param id
     * @return
     */
    public boolean  getTbjl(String id){
    	DBObject db=baseDao.getMessage(PubConstants.INTE_TBJL,id);
    	if(db!=null&&db.get("state").toString().equals("1")){ 
    		return true;
    	}
		return false; 
    }


}
