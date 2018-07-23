<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>基础信息</title>
		<link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet">
	    <link href="${ctx}/app/css/font-awesome-ie7.min.css" rel="stylesheet">	
	    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
	    <link href="${ctx}/xmMobile/css/style.css" rel="stylesheet" />
	    
	    <link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/mvccol/mui-css/mui.picker.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<style type="text/css">
			.mui-input-group label {
				width: 30%;
				font-size: 12px;
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
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 70%;
				font-size: 14px;
			}
			
			input {
				font-size: 14px;
			}
			
			.mui-input-row label~input::-webkit-input-placeholder,
			.mui-input-row textarea::-webkit-input-placeholder {
				font-size: 12px;
			}
			
			/*.mui-row::after {
				content: '';
				width: calc(100% - 15px);
				height: 1px;
				position: absolute;
				bottom: 0;
				left: 10px;
				background-color: #c8c7cc;
				-webkit-transform: scaleY(.5);
				transform: scaleY(.5);
			}
			*/
			.mui-radio::after,
			.mui-input-group::after {
				display: none;
			}
			
			.afterNone::after {
				display: none;
			}
			
			.link {
				/*width: 50px;*/
				height: 34px;
				line-height: 34px;
				position: absolute;
				right: 0;
				bottom: 3px;
				font-size: 14px;
				background: #007AFF;
				color: #fff !important;
				padding: 0px 10px;
				border-radius: 5px;
			}
			
			.tit {
				width: 100%;
				height: 24px;
				line-height: 24px;
				text-align: left;
				font-size: 12px;
				padding-left: 10px;
			}
			
			.photo {
				width: 80%;
				height: 100px;
				background: #e2e2e2;
				margin: 0 auto;
				border-radius: 5px;
				line-height: 100px;
				text-align: center;
				margin-bottom: 10px;
				color: #999;
			}
			
			.photo input[type='file'] {
				width: 100%;
				height: 100%;
				display: block;
				opacity: 0;
			}
			
			.baseBtn {
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
<body style="background: #fff;">
<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-undo mui-pull-left"></a>
			<h1 class="mui-title">基础信息</h1>
		</header>
		<div class="mui-content" style="padding: 0 20px;background: #fff;height: 100%;padding-top: 44px;">
			<form action="" class="mui-input-group">
				<div class="mui-input-row">
					<label>编号</label>
					<input id="number" type="text" value="${user.number }" class="mui-input-clear" maxlength="16" placeholder="编号" readonly="readonly" >
				</div>
				<div class="mui-input-row">
					<label>登陆密码</label>
					<input id="onepassword" type="password" value="${user.password }" class="mui-input mui-input-password" maxlength="16" placeholder="请输入密码">
				</div>
				<%-- <c:if test="${user.isfull != 1 }"> --%>
				<div class="mui-input-row">
					<label>支付密码</label>
					<input id="paypassword" type="password" value="" class="mui-input mui-input-password" maxlength="16" placeholder="请输入密码">
				</div>
				<%-- </c:if> --%>
				<div class="mui-input-row">
					<label>姓名</label>
					<input id="name" type="text" value="${user.userName }" class="mui-input-clear" maxlength="16" placeholder="请输入姓名">
				</div>
				<div class="mui-input-row">
					<label>身份证号码</label>
					<input id="idCard" type="text" value="${user.id_card }" class="mui-input-clear" maxlength="18" placeholder="请输入身份证号">
				</div>
				<div class="mui-input-row">
					<label>电话</label>
					<input id="tel" type="tel" value="${user.tel }" class="mui-input-clear" maxlength="11" placeholder="请输入电话">
				</div>
				<div class="mui-input-row" style="position: relative;">
					<label>USKD账号</label>
					<input id="uskd" type="text" value="${user.uskd }" class="mui-input-clear" value="" placeholder="请输入USKD账号/无账号请注册">
					<a class="link" href="http://www.uskdpro.com">去注册</a>
				</div>
			</form>
			
			<div class="line-bottom overflow-hidden">
                <div class="col-3 hang50 line-height50 weight500 zi-6 txt-c" style="font-size:10px;">身份证地址</div>
                
                <div class="col-9" id='showCityPicker3'>
                    <div class="col-4">
                    	<c:if test="${user.province!=''&&user.province!=null }">
                    		<input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               value="${user.province }" id="province" onfocus="if(value=='省份'){value=''}"
                               onblur="if (value ==''){value='省份'}" readonly="readonly"  style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                    	</c:if>
                    	<c:if test="${user.province==''||user.province==null }">
                    		<input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="province" onfocus="if(value=='省份'){value=''}"
                               onblur="if (value ==''){value='省份'}" readonly="readonly"  style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                    	</c:if>
                        
                    </div>
                    <div class="col-4">
                    <c:if test="${user.city!=''&&user.city!=null }">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               value="${user.city }" id="city" value="城市" onfocus="if(value=='城市'){value=''}"
                               onblur="if (value ==''){value='城市'}" readonly="readonly" style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                  	</c:if>
                  	<c:if test="${user.city==''||user.city==null }">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="city" value="城市" onfocus="if(value=='城市'){value=''}"
                               onblur="if (value ==''){value='城市'}" readonly="readonly" style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                  	</c:if>
                    </div>
                    <div class="col-4">
                    <c:if test="${user.county!=''&&user.county!=null }">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               value="${user.county }" id="county" value="区/县" onfocus="if(value=='区/县'){value=''}"
                               onblur="if (value ==''){value='区/县'}" readonly="readonly" style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                   	</c:if>
                   	<c:if test="${user.county==''||user.county==null }">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="county" value="区/县" onfocus="if(value=='区/县'){value=''}"
                               onblur="if (value ==''){value='区/县'}" readonly="readonly" style="padding: 10px 5px;border:none;margin-bottom:0;"/>
                   	</c:if>
                    </div>
                </div>
               <!-- <div class="col-2 hang50 line-height50 txt-c"><i
                        class="fa fa-1dx fa-map-marker zi-green line-height50"></i></div>
            	</div>-->
			
			
			<!-- 身份证上传 -->
			<div class="mui-input-row afterhide" style="height:280px;overflow: hidden;">
					<label style="width: 100%;font-size:10px;">上传身份证照片</label>
					<p style="margin:40px 0 5px 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload" value="" placeholder="" style="display: none;" onclick="upload_id_card_front()"/>
	        			<label for="upload">
	        				<c:if test="${user.id_card_front==''||user.id_card_front==null }">
	        				<img class="Idimg" src="" alt="" id="imgss_front" />
	        				</c:if>
	        				<span class="Idimg" id="Idimg1">身份证正面</span>
	        				<c:if test="${user.id_card_front!=''&&user.id_card_front!=null }">
	        				${ctx}/uploads/${user.id_card_front } 
	        				<img class="Idimg" src="${ctx}/uploads/${user.id_card_front } " alt="" id="imgss_front" />
	        				</c:if>
	        			</label>
	        			<input type="hidden" id="up_picture_front"/>
					</p>
					<p style="margin:0 0 0 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture1" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload1" value="" placeholder="" style="display: none;" onclick="upload_id_card_reverse()"/>
	        			<label for="upload">
	        				<c:if test="${user.id_card_reverse==''||user.id_card_reverse==null }">
	        				<img class="Idimg" src="" alt="" id="imgss_reverse" />
	        				</c:if>
	        				<span class="Idimg" id="Idimg2">身份证反面</span>
	        				<c:if test="${user.id_card_reverse!=''&&user.id_card_reverse!=null }">
	        				<img class="Idimg" src="${ctx}/uploads/${user.id_card_reverse } " alt="" id="imgss_reverse" />
	        				</c:if>
	        			</label>
	        			<input type="hidden" id="up_picture_reverse"/>
					</p>
				</div>
				<!-- 提示信息 -->
			<div class="" style="width: 100%;line-height: 20px;padding: 0 10px;font-size: 12px;">
				提示：请确保所填信息真实有效，如出现问题责任自负！
			</div>
			<div class="mui-row" style="margin-top: 15px;">
				<button class="mui-btn mui-btn-block baseBtn" onclick="update_submit()">提交</button>
			</div>
			
			
		</div>
		<script>
		//提交
		function update_submit(){
			var tel = $("#tel").val();
			if(tel==""){//手机
				mui.alert('手机号不能为空')
				return;
			}
			
			var name = $("#name").val();
			if(name==""){//姓名
				mui.alert('姓名不能为空')
				return;
			}
			
			var password = $("#onepassword").val();
			if(password==""){
				mui.alert('密码不能为空')
				return;
			}
			
			var paypassword = $("#paypassword").val();
			if(paypassword==""){
				mui.alert('支付密码不能为空')
				return;
			}
			
			var uskd = $("#uskd").val();
			if(uskd==""){
				mui.alert('uskd账号不能为空')
				return;
			}
			
			var province = $("#province").val();
			if(province==""){
				mui.alert('身份证地址不能为空')
				return;
			}
			
			var idCard = $('#idCard').val();
			if(idCard==""){
				mui.alert('身份证不能为空');
				return;
			}else if(idCard.length != 18){
				mui.alert('请输入正确的身份证号码');
				return;
			}
			$.ajax({
				type:"post",
				url:"${ctx}/user/fromuser!ajaxUserUpdate.action",
				async:true,
				data:{
					lscode:'${lscode}',
					name:$('#name').val(),
					tel:$('#tel').val(),
					uskd:$('#uskd').val(),
					idCard:$('#idCard').val(),
					password:$('#onepassword').val(),
					paypassword:$('#paypassword').val(),
					province:$('#province').val(),
					city:$('#city').val(),
					county:$('#county').val(),
					up_picture_front:$('#up_picture_front').val(),
					up_picture_reverse:$('#up_picture_reverse').val()
				},
				success:function(json){
					if(json.state==0){
						mui.alert('信息完善成功！');
						if('${url}' != ''){
							window.location.href='${ctx}${url}?custid=${custid}&agid=&lscode=${lscode}'
						}else{
							window.location.href='${ctx}/user/fromuser!UserDetail.action?custid=${custid}&agid=&lscode=${lscode}';
						}
						
					}
				}
			});
		}
		</script>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/mvccol/mui-js/mui.picker.js"></script>
		<script src="${ctx}/mvccol/mui-js/mui.poppicker.js"></script>
		<script src="${ctx}/mvccol/mui-js/city.data-3.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			(function($, doc) {
				$.init();
				$.ready(function() {

					var cityPicker3 = new $.PopPicker({
						layer: 3
					});
					cityPicker3.setData(cityData3);
					var showCityPickerButton = doc.getElementById('showCityPicker3');
					var province = doc.getElementById('province');
					var city = doc.getElementById('city');
					var county = doc.getElementById('county');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker3.show(function(items) {
							province.value = (items[0] || {}).text;
							city.value = (items[1] || {}).text;
							if(typeof((items[2] || {}).text) == "undefined") {
								county.value = '';
							} else {
								county.value = (items[2] || {}).text;
							}

							//返回 false 可以阻止选择框的关闭

							//return false;
						});
					}, false);
				});
			})(mui, document);
		</script>
		
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
		
		</script>
</body>
</html>
