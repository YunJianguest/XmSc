package com.lsp.jwt.filter; 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import com.lsp.jwt.config.CheckResult;
import com.lsp.jwt.config.Constant;
import com.lsp.jwt.config.ResponseMsg;
import com.lsp.jwt.config.TokenTool;
import com.lsp.jwt.util.JsonUtil;
import com.lsp.pub.util.Struts2Utils;

import io.jsonwebtoken.Claims; 
 
public class SignFilter{
 /**
  * 验证签名
  * @param request
  * @param response
  * @return
  * @throws IOException
  */
	public static Claims checkSign() throws IOException {
		String tokenStr=Struts2Utils.getRequest().getParameter("token");
    	CheckResult checkResult = TokenTool.validateJWT(tokenStr);
    	if (checkResult.isSuccess()) {  
    		return checkResult.getClaims();
		}else{
			switch (checkResult.getErrCode()) { 
			case Constant.JWT_ERRCODE_EXPIRE:
				PrintWriter printWriter = Struts2Utils.getResponse().getWriter();
				printWriter.print(ResponseMsg.loginExpire());
				printWriter.flush();
				printWriter.close();
				break; 
			case Constant.JWT_ERRCODE_FAIL:
				PrintWriter printWriter2 = Struts2Utils.getResponse().getWriter();
				printWriter2.print(ResponseMsg.noAuth());
				printWriter2.flush();
				printWriter2.close();
				break;
			default:
				break;
			}
		}
		return null; 
	}
	/**
	 * 验证签名并发送数据
	 * @throws IOException 
	 */
	public static boolean  print(HttpServletRequest request, HttpServletResponse response,Object data) throws IOException {
		String tokenStr=request.getParameter("token");
    	CheckResult checkResult = TokenTool.validateJWT(tokenStr);
    	if (checkResult.isSuccess()) { 
    		PrintWriter printWriter = response.getWriter();
    		printWriter.print(ResponseMsg.successWithData(data));
			printWriter.flush();
			printWriter.close();
    		return true;
		}else{
			switch (checkResult.getErrCode()) { 
			case Constant.JWT_ERRCODE_EXPIRE:
				PrintWriter printWriter = response.getWriter();
				printWriter.print(ResponseMsg.loginExpire());
				printWriter.flush();
				printWriter.close();
				break; 
			case Constant.JWT_ERRCODE_FAIL:
				PrintWriter printWriter2 = response.getWriter();
				printWriter2.print(ResponseMsg.noAuth());
				printWriter2.flush();
				printWriter2.close();
				break;
			default:
				break;
			}
		}
		return false;  
		
	}
	/**
	 * 验证签名并发送数据
	 * @throws IOException 
	 */
	public static boolean  print(Object data) throws IOException {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		String tokenStr=request.getParameter("token");
    	CheckResult checkResult = TokenTool.validateJWT(tokenStr);
    	if (checkResult.isSuccess()) {
    		Map<String, Object>map=new HashMap<>();
    		map.put("data", data);
    		PrintWriter printWriter = response.getWriter();
			printWriter.print(JsonUtil.objectToJsonStr(ResponseMsg.successWithData(map)));
			printWriter.flush();
			printWriter.close();
    		return true;
		}else{
			switch (checkResult.getErrCode()) { 
			case Constant.JWT_ERRCODE_EXPIRE:
				PrintWriter printWriter = response.getWriter();
				printWriter.print(ResponseMsg.loginExpire());
				printWriter.flush();
				printWriter.close();
				break; 
			case Constant.JWT_ERRCODE_FAIL:
				PrintWriter printWriter2 = response.getWriter();
				printWriter2.print(ResponseMsg.noAuth());
				printWriter2.flush();
				printWriter2.close();
				break;
			default:
				break;
			}
		}
		return false;  
		
	}

	/**
	 * 发送数据
	 * 
	 * @throws IOException
	 */
	public static void printNoCheck1(Object data) throws IOException {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		
	 
		PrintWriter printWriter2 = response.getWriter();
		printWriter2.print(JsonUtil.objectToJsonStr(data));
		printWriter2.flush();
		printWriter2.close();

	}
	/**
	 * 发送数据
	 * 
	 * @throws IOException
	 */
	public static void printNoCheck(Object data) throws IOException {
		
		PrintWriter printWriter2 = Struts2Utils.getResponse().getWriter();
		
		printWriter2.print(JsonUtil.objectToJsonStr(data));
		printWriter2.flush();
		printWriter2.close();

	}
	/**
	 * 发送数据
	 * 
	 * @throws IOException
	 */
	public static void printNo(HttpServletRequest request, HttpServletResponse response,Object data) throws IOException {
		
		PrintWriter printWriter2 = response.getWriter();
		
		printWriter2.print(ResponseMsg.successWithData(data));
		printWriter2.flush();
		printWriter2.close();

	}

}
