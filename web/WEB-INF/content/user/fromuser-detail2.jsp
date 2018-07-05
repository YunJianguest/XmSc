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
			width:100%;height:80px;display:flex;padding-top:20px;
		}
		.tab-nav li{
			flex:1;
			width:1%;
			text-align: center;
		}
		.tab-nav li a{
			color: #000;
		}
		.tab-nav li i{
			font-size:20px;
			margin-bottom:10px;
			color:#fff;
		}
    </style>
     
</head>
<body class="cmp640">
<main>
   <div class="overflow-hidden width-10 position-r line-bottom-dddddd mine-headbox pd-20">
   <div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'" style='margin-top: 15px;margin-right:15px;'>
   <i class="zi-lan-tq fa fa-gear" style='font-size:16px;color:#fff;'></i></div>
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
                    </font>
                    
                    <%-- <div class="hang25 line-height20 pt-5" style="color:#888888">
                        <div class="txt-c"><i class="pr-10 zi-cheng">积分<i class="pl-2 zi-cheng">${entity.jf}</i></i><c:if test="${not empty  entity.email}">${entity.email}</c:if><c:if test="${empty entity.email}">这家伙很懒，没有邮箱！</c:if></div>
                        <<div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'"><i class="zi-lan-tq">修改</i></div> 
                    </div> --%>
                </font>
            </div>
        </div>
    </div>

    

    <c:if test="${not empty isAgent}">
    <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
     <div class="col-6 line-right-dddddd line-bottom-dddddd hang70 pt-20" onclick="window.location.href='${ctx}/shop/shop!agenttx.action?custid=${custid}&lscode=${lscode}&agid=${agid}'">
        <div class="hang30 width-6 maring-a">
            <div class="pull-left txt-c img-wh30 bj-cheng border-radius5 zi-bai"><i
                    class="fa fa-dollar  line-height30"></i></div>
            <div class="pull-left pl-10">
                <font size="2">
                    <div class="hang20 zi-hei">佣金管理</div>
                </font>
                <font size="1">
                    <div class="hang20 zi-hui-wx">佣金:<fmt:formatNumber value='${agent.price}' pattern="0.0#"/></div>
                </font>
            </div>
        </div>
    </div>
     
    <div class="col-6 line-right-dddddd line-bottom-dddddd hang70 pt-20" onclick="window.location.href='${ctx}/shop/shop!agentweb.action?custid=${custid}&lscode=${lscode}&agid=${agid}'">
        <div class="hang30 width-6 maring-a">
            <div class="pull-left txt-c img-wh30 bj-lan1 border-radius5 zi-bai"><i
                    class="fa fa-bar-chart  line-height30"></i></div>
            <div class="pull-left pl-10">
                <font size="2">
                    <div class="hang20 zi-hei">佣金记录</div>
                </font>
                <font size="1">
                    <div class="hang20 zi-hui-wx">佣金排行:0</div>
                </font>
            </div>
        </div>
    </div>
    </c:if>
   
    <c:if test="${fn:length(func.lsfunc)>0}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    </c:if> 
	<ul class='tab-nav'>
		<li>
			<a href="${ctx}/shop/shop!orderform.action?custid=${custid}&lscode=${lscode}">
				<div class="txt-c ${bean.color } maring-a border-radius5 bg-cheng img-wh30 mb-10" style='margin-bottom:10px;'>
	            <i class="fa fa-bars  line-height30"></i>
	            </div>全部订单
            </a>
          </li>
		<li>
			<a href="${ctx}/shop/shop!orderform.action?custid=${custid}&lscode=${lscode}&state=0">	
				<div class="txt-c ${bean.color } maring-a border-radius5 bg-cheng img-wh30 mb-10" style='margin-bottom:10px;'>
	              <i class="fa fa-credit-card  line-height30"></i>
	            </div>待付款
              </a>     
        </li>
        <li>
        	<a href="${ctx}/shop/shop!orderform.action?custid=${custid}&lscode=${lscode}&state=2">	
		       <div class="txt-c ${bean.color } maring-a border-radius5 bg-cheng img-wh30" style='margin-bottom:10px;'>
		         <i class="fa fa-truck  line-height30"></i>
		       </div>待发货
	       </a> 
        </li>
		<li>
			<a href="${ctx}/shop/shop!orderform.action?custid=${custid}&lscode=${lscode}&state=3">	
				<div class="txt-c ${bean.color } maring-a border-radius5 bg-cheng img-wh30 mb-10" style='margin-bottom:10px;'>
                  <i class="fa fa-envelope  line-height30"></i>
                </div>待收货
            </a>
        </li>
		
		<li>
			<a href="${ctx}/shop/shop!orderform.action?custid=${custid}&lscode=${lscode}&state=4">	
				<div class="txt-c ${bean.color } maring-a border-radius5 bg-cheng img-wh30" style='margin-bottom:10px;'>
	            	<i class="fa fa-commenting-o  line-height30"></i>
	          	</div>待评价
          	</a>
        </li>
	</ul>
	<c:if test="${fn:length(func.lsfunc)>0}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    </c:if> 
    <c:forEach items="${func.lsfunc}" var="bean" varStatus="1" begin="0" end="5">
        <c:choose>
          <c:when test="${fn:contains(bean.url,'http')}">
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${bean.url}'">
                   <div class="txt-c ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          </c:when>
          <c:otherwise>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}${bean.url}?custid=${custid}&lscode=${lscode}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          
          </c:otherwise> 
        </c:choose>
       
    </c:forEach>
   
     
      <c:if test="${fn:length(func.lsfunc)>6}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
      </c:if> 
        <c:forEach items="${func.lsfunc}" var="bean" varStatus="1" begin="6" end="11">
        <c:choose>
          <c:when test="${fn:contains(bean.url,'http')}">
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${bean.url}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          </c:when>
          <c:otherwise>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}${bean.url}?custid=${custid}&lscode=${lscode}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          
          </c:otherwise> 
        </c:choose>
       
    </c:forEach> 
    <c:if test="${fn:length(func.lsfunc)>12}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    </c:if>   
    <c:forEach items="${func.lsfunc}" var="bean" varStatus="1" begin="12" end="17">
        <c:choose>
          <c:when test="${fn:contains(bean.url,'http')}">
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${bean.url}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc} line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          </c:when>
          <c:otherwise>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}${bean.url}?custid=${custid}&lscode=${lscode}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc} line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          
          </c:otherwise> 
        </c:choose>
       
    </c:forEach>
    <c:if test="${fn:length(func.lsfunc)>18}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    </c:if>  
    <c:forEach items="${func.lsfunc}" var="bean" varStatus="1" begin="18" end="23">
        <c:choose>
          <c:when test="${fn:contains(bean.url,'http')}">
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${bean.url}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          </c:when>
          <c:otherwise>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}${bean.url}?custid=${custid}&lscode=${lscode}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          
          </c:otherwise> 
        </c:choose>
       
    </c:forEach>
    <c:if test="${fn:length(func.lsfunc)>24}">
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    </c:if> 
    <c:forEach items="${func.lsfunc}" var="bean" varStatus="1" begin="24" end="29">
        <c:choose>
          <c:when test="${fn:contains(bean.url,'http')}">
               <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${bean.url}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc} line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          </c:when>
          <c:otherwise>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}${bean.url}?custid=${custid}&lscode=${lscode}'">
                   <div class="txt-c  ${bean.color } maring-a border-radius5 zi-bai" style='width:24px;height:24px;'>
                     <i class="fa ${bean.ioc}  line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">${bean.title}</div>
                    </font>
                  </div>
              </div>
          
          </c:otherwise> 
        </c:choose>
       
    </c:forEach>
    <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    <div class="clear hang10"></div> 
     
</main>
<%@include file="/webcom/shop-foot.jsp" %>
<script>
 
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
</body>
</html>