<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
		<meta charset="utf-8" />
		<title>行情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" href="${ctx}/xmMobile/css/index.css" />
	</head>
	<body>
		<%-- <div class="top">
			<a href="javascript:void(0)" style="float: left;"><img src="${ctx}/xmMobile/img/gohome.png" alt="" /></a>
			行情
			<img class="serch_img" style="float: right;" src="${ctx}/xmMobile/img/serch.png" alt="" />
		</div>
		
		<div class="serch">
			<input type="text" placeholder="币种查询"/>
			<button></button>
		</div> --%>
		<!-- <script type="text/javascript">
			var serch_img=document.getElementsByClassName("serch_img")[0];
			var serch=document.getElementsByClassName("serch")[0]
			serch_img.onclick=function(){
				serch.style.display="block"
			}
		</script> -->
		
		<!--修改后的-->
		<div class="zichan">
			<p><span>资产名称</span><span>今日交易额</span><span style="padding-right:20px">方式</span></p>
			<div  style="clear: both;"></div>
			<p><span><img src="${ctx}/xmMobile/img/left2.png" alt="" />盼盼币</span><span>
			
			<c:if test="${jf == null }">0.00</c:if>
			<c:if test="${jf != null }">${jf.uservalue}</c:if>
			</span><span><img src="${ctx}/xmMobile/img/jiaoyi.png" alt="" /></span></p>
			<p><span><img src="${ctx}/xmMobile/img/left2.png" alt="" />乐乐币</span><span>
			
			<c:if test="${jf == null }">0.00</c:if>
			<c:if test="${jf != null }">${jf.llkyvalue}</c:if>
			</span><span><img src="${ctx}/xmMobile/img/jiaoyi.png" alt="" /></span></p>
		</div>
	</body>
</html>
