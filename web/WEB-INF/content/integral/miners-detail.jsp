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
			<h1 class="mui-title">矿机购买</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view">
				${db._id}
				开始时间：${db.price}
				开始时间：${db.createdate}
				结束时间：${db.enddate}
			</ul>
		</div>
		
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		
		</script>
	</body>

</html>