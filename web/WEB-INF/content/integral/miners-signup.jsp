<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html class="ui-page-login">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>注册</title>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link href="${ctx}/xmMobile/css/style.css" rel="stylesheet" />
		<style>
			.area {
				margin: 20px auto 0px auto;
			}
			
			.mui-input-group:first-child {
				/*margin-top: 20px;*/
			}
			
			.mui-input-group label {
				width: 25%;
				font-size: 15px;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 75%;
			}
			
			.mui-input-row label~input::-webkit-input-placeholder {
				font-size: 12px;
			}
			
			.mui-checkbox input[type=checkbox],
			.mui-radio input[type=radio] {
				top: 6px;
			}
			
			.mui-content-padded {
				margin-top: 45px;
			}
			
			#reg.mui-btn {
				padding: 0px;
				border-radius: 17px;
				height: 34px;
				line-height: 34px;
				background: #F7941D;
				color: #fff;
				font-size: 16px;
			}
			
			.mui-btn.verBtn {
				width: 100px;
				position: absolute;
				right: 0;
				top: 8px;
				padding: 5px 10px;
			}
			
			.mui-popup-title {
				color: #000;
				padding: 32px 0 36px 0;
			}
			
			.mui-row::after,
			.mui-row::before,
			.mui-input-group::before,
			.mui-input-group::after {
				display: none;
			}
		</style>
	</head>

	<body>

		<div class="mui-content" style="background: #fff;padding: 0 16px;">
			<div class="mui-row">
				<p class="mui-popup-title">注册</p>
			</div>
			<form class="mui-input-group">
				<div class="mui-input-row">
					<label>手机号</label>
					<input id="phone" type="text" class="mui-input-clear mui-input" minlength='11' maxlength="11" placeholder="请输入手机号码" />
				</div>
				<div class="mui-input-row" style="position: relative;">
					<label>验证码</label>
					<input id="verCode" type="text" class="mui-input-clear mui-input" placeholder="请输入验证码" />
					<span class="mui-btn mui-btn-grey verBtn">获取验证码</span>
				</div>
				<div class="mui-input-row">
					<label>密码</label>
					<input id='password' type="password" class="mui-input mui-input-password" placeholder="请输入密码">
				</div>
				<div class="mui-input-row">
					<label>确认</label>
					<input id='password_confirm' type="password" class=" mui-input mui-input-password" placeholder="请确认密码">
				</div>

			</form>
			<div class="mui-content-padded">
				<button id='reg' class="mui-btn mui-btn-block " data-loading-icon= = "mui-spinner mui-spinner-custom">注册</button>
			</div>
			<div class="mui-content-padded">

			</div>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script>
			$(function() {

				var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
				$('#phone').blur(function() {
					if($(this).val() == '') {
						mui.alert('请输入手机号')
					} else if(!reg.test($(this).val())) {
						mui.alert('手机号码不正确')
					}
				})
				$('.verBtn').click(function() {
					var count = 60;
					var timer;
					if($('#phone').val() == '') {
						mui.alert('请输入手机号')
					} else if(!reg.test($('#phone').val())) {
						mui.alert('手机号码不正确')
					} else {
						function countDown() {
							if(count == 0) {
								clearInterval(timer);
								$('.verBtn').removeAttr('disabled', true);
								$('.verBtn').html('重新发送');
							} else {
								count--;
								$('.verBtn').removeAttr('disabled', false);
								$('.verBtn').html(count + 's');

							}
						}
						var timer = setInterval(countDown, 1000);
						$.ajax({
							type:"post",
							url:"${ctx}/user/fromuser!createTelCode.action",
							async:true,
							data:{
								tel:$('#phone').val()
							},
							success:function(json){
								/* if(json.state == 0){
									countDown();
								}else{
									alert('操作失败');
								} */
							}
						});
					}
				});
				$('#password').blur(function(){
					pwd($(this).val())
				});
				$('#password_confirm').blur(function(){
					if ($(this)) {
						pwd($(this).val())
					}else if($('#password_confirm').val() !=$('#password').val() ){
						mui.alert('两次输入的密码不一致')
					}
				});
				function pwd(val){
					var pwdVal = val;
					if (pwdVal == '') {
						mui.alert('密码不能为空');
					}
				}
				$('#reg').click(function(){
					mui($(this)).button('loading');
					$.ajax({
						type:"post",
						url:"${ctx}/user/fromuser!signup.action",
						async:true,
						data:{tel:$('#phone').val(),password:$('#password').val(),yzcode:$('#verCode').val()},
						success:function(json){
							if(json){
								if(json.state == 0){
									location.href='${ctx}/integral/miners!list.action?lscode='+json.lscode;
								}else if(json.state == 1){
									alert('请求超时，重新注册');
								}else if(json.state == 2){
									alert('用户不存在');
								}else if(json.state == 3){
									alert('验证码输入错误');
								}else if(json.state == 4){
									alert('验证码超时');
								}
								
							}else{
								mui($(this)).button('reset');
							}
						}
					});
				})
				

			})
			
			
		</script>
	</body>

</html>