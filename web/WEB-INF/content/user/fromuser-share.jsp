<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta charset="UTF-8"/>
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css"/>
		 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<style type="text/css">
			html,body{
				background: #fff;
				height: 100%;
			}
			
			.mui-content{
				width: 100%;
				height: 100%;
				overflow: scroll;
				background: url('${ctx}/xmMobile/img/beijing.jpg') no-repeat;
				background-size:100% 100%;
			}
			.share{
				width: 100%;
				margin-top: 90px;
			}
			.share-canvas{
				width: 100%;
				text-align: center;
				padding: 10px;
				border-radius:10px;
			}
			canvas{
				padding: 10px;
				border-radius: 10px;
				background: #fff;
			}
			.share-logo{
				margin-bottom: 14px;
			}
			.share-logo-box{
				width: 150px;
				height: 64px;
				margin: 0 auto;
				border: 2px solid #00d6ff;
				text-align: center;
			}
			.share-logo-box img{
				width: 100px;
				height: 64px;
				margin: 0 auto;
				vertical-align: middle;
				text-align: center;
			}
			.share-tit{
				width: 240px;
				height: 40px;
				line-height: 40px;
				border: 2px solid rgba(255,255,255,.5);
				margin: 0 auto;
				text-align: center;
				color: #fff;
				font-size: 18px;
				margin-top: 10px;
				/*position: absolute;
			    bottom: -100px;
			    left: 50%;
			    margin-left: -120px;*/
			}
			.share-txt{
				width: 100%;
				height: 34px;
				line-height: 34px;
				color: #fff;
				text-align: center;
				font-size: 12px;
				font-weight: 600;
				/*position: absolute;
			    bottom: -150px;
			    left: 0;*/
			}
			/*@media screen and (min-width:375px){
				.share-tit{
					bottom: 0;
				}
				.share-txt{
					bottom: -30px;
				}
			}
			@media screen and(min-width:414px){
				.share-tit{
					bottom: -40px;
				}
				.share-txt{
					bottom: -80px;
				}
			}
			@media screen and(min-width:768px){
				.share-tit{
					bottom: -50px;
				}
				.share-txt{
					bottom: -90px;
				}
			}
			@media screen and(min-width:1024px){
				.share-tit{
					bottom: -130px;
				}
				.share-txt{
					bottom: -170px;
				}
			}*/
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav" style="background: #fff;box-shadow: none;">
		    <a class="mui-action-back mui-icon mui-icon-undo mui-pull-left" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">分享</h1>
		    <!--<span class="mui-pull-right">保存到手机</span>-->
		</header>
		<div class="mui-content" style="padding-top: 10px;">
			
		    <div class="mui-row">
		    	
		    	<div class="share">
		    		<div class="share-logo">
		    			<div class="share-logo-box">
		    				<img src="${ctx}/xmMobile/img/icon/sharelogo.png"/>
		    			</div>
		    		</div>
		    		<div class="share-canvas" id="qrcode"></div>
		    		<div class="share-tit">
		    			扫描进入熊猫商城
		    		</div>
		    		<div class="share-txt">
		    			全球首家数字资产购物商城
		    		</div>
		    	</div>
		    </div>
		    
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/app/js/jquery.qrcode.js"></script>
		<script type="text/javascript" src="${ctx}/app/js/qrcode.js"></script> 
		<script type="text/javascript">
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

			var w= ($(window).width()-38*2)/2+50;
			$(function(){
				$('#qrcode').qrcode({ 
					  width : w,
			          height : w,
			          text	: '${ctxurl}/shop/shop!index.action?agid=${user.no}'
				     });
			});
			
		</script>
	</body>

</html>