<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!doctype html>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
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
				/*background: url('${ctx}/xmMobile/img/minerback.gif') no-repeat;*/
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
				opacity: .8;
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
			#matrix{
				position: fixed;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				display: block;
			}
		</style>
	</head>

	<body>
		 <header class="mui-bar mui-bar-nav" style="background: #fff;">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">标题</h1>
		</header> 
		
		<div class="mui-content">
			<canvas id="matrix" width="" height=""></canvas>
			<div class="animation-back"></div>
			<div class="animation-cont">运行中</div>
			<div class="mui-row mui-col-xs-12" style="position: absolute;bottom: 20px;padding-left: 20px;box-sizing: border-box;">
				<div class="miner-txt">
					<p class="miner-tit">${db.money}</p>
					<p>我的算力：<span id="mycount"></span>GH/d</p>
					<!-- <p>累计获得：305.4664646JRL</p> -->
					<p>全网算力：${setting.num}GHS</p>
					<p>结束时间：${db.end}</p>
				</div>
			</div>
		</div>

		<script type="text/javascript">
		$(function(){
			$("#mycount").html(${db.money}/${db.time});
		});
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
			canvas()
			function canvas(){
				var c = document.getElementById("matrix");
				var ctx = c.getContext("2d");
				
				c.height = window.innerHeight;
				c.width = window.innerWidth;
//				var chinese = "我们共同为建站事业而奋斗！";
//				chinese = chinese.split("");
				
				var font_size = 10;
				var columns = c.width/font_size;
				//an array of drops - one per column
				var drops = [];
				for(var x = 0; x < columns; x++)
					drops[x] = 1; 
				//drawing the characters
				function draw()
				{
					ctx.fillStyle = "rgba(0, 0, 0, 0.05)";
					ctx.fillRect(0, 0, c.width, c.height);
					
					ctx.fillStyle = "#0F0"; //green text
					ctx.font = font_size + "px arial";
					//looping over drops
					for(var i = 0; i < drops.length; i++)
					{
						var text = Math.floor(Math.random()*10);
						ctx.fillText(text, i*font_size, drops[i]*font_size);
						
						if(drops[i]*font_size > c.height && Math.random() > 0.975)
							drops[i] = 0;
						
					drops[i]++;
					}
				}
				setInterval(draw, 33);
			}
		</script>
	</body>

</html>