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
				padding-left: 26px;
				padding-right: 26px;
				height: 100%;
				background: #fff;
			}
			.share{
				width: 100%;
				padding: 58px 38px 35px 38px;
				background: linear-gradient(to bottom right,#f5d2c3,#f881af);
				border-radius: 5px;
				display: flex;
				flex-wrap: wrap;
			}
			.share-canvas{
				width: 100%;
				text-align: center;
			}
			.share-tit{
				width: 100%;
				margin-top: 40px;
				line-height: 46px;
				display: flex;
				justify-content: center;
				align-items: center;
				
			}
			.share-tit img{
				width: 70px;
				height: 44px;
			}
			.share-tit p{
				font-size:17px;
				color: #FFFEFE;
				margin-left: -10px;
				font-weight: 600;
			}
			.share-area{
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-top: 50px;
			}
			.share-area span{
				width: 150px;
				height: 50px;
				line-height: 50px;
				border-radius: 13px;
				border: 1px solid #000;
				display: flex;
				justify-content: center;
				align-items: center;
				font-size: 14px;
				color: #000;
			}
			.friends{
				background: url('${ctx}/xmMobile/images/CircleFriends.png') no-repeat;
				display: inline-block;
				width: 31px;
				height: 31px;
				background-size: 100% auto;
				margin-right: 10px;
			}
			.wx{
				background: url('${ctx}/xmMobile/images/weixin.png') no-repeat;
				display: inline-block;
				width: 31px;
				height: 31px;
				background-size: 100% auto;
				margin-right: 10px;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav" style="background: #fff;box-shadow: none;">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">分享</h1>
		    <!--<span class="mui-pull-right">保存到手机</span>-->
		</header>
		<div class="mui-content">
		    <div class="mui-row">
		    	<div class="share">
		    		<div class="share-canvas" id="qrcode">
		    			
		    		</div>
		    		<div class="share-tit">
		    			<img src="${ctx}/xmMobile/img/icon/icon-logo-2.png"/>
		    			<p>熊猫商城APP</p>
		    		</div>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	<div class="share-area">
		    		<span>
		    			<i class="mui-icon friends"></i>
		    			朋友圈
		    		</span>
		    		<span>
		    			<i class="mui-icon wx"></i>
		    			微信
		    		</span>
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

			var w= ($(window).width()-38*2)/2;
			$(function(){
				$('#qrcode').qrcode({ 
					  width : w,
			          height : w,
			          text	: '${ctx}/shop/shop!index.action?'
				     });
			});
			
		</script>
	</body>

</html>