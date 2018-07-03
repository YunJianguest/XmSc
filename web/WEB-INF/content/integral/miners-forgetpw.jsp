<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>忘记密码</title>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link href="${ctx}/xmMobile/css/style.css" rel="stylesheet" />
		<link href="${ctx}/xmMobile/css/common.css" rel="stylesheet" />
		<style>
			.area {
				margin: 20px auto 0px auto;
			}
			
			.mui-input-group::before,
			.mui-input-group::after {
				display: none;
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
				margin-top: 25px;
			}
			
			.mui-btn {
				padding: 10px;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				margin-top: 1px;
			}
			
			.mui-btn.verBtn {
				width: 100px;
				position: absolute;
				right: 0;
				top: 8px;
				padding: 5px 10px;
			}
			
			#sendNewpwd {
				padding: 0px;
				border-radius: 10px;
				height: 34px;
				line-height: 34px;
				background: #E4393C;
				color: #fff;
				font-size: 16px;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">找回密码</h1>
		</header>
		<div class="mui-content" style="background: #fff;padding: 0 30px;padding-top: 30px;">
			<form class="mui-input-group">

				<div class="mui-input-row">
					<label>手机号</label>
					<input id='phone' type="tel" minlength='11' maxlength="11" class="mui-input-clear mui-input" placeholder="请输入手机号">
				</div>
				<div class="mui-input-row" style="position: relative;">
					<label>验证码</label>
					<input id="verCode" type="text" class="mui-input-clear mui-input" placeholder="请输入验证码" />
					<span class="mui-btn mui-btn-grey verBtn">获取验证码</span>
				</div>
				<div class="mui-input-row">
					<label>新密码</label>
					<input type="password" id="password" class="mui-input-clear mui-input" placeholder="请输入新密码" />
				</div>
			</form>
			<div class="mui-content-padded">
				<button id='sendNewpwd' class="mui-btn mui-btn-block"  data-loading-icon= "mui-spinner mui-spinner-custom">提交</button>
			</div>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script>
			$(function() {
				var reg =/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
				$('#phone').blur(function() {
					if($(this).val() == '') {
						mui.alert('请输入手机号', )
					} else if(!reg.test($(this).val())) {
						mui.alert('手机号码不正确')
					}
				})
				$('.verBtn').click(function() {
					var count = 60;
					var timer;
					if($('#phone').val() == '') {
						mui.alert('请输入手机号', )
					} else if(!reg.test($('#phone').val())) {
						mui.alert('手机号码不正确')
					} else {
						function countDown() {
							if(count == 0) {
								clearInterval(timer);
								$('.verBtn').removeAttr('disabled', true);
								$('.verBtn').html('重新发送');
							} else {
								
								$('.verBtn').removeAttr('disabled', false);
								$('.verBtn').html(count + 's');
								count--;
								console.log(count)
							}
						}
						var timer = setInterval(countDown, 1000);
						$.ajax({
							type: "post",
							url: "${ctx}/user/fromuser!createTelCode.action",
							async: true,
							data: {
								tel: $('#phone').val()
							},
							success: function(json) {

							}
						});
					}
				});
				
				$('#sendNewpwd').click(function(){
					if ($('#phone').val() == '' && $('#verCode').val() == '' && $('#password').val()=='') {
						mui.alert('手机号验证码密码不能为空')
					} else{
						mui($(this)).button('loading');
						$.ajax({
							type:'post',
							url:'${ctx}/user/fromuser!changepw.action',
							data:{
								tel:$('#phone').val(),
								yzcode:$('#verCode').val(),
								password:$('#password').val()
							},
							success:function(json){
								if (json.state  == 0) {
									location.href='${ctx}/login!signin.action'
								}else if(json.state  == 1){
									mui.alert('操作失败，请重新操作')
								}else if(json.state  == 2){
									mui.alert('该账户不存在')
								}else if(json.state  == 3){
									mui.alert('密码错误')
								}else if(json.state  == 4){
									mui.alert('验证码错误')
								}else if(json.state  == 5){
									mui.alert('验证码超时');
								}
							}
						})
					}
					
				})
			})
		</script>
	</body>

</html>