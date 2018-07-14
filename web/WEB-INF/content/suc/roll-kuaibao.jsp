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
		<div class="mui-content">
		    <ul class="mui-table-view" style="margin-top: 0;">
		    	<c:forEach items="${rollList }" var="obj">
			            <li class="mui-table-view-cell">
			                <a class="">
			                    <div class="mui-table">
			                    	<div class="mui-table-cell mui-col-xs-10">
			                    	<c:if test="${not empty obj.url}">
			                    		<h5 class="mui-ellipsis-2" style="color: #000;">
			                    		<a href="${obj.url}" style="text-decoration: underline;">${obj.title }</a>
			                    		</h5>
			                    	</c:if>
			                    	
			                    	<c:if test="${empty obj.url}">
			                    		<h5 class="mui-ellipsis-2" style="color: #000;">
			                    			<a href="javascript:void(0);" style="text-decoration: underline;">
			                    				${obj.title }
			                    			</a>
			                    		</h5>
			                    	</c:if>
			                    		<!-- <p class="mui-h6 mui-ellipsis">Hi，李明明，申请交行信息卡，100元等你拿，李明明，申请交行信息卡，100元等你拿，</p> -->
			                    	</div>
			                    </div>
			                </a>
			            </li>
		            </c:forEach>
		            
		        </ul>
		</div>
	</body>
</html>
