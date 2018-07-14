<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
	
    <style type="text/css">
			.mui-input-group label {
				width: 30%;
				font-size: 12px;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 70%;
			}
			
			.mui-input-row label~input::-webkit-input-placeholder {
				font-size: 12px;
			}
			
			.title-txt {
				position: relative;
				display: -webkit-box;
				margin: 14px 30px;
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
			
			.title-txt-txt {
				padding: 5px 10px;
				background: #fff;
				position: relative;
				display: block;
				z-index: 10;
			}
			
			.mui-btn {
				padding: 0px;
				border-radius: 10px;
				height: 34px;
				line-height: 34px;
				background: #e4393c;
				color: #fff;
				font-size: 16px;
				margin-top: 30px;
			}
		</style>
</head>
<body>
<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/xmMobile/js/mui.min.js"></script>
<script>
		//提交
		function update_submit(){
			console.log('${lscode}');
			var oldpassword = $("#oldpassword").val();
			if(oldpassword==""){//旧密码
				mui.alert('旧密码不能为空')
				return;
			}
			
			var newpassword = $("#newpassword").val();
			if(newpassword==""){//新密码
				mui.alert('新密码不能为空')
				return;
			}
			
			var twopassword = $("#twopassword").val();
			if(twopassword==""){//确认密码
				mui.alert('旧密码不能为空')
				return;
			}
			if(newpassword!=twopassword){
				mui.alert('二次密码不一致')
				return;
			}
			
			$.ajax({
				type:"post",
				url:"${ctx}/user/fromuser!ajaxsafePwd.action",
				async:true,
				data:{
					lscode:'${lscode}',
					oldPwd:oldpassword,
					newPwd:newpassword
				},
				success:function(json){
					console.log(json.state);
					if(json.state==1){
						mui.alert('密码修改成功！')
						location.href='${ctx}/user/fromuser!UserDetail.action?custid=${custid}&agid=&lscode=${lscode}';
					}else if(json.state==0){
						mui.alert('旧密码错误！')
					}
				}
			});
		}
		</script>	
<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">修改密码</h1>
		</header>
		<div class="mui-content" style="background: #fff;padding: 5px 10px;padding-top: 44px;">
			<form class="mui-input-group">
				<div class="mui-input-row">
					<label>旧密码</label>
					<input id="oldpassword" type="password" class="mui-input-password" maxlength="16" id="oldpwd" placeholder="请输入密码">
				</div>
				<div class="mui-input-row">
					<label>新密码</label>
					<input id="newpassword" type="password" class="mui-input-password" maxlength="16" id="newPwd" placeholder="请输入新密码">
				</div>
				<div class="mui-input-row">
					<label>确认密码</label>
					<input id="twopassword" type="password" class="mui-input-password" maxlength="16" id="surePwd" placeholder="确认新密码">
				</div>
				<!-- <div class="title-txt">
					<span class="title-txt-txt">二级安全码</span>
				</div>
				<div class="mui-input-row">
					<label>旧安全码</label>
					<input type="password" class="mui-input-password" maxlength="16" id="oldSafe" placeholder="请输入当前安全码">
				</div>
				<div class="mui-input-row">
					<label>新安全码</label>
					<input type="password" class="mui-input-password" maxlength="16" id="newSafe" placeholder="请输入新安全码">
				</div>
				<div class="mui-input-row">
					<label>确认安全码</label>
					<input type="password" class="mui-input-password" maxlength="16" id="sureSafe" placeholder="确认新安全码">
				</div> -->
			</form>
			<div class="mui-button-row">
				<button class="mui-btn mui-btn-block" onclick="update_submit()">保存密码</button>
			</div>
		</div>
</body>
</html>
