<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html class="ui-page-login">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link href="${ctx}/xmMobile/css/common.css" rel="stylesheet" />
		<style>
			.ui-page-login,
			body {
				width: 100%;
				height: 100%;
				margin: 0px;
				padding: 0px;
			}
			
			.mui-content {
				height: 100%;
				background: #fff;
			}
			
			.mui-input-group {
				padding: 0 30px;
			}
			
			.mui-input-group::after,
			.mui-input-group::before {
				display: none;
			}
			
			.mui-input-group label {
				width: 25%;
				font-size: 14px;
				color: #020202;
				padding:11px 0 !important;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 75%;
			}
			
			.mui-content-padded {
				margin-top: 30px;
				padding: 0 30px;
				margin: 0;
			}
			
			.mui-btn {
				padding: 0px;
				border-radius: 10px;
				height: 34px;
				line-height: 34px;
				background: #e4393c;
				color: #fff;
				font-size: 16px;
			}
			
			.link-area {
				display: flex;
				margin-top:0;
				justify-content: space-between;
				font-size: 12px;
			}
			
			.link-area a {
				color: #000;
			}
			
			.oauth-area {
				/*position: absolute;
				bottom: 20px;
				left: 0px;*/
				text-align: center;
				width: 100%;
				padding: 0px;
				margin: 0px;
				display: flex;
				justify-content: center;
			}
			
			.oauth-area .oauth-btn {
				display: inline-block;
				width: 50px;
				height: 50px;
				background-size: 30px 30px;
				background-position: center center;
				background-repeat: no-repeat;
				margin: 0px 20px;
				/*-webkit-filter: grayscale(100%); */
				border: solid 1px #ddd;
				border-radius: 50%;
				border: none;
			}
			
			/*.oauth-area .oauth-btn:active {
				border: solid 1px #aaa;
			}
			*/
			.oauth-area .oauth-btn.disabled {
				background-color: #ddd;
			}
			
			.login-logo {
				width: 100%;
				height: 150px;
				display: flex;
				justify-content: center;
				align-items: center;
			}
			
			.login-logo img {
				width: 104px;
				height: 104px;
			}
			
			.title-txt {
				position: relative;
				display: -webkit-box;
				/*margin: 14px 30px;*/
				font-size: 14px;
				-webkit-box-pack: center;
			}
			
			.title-txt::after {
				content: '';
				width: 100%;
				height: 1px;
				position: absolute;
				top: 50%;
				left: 0;
				background: #E1DFDD;
			}
			
			.title-txt .title-layout {
				background-color: #fff;
				z-index: 10;
				position: relative;
				display: block;
				padding: 0 10px;
			}
			.wx-icon{
				background: url('${ctx}/xmMobile/images/weixin.png') no-repeat; 
				/*border: none;*/
			}
			.mui-input-group .mui-input-row:after {
			    position: absolute;
			    right: 0;
			    bottom: 0;
			    left: 0px;
			    height: 1px;
			    content: '';
			    -webkit-transform: scaleY(.5);
			    transform: scaleY(.5);
			    background-color: #c8c7cc;
			}
			.mui-btn-block {
			    margin-bottom: 0px !important;
			}
		</style>
	</head>

	<body>
		 <header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">登录</h1>
		</header> 
		<div class="mui-content">
			<div class="login-logo">
				<img src="${ctx}/xmMobile/img/icon/icon-login-logo.png"/>
			</div>
			<form id='login-form' class="mui-input-group">
				<div class="mui-input-row">
					<label>账号</label>
					<input id='tel' type="text" class="mui-input-clear mui-input" placeholder="请输入账号">
				</div>
				<div class="mui-input-row">
					<label>密码</label>
					<input id='password' type="password" class="mui-input-clear mui-input" placeholder="请输入密码">
				</div>
			</form>
			
			<div class="mui-content-padded">
				<button id='login' class="mui-btn mui-btn-block" onclick="login()">登录</button>
				<div class="link-area">
					<!--<a id='reg' href="${ctx}/integral/miners!signup.action">注册</a>-->
					<a id='forgetPassword' href="${ctx}/integral/miners!forgetpw.action">忘记密码</a>
				</div>
			</div>
			<div class="mui-content-padded">
				<div class="title-txt">
					<span class="title-layout">
						<span class="title-txt-txt">其他方式登录</span>
					</span>
				</div>
			</div>
			<div class="mui-content-padded oauth-area" style="margin-top: 50px;">
				<button class="oauth-btn wx-icon"></button>
				
			</div>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/xmMobile/js/app.js"></script>
		<script type="text/javascript" >
		function login(){
			
			var  tel=$('#tel').val();
			var  password=$('#password').val();
			if(tel.length==0||password.length==0){
				alert("请输入账号或密码");
				return;
			}
	            jQuery.ajax({  
	            url:"${ctx}/user/fromuser!signin.action",
	            dataType:"json",
	            data:"tel="+tel+"&password="+password,
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	               alert("登录失败！");
				},
	            success:function(json){
	            	if(json.state==0){
	            		window.location.href ="${ctx}/integral/miners!list.action?lscode="+json.lscode;
	            	}else if(json.state == 1){
						alert('请求超时，重新登录');
					}else if(json.state == 2){
						alert('用户不存在');
					}else if(json.state == 3){
						alert('密码错误');
					}
	            }
	        });
		} 
		</script>
	</body>

</html>