<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html>
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
				width: 30%;
				font-size: 13px;
			}
			
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 70%;
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
				border-radius: 10px;
				height: 34px;
				line-height: 34px;
				background: #E4393C;
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
				padding-top: 32px;
				margin: 0;
			}
			.zhuce-fenlei{
				height:30px;
				line-height: 30px;
				padding:0;
				font-size:14px;
				
			}
			
			.mui-row::after,
			.mui-row::before,
			.mui-input-group::before,
			.mui-input-group::after {
				display: none;
			}
			
			/*新加*/
			.afterhide::after{
				display: none;
			}
			.Idimg{
				display: inline-block;
				width: 100%;
				height:100%;
				position:absolute;
				left:0;
				top:0;
				background: #eee;
				text-align: center;
				line-height: 100px;
				font-size: 20px;
				z-index: 0;
			}
			.box{
				display: none;
			}
		</style>
    </head>
    <body style="background: #fff;">
    	<div class="mui-content" style="background: #fff;padding: 0 20px;">
			<div class="mui-row">
				<p class="mui-popup-title">商家入驻</p>
				 <p class="mui-popup-title zhuce-fenlei">
					<span style="float: left;" class="personal"></span>
					<span style="float: right;" class="personal"></span>
					<input type="hidden" id="mui_title" value="2"/>
				</p>
			</div>
			<!--商家注册-->
			<div class="box">
				<form class="mui-input-group">
				<div class="mui-input-row">
					<label>手机号</label>
					<input id="phone1" type="text" class="mui-input-clear mui-input" minlength='11' maxlength="11" placeholder="请输入手机号码" />
				</div>
				<div class="mui-input-row" style="position: relative;">
					<label>验证码</label>
					<input id="verCode1" type="text" class="mui-input-clear mui-input" placeholder="请输入验证码" />
					<span class="mui-btn mui-btn-grey verBtn">获取验证码</span>
				</div>
				<div class="mui-input-row">
					<label>密码</label>
					<input id='password1' type="password" class="mui-input mui-input-password" placeholder="请输入密码">
				</div>
				<div class="mui-input-row">
					<label>确认</label>
					<input id='password_confirm1' type="password" class=" mui-input mui-input-password" placeholder="请确认密码">
				</div>
				<div class="mui-input-row">
					<label>真实姓名</label>
					<input id='name_confirm' type="text" class=" mui-input " placeholder="请填写真实姓名">
				</div>
				<div class="mui-input-row">
					<label>身份证号</label>
					<input id='nameId' type="text" class=" mui-input " placeholder="请填写正确的身份证号">
				</div>
				<div class="mui-input-row afterhide" style="height:auto;overflow: hidden;">
					<label style="width: 100%;">上传身份证照片</label>
					<p style="margin:40px 0 5px 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload" value="" placeholder="" style="display: none;" onclick="upload_id_card_front()"/>
	        			<label for="upload">
	        				<img  class="Idimg" alt="" id="imgss_front" />
	        				<span class="Idimg" id="Idimg1">身份证正面</span>
	        			</label>
	        			<input type="hidden" id="up_picture_front"/>
					</p>
					<p style="margin:0 0 0 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture1" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload1" value="" placeholder="" style="display: none;" onclick="upload_id_card_reverse()"/>
	        			<label for="upload">
	        				<img class="Idimg" alt="" id="imgss_reverse" />
	        				<span class="Idimg" id="Idimg2">身份证反面</span>
	        			</label>
	        			<input type="hidden" id="up_picture_reverse"/>
					</p>
				</div>
                <div class="mui-input-row">
					<label>公司名称</label>
					<input id='yingyename' type="text" class=" mui-input " placeholder="请填写公司名称">
				</div>
				<div class="mui-input-row">
					<label>营业证号</label>
					<input id='yingyeId' type="text" class=" mui-input " placeholder="请填写准确的营业证号码">
				</div>
				<div class="mui-input-row afterhide" style="height:auto;overflow: hidden;">
					<label style="width: 100%;">上传营业证照片</label>
					<p style="margin:40px 0 5px 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture2" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload2" value="" placeholder="" style="display: none;" onclick="upload_business()"/>
	        			<label for="upload">
	        				
	        				<img class="Idimg"  alt="" id="imgss" />
	        				<span class="Idimg" id="Idimg3">营业执照照片</span>
	        			</label>
	        			<input type="hidden" id="up_picture"/>
					</p>
				</div>
			</form>
			</div>	
			<!--个人注册-->
			<div class="box">
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
			</div>
			
			
			
			<div class="mui-content-padded" style="margin-right: 0;">
				<button id='reg' class="mui-btn mui-btn-block " data-loading-icon= "mui-spinner mui-spinner-custom">注册</button>
			</div>
			<div class="mui-content-padded">

			</div>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script>
			$(function() {
				$(".box").eq(0).css({"display":"block"})
				$(".personal").eq(0).css({"color":"red"})
				$(".personal").each(function(e){
					$(this).click(function(){
						$(".personal").css({"color":"#000"})
						$(this).css({"color":"red"})
						$(".box").css({"display":"none"})
						.eq(e).css({"display":"block"})
						if($(this).html()=='商家注册'){
							$("#mui_title").attr("value","2");
						}else if($(this).html()=='个人注册'){
							$("#mui_title").attr("value","1");
						}
					})
				})

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
					var status = $("#mui_title").val();
					if(status=='1'){//个人注册
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
									
									$('.verBtn').removeAttr('disabled', false);
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
									tel:$('#phone').val()
								},
								success:function(json){
									
								}
							});
						}
					}else if(status=='2'){//商家注册
						if($('#phone1').val() == '') {
							mui.alert('请输入手机号')
						} else if(!reg.test($('#phone1').val())) {
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
								}
							}
							var timer = setInterval(countDown, 1000);
							$.ajax({
								type:"post",
								url:"${ctx}/user/fromuser!createTelCode.action",
								async:true,
								data:{
									tel:$('#phone1').val()
								},
								success:function(json){
									
								}
							});
						}
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
					var status = $("#mui_title").val(); 
					if(status=='1'){//个人注册  验证
						var phone = $("#phone").val();
						if(phone==""){//手机
							mui.alert('手机号不能为空')
							return;
						}
						
						var verCode = $("#verCode").val();
						if(phone==""){//验证码
							mui.alert('验证码不能为空')
							return;
						}
						
						var password = $("#password").val();
						if(password==""){
							mui.alert('密码不能为空')
							return;
						}
						
						var password_confirm = $("#password_confirm").val();
						if(password_confirm==""){
							mui.alert('确认密码不能为空')
							return;
						}
					}else if(status=='2'){//商家注册
						var phone = $("#phone1").val();
						if(phone==""){//手机
							mui.alert('手机号不能为空')
							return;
						}
						
						var verCode = $("#verCode1").val();
						if(phone==""){//验证码
							mui.alert('验证码不能为空')
							return;
						}
						
						var password = $("#password1").val();
						if(password==""){
							mui.alert('密码不能为空')
							return;
						}
						
						var password_confirm = $("#password_confirm1").val();
						if(password_confirm==""){
							mui.alert('确认密码不能为空')
							return;
						}
						
						var name_confirm = $("#name_confirm").val();
						if(name_confirm==""){
							mui.alert('真实姓名不能为空')
							return;
						}
						
						var nameId = $("#nameId").val();
						if(nameId==""){
							mui.alert('身份证号码不能为空')
							return;
						}
					
						var yingyename = $("#yingyename").val();
						if(yingyename==""){
							mui.alert('公司名称不能为空')
							return;
						}
					
						var yingyeId = $("#yingyeId").val();
						if(yingyeId==""){
							mui.alert('营业证号不能为空')
							return;
						}
					
					}
					mui($(this)).button('loading');
					if(status=='1'){//个人注册
						$.ajax({
							type:"post",
							url:"${ctx}/user/fromuser!signup.action",
							async:true,
							data:{tel:$('#phone').val(),password:$('#password').val(),yzcode:$('#verCode').val(),status:1},
							success:function(json){
								if(json){
									if(json.state == 0){
										location.href='${ctx}/shop/shop!index.action?lscode='+json.lscode;
									}
								}else{
									mui($(this)).button('reset');
								}
							}
						});
					}else if(status=='2'){//商家注册
						$.ajax({
							type:"post",
							url:"${ctx}/user/fromuser!signup.action",
							async:true,
							data:{
								tel:$('#phone1').val(),
								password:$('#password1').val(),
								verCode:$('#verCode1').val(),
								username:$('#name_confirm').val(),
								id_card:$('nameId').val(),
								id_card_front:$('#up_picture_front').val(),
								id_card_reverse:$('#up_picture_reverse').val(),
								company_name:$('#yingyename').val(),
								lisense_number:$('#yingyeId').val(),
								lisense_photo:$('#up_picture').val(),
								status:2
							},
							success:function(json){
								if(json){
									console.log("++++++++++++++++"+json.state);
									if(json.state == 0){
										location.href='${ctx}/shop/shop!index.action?lscode='+json.lscode;
									}
								}else{
									mui($(this)).button('reset');
								}
							}
						});
					}
				})
			})
			
			
		</script>
		
 	</body>
 	
 	<script src="${ctx}/mvccol/lrz/exif.js" type="text/javascript" charset="utf-8"></script>
 	
 	<script src="${ctx}/mvccol/lrz/lrz.js" type="text/javascript" charset="utf-8"></script>
 	<script src="${ctx}/mvccol/lrz/mobileFix.mini.js" type="text/javascript" charset="utf-8"></script>
 	<script src="${ctx}/mvccol/lrz/index2.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		
		function upload_id_card_front(){
			//picture代表input<file类型>的class的值，up_picture代表隐藏（hidden）的input的id值，imgss代表<img>标签的id值
			fileInput("picture","up_picture_front","imgss_front");
			$("#Idimg1").attr("style","display:none");
			
		}
		
		function upload_id_card_reverse(){
			fileInput("picture1","up_picture_reverse","imgss_reverse");
			$("#Idimg2").attr("style","display:none");
		}
		
		function upload_business(){
			fileInput("picture2","up_picture","imgss");
			$("#Idimg3").attr("style","display:none");
		}
		</script>
</html>