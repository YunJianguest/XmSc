package com.lsp.shop.mobile;


import com.lsp.jwt.filter.SignFilter;
import com.lsp.jwt.config.Constant;
import com.lsp.jwt.config.TokenTool;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.SpringSecurityUtils;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.user.entity.UserInfo;
import com.lsp.user.service.LoginService;
import com.mongodb.DBObject;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired; 
/***
 * 登录管理
 * @author lsp
 *
 */
@Namespace("/wap")
@Results({@org.apache.struts2.convention.annotation.Result(name="reload", location="login.action", type="redirect")})
public class LoginAction extends ActionSupport
{
  private static final long serialVersionUID = 1L;
  public static final String RELOAD = "reload";

  @Autowired
  private LoginService login;
  
  @Autowired
  private BaseDao baseDao;

  public String execute()throws Exception{    

    return "success";
  }

  public String error() throws Exception {
    addActionMessage("账号或密码错误");
    return "reload";
  }

  public String logout() throws Exception {
    try {
      Struts2Utils.getSession().invalidate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "ok";
  }

  public String expired() throws Exception {
    return "expired";
  }

  public String  move(){	  
	return "move";   
  }
  /**
   * 登录 
   * @throws Exception
   */
  /*public void  ajaxlogin() throws Exception{
	  HashMap<String, Object> whereMap = new HashMap<String, Object>();
	  Map<String, Object> sub_map = new HashMap<String, Object>(); 
	  
	  String password =Struts2Utils.getParameter("password");
  	  String name =Struts2Utils.getParameter("name");  
  	  
  	  if (StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(password)) {
  		   DBObject user = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
  		   
  		   //当前密码未验证
  		   
  		   if(user != null){
  			   String result=TokenTool.createJWT(user.get("_id").toString(),user.get("account").toString(), Constant.JWT_TTL);
  			   sub_map.put("token", result);
  			   sub_map.put("user", user);
  			   SignFilter.printNoCheck(sub_map);
  		   }
  	  }
  	  SignFilter.printNoCheck(null);
  }*/
  
  public void  ajaxlogin() throws Exception{
	  HashMap<String, Object> whereMap = new HashMap<String, Object>();
	  Map<String, Object> sub_map = new HashMap<String, Object>(); 
	  
	  String password =Struts2Utils.getParameter("password");
  	  String name =Struts2Utils.getParameter("name");  
  	  System.out.println(password);
  	  System.out.println(name);
  	  whereMap.put("account", name);
  	  if (StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(password)) {
  		   DBObject user = baseDao.getMessage(PubConstants.USER_INFO, whereMap);
  		   
  		   //当前密码未验证
  		   System.out.println(user.get("password").toString());
  		   System.out.println(user.get("account").toString());
  		   if(user != null){
  			   if(user.get("password").toString().equals(password)) {
	  			   String result=TokenTool.createJWT(user.get("_id").toString(),user.get("account").toString(), Constant.JWT_TTL);
	  			   System.out.println(result);
	  			   sub_map.put("token", result);
	  			   sub_map.put("user", user);
	  			   SignFilter.printNoCheck(Struts2Utils.getRequest(),Struts2Utils.getResponse(),sub_map);
  			   }
  		   }
  	  }
  	  SignFilter.printNoCheck(Struts2Utils.getRequest(),Struts2Utils.getResponse(),null);
  }

  
 
}