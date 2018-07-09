<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>评价中心</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${contextPath}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/xmMobile/css/common.css" />
		<style type="text/css">
			.assess-top-img {
				width: 100%;
				height: 120px;
				/*background: url('img/assess-banner.png') no-repeat;*/
				background-size: 100% 100%;
			}
			
			.assess-top-img img {
				width: 100%;
				height: 100%;
			}
			
			.assess-start-box li {
				float: left;
				width: 24px;
				height: 24px;
				text-indent: -9999px;
				margin-right: 5px;
				background: url('${contextPath}/xmMobile/img/icon/icon-assess-start.png') no-repeat;
				background-size: 100% auto;
				background-position: 0 -25px;
			}
			
			.assess-start-box li.on {
				background: url('${contextPath}/xmMobile/img/icon/icon-assess-start.png') no-repeat;
				background-size: 100% auto;
				background-position: 0 0;
			}
		</style>
	</head>

	<body style="background: #fff;">
		<header class="mui-bar mui-bar-nav" style="background: #fff;">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">评价中心</h1>
		</header>
		<div class="mui-content" style="background: #fff;">
			<div class="mui-row">
				<div class="assess-top-img">
					<img src="${contextPath}/xmMobile/img/assess-banner.png" />
				</div>
			</div>
			<div class="mui-row" style="padding: 36px 30px 0 30px;">
				<div class="mui-ellipsis" style="line-height: 30px;font-size: 16px;">
					您的评价是我们持续前进的动力，请认真对待
				</div>
				<div class="mui-col-xs-12 mui-row">
					<div class="mui-col-xs-4">商品评价：</div>
					<div class="mui-col-xs-8">
						<ul class="assess-start-box mui-col-xs-12 goodsEvalu">
							<li><i>1</i></li>
							<li><i>2</i></li>
							<li><i>3</i></li>
							<li><i>4</i></li>
							<li><i>5</i></li>
						</ul>
					</div>
				</div>
				<div class="mui-col-xs-12 mui-row">
					<div class="mui-col-xs-4">客服态度：</div>
					<div class="mui-col-xs-8">
						<ul class="assess-start-box mui-col-xs-12 serviceEvalu">
							<li><i>1</i></li>
							<li><i>2</i></li>
							<li><i>3</i></li>
							<li><i>4</i></li>
							<li><i>5</i></li>
						</ul>
					</div>
				</div>
				<div class="mui-col-xs-12 mui-row">
					<div class="mui-col-xs-4">物流运输：</div>
					<div class="mui-col-xs-8">
						<ul class="assess-start-box mui-col-xs-12 logisticsEvalu">
							<li><i>1</i></li>
							<li><i>2</i></li>
							<li><i>3</i></li>
							<li><i>4</i></li>
							<li><i>5</i></li>
						</ul>
					</div>
				</div>

				<div class="mui-input-row" style="padding-top: 30px;">
					<textarea name="" id="cause" rows="5" cols="" maxlength="200" placeholder="请添加描述....."></textarea>
				</div>
			</div>
			<div class="mui-button-row mui-col-xs-12" style="position: fixed;bottom: 17px;">
				<button type="button" id="assessBtn" class="mui-btn mui-badge-red" style="background: #FF0000;">提交评价</button>
			</div>
		</div>
		<script src="${contextPath}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function chooseStart() {
				var ali = $('.assess-start-box li')
				ali.click(function() {
					var num = $(this).index();
					var list = $(this).parent().find('li');
					for(var i = 0; i <= num; i++) {
						list.eq(i).attr('class', 'on')
					}
					for(var i = num + 1, len = list.length - 1; i <= len; i++) {
						list.eq(i).attr('class', '');
					}
				})
			}
			chooseStart();
			var goodsevalulen;
			var logisticsEvalu;
			var serviceEvalu;
			$('.goodsEvalu li').click(function(){
				goodsevalulen = $(this).children('i').text();
				console.log('商品评价'+goodsevalulen)
			})
			$('.serviceEvalu li').click(function(){
				serviceEvalu = $(this).children('i').text();
				console.log('客服态度'+serviceEvalu)
			})
			$('.logisticsEvalu li').click(function(){
				logisticsEvalu = $(this).children('i').text();
				console.log('物流运输'+logisticsEvalu)
			})
			$('#assessBtn').click(function(){
				$.ajax({
					type:"post",
					url:"${ctx}/shop/shopcom!ajaxSaveCom.action?custid=${custid}&agid=${agid}&lscode=${lscode}",
					async:true,
					data:{
						oid:'${oid}',
						gid:'${gid}',
						sid:'${sid}',
						goodsevalulen:goodsevalulen,
						serviceEvalu:serviceEvalu,
						logisticsEvalu:logisticsEvalu,
						cause:$('#cause').val()
					},
					success:function(json){
						if(json.state==0){
							
							alert('提交成功');
		            		window.location.href="${ctx}/shop/shop!orderform.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
						}else{
		            		alert('操作失败，请重新提交');
	            	    }
					}
				});
			})
		</script>
	</body>

</html>