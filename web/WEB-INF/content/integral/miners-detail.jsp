<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>矿机购买</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
        <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
		<script src="${ctx}/app/js/iosOverlay.js"></script>
        <script src="${ctx}/app/js/spin.min.js"></script>
        <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
        <script src="${ctx}/mvccol/js/fomatdate.js"></script>
		<style type="text/css">
			.mui-content {
				padding-top: 44px;
			}
			
			.miner-txt{
				display: flex;
				justify-content: space-between;
			}
			.miner-txt-cont{
				overflow: hidden;
			}
			.miner-buyBtn{
				display: flex;
				justify-content: center;
				align-items: center;
			}
			
			.mui-table-view .mui-media-object{
				margin-top: 15px;
				max-width: 60px;
				height: 60px;
				vertical-align: middle;
			}
		</style>
		<script type="text/javascript">
	
		
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">矿机详情</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view">
				${db._id}
				价值：${db.price}
				开始时间：${db.createdate}
				结束时间：${db.enddate}
			</ul>
		</div>
		<%@include file="/webcom/shop-foot3.jsp" %>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
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