<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>提现记录</title>
		<style>
			*{
				margin: 0;
				padding: 0;
				box-sizing: border-box;
			}
			a{
				text-decoration:none;
			}
			li{
				list-style: none;
			}
			body{
				width: 100%;
				height: 100%;
				background: #eee;
				font-family: "微软雅黑";
				font-size: 14px;
			}
			.record{
				width: 100%;
				line-height: 40px;
				padding: 0 25px;
				background: #fff;
				margin-bottom: 5px;
				text-align: center;
				color:#555;
			}
			.water{
				width: 100%;
				height: 100%;
				padding: 25px;
				background: #fff;
			}
			.water>p{
				width: 100%;
				height: 25px;
				line-height: 25px;
				font-size: 12px;
				color: #777;
			}
			.water>p:first-of-type>span{
				color:#000;
				font-weight: bold;
			}
			.Currency>span,.water>p>span{
				float: left;
			}
			.top>p>span:last-of-type,.Currency>span:last-of-type,.water>p>span:last-of-type,.water>p>span:nth-of-type(2){
				float:right;
			}
			.water>p>span:last-of-type{
			    margin-right: 20px;
			}
			.Currency_list-color{
				color:red;
			}
		</style>
	
	</head>
	<body>
		<p class="record">提现记录</p>
		<div class="water">
			<p><span>日期时间</span><span>金额</span><span>当前状态</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>待处理</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>已到账</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>已到账</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>已到账</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>待处理</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>待处理</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>待处理</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>已到账</span></p>
			<p><span>2018/06/11-10:21:30</span><span>2000</span><span>已到账</span></p>
		</div>
		
	</body>
</html>
