<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
		<style type="text/css">
			*{padding: 0;margin: 0;box-sizing: border-box;}
  			html,body{width: 100%;height: 100%;overflow: hidden;background: #fff;}
  			.container{width: 100%;height: 100%;background: url('${ctx}/pc_gw/images/loginbanner.jpg') no-repeat; background-size: 100% 100%;}
  			/*.header{width: 100%;height: 80px;padding: 0 100px;display: flex;align-items: center;position: fixed;}*/
  			/*.header img{width: 145px;border-radius: 50%;margin-left: 30px;margin-top: 10px;}*/
  			.content{width: 100%;height: 100%;margin: 0 auto;padding-top: 150px;}
			.register-form{display:block;width: 448px;height: auto;padding: 30px;position: relative;background: #75BCF3;border-radius: 6px;box-shadow: 1px 2px 3px #ccc;margin: 0 auto;padding-bottom: 10px;}
			.register-form-head{width: 381px;height:60px;position: absolute;top: -12px;left: 30px;color: #fff;background: url('${ctx}/pc_gw/images/logintit.png') no-repeat;background-size: 100% auto;text-align: center;font-size: 18px;line-height: 60px;padding-left: 20px;}
			.register-form-cont{width: 100%;height: auto;line-height: 40px;position: relative;}
			.input-group{width: 100%;height: 40px;line-height: 40px;position: relative;margin-top: 5px;}
			.input-group input{width: 100%;padding: 10px;border-radius: 3px;border: none;outline: none;}
			.input-group span{width: 120px;height: 36px;line-height: 36px;position: absolute;top: 4px;right: 0;background: #214979;border-radius: 3px;color: #fff;text-align: center;font-size: 16px;}
			.btn{width: 100%;height: 40px;line-height: 40px;background: #fff;text-align: center;border: none;border-radius: 3px;color: #636e7b;font-size: 18px;}
			.logos{display: block;float:left;width: 80px;height: 30px;background: url('${ctx}/pc_gw/images/login.png') no-repeat;background-size: 100% auto;margin-right: -10px;position: absolute;left: 70px;top: 15px;}
			.mask{display: none;width:100%;height: 100%;background: rgba(0,0,0,.6);position: fixed;top: 0;left: 0;right: 0;bottom: 0;z-index: 10;}
			.mask-cont{width: 440px;height: 400px;margin: 0 auto;position: absolute;top: 50%;left: 50%;margin-left: -220px;margin-top: -200px;background: #fff;z-index: 10;border-radius: 6px;padding: 10px;}
			.mask-tit{width: 100%;height: 40px;line-height: 40px;text-align: center;font-size: 16px;}
			.mask-body{width: 100%;height: 300px;overflow-y: auto;padding: 10px;position: relative;}
			.mask-body::after,.mask-body::before{content: '';width: 100%;height: 1px;background: #ddd;position: absolute;left: 0;}
			.mask-body::after{top: 0;}
			.mask-body::before{bottom: 0;}
			.mask-foot{width: 100%;height: 40px;text-align: right;}
		</style>
    </head>
    <body>
    	<div class="container">
			<!--<div class="header">
				<img src="${ctx}/pc_gw/images/login.png"/>
			</div>-->
			<div class="content">
				<a href="${ctx}/login.action" style="text-decoration: none;position: absolute;top: 20px;right: 20px;color: #fff;">登录</a>
				<div class="register-form">
					<div class="register-form-head">
						<i class="logos"></i>熊猫商城注册
					</div>
					<div class="register-form-cont">
						<div class="input-group" style="margin-top: 20px;">
							<input type="text" name="" id="nickname" value="" maxlength="10" placeholder="请输入昵称"/>
						</div>
						<div class="input-group">
							<input type="tel" name="" id="tel" value="" placeholder="请输入手机号" maxlength="11"/>
						</div>
						<div class="input-group">
							<input type="text" name="" id="verCode" value="" placeholder="请输入验证码"/>
							<span class="verBtn">获取验证码</span>
						</div>
						<div class="input-group">
							<input type="password" name="" id="password" value="" placeholder="请输入密码"/>
						</div>
						<div class="input-group">
							<input type="password" name="" id="password_confirm" value="" placeholder="请再次输入密码"/>
						</div>
						<div class="input-group" style="margin-top: 20px;">
							<button class="btn" id="reg">注册</button>
						</div>	
					</div>
					<div style="width: 100%;height: 20px;font-size: 12px;text-align: right;margin-top: 10px;cursor: pointer;" >
						<input type="checkbox" name="" id="" value="" /><span id="Agreement">用户协议</span>
					</div>
				</div>
				<div style="font-size: 18px;margin-top: 20px;width: 100%;text-align: center;">
					上合集团&copy;
				</div>
			</div>
			<div class="mask">
				<div class="mask-cont">
					<div class="mask-tit">用户协议</div>
					<div class="mask-body"></div>
					<div class="mask-foot" style="margin-top: 2px;">
						<!--<input type="checkbox" style="text-align: left;" name="" id="" value="" />-->
						<button id="hideBtn" style="border: none;background: #004499;width: 100px;height: 40px;line-height: 40px;color: #fff;border-radius: 6px;">确定</button>
					</div>
				</div>
			</div>
		</div>
		<script src="${ctx}/pc_gw/pc-wj/jquery-1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				$('#Agreement').click(function(){
					$('.mask').show()
				})
				$('#hideBtn').click(function(){
					$('.mask').hide()
				})
				var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
				/* $('#nickname').blur(function(){
					if ($(this).val() == '') {
						confirm('请输入昵称')
					}
				})
				$('#tel').blur(function() {
					if($(this).val() == '') {
						confirm('请输入手机号', )
					} else if(!reg.test($(this).val())) {
						confirm('手机号码不正确')
					}
				}) */
				$('.verBtn').click(function() {
					var count = 60;
					var timer;
					if($('#tel').val() == '') {
						confirm('请输入手机号')
					} else if(!reg.test($('#tel').val())) {
						confirm('手机号码不正确')
					} else {
						function countDown() {
							if(count == 0) {
								clearInterval(timer);
								$('.verBtn').attr('disabled', true);
								$('.verBtn').css('background','#214979')
								$('.verBtn').html('重新发送');
							} else {
								$('.verBtn').css('background','#ddd')
								$('.verBtn').attr('disabled', false);
								$('.verBtn').html(count + 's');
								count--;
							}
						}
						var timer = setInterval(countDown, 1000);
						$.ajax({
							type:"post",
							url:"${ctx}/user/fromuser!createTelCode.action",
							async:true,
							data:{
								tel:$('#tel').val()
							},
							success:function(json){
								
							}
						});
					}
				});
				/* $('#password').blur(function(){
					pwd($(this).val())
				});
				$('#password_confirm').blur(function(){
					if ($(this)) {
						pwd($(this).val())
					}else if($('#password_confirm').val() !=$('#password').val() ){
						confirm('两次输入的密码不一致')
					}
				}); */
				function pwd(val){
					var pwdVal = val;
					if (pwdVal == '') {
						confirm('密码不能为空');
					}
				}
				$('#reg').click(function(){
					$.ajax({
						type:"post",
						url:"${ctx}/login!ajaxsave.action",
						async:true,
						data:{
							tel:$('#tel').val(),
							yzcode:$('#verCode').val(),
							password:$('#password').val(),
							nickname:$('#nickname').val()
						},
						success:function(json){
							console.log(json.state)
							if(json.state == 1){
								confirm('网络超时，请重新操作')
							}else if(json.state == 2){
								confirm('用户名已存在')
							}else if (json.state == 3) {
								confirm('验证码错误')
							} else if (json.state == 4) {
								confirm('验证码已过期')
							} else{
								location.href='${ctx}/login.action';
							}
						}
						
					});
				})
				

			})
		</script>
 	</body>
</html>