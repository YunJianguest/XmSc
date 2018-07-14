<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>快报列表</title>
		<link rel="stylesheet" href="${ctx}/app/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css"/><link rel="stylesheet" type="text/css" href="${ctx}/app/css/mui.min.css" />
		
		<style type="text/css">
			.mui-table h4{
				line-height: 20px;
				font-size: 500;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
    	    <a class=" mui-icon mui-icon-undo mui-pull-left" style="color: #000;"></a>
    	    <h1 class="mui-title">快报列表</h1>
    	</header>
		<div class="mui-content" style="background: #ececec;">
		    <ul class="mui-table-view" style="margin-top: 0;padding-bottom: 0;">
		    	<c:forEach items="${rollList }" var="obj">
		    	
		    		<li class="mui-table-view-cell">
		    			<c:if test="${not empty obj.url}">
			    			<a class="mui-navigate-right" href="${obj.url}">
			                    <div class="mui-table">
			                    	<div class="mui-table-cell mui-col-xs-10">
			                    		<h5 class="mui-ellipsis-2" style="color: #000;">${obj.title }</h5>
			                    	</div>
			                    </div>
			                </a>
				    	</c:if>
				    	<c:if test="${empty obj.url}">
				    		<a class="mui-navigate-right" href="javascript:void(0);">
			                    <div class="mui-table">
			                    	<div class="mui-table-cell mui-col-xs-10">
			                    		<h5 class="mui-ellipsis-2" style="color: #000;">${obj.title }</h5>
			                    	</div>
			                    </div>
			                </a>
				    	</c:if>
		            </li>
			            
		            </c:forEach>
		            
		        </ul>
		</div>
	</body>
</html>
