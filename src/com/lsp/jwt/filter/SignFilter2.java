package com.lsp.jwt.filter;

import io.jsonwebtoken.Claims;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsp.user.entity.UserInfo;
import com.lsp.jwt.config.CheckResult;
import com.lsp.jwt.config.Constant;
import com.lsp.jwt.config.ResponseMsg;
import com.lsp.jwt.config.TokenTool;
import com.lsp.jwt.util.JsonUtil; 
 
public class SignFilter2 implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
		HttpServletResponse httpServletResponse = (HttpServletResponse) arg1;
		
		
		String tokenStr = httpServletRequest.getParameter("token");
		if (tokenStr == null || tokenStr.equals("")) {
			PrintWriter printWriter = httpServletResponse.getWriter();
			printWriter.print(ResponseMsg.err());
			printWriter.flush();
			printWriter.close();
			return;
		}
		 
		CheckResult checkResult = TokenTool.validateJWT(tokenStr);
		if (checkResult.isSuccess()) {
			Claims claims = checkResult.getClaims();
			UserInfo model = JsonUtil.jsonStrToObject(claims.getSubject(), UserInfo.class);
			httpServletRequest.setAttribute("tokensub", model);
			httpServletRequest.getRequestDispatcher("/success.jsp").forward(httpServletRequest, httpServletResponse);
		} else {
			switch (checkResult.getErrCode()) { 
			case Constant.JWT_ERRCODE_EXPIRE:
				PrintWriter printWriter = httpServletResponse.getWriter();
				printWriter.print(ResponseMsg.loginExpire());
				printWriter.flush();
				printWriter.close();
				break; 
			case Constant.JWT_ERRCODE_FAIL:
				PrintWriter printWriter2 = httpServletResponse.getWriter();
				printWriter2.print(ResponseMsg.noAuth());
				printWriter2.flush();
				printWriter2.close();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	//验证签名
	public static boolean checkSign(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tokenStr=request.getParameter("token");
    	CheckResult checkResult = TokenTool.validateJWT(tokenStr);
    	if (checkResult.isSuccess()) {
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

}
