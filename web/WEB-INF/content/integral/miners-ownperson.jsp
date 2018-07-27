<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>个人中心</title>
    <!-- Resource style -->
    <script src="${ctx }/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet"/> 
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <style>
.line-bottom-dddddd {
            border-bottom: 1px solid #dddddd;
        }

        .line-top-dddddd {
            border-top: 1px solid #dddddd;
        }

        .line-left-dddddd {
            border-left: 1px solid #dddddd;
        }

        .line-right-dddddd {
            border-right: 1px solid #dddddd;
        }

        .bg-f5f5f9 {
            background-color: #f5f5f9;
        }

        .zi-6b6b6b {
            color: #6b6b6b
        }

        .line-height22 {
            line-height: 22px;
        }
        .mine-headbox{
        	min-height:200px;
        	background:url(${ctx}/mvccol/img/mine-headerback.jpg);
        	background-size:100% 100%;
        }
        .line-height30 {
    		line-height: 24px !important;
		}
		.tab-nav{
			width:100%;
			height:80px;
			/*display:-webkit-box;
			display:-ms-flexbox;
			display:flex;
			padding-top:20px;*/
			overflow: hidden;
		}
		.tab-nav li{
			/*-webkit-box-flex:1;
			    -ms-flex:1;
			        flex:1;
			width:1%;*/
			width: 25%;
			float: left;
			text-align: center;
			font-size: 12px;
		}
		.tab-nav li a{
			color: #000;
		}
		.tab-nav li i{
			font-size:20px;
			/*margin-bottom:10px;*/
			color: #f3e392;
		}
		.my_cate{
			/*position: relative;*/
    		padding-top: 13px;
    		padding-bottom: 10px;
    		overflow: hidden;
		}
		.my_cate_item {
		    -webkit-box-sizing: border-box;
		            box-sizing: border-box;
		    float: left;
		    width: 25%;
		    margin-bottom: 10px;
		}
		.my_cate_item a{
			text-decoration: none;
			color: #000;
		}
		.my_cate_item_logo {
		    display: block;
		    margin: 0 auto 7px;
		    width: 30px;
		    height: 30px;
		    padding: 5px;
		    border-radius: 5px;
		    text-align: center;
		    line-height: 20px;
		    color: #fff;
		}
		.my_cate_item_name {
		    font-size: 12px;
		    color: #666;
		    text-align: center;
		}
		.modal{
        	width: 100%;
        	height: 100%;
        	background: rgba(0,0,0,.7);
        	position: fixed;
        	top: 0;
        	left: 0;
        	right: 0;
        	bottom: 0;
        	z-index: 1001;
        	display: -webkit-box;
        	display: -ms-flexbox;
        	display: flex;
        	-webkit-box-pack: center;
        	    -ms-flex-pack: center;
        	        justify-content: center;
        	-webkit-box-align: center;
        	    -ms-flex-align: center;
        	        align-items: center;
        	display: none;
       }
        .modal-cont{
        	width: 80%;
        	height: 150px;
        	background: #fff;
        	border-radius: 10px;
        	z-index: 1002;
        	position: relative;
        	margin-top: -150px;
        }
        .modal-cont-tit{
        	width: 100%;
        	height: 30px;
        	line-height: 30px;
        	text-align: center;
        }
        .modal-cont-cont{
        	width: 100%;
        	height: 120px;
        	line-height: 20px;
        	font-size: 12px;
        	overflow-y: auto;
        	padding: 0 10px;
        }
        .modal-cont-foot{
        	width: 100%;
        	height: 30px;
        	line-height: 30px;
        	text-align: center;
        	position: absolute;
        	bottom: -50px;
        	left: 0;
        	color: #fff;
        }
        .modal-cont-foot span{
        	display: block;
        	margin: 0 auto;
        	border-radius: 50%;
        	width: 24px;
        	height: 24px;
        	line-height: 24px;
        	border: 1px solid #fff;
        }
        .collector{
        	width: 100%;
        	height: 80px;
        	display: -webkit-box;
        	display: -ms-flexbox;
        	display: flex;
        	padding: 20px 0;
        	background: #fff;
        }
        .collector li{
        	-webkit-box-flex: 1;
        	    -ms-flex: 1;
        	        flex: 1;
        	width: 1%;
        	text-align: center;
        }
        .collector li a{
        	color: #000;
        	font-size: 12px;
        	color: #999;
        }
        .collector li a div{
        	margin-bottom: 5px;
        	color: #6D6D6D;
        }
        .vipNumName{
        	color: #F02B2B;
        }
    </style>
     
</head>
<body class="cmp640">
	
	<header style="width: 100%;height: 44px;line-height: 44px;text-align: center;padding: 0 10px;background: #fff;">
			<a  href="javascript:history.go(-1);" style="display: inline-block;float: left;width: 30px;height: 30px;background: url('${ctx}/xmMobile/img/goback.png') no-repeat;background-size: 100% 100%;margin-top: 10px;"></a>
			个人中心
	</header>
