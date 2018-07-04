<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!doctype html>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<style type="text/css">
			html,
			body {
				width: 100%;
				height: 100%;
				box-sizing: border-box;
			}
			
			*::before,
			*::after {
				box-sizing: border-box;
			}
			
			.mui-content {
				width: 100%;
				height: 100%;
				background: url('${ctx}/xmMobile/img/minerbanner.jpg') no-repeat;
				background-size: 100% 100%;
				position: relative;
			}
			
			.animation-back {
				width: 150px;
				height: 150px;
				margin: 0 auto;
				margin-top: -150px;
				background: url(${ctx}/xmMobile/img/animation-back.png) no-repeat;
				background-size: 100% 100%;
				border-radius: 50%;
				-webkit-animation: rotation 6s linear infinite;
				animation: rotation 6s linear infinite;
				position: absolute;
				top: 50%;
				left: 0;
				right: 0;
				bottom: 0;
				
			}
			
			@-webkit-keyframes rotation {
				from {
					-webkit-transform: rotate(0deg);
					transform: rotate(0deg);
				}
				to {
					-webkit-transform: rotate(360deg);
					transform: rotate(360deg);
				}
			}
			
			@keyframes rotation {
				from {
					-webkit-transform: rotate(0deg);
					transform: rotate(0deg);
				}
				to {
					-webkit-transform: rotate(360deg);
					transform: rotate(360deg);
				}
			}
			.animation-cont{
				width: 100px;
				height: 100px;
				margin:  0 auto;
				margin-top: -125px;
				background: #2c7088 ;
				position: absolute;
				border-radius: 50%;
				line-height: 100px;
				text-align: center;
				color: #fff;
				font-size: 24px;
				top: 50%;
				left: 0;
				right: 0;
				bottom: 0;
			}
			.miner-txt{
				color: #fff;
			}
			.miner-tit{
				font-size: 28px;
			}
			.miner-txt p{
				color: #fff;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav" style="background: #fff;">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">标题</h1>
		</header>
		<div class="mui-content">
			<div class="animation-back"></div>
			<div class="animation-cont">运行中</div>
			<div class="mui-row mui-col-xs-12" style="position: absolute;bottom: 20px;padding-left: 20px;box-sizing: border-box;">
				<div class="miner-txt">
					<p class="miner-tit">${db.money}</p>
					<p>我的算力：${db.money}/${db.time}GH/d</p>
					<!-- <p>累计获得：305.4664646JRL</p> -->
					<p>全网算力：${setting.num}GHS</p>
					<p>结束时间：${db.end}</p>
				</div>
			</div>
		</div>

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

		</script>
	</body>

</html>