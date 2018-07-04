<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta charset="UTF-8"/>
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css"/>
		<style type="text/css">
			
			.tip-msg{
				width: 100%;
				height: 100px;
				background: url('${ctx}/xmMobile/img/sale-back.jpg') no-repeat;
				background-size: 100% 100%;
				line-height: 50px;
				color: #fff;
				font-size: 12px;
				padding: 0 10px;
			}
			.tip-msg p{
				margin: 0;
				color: #fff;
				
			}
			.tip-txt{
				font-size: 16px;
			}
			.service-num{
				width: 100%;
				height: 60px;
				background: #fff;
				color: #000;
				padding: 0 10px;
				display: flex;
				justify-content: space-between;
				align-items: center;
				font-size: 14px;
			}
			.service-detaile{
				margin-top: 10px;
				padding: 0 10px;
				background: #fff;
			}
			.service-detaile-tit{
				width: 100%;
				height: 40px;
				line-height: 40px;
				font-size: 14px;
				font-weight: 600;
				position: relative;
			}
			.service-detaile-tit::before{
				content: '';
				width: 100%;
				height: 0.5px;
				background: #ccc;
				position: absolute;
				left: 0;
				bottom: 0;
			}
			.service-detaile-cont{
				width: 100%;
				height: auto;
				font-size: 14px;
				line-height: 30px;
			}
		</style>
	</head>

	<body>
		<!--<header class="mui-bar mui-bar-nav" style="background: #fff;">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">售后详情</h1>
		</header>-->
		<div class="mui-content">
		    <div class="mui-row">
		    	<div class="tip-msg">
		    		<p class="tip-txt">待您反馈</p>
		    		<p class="mui-ellipsis">服务受理中</p>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	<div class="service-num">
		    		<span>服务单号  ${service._id}</span><span>申请时间${service.createdate}</span>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	<div class="service-detaile">
		    		<div class="service-detaile-tit">问题描述</div>
		    		<div class="service-detaile-cont" style="line-height: 24px;text-indent: 24px;">
		    			${service.remark}
		    		</div>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	<div class="service-detaile">
		    		<div class="service-detaile-tit">服务单信息</div>
		    		<div class="service-detaile-cont">
		    			<div>服务类型：维修</div>
		    			<div>申请原因：质量问题</div>
		    			<div>联系人：   </div>
		    			<div>联系电话：</div>
		    			<div>收货地址：</div>
		    		</div>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	<div style="width: 100%;height: auto;padding: 10px 0;padding-top: 30px;background: #fff;text-align: right;">
		    		<button class="mui-btn" style="margin-right: 20px;">取消申请</button>
		    	</div>
		    </div>
		</div>
		<script type="text/javascript">
			
		</script>
	</body>

</html>