<main style='padding-bottom: 50px;z-index: -10;'>
   	<div class="overflow-hidden width-10 position-r line-bottom-dddddd mine-headbox pd-20">
   	<div style="width: 100%;height: 45px;">
   		<div class="pull-left" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}&backurl=/integral/miners!ownperson.action'" style='margin-top: 15px;margin-left:15px;'>
   			<i class="zi-lan-tq fa fa-gear" style='font-size:16px;color:#000;'></i>
   		</div>
   		<!-- <div class="pull-right" style="margin-top: 15px;margin-right: 15px;">
   			<i class="zi-lan-tq fa fa-commenting-o" style='font-size:16px;color:#000;'></i>
   		</div> -->
   	</div>
        <div class="img-wh70 position-a border-radius50" style="top: 50%;left: 0;margin-left:20px;margin-top:-35px;">
          <c:if test="${empty entity.headimgurl}">
            <img src="${ctx}/mvccol/img/user/weizhuce.jpg" class="width-10 border-radius50"/>
          </c:if>
          <c:if test="${not empty entity.headimgurl}">
            <img src="${filehttp}/${entity.headimgurl}" class="width-10 border-radius50"/>
          </c:if>
           
        </div>
        <div class="width-10" style='position:absolute;bottom:15px;'>
            <div class=" hang70">
                <font size="2">
                    <div class="hang25 txt-c line-height25 zi-hei-tq weight500">${entity.nickname}</div>
                    <font size="1">
                    	<div class="clear txt-c pt-10" style='display:flex;justify-content: center;'>
	                            <div class="pull-left hang20 line-height22 bg-cheng zi-bai border-radius3 pl-5 pr-5"><i
	                                    class="fa fa-user line-height20"></i><i class="pl-2" id="sfxs">
	                                    <c:if test="${user.agentLevel==1}">省级代理</c:if>
	                                    <c:if test="${user.agentLevel==2}">市级代理</c:if>
	                                    <c:if test="${user.agentLevel==3}">县级代理</c:if>
	                                    <c:if test="${user.agentLevel==4}">报单中心</c:if>
	                                    <c:if test="${user.agentLevel== '' || user.agentLevel == null}">
	                                        <c:choose>
	                                            <c:when test="${user._id != 'notlogin'}">
	                                                                                                                                         游客
	                                            </c:when>
	                                            <c:otherwise>
	                                            未登录                                                                                                
                                                </c:otherwise>
	                                        </c:choose>
	                                    </c:if>
	                                    </i></div>
	                           
	                        </div>
                    </font>
                    <!-- 会员编号 -->
                   <div class="hang25 txt-c line-height25 zi-hei-tq weight500">
                   		<span class="vipNumName">会员编号：</span><span>${user.no}</span>
                   </div>
                    
                    <%-- <div class="hang25 line-height20 pt-5" style="color:#888888">
                        <div class="txt-c"><i class="pr-10 zi-cheng">积分<i class="pl-2 zi-cheng">${entity.jf}</i></i><c:if test="${not empty  entity.email}">${entity.email}</c:if><c:if test="${empty entity.email}">这家伙很懒，没有邮箱！</c:if></div>
                        <<div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'"><i class="zi-lan-tq">修改</i></div> 
                    </div> --%>
                </font>
            </div>
            <div style="padding:0 15px;font-size: 12px;margin-top: 25px;height: auto;overflow: hidden;">
             
                <c:if test="${not empty llb}">
                <span style="color: #FF0000;float:left">LLB:<i style="color: #000;">${llb}</i></span> 
                </c:if>
                <c:if test="${empty llb}">
                <span style="color: #FF0000;float:left">LLB:<i style="color: #000;">0.00</i></span> 
                </c:if>
                 
            	<c:if test="${not empty jf}">
            	<span style="color: #FF0000;float:right">PPB:<i style="color: #000;">${jf}</i></span> 
            	</c:if> 
            	<c:if test="${empty jf}">
            	<span style="color: #FF0000;float:right">PPB:<i style="color: #000;">0.00</i></span> 
            	</c:if>   
            </div>
            <!--<div style="text-align: right;color: #FF0000;">冻结PPB：<i style="color: #000;">1000000.00000</i></div>-->
        </div>
    </div>
    <div class="clear hang10"></div> 
      <!-- 个人中心导航 -->
    <ul class="my_cate">
    	
    	<li class="my_cate_item">
    		<a href="${ctx}/integral/miners!list.action?custid=${custid}&agid=${agid}&lscode=${lscode}">
    			<span class="my_cate_item_logo  bg-cheng">
    				<i class="fa fa-diamond"></i>
    			</span>
    			<p class="my_cate_item_name ">矿机商城</p>
    		</a>
    	</li>
    	<li class="my_cate_item">
    		<a href="${ctx}/integral/miners!ownminer.action?custid=${custid}&agid=${agid}&lscode=${lscode}">
    			<span class="my_cate_item_logo  bg-cheng">
    				<i class="fa fa-diamond"></i>
    			</span>
    			<p class="my_cate_item_name ">我的矿机</p>
    		</a>
    	</li>
		<li class="my_cate_item">
    		<a href="${ctx}/integral/miners!ppbtj.action?custid=${custid}&agid=${agid}&lscode=${lscode}">
    			<span class="my_cate_item_logo  bg-cheng"><i class="fa fa-diamond"></i></span>
    			<p class="my_cate_item_name ">我的盼盼币收益</p>
    		</a>
    	</li>
    	<li class="my_cate_item">
    		<a href="${ctx}/suc/integral!webll.action?custid=${custid}&lscode=${lscode}">
    			<span class="my_cate_item_logo bg-cheng"><i class="fa fa-diamond"></i></span>
    			<p class="my_cate_item_name ">我的乐乐币收益</p>
    		</a>
    	</li>
    	<li class="my_cate_item">
    		<a href="${ctx}/integral/miners!szjy.action?custid=${custid}&agid=${agid}&lscode=${lscode}">
    			<span class="my_cate_item_logo bg-cheng"><i class="fa fa-diamond"></i></span>
    			<p class="my_cate_item_name ">数字交易</p>
    		</a>
    	</li>
    	<li class="my_cate_item">
    		<a href="javascript:void(0)">
    			<span class="my_cate_item_logo  bg-cheng"><i class="fa fa-diamond"></i></span>
    			<p class="my_cate_item_name ">我的待定</p>
    		</a>
    	</li>
    </ul>
    <!--<div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    <div class="clear hang10"></div> -->
     <div class="modal">
	<div class="modal-cont">
		<div class="modal-cont-tit">
			提示
		</div>
		<div class="modal-cont-cont">
			提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示
		</div>
		<div class="modal-cont-foot">
			<span class="fa fa-close"></span>
		</div>
	</div>
