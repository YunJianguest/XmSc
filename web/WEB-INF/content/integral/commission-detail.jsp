<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>账单详情</title>
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		 <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
         <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
		<style>
			*{
				margin:0;
				padding:0;
				box-sizing:border-box;
			}
			a{
				text-decoration:none;
			}
			li{
				list-style:none;
			}
			body{
				font-size:14px;
				color:#555555;
				font-family: "微软雅黑";
				background: #fff;
				padding: 20px 25px;
			}
			.details{
				width: 100%;
				height:auto;
				overflow: hidden;
				text-align: center;
				border-bottom: 1px solid #eee;
				padding-bottom: 30px;
			}
			.details>p{
				margin: 5px 0;
			}
			.details>img{
				width: 50px;
			}
			.details_list{
				width: 100%;
				height:auto;
				overflow: hidden;
			}
			.details_list>p{
				width: 100%;
				line-height: 30px;
				color:#aaa;
			}
			.details_list>p>span{
				text-align: left;
				margin-left: 15px;
				color:#333;
			}
		</style>
	</head>
	<body>
		<div class="details">
			<img src="http://pic29.photophoto.cn/20131229/0020033084153379_b.jpg" alt="" />
			<p><span>熊猫商城提现</span>-到<span>
			<c:if test="${dbObject.type == 1}">
			${dbObject.yname}(${dbObject.account})
			</c:if>
			<c:if test="${dbObject.type == 2}">
			支付宝(${dbObject.account})
			</c:if>
			</span></p>
			<h4><fmt:formatNumber value='${dbObject.price}' pattern="0.00"/></h4>
		</div>
		<div class="details_list">
			<p>当前状态
			<c:if test="${dbObject.state == 0}"><span>待审批</span></c:if>
            <c:if test="${dbObject.state == 1}"><span>审批通过，等待打款</span></c:if>
            <c:if test="${dbObject.state==2}"><span>审批通过</span></c:if>
			<c:if test="${dbObject.state==3}"><span style="color:#F00">已打款</span></c:if>
			</p>
			<p>提现金额<span><fmt:formatNumber value='${dbObject.price}' pattern="0.00"/></span></p>
			<p>手&nbsp;&nbsp;续&nbsp;&nbsp;费<span><fmt:formatNumber value='${dbObject.cost}' pattern="0.00"/></span></p>
			<p>申请时间<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dbObject.createdate}" /></span></p>
			<c:if test="${dbObject.state <= 3 && dbObject.state >= 1}">
			<p>审核时间<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dbObject.updatedate}" /></span></p>
			</c:if>${dbObject.state}
			<c:if test="${dbObject.state == 3}">
			<p>打款时间<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dbObject.confirmdate}" /></span></p>
			</c:if>
			<c:if test="${dbObject.type == 1}">
			<p>提现银行<span>${dbObject.yname}</span></p>
			</c:if>
			<p>提现单号<span>${dbObject._id}</span></p>
		</div>
		<%@include file="/webcom/shop-foot.jsp" %>
	</body>
</html>
