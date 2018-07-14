<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>币兑换</title>
		<link rel="stylesheet" href="${ctx}/xmMobile/css/mui.min.css" />
		<script src="${ctx}/xmMobile/js/mui.min.js"></script><link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<style>
			* {
				margin: 0;
				padding: 0;
				box-sizing: border-box;
			}
			
			html {
				width: 100%;
				height: 100%;
			}
			
			body {
				width: 100%;
				height: 100%;
				background: #f1eff1;
			}
			
			li {
				list-style: none;
			}
			
			a {
				text-decoration: none;
			}
			
			.top {
				width: 100%;
				height: auto;
				overflow: hidden;
			}
			
			.top>img {
				width: 100%;
			}
			
			.content {
				width: 100%;
				height: auto;
				overflow: hidden;
				padding: 0 10px;
				margin-top: 20px;
			}
			
			.content>ul {
				width: 100%;
				height: auto;
				overflow: hidden;
			}
			
			.content>ul>li {
				width: 100%;
				line-height: 20px;
				font-size: 12px;
				font-family: "微软雅黑";
				text-align: left;
				overflow: hidden;
				text-overflow: ellipsis; 
				display: -webkit-box;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
				margin-bottom: 10px;
			}
			
			.btnClass {
				width: 50%;
				height: 40px;
				font-size: 16px;
				font-family: "微软雅黑";
				font-weight: bold;
				color: #fff;
				margin: 20px 0 0 25%;
				border-radius: 20px;
				border: none;
				background: linear-gradient(#edeb21, #f39547, #fecb12);
			}
		</style>
	</head>

	<body>
		<div class="top"><img src="${ctx}/xmMobile/img/banner1.jpg" alt="" /></div>
		<div class="content">
			<ul>
				<li>中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！</li>
				<li>中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！</li>
				<li>中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！</li>
				<li>中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！</li>
				<li>中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！中华人民共和国领土神圣不可侵犯；侵我中华者，虽远必诛！！</li>
			</ul>
		</div>
		<button class="btnClass" onclick="exchange()">立即兑换></button>
		<script type="text/javascript">
			function exchange() {
				var address="${address}";
				var btn = ["取消", "确认"];
				var flag = mui.confirm("是否进行兑换", "兑换提示", btn, function(e) {
					if(e.index==1){
						$.ajax({
							type:"post",
							url:"${ctx}/integral/miners!dhZsb.action",
							async:true,
							data:{
								address:address
							},
							success:function(json){
								if(json.state == 0){
									alert("兑换成功");
								}else if(json.state == 1){
									alert("抱歉兑换过程异常");
								}else if(json.state == 2){
									alert("服务器未开通该功能");
								}
								else if(json.state == 3){
									alert("余额不足");
								}else if(json.state == 4){
									alert("未选择所属县域");
								}
							}
						});
						
					}
					  
				})
			}
		</script>
	</body>
</html>