</div>
	<!-- <div class="bmask">
		<div class="bmask-cont">
			<div class="bmask-cont-tit">
				请选择付产币方式
				<i class="fa fa-close pull-right" style="font-size: 16px;padding-right: 5px;padding-top: 5px;" onclick="cllx()" id="close"></i>
			</div>
			<div class="bmask-cont-tit">
				只有选择产币方式后矿机才能正常产币
			</div>
			<div class="bmask-cont-cont"> 
				<button onclick="popcode(1)" class="currency">盼盼币</button>
			</div>
		</div>
	</div> -->
	  
	<div class="modal">
		<div class="modal-cont">
			<div class="modal-cont-tit">
				提示
			</div>
			<div class="modal-cont-cont">
				提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示提示
			</div>
			<div class="modal-cont-foot">
				<span class="fa fa-close"></span>
			</div>
		</div>
	</div>
<script>
		function cllx(){
			$(".bmask").hide(); 
		}
		function  popcode(v){
			  var submitData = {
					  type:v
					};
					$.post('${ctx}/integral/miners!stkj.action?custid=${custid}&lscode=${lscode}', submitData, function(json) {
						  if(json.state==0){
							  alert("设置成功！");
							  window.location.href=window.location.href;
						  }
					}, "json");
					  
		}
		
		function getcbLx(){
			  var submitData = { 
					};
					$.post('${ctx}/integral/miners!getkjlx.action?custid=${custid}&lscode=${lscode}', submitData, function(json) {
						if(json.state==0){
							if(json.data==0){
								$(".bmask").show();
							}
						}else if(json.state==2){
							$(".bmask").show(); 
						}else {
							alert("获取产币类型失败！");
						} 
						
						
					}, "json");
					  
		}
		getcbLx();
		$('#close').click(function(){
				$('.mask').css('display','none')
		})
		$('.modal-cont-foot span').click(function(){
		 	$('.modal').css('display','none')
		})
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
		
		function checksf(){
			 if($.trim($("#sfxs").html())=="游客"){
				  $.post('${ctx}/user/user!getScxf.action?lscode=${lscode}', submitData,
		                  function (json) {
					      if(json.state==0){
					    	  $("#sfxs").html("会员"); 
					      } 
		                  }, "json")
			 }
			 
			
		}
		checksf();

</script>
<%@ include file="/webcom/toast.jsp" %>
<c:if test="${com.zsjf>0}">
  <c:if test="${sczs==1}">
  <%@ include file="/webcom/jfts-page.jsp" %>
  </c:if> 
</c:if> 
<%@include file="/webcom/shop-foot3.jsp" %>
</body>
</html>