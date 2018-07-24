<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>支付申请</title>
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		 <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
         <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
		<style type="text/css">
			*{
				margin:0;
				padding:0;
				box-sizing:border-box;
			}
			a{
				text-decoration:none;
			}
			li{
				list-style:none;
			}
			html{
				width: 100%;
				height:100%;
			}
			body{
				width: 100%;
				height:100%;
				background: #fff;
				position: relative;
			}
			.payment_money{
				width: 100%;
				height:auto;
				overflow: hidden;
				padding: 15px 35px;
			}
			.payment_money>p,.payment_money>div>p{
				width: 100%;
				height:auto;
				overflow: hidden;
				font-size: 12px;
				color:#aaa;
				font-family: "微软雅黑";
				margin-bottom: 10px;
			}
			
			.payment_money>p>span,.payment_money>div>p>span{
				display: inline-block;
				width: 30%;
				height:30px;
				line-height: 30px;
				text-align: center;
			}
			.payment_money>p>span{
				border: 1px solid #DDDDDD;
				color:#333;
				line-height: 29px;
			}
			.payment_money>p>input,.payment_money>div>p>input{
				width: 65%;
				height:30px;
				outline: none;
				border: 1px solid #eee;
				padding-left: 10px;
				font-size: 12px;
				color:#aaa;
				font-family: "微软雅黑";
				border-radius:3px;
				float: right;
			}
			.payment_money>div{
				margin-top: 20px;
			}
			
			
			button{
				width: 100%;
				height: 30px;	
				outline: none;
				border:none;
				border-radius:5px;
				background: #E4393C;
				color:#FFF;
				font-family: "微软雅黑";
				font-size:12px;
				font-weight: bold;
				margin-top: 30px;
			}
			.money_box{
				display: none;
			}
			/*确认申请提现弹框样式*/
			.Submission_box{
				width: 100%;
				height:100%;
				background: rgba(0,0,0,0.5);
				position:absolute;
				left:0;
				top:0;
				display: none;
			}
			.Submission_box>div{
				width:260px;
				height:200px;
				background: #fff;
				padding: 30px;
				margin-left: -130px;
				margin-top: -100px;
				position:absolute;
				left:50%;
				top:50%;
				border-radius:5px;
			}
			.Submission_box>div>p{
				width: 100%;
				height:30px;
				line-height: 30px;
				font-size: 14px;
				font-family: "微软雅黑";
				color:#555;
				margin-bottom: 5px;
			}
			.Submission_box>div>p>input{
				width: 100px;
				height:100%;
				outline: none;
                border:1px solid #eee;
				padding-left:5px;
				font-size:12px;
				margin-left: 30px;
			}
			.Submission_box>div>p>span{
				margin-left: 30px;
			}
			.Submission_box>div>p:last-of-type{
				margin-top: 15px;
				margin-bottom: 0;
			}
			.Submission_box>div>p:last-of-type>span{
				display: inline-block;
				width: 50px;
				height:25px;
				line-height: 25px;
				background: #eee;
				color:#555;
				text-align: justify;
				border-radius:3px;
				margin-left: 0;
			}
		</style>
	</head>
	<body>
		<div class="payment_money">
			<p><span class="Method" style="border-radius: 5px;">银行卡</span><span class="Method" style="float:right;border-radius: 5px;">支付宝</span></p>
			<input id="mui_title" type="hidden" value="1"/>
			<div class="money_box">
				<p><span style="text-align: left;">提&nbsp;现&nbsp;金&nbsp;额：</span><input  type="text" placeholder="请输入提现金额" id="yprice"/></p>
			    <p><span style="text-align: left;">银行卡卡号：</span><input type="text" placeholder="请输入银行卡号" id="ycode"/></p>
			    <p><span style="text-align: left;width: 30%;">所&nbsp;属&nbsp;银&nbsp;行：</span><input type="text" placeholder="请输入所属银行" id="yname"/></p>
			    <p><span style="text-align: left;width: 30%;">信&nbsp;息&nbsp;备&nbsp;注：</span><input type="text" placeholder="申请备注" id="yremark"/></p>
			</div>
			<div class="money_box">
				<p><span style="width: 30%;text-align: left;">提&nbsp;现&nbsp;金&nbsp;额：</span><input  type="text" placeholder="请输入提现金额" id="zprice"/></p>
			    <p>
			    	<span style="text-align: left;width: 30%;">支付宝账号：</span>
			    	<input type="text" placeholder="请输入支付宝账号" id="zcode"/>
			    </p>
			    <p><span style="text-align: left;width: 30%;">信&nbsp;息&nbsp;备&nbsp;注：</span><input type="text" placeholder="申请备注" id="zremark"/></p>
			    
			</div>
			
			<p><button class="Submission">申请提现</button></p>	
			<p style="font-size:12px;color:#333;">本次提现收取10%的手续费。</p>
		</div>
		<!--确认申请提现弹框-->
			<div class="Submission_box">
				<div>
					<p>提现金额：<span id="price">20000</span></p>
					<p>手&nbsp;&nbsp;续&nbsp;&nbsp;费：<span id="cost">2000</span></p>
					<p><span style="margin-left: 0;">提现密码：</span><input type="password" id="password" placeholder="请输入提现密码"/></p>
					<p><span style="float:left;text-align: center;" class="onOff">取消</span><span  style="float:right;text-align: center;" onclick="resure()">确认</span></p>
				</div>
			</div>
		<%@include file="/webcom/shop-foot.jsp" %>
		<script type="text/javascript" src="${ctx}/xmMobile/js/jquery-2.1.0.js"></script>
		<script type="text/javascript">
			$(function(){
				$(".Method").eq(0).css({"background":"#E4393C","color":"#fff"})
				$(".money_box").eq(0).css({"display":"block"})
				$(".Method").each(function(index){
					$(this).click(function(){
						$(".Method").css({"background":"","color":"#aaa"})
						$(this).css({"background":"#E4393C","color":"#fff"})
						$(".money_box").css({"display":"none"})
						.eq(index).css({"display":"block"})
						
						if($(this).html()=='银行卡'){
							$("#mui_title").val(1);
						}else if($(this).html()=='支付宝'){
							$("#mui_title").val(2);
						}
					})
				})
				
			})
			$(".Submission").click(function(){
					if($("#mui_title").val()==1){
						if($("#yprice").val() == ''){
							alert('请输入金额');
							return;
						}
						if($("#yprice").val() == 0){
							alert('提现金额不能为0');
							return;
						}
						if($("#ycode").val() == ''){
							alert('请银行卡号');
							return;
						}
						if($("#yname").val() == ''){
							alert('请输入所属银行');
							return;
						}
						$("#price").html($("#yprice").val());
					}
					if($("#mui_title").val()==2){
						if($("#zprice").val() == ''){
							alert('请输入金额');
							return;
						}
						if($("#zprice").val() == 0){
							alert('提现金额不能为0');
							return;
						}
						if($("#zcode").val() == ''){
							alert('请支付宝账号');
							return;
						}
						$("#price").html($("#zprice").val());
						
					}
					$("#cost").html(Number($("#price").html())*Number("0.1").toFixed(2));
					$(".Submission_box").css({"display":"block"})
				})
				//取消确认按钮
				$(".onOff").each(function(){
					$(this).click(function(){
						$(".Submission_box").css({"display":"none"})
					})
				})
				
				function resure(){
				if($('#password').val() == ''){
					alert('请输入密码');
					return;
				} 
				var submitData = { 
		    			  password:$('#password').val()
		    	    }; 
		    		 $.post('${ctx}/integral/miners!wdpassword.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
					        	function (json) {
					            	if(json.state==0){ 
								        var submitData = "";
									    if($("#mui_title").val()==1){
									    	submitData = {
									            price: $("#yprice").val(),
									            type: 1,
									            yname: $("#yname").val(),
									            account: $("#ycode").val(),
									            remark: $("#yremark").val()
									        }; 
									    }
					                    if($("#mui_title").val()==2){
					                    	submitData = {
					    				            price: $("#zprice").val(),
					    				            type: 2,
					    				            account: $("#zcode").val(),
					    				            remark: $("#zremark").val()
					    				        }; 
									    }
					                    $.post("${ctx}/integral/commission!ajaxsave.action?custid=${custid}&agid=${agid}&lscode=${lscode}", submitData,
					                        	function (json) {
					                            	if(json.state == 0){
					                            		alert('提现申请成功，请等待审核');
					                            	}   
					                            	if(json.state == 1){
					                            		alert('操作失败，请重新提交');
					                            	}
					                            	if(json.state == 2){
					                            		alert('信息不足，请重新填写');
					                            	}
					                            	if(json.state == 3){
					                            		alert('请重新登录');
					                            	}
					                            	window.location.reload();
					                            }, "json")
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
	</body>
</html>
