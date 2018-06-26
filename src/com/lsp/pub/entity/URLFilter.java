package com.lsp.pub.entity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 拦截器
 * @author lsp
 *
 */
public class URLFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		    HttpServletRequest request = (HttpServletRequest) servletRequest;
	        String requestURI = request.getRequestURI(); 
	        System.out.println(requestURI);
	        String serverName = request.getServerName().toLowerCase();
	        String realURI = getRealRequestURI(serverName, requestURI);  
	        	//request.getRequestDispatcher(realURI).forward(request, response);  
	        
	        if(requestURI.indexOf("/wap/")>0){
	        	  HttpServletResponse response1 = (HttpServletResponse) response;
	  	        response1.setContentType("text/json");
	  	        response1.setHeader("Access-Control-Allow-Origin", "*");
	  	        response1.setCharacterEncoding("UTF-8");
	  	        response1.setHeader("Access-Control-Max-Age", "3600");
	  	        response1.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
	        } 
	        filterChain.doFilter(servletRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("拦截器启动...");
		
	}
	 private String getRealRequestURI(String serverName, String requestURI) {
		  
		return requestURI;    
    }

}
