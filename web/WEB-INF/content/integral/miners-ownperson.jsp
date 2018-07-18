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
    <link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet"/> 
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
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
        .my_cate{
			/*position: relative;*/
    		padding-top: 13px;
    		padding-bottom: 10px;
    		overflow: hidden;
		}
		.my_cate_item {
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
        	display: flex;
        	justify-content: center;
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
        	font-size: 16px;
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
        .vipNumName{
        	color: #F02B2B;
        }
    	.bmask{
				width: 100%;
				height: 100%;
				background: rgba(0, 0, 0, .1);
				position: fixed;
				top: 0;
				left: 0;
				right: 0;
				bottom: 0;
				z-index: 1008;
				display: none;
			}
			
			.bmask-cont {
				width: 100%;
				height: auto;
				background: #fff;
				position: absolute;
				bottom: 50px;
				left: 0;
				padding: 0 10px;
				z-index: 101;
			}
			
			.bmask-cont-tit {
				width: 100%;
				height: 30px;
				line-height: 30px;
				text-align: center;
				position: relative;
			}
			
			.bmask-cont-tit::after {
				content: '';
				width: 100%;
				height: 0.5px;
				position: absolute;
				bottom: 0;
				left: 0;
				background: #ddd;
			}
			
			.bmask-cont-cont {
				width: 100%;
				height: auto;
				padding-bottom: 10px;
			}
			
			.bmask-cont-cont button {
				width: 100%;
				height: 34px;
				line-height: 34px;
				background: none;
				border: 1px solid #000;
				border-radius: 5px;
				display: block;
				margin-top: 10px;
			}
			
    </style>
     
</head>
<body class="cmp640">
	 <header style="width: 100%;height: 40px;line-height: 40px;text-align: center;padding: 0 10px;background: #fff;">
		<a href="javascript:history.go(-1);" style="float: left;color: #000;width: 30px;display: inline-block;height: 40px;line-height: 40px;" class="mui-icon mui-icon-undo"></a>
			个人中心
	</header>
<main>
   <div class="overflow-hidden width-10 position-r line-bottom-dddddd mine-headbox pd-20">
   <div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'" style='margin-top: 15px;margin-right:15px;'><i class="zi-lan-tq fa fa-gear" style='font-size:16px;color:#000;'></i></div>
        <div class="img-wh70 position-a border-radius50" style="top: 50%;left: 50%;margin-left:-35px;margin-top:-70px;">
          <c:if test="${empty entity.headimgurl}">
            <img src="${ctx}/mvccol/img/user/weizhuce.jpg" class="width-10 border-radius50"/>
          </c:if>
          <c:if test="${not empty entity.headimgurl}">
            <img src="${filehttp}/${entity.headimgurl}" class="width-10 border-radius50"/>
          </c:if>
           
        </div>
        <div class="width-10" style='position:absolute;bottom:20px;'>
            <div class=" hang70">
                <font size="2">
                    <div class="hang25 txt-c line-height25 zi-hei-tq weight500">${entity.nickname}</div>
                    <font size="1">
                    	<c:if test="${not empty daili}">
	                    	<div class="clear txt-c pt-10" style='display:flex;justify-content: center;'>
	                            <div class="pull-left hang20 line-height22 bg-cheng zi-bai border-radius3 pl-5 pr-5"><i
	                                    class="fa fa-user line-height20"></i><i class="pl-2">${daili }</i></div>
	                           
	                        </div>
                    	</c:if>
                    </font>
                    <!--<font size="1">
                        <div class="clear txt-c pt-5" style='display:flex;justify-content: center;'>
                            <div class="pull-left hang20 line-height22 bg-green zi-bai border-radius3 pl-5 pr-5 mr-5">LV<i
                                    class="pl-2">${entity.level}</i></div>
                            <c:if test="${empty isAgent}">
                             <div class="pull-left hang20 line-height22 bg-cheng zi-bai border-radius3 pl-5 pr-5"><i
                                    class="fa fa-user line-height20"></i><i class="pl-2">普通会员</i></div>
                            </c:if>
                            <c:if test="${not empty isAgent}">
                            <div class="pull-left hang20 line-height22 bg-cheng zi-bai border-radius3 pl-5 pr-5"><i
                                    class="fa fa-diamond line-height20"></i><i class="pl-2">店铺已认证</i></div>
                            </c:if>
                           
                        </div>
                    </font>-->
                    
                    <!--<div class="hang25 line-height20 pt-5" style="color:#888888">
                        <div class="txt-c"><i class="pr-10 zi-cheng">积分<i class="pl-2 zi-cheng">${entity.jf}</i></i><c:if test="${not empty  entity.email}">${entity.email}</c:if><c:if test="${empty entity.email}">这家伙很懒，没有邮箱！</c:if></div>-->
                        <%-- <div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'"><i class="zi-lan-tq">修改</i></div> --%>
                    <!--</div>-->
                     <div class="hang25 txt-c line-height25 zi-hei-tq weight500">
                   		<span class="vipNumName">会员编号：</span><span>${user.no}</span>
                   </div>
                </font>
                
            </div>
            <div style="padding:0 15px;font-size: 12px;margin-top: 25px;">
             
                <c:if test="${llb>0||llb==0.0}">
                <span style="color: #FF0000;float:left">LLB:<i style="color: #000;">${llb}</i></span> 
                </c:if>
                <c:if test="${llb==null}">
                <span style="color: #FF0000;float:left">LLB:<i style="color: #000;">0.00</i></span> 
                </c:if>
                 
            	<c:if test="${jf>0||jf==0.0}">
            	<span style="color: #FF0000;float:right">PPB:<i style="color: #000;">${jf}</i></span> 
            	</c:if> 
            	<c:if test="${jf==null}">
            	<span style="color: #FF0000;float:right">PPB:<i style="color: #000;">0.00</i></span> 
            	</c:if>   
               
            </div>
        </div>
    </div>
 
    
      <!--<div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
				<div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/integral/miners!list.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">矿机商城</div>
                    </font>
                  </div>
              </div>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/integral/miners!ownminer.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond   line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的矿机</div>
                    </font>
                  </div>
              </div>
          <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/suc/integral!web.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的盼盼币收益</div>
                    </font>
                  </div>
              </div>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" >
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的乐乐币收益</div>
                    </font>
                  </div>
              </div>
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd">
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的待定</div>
                    </font>
                  </div>
              </div>
   
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd">
                   <div class="txt-c img-wh30 bg-cheng maring-a border-radius5 zi-bai">
                     <i class="fa fa-diamond  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的待定</div>
                    </font>
                  </div>
              </div>
   -->
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
    		<a href="">
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
<div class="bmask">
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
</div>
</main>


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