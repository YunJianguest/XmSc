<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta charset="UTF-8"/>
		<title>售后申请</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css"/>
		<style type="text/css">
			.mui-table-view .mui-media-object{
				max-width: 94px;
				height: 94px;
				vertical-align: middle;
			}
			.mui-media-body .mui-ellipsis{
				color: #000;
			}
			.mui-media-body .mui-ellipsis span{
				margin-right: 10px;
			}
			.mui-row.returns{
				background: #fff;
				height: 40px;
				line-height: 40px;
				padding: 0 16px;
				font-size: 14px;
				display: flex;
				
			}
			.mui-row.returns .mui-radio input[type='radio']::before{
				font-size: 22px;
				line-height: 1.5;
			}
			.mui-row.returns .mui-radio input[type='radio']:checked::before{
				color: #000;
			}
			.opinion{
				background: #fff;
				margin-top: 14px;
				padding: 10px 15px 32px 15px;
			}
			.mui-input-row textarea{
				font-size: 14px;
			}
		</style>
		<script type="text/javascript">
                function submit(){
		         var submitData = {  
		       		 orderproId:$('#orderproId').val(),
		       		 type:$('input[name="state"]:checked').val(),
		       		 remark:$('#remark').val()
		        };
		
		        $.post('${ctx}/shop/service!ajaxsave.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
		        	function (json) {
		            	if(json.state==0){
		            		  alert('提交成功');
		            		  window.location.href="${ctx}/shop/shop!orderform.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
		            		 }else{
		            		   alert('操作失败，请重新提交');
		            	    }
		            },"json")
        
        
        }
       </script>
		
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">售后申请</h1>
		</header>
		<input id="orderproId" value="${db._id}" type="hidden"/>
		<div class="mui-content">
		    <div class="mui-row">
		    	<ul class="mui-table-view" style="margin-top: 14px;">
		    	    <li class="mui-table-view-cell mui-media">
		    	        <a href="${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=${db.pro._id}">
		    	            <img class="mui-media-object mui-pull-left" src="${filehttp}${db.pro.logo}">
		    	            <div class="mui-media-body" style="padding-top: 10px;">
		    	                <p class="mui-ellipsis">${db.pro.ptitle} </p>
		    	                <p class="mui-ellipsis">${db.spec}</p>
		    	                <p class="mui-ellipsis">
		    	                	<span>价格:${db.pro.price}</span><span>数量:X${db.count}</span>
		    	                </p>
		    	            </div>
		    	        </a>
		    	    </li>
		    	</ul>
		    	<div class="mui-row returns mui-col-xs-12">
		    		<div class="mui-col-xs-2">请选择</div>
		    		<div class="mui-input-row mui-col-xs-5 mui-radio mui-left">
		    			<input type="radio" class="" name="state" id="" value="2" checked/>
		    			<label style="padding-left: 48px">换货</label>
		    		</div>
		    		<div class="mui-input-row mui-col-xs-5 mui-radio mui-left">
		    			<input type="radio" class="" name="state" id="" value="1" />
		    			<label style="padding-left: 48px">退货</label>
		    		</div>
		    		
		    	</div>
		    </div>
		    
		    <div class="mui-row">
		    	<div class="mui-col-xs-12 opinion">
		    		<h5 style="color: #000;">问题描述</h5>
		    		<div class="mui-input-row">
		    			<textarea id="remark" name="" rows="5" cols="5" placeholder="请您在此描述问题"></textarea>
		    		</div>
		    	</div>
		    </div>
		    <div class="mui-row">
		    	
		    </div>
		</div>
		
		<div class="mui-bar nav-bottom mui-bar-footer" style="display: flex;align-items: center;justify-content: center;">
			<button class="btn " style="background: #FF0000;color: #fff;width: 100px;" onclick="submit()">提交</button>
		</div>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
		</script>
	</body>
</html>