<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<style type="text/css">
			input:focus {
				outline: none;
			}
			
			.mui-input-row {
				position: relative;
			}
			
			.mui-input-row label {
				width: 40%;
				font-size: 14px;
				text-align: justify;
			}
			.mui-input-row label::after{
				display: inline-block ; 
				content: ''; 
				padding-left: 100%; 
			}
			.mui-input-row label~input,
			.mui-input-row label~select,
			.mui-input-row label~textarea {
				width: 60%;
			}
			
			.mui-input-row::after {
				width: 100%;
				content: '';
				height: 1px;
				background: #ccc;
				position: absolute;
				bottom: 0;
				left: 0;
			}
			
			.liftCash-tit {
				line-height: 20px;
				font-size: 12px;
				color: #999;
				margin-top: 5px;
			}
			
			.rank {
				width: 100%;
				height: auto;
				margin-top: 10px;
			}
			
			.liftBtn {
				margin: 0 auto;
				background: #E4393C;
				border: none;
				color: #fff;
			}
			
			.modal {
				width: 100%;
				height: 100%;
				position: fixed;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				background: rgba(0, 0, 0, .6);
				z-index: 1999;
				display: none;
			}
			
			.modal-cont {
				width: 80%;
				height: 200px;
				background: #fff;
				border-radius: 10px;
				margin: 0 auto;
				margin-top: 50px;
			}
			
			.modal-cont-tit {
				width: 100%;
				height: 34px;
				line-height: 34px;
				text-align: center;
				position: relative;
			}
			
			.modal-cont-tit::after {
				content: '';
				width: 100%;
				height: 1px;
				background: #e3e3e3;
				position: absolute;
				bottom: 0;
				left: 0;
			}
			
			.modal-cont-cont {
				width: 100%;
				height: 132px;
				line-height: 132px;
				position: relative;
			}
			
			.sixpwd {
				width: 80%;
				margin: 0 auto;
				border: 1px solid #000;
				position: absolute;
				top: 50%;
				left: 10%;
				height: 34px;
				line-height: 34px;
				margin-top: -17px;
				border-radius: 5px;
				display: flex;
				box-sizing: border-box;
				z-index: 10;
			}
			
			.sixpwd input {
				flex: 1;
				width: 1%;
				margin: 0;
				height: 32px;
				line-height: 32px;
				position: relative;
				border: none;
				border-right: 1px solid #000;
				border-radius: 0;
			}
			
			.sixpwd input:last-child {
				border: none;
			}
			.madol-foot{
				width: 100%;
				display: flex;
				height: 34px;
				line-height: 34px;
			}
			.madol-foot button{
				width: 1%;
				flex: 1;
				border: none;
				color: #fff;				
				border-radius:0 ;
			}
			.madol-foot button:first-child{
				border-bottom-left-radius: 5px;
				background: #E4393C;
			}
			.madol-foot button:last-child{
				
				background: #007AFF;
				border-bottom-right-radius: 5px;
			}
		</style>
		<script type="text/javascript">
		      function withdrawal(){
		    	  if($('#eth').val() == ''){
		    		  alert('请输入账号');
		    		  $('.modal').hide();
		    		  return ;
		    	  }
		    	  if($('#price').val() == ''){
		    		  alert('请输入交易所金额');
		    		  $('.modal').hide();
		    		  return ;
		    	  }
		    	  if($('#password').val() == ''){
		    		  alert('请输入支付密码');
		    		  return ;
		    	  }
		    	  var submitData = { 
		    			  password:$('#password').val()
		    	    }; 
		    		 $.post('${ctx}/integral/miners!wdpassword.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
					        	function (json) {
					            	if(json.state==0){
					            		var submitData1 = { 
							    				eth:$('#eth').val(),
							    				price:$('#price').val(),
							    				remark:$('#remark').val()
							    	    }; 
							    		 $.post('${ctx}/integral/miners!withdrawal.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData1,
										        	function (json) {
										            	if(json.state==0){
										            		alert('交易所申请成功，请等待审核');
										            		//window.location.href="${ctx}/integral/miners!ownminer.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
										            	}else if(json.state==1){
										            		alert('交易所失败，请重新提交');
										            	}else if(json.state==2){
										            		alert('余额不足');
										            	}else {
										            		alert('交易所失败');
										            	}
										},"json")
					            	}else if(json.state==1){
					            		alert('操作失败');
					            	}else if(json.state==2){
					            		alert('账号不存在');
					            	}else  if(json.state==3){
					            		alert('未设置密码，请先设置支付密码');
					            		window.location.href="${ctx}/user/fromuser!safePwd.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
					            	}else  if(json.state==4){
					            		alert('密码错误');
					            	}
					},"json")
		      }
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class=" mui-icon mui-icon-undo mui-pull-left" href="javascript:history.go(-1)"></a>
			<h1 class="mui-title">交易所</h1>
		</header>
		<div class="mui-content" style="background: #fff;padding-left:10px ;padding-right: 10px;">
			<div class="mui-row">
				<div class="mui-input-row">
					<label for="">账号</label>
					<input type="text" id="eth" class="mui-input-clear" value="" />
				</div>
				<div class="mui-input-row">
					<label for="">金额</label>
					<input type="text" id="price" class="mui-input-clear" value="" />
				</div>
				<div class="mui-input-row">
					<label for="">备注</label>
					<input type="text" id="remark" class="mui-input-clear" value="" />
				</div>
				<!-- <div class="liftCash-tit">
					可用余额:100.00ppt
				</div> -->
				<!--<div class="rank">
		    		<textarea name="" rows="2" cols=""></textarea>
		    	</div>-->
			</div>
			<div class="mui-row" style="width: 100%;display: flex;justify-content: center;margin-top: 20px;">
				<button class="mui-btn mui-btn-block liftBtn">申请到交易所</button>
			</div>
			<div class="modal">
				<div class="modal-cont">
					<div class="modal-cont-tit">
						请输入支付密码
					</div>
					<div class="modal-cont-cont">
						<div class="sixpwd">
							<input type="password" name="" class="pwd" id="password" value="" maxlength="16" />
							<!-- <input type="password" name="" class="pwd" id="password" value="" maxlength="1" />
							<input type="password" name="" class="pwd" id="password" value="" maxlength="1" />
							<input type="password" name="" class="pwd" id="password" value="" maxlength="1" />
							<input type="password" name="" class="pwd" id="password" value="" maxlength="1" />
							<input type="password" name="" class="pwd" id="password" value="" maxlength="1" /> -->
							<!-- <input type="password" name="" class="pwd" id="password" value="" maxlength="1" /> -->
						</div>

					</div>
					<div class="madol-foot">
						<button class="cancel">取消</button><button onclick="withdrawal()">确认</button>
					</div>
				</div>
			</div>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$('.liftBtn').click(function() {
				$('.modal').show()
			})
			$('.cancel').click(function(){
				$('.modal').hide()
			})
			/* function sixPwd() {
				var flg = 2;
				var txts = $('.pwd');
				for(var i = 0; i < txts.length; i++) {
					var t = txts[i];
					t.index = i;
					t.onkeyup = function() {
						if(this.value) {
							
								var next = this.index + 1;
								if(next > txts.length - 1) {
									return
								}
								txts[next].focus()
						} else {
							if(flg == 1) {
								var prev = this.index;
								if(prev < 0) return;
								txts[prev].focus();
								flg = 2;
							} else {
								var prev = this.index - 1;
								if(prev < 0) return;
								txts[prev].focus();
								flg = 1;
							}
						}
					}
				}
			}
			sixPwd() */
			 wx.config({
				    debug: false,
				    appId: '${token.appid}', 
				    timestamp: '${token.timestamp}', 
				    nonceStr: '${token.noncestr}', 
				    signature: '${token.signature}',
				    jsApiList: [ 'checkJsApi',
				                 'onMenuShareTimeline',
				                 'onMenuShareAppMessage',
				                 'onMenuShareQQ',
				                 'onMenuShareWeibo',
				                 'hideMenuItems',
				                 'showMenuItems'
				                 ] 
				});
				wx.ready(function(){ 
					var share={
						    title: '${share.fxtitle}', // 分享标题
						    desc: '${share.fxsummary}', // 分享描述
						    link: '${share.fxurl}', // 分享链接
						    imgUrl: '${filehttp}${share.fximg}', // 分享图标
						    success: function () { 
						      
						    },
						    cancel: function () { 
						    	
						    }
						};
					wx.onMenuShareAppMessage(share);
					wx.onMenuShareTimeline(share);
					wx.onMenuShareAppMessage(share);
					wx.onMenuShareQQ(share);
					wx.onMenuShareWeibo(share);
				});
		</script>
	</body>
</html>