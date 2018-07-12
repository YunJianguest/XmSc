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
		<link href="${ctx}/mvccol/mui-css/mui.picker.css" rel="stylesheet"/>
	    <link href="${ctx}/mvccol/mui-css/mui.poppicker.css" rel="stylesheet"/>
	    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet">
	    <link href="${ctx}/app/css/font-awesome-ie7.min.css" rel="stylesheet">	
	    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		 <!--标准mui.css-->
    	<link rel="stylesheet" href="${ctx}/mvccol/mui-css/mui.min.css">
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
				border-radius: 10px;
				height: 34px;
				line-height: 34px;
				background: #E4393C;
				color: #fff;
				font-size: 16px;
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
			
			.mui-btn.verBtn {
				width: 100px;
				position: absolute;
				right: 0;
				top: 8px;
				padding: 5px 10px;
				background:#214979;
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
			.mask{display: none;width:100%;height: 100%;background: rgba(0,0,0,.6);position: fixed;top: 0;left: 0;right: 0;bottom: 0;z-index: 10;}
			.mask-cont{width: 300px;height: 400px;margin: 0 auto;position: absolute;top: 50%;left: 68%;margin-left: -220px;margin-top: -200px;background: #fff;z-index: 10;border-radius: 6px;padding: 10px;}
			.mask-tit{width: 100%;height: 40px;line-height: 40px;text-align: center;font-size: 16px;}
			.mask-body{width: 100%;height: 300px;overflow-y: auto;padding: 10px;position: relative;}
			.mask-body::after,.mask-body::before{content: '';width: 100%;height: 1px;background: #ddd;position: absolute;left: 0;}
			.mask-body::after{top: 0;}
			.mask-body::before{bottom: 0;}
			.mask-foot{width: 100%;height: 40px;text-align: right;}
		</style>
		<script>
		var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		function sendsms(){
			if($('#phone').val() == '') {
				mui.alert('请输入手机号')
				return ;
			} else if(!reg.test($('#phone').val())) {
				mui.alert('手机号码不正确')
				return ;
			} 
			
			var count = 60;
			var timer;
			function countDown() {
				if(count == 0) {
					clearInterval(timer);
					$('.verBtn').prop("disabled",false);
					$('.verBtn').html('重新发送');
					$('.verBtn').attr('onclick','sendsms1()');
				} else {
					$('.verBtn').removeAttr('onclick'); 									
					$('.verBtn').html(count + 's');
					count--;
				}
			}
			var timer = setInterval(countDown, 1000);
			
			if($('.verBtn').html()=='重新发送'||$('.verBtn').html()=='获取验证码'){
				sendsms1();
			}
		}
		
		function sendsms1(){
			
					$.ajax({
						type:"post",
						url:"${ctx}/user/fromuser!createTelCode.action",
						async:true,
						data:{
							tel:$('#phone').val()
						},
						success:function(json){
							if(json.state == 0){
								
							}else{
								clearInterval(timer);
								$('.verBtn').removeAttr('disabled', true);
								$('.verBtn').html('重新发送');
							}
						}
					});
			

			} 
		</script>
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
					<span class="mui-btn mui-btn-grey verBtn" onclick="sendsms()">获取验证码</span>
				</div>
				<div class="mui-input-row">
					<label>密码</label>
					<input id='password' type="password" class="mui-input mui-input-password" placeholder="请输入密码">
				</div>
				<div class="mui-input-row">
					<label>确认</label>
					<input id='password_confirm' type="password" class=" mui-input mui-input-password" placeholder="请确认密码">
				</div>
				
				<div class="line-bottom overflow-hidden">
                <div class="col-3 hang50 line-height50 weight500 zi-6 txt-c">地区信息</div>
                <div class="col-7" id='showCityPicker3'>
                    <div class="col-4">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="province" value="省份" onfocus="if(value=='省份'){value=''}"
                               onblur="if (value ==''){value='省份'}" readonly="readonly"/>
                    </div>
                    <div class="col-4">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="city" value="城市" onfocus="if(value=='城市'){value=''}"
                               onblur="if (value ==''){value='城市'}" readonly="readonly"/>
                    </div>
                    <div class="col-4">
                        <input class="hang50 width-9 maring-a line-height50 size14 zi-hui-wx" type="text"
                               id="county" value="区/县" onfocus="if(value=='区/县'){value=''}"
                               onblur="if (value ==''){value='区/县'}" readonly="readonly"/>
                    </div>
                </div>
                <div class="col-2 hang50 line-height50 txt-c"><i
                        class="fa fa-1dx fa-map-marker zi-green line-height50"></i></div>
            	</div>
				
				
				<div class="mui-input-row afterhide" style="height:auto;overflow: hidden;">
					<label style="width: 100%;">上传身份证照片</label>
					<p style="margin:40px 0 5px 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload" value="" placeholder="" style="display: none;" onclick="upload_id_card_front()"/>
	        			<label for="upload">
	        				
	        				<img class="Idimg" src="" alt="" id="imgss_front" />
	        				<span class="Idimg" id="Idimg1">身份证正面</span>
	        			</label>
	        			<input type="hidden" id="up_picture_front"/>
					</p>
					<p style="margin:0 0 0 10%;width: 80%;height:100px;position:relative">
						<input type="file" class="picture1" style="width: 100%;height:100%;position:absolute;z-index: 1;opacity: 0;" name="zb_tupian" id="upload1" value="" placeholder="" style="display: none;" onclick="upload_id_card_reverse()"/>
	        			<label for="upload">
	        				
	        				<img class="Idimg" src="" alt="" id="imgss_reverse" />
	        				<span class="Idimg" id="Idimg2">身份证反面</span>
	        			</label>
	        			<input type="hidden" id="up_picture_reverse"/>
					</p>
				</div>
				
				<div style="width: 100%;height: 20px;font-size: 12px;text-align: right;margin-top: 10px;cursor: pointer;" >
					<input type="checkbox" name="Agreement" id="Agreement" value="" /><span id="Agreement">用户协议</span>
				</div>
			</form>
			<div class="mui-content-padded">
				<button id='reg' class="mui-btn mui-btn-block " data-loading-icon= = "mui-spinner mui-spinner-custom">注册</button>
			</div>
			<div class="mui-content-padded">

			</div>
		</div>
		
		<div class="mask">
				<div class="mask-cont">
					<div class="mask-tit">用户协议</div>
					<div class="mask-body">
					熊猫商城平台服务协议

本协议由同意并承诺遵守本协议规定使用熊猫商城服务的法律主体（下称“商户”或“乙方”）、陕西上合供应链管理有限公司（下称“甲方”）共同缔结，本协议具有合同效力。
本协议中协议双方合称协议方，甲方称“熊猫商城”。
一、协议内容及生效：
（一）本协议内容包括协议正文及所有熊猫商城已经发布的或将来可能发布的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。
（二）商户在使用熊猫商城提供的各项服务的同时，承诺接受并遵守各项相关规则的规定。熊猫商城有权根据需要不时地制定、修改本协议或各类规则，如本协议有任何变更，熊猫商城将在网站上以公示形式通知予商户。如商户不同意相关变更，必须立即以书面通知的方式终止本协议。任何修订和新规则一经公布即自动生效，成为本协议的一部分。登录或继续使用服务将表示商户接受经修订的协议。除另行明确声明外，任何使服务范围扩大或功能增强的新内容均受本协议约束。
（三）商户签署或在线接受本协议并不导致本协议立即生效，经过熊猫商城审核通过并向商户发出服务开通通知时，本协议即在商户和熊猫商城之间产生法律效力。
二、定义：
“熊猫商城”：指由陕西上合供应链管理有限公司的电子商务平台网站。
“熊猫商城网上交易平台”：指熊猫商城上供用户发布或查询商品信息，进行信息交流，达成交易意向及向用户提供其他与交易有关的辅助信息服务的空间。
“商户及商户注册”：商户必须是符合国家规定具有经营资格的法律主体，如无经营资格或违反本协议第五条之声明与保证的组织不当注册为熊猫商城商户或超越其民事权利或行为能力范围从事交易的，其与熊猫商城之间的协议自始无效，一经发现，熊猫商城有权立即注销该商户熊猫商城服务账户，并追究其使用熊猫商城服务的一切法律责任。商户注册是指商户登陆熊猫商城，按要求填写相关信息，且在线阅读并确认接受或书面签署本协议以最终激活其熊猫商城服务账户的过程。
“熊猫商城服务账户”：即商户完成商户注册流程而获得的其将在使用服务的过程中必须与自设的账户密码共同使用的用户名，又称“熊猫商城用户名”。商户应妥善保管其熊猫商城服务账户及密码信息，商户不得以任何形式擅自转让或授权他人使用自己的熊猫商城服务账户；
“服务”：是指由甲方在熊猫商城网上交易平台向商户提供的互联网信息发布、商业推广及与此有关的互联网技术服务。具体服务内容如下：
1、网络信息服务：指熊猫商城提供的商户根据本协议的规定利用熊猫商城网上交易平台查询商品信息、发布商品信息、作为卖方与其它用户订立商品买卖合同、评价其它用户的信用、参加熊猫商城有关活动以及其他由熊猫商城同意提供的信息服务；
2、推广服务：指由熊猫商城向商户提供的通过熊猫商城网上交易平台实施的商业推广服务。
3、其他服务：具体服务项目以协议方在本协议附件补充协议中确定。熊猫商城保留在任何时候自行决定对服务及其相关功能、应用软件变更、升级的权利。熊猫商城进一步保留在服务中开发新的模块、功能和软件或其它语种服务的权利。上述所有新的模块、功能、软件服务的提供，除非熊猫商城另有说明，否则仍适用本协议。服务随时可能因熊猫商城的单方判断而被增加或修改，或因定期、不定期的维护而暂缓提供，商户将会得到相应的变更通知。商户在此同意熊猫商城在任何情况下都无需向其或第三方在使用服务时对其在传输或联络中的迟延、不准确、错误或疏漏及因此而致使的损害负责。
“熊猫商城服务条款”：由协议方另行签署的确认与本协议服务有关的各项个性化规定的契约文件。如熊猫商城服务条款内容与本协议内容存在冲突，以熊猫商城服务条款内容为准。
三、证明文件提交：
（一）证明文件提交：商户欲使用本协议以下服务，必须按照熊猫商城的要求向熊猫商城或熊猫商城指定的合作方提交其在申请服务时应当提供的证明文件或其他相关证明。
（二）证明文件变更的通知：协议期内，上述相关证明文件的任何变更商户都应及时通知熊猫商城，如上述证明文件变更后导致商户不再具备履行本协议的情形出现时，熊猫商城有权立即终止或中止本协议。
（三）熊猫商城有权对商户的证明文件进行不定时的抽查，商户应按熊猫商城要求进行提供或补充。如商户不能按期提供或补充，熊猫商城则有权立即终止或中止本协议。
（四）责任条款：若商户未尽到及时的通知或更新其变更证明文件或其他证明信息的义务，全部责任由商户承担。商户保证向熊猫商城提供的全部证明文件真实、准确且不存在超过时效问题（即保证所有证明文件在整个合同履行期间都处于有效期内）如因上述原因发生纠纷或被相关国家主管机关处罚，商户应当独立承担全部责任，如给熊猫商城（包括其合作伙伴、代理人、职员等）造成损失的，商户应承担赔偿责任。
四、申请条件：申请使用服务的商户必须同时满足以下条件：
（一）确认接受本协议，注册成为熊猫商城用户；
（二）提交了本协议第三条约定的相关证明文件并获得熊猫商城认可；
五、商户的声明与保证
（一）向熊猫商城提供真实、合法、准确、有效的注册资料，并保证其诸如电子邮件地址、联系电话、联系地址等内容的有效性及安全性，保证熊猫商城及其他用户可以通过上述联系方式与自己进行联系。同时，商户也有义务在相关资料实际变更时及时更新有关注册资料。
（二）其承诺遵守本协议、《熊猫商城商家入驻服务合作协议》、《熊猫商城入驻规则》以及所有公示于熊猫商城的规则和流程。
（三）其有合法的权利缔结本协议，使用熊猫商城相关服务。
（四）其发布于熊猫商城的所有信息真实、准确，符合相关法律法规及熊猫商城规则。
（五）其对其发布于熊猫商城的交易信息中所有商品具有合法的销售权。
（六）其将按照不低于《中华人民共和国产品质量法》、《中华人民共和国消费者权益保护法》及其他法规、部门规章和国家强制性标准规定的要求，出售商品并提供“三包”等售后服务。
（七）其在熊猫商城出售商品，有义务按照买家实际支付的金额为买家开具发票，相关税收应按国家相关规定由商户自行承担。
（八）其保证在使用服务进行交易的过程中遵守诚实信用的原则，不在交易过程中采取不正当竞争行为，不扰乱网上交易的正常秩序，不从事与网上交易无关的行为。
（九）其保证在使用服务时实施的所有行为均遵守国家法律、法规和熊猫商城的相关规则、规定以及各种社会公共利益及公共道德。如有违反导致任何法律后果的发生，商户将以自己的名义独立承担所有相应的法律责任；  
（十）其同意不对熊猫商城上任何数据进行商业性利用，包括但不限于在未经熊猫商城事先书面批准的情况下，以复制、传播或其他形式向第三方披露，不得使用在熊猫商城网站上其他用户展示的任何资料；
（十一）其承诺拥有合法的权利和资格向熊猫商城上传有关商品销售信息并进行销售，且前述行为未对任何第三方合法权益，包括但不限于第三方知识产权、物权等构成侵害，如因其行为导致熊猫商城遭受任何第三方提起的索赔，诉讼或行政责任，其将承担相应责任并使熊猫商城免责。
（十二）其任何在线接受本协议的行为并不当然导致本协议发生法律效力，本协议是附条件生效协议，即必须经过熊猫商城对其提交的全部资料审核通过且满足本协议约定的生效条件后方可生效。同时，其认可熊猫商城有权独立的对其入驻资料、品牌经营权限开通申请进行评估、判断。审核结果以熊猫商城评估、判断为准，其对此不持有任何异议。
（十三）其承诺不在发布的商品中使用任何未获授权品牌的关键字。
（十四）其承诺接受熊猫商城对其出售商品是否具有合法进货来源的不定期检查，其应当保留所受商品具有合法进货来源的相关凭证。对于无法提供合法进货来源凭证的，熊猫商城将根据实际情况对商品的真伪作出判断并根据本协议以及熊猫商城相关规则进行处理，商户对此承担举证不利的所有法律后果。
（十五）其承诺接受熊猫商城基于商品品质控制需求对其在售商品进行的质量抽检，检测报告由专业的第三方质检机构出具，检测费用由商户承担。
六、消费者保障
消费者保障，只指商户根据本协议约定的条款和条件及熊猫商城网站其他公示规则的规定，通过熊猫商城发布信息向买家出售全新商品时，应履行“商品如实描述”、“商品7天无理由退换货”、“正品保障”义务。熊猫商城将在熊猫商城网站不时公示新增的消费者保障义务或对原消费者保障义务内容进行修订。如：“您对新增或修订的消费者保障义务持有异议，您应当终止本协议，如继续使用熊猫商城服务，则意味着接受熊猫商城的新增或修订内容。”
（一）消费者保障内容在商户通过熊猫商城网站发布商品信息以及与买家进商品交易过程中，商户承诺遵守以下约定:
1、“如实描述”义务，指对上传于熊猫商城网站的商品信息进行如实描述，并对描述的商品信息负有举证责任。有效的“如实描述”应涵盖以下内容：
（1）商户在发布商品时选择及填写的所有与商品本身有关的信息，包括但不限于文字描述、商品图片，买家均可在该商品的详情页面上进行直接查看；
（2）商户与买家在交易过程中利用微信进行沟通时，商户就商品本身信息、邮费、发货情况、交易附带物件向买家描述的内容也属于“商品描述信息”范围。商品功效信息及商品货源渠道信息描述则不属于“商品描述信息”范围；
（3）商户有义务对商品本身存在的质量问题及瑕疵承担责任，除非商户已事先进行了质量问题及瑕疵的描述；
（4）如商户在服装类目下发布商品，应当使用实物拍摄图片，即商户针对该件商品本身实际拍摄的图片，不包括杂志图片、官方网站图片及宣传图片。如商户违反本条款之约定，则视为商户违反了商品如实描述义务；
2、“7天无理由退换货”义务：指当买家购买商户出售的商品，在签收货物后7天内，如因买家主观原因不愿完成本次交易，商户承诺同意按照本协议之约定向买家提供退换货服务。如买家与商户就退换货事宜协商未果，买家向熊猫商城发起针对商户的维权，并申请“7天无理由退换货”赔付时，如熊猫商城判定买家赔付申请成立，商户同意按照本协议约定的赔偿金额对买家进行相应的赔付。
					
					</div>
					<div class="mask-foot" style="margin-top: 2px;">
						<!--<input type="checkbox" style="text-align: left;" name="" id="" value="" />-->
						<button id="hideBtn" style="border: none;background: #004499;width: 100px;height: 40px;line-height: 20px;color: #fff;border-radius: 6px;">确定</button>
					</div>
				</div>
			</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/mvccol/mui-js/mui.picker.js"></script>
<script src="${ctx}/mvccol/mui-js/mui.poppicker.js"></script>

<script src="${ctx}/mvccol/mui-js/city.data-3.js" type="text/javascript" charset="utf-8"></script>
<script>
    (function ($, doc) {
        $.init();
        $.ready(function () {

            var cityPicker3 = new $.PopPicker({
                layer: 3
            });
            cityPicker3.setData(cityData3);
            var showCityPickerButton = doc.getElementById('showCityPicker3');
            var province = doc.getElementById('province');
            var city = doc.getElementById('city');
            var county = doc.getElementById('county');
            showCityPickerButton.addEventListener('tap', function (event) {
                cityPicker3.show(function (items) {
                    province.value =(items[0] || {}).text;
                    city.value=(items[1] || {}).text;
                    if (typeof((items[2] || {}).text) == "undefined") { 
                     county.value='';
                     }else{
                     county.value=(items[2] || {}).text;
                     }  
                    
                    //返回 false 可以阻止选择框的关闭
                    
                    //return false;
                });
            }, false);
        });
    })(mui, document);
    
</script> 
		<script>
			$(function() {
				$('#hideBtn').click(function(){
					$('.mask').hide()
				})
				$('#Agreement').click(function(){
					$('.mask').show()
				})
				
				//var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
				/*$('#phone').blur(function() {
					if($(this).val() == '') {
						mui.alert('请输入手机号')
					} else if(!reg.test($(this).val())) {
						mui.alert('手机号码不正确')
					}
				}) */
				/*$('.verBtn').click(function() {
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
								$('.verbtn').css('background','#214979')
								$('.verBtn').removeAttr('disabled', true);
								$('.verBtn').html('重新发送');
							} else {
								count--;
								$('.verbtn').css('background','#ddd')
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
								// if(json.state == 0){
								//	countDown();
								//}else{
								//	alert('操作失败');
								//} 
							}
						});
					}
				});*/
				
			/* 	$('#password').blur(function(){
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
				} */
				$('#reg').click(function(){
					mui($(this)).button('loading');
					$.ajax({
						type:"post",
						url:"${ctx}/user/fromuser!signup.action",
						async:true,
						data:{
							tel:$('#phone').val(),
							password:$('#password').val(),
							yzcode:$('#verCode').val(),
							province:$('#province').val(),
							city:$('#city').val(),
							county:$('#county').val(),
							username:$('#username').val(),
							id_card_front:$('#up_picture_front').val(),
							id_card_reverse:$('#up_picture_reverse').val(),
							status:1
						},
						success:function(json){
							if(json){
								if(json.state == 0){
									mui.alert('注册成功！')
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