<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>账单详情</title>
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
			<p><span>熊猫商城提现</span>-到<span>建设银行（7766）</span></p>
			<h4>100.10</h4>
		</div>
		<div class="details_list">
			<p>当前状态<span>待处理</span></p>
			<p>提现金额<span>100.10</span></p>
			<p>手&nbsp;&nbsp;续&nbsp;&nbsp;费<span>10.01</span></p>
			<p>申请时间<span>2018-07-2317：31:21</span></p>
			<p>到账时间<span>2018-07-2317：56:21</span></p>
			<p>提现银行<span>建设银行</span></p>
			<p>提现单号<span>778852166554892123232</span></p>
		</div>
	</body>
</html>
