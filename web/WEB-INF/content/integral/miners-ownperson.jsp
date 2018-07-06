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
    </style>
     
</head>
<body class="cmp640">
<main>
   <div class="overflow-hidden width-10 position-r line-bottom-dddddd mine-headbox pd-20">
   <div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'" style='margin-top: 15px;margin-right:15px;'><i class="zi-lan-tq fa fa-gear" style='font-size:16px;color:#fff;'></i></div>
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
                    
                    <div class="hang25 line-height20 pt-5" style="color:#888888">
                        <!--<div class="txt-c"><i class="pr-10 zi-cheng">积分<i class="pl-2 zi-cheng">${entity.jf}</i></i><c:if test="${not empty  entity.email}">${entity.email}</c:if><c:if test="${empty entity.email}">这家伙很懒，没有邮箱！</c:if></div>-->
                        <%-- <div class="pull-right" onclick="window.location.href='${ctx}/user/fromuser!detail.action?custid=${custid}&lscode=${lscode}'"><i class="zi-lan-tq">修改</i></div> --%>
                    </div>
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
   
    
      <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
				<div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/integral/miners!list.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30  maring-a border-radius5 zi-bai">
                     <i class="fa   line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">矿机商城</div>
                    </font>
                  </div>
              </div>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/integral/miners!ownminer.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30  maring-a border-radius5 zi-bai">
                     <i class="fa   line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的矿机</div>
                    </font>
                  </div>
              </div>
          <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/suc/integral!web.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30  maring-a border-radius5 zi-bai">
                     <i class="fa   line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的盼盼币收益</div>
                    </font>
                  </div>
              </div>
              <div class="col-4 hang100 line-bottom-dddddd pt-20 line-right-dddddd" onclick="window.location.href='${ctx}/suc/integralll!web.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                   <div class="txt-c img-wh30  maring-a border-radius5 zi-bai">
                     <i class="fa   line-height30"></i>
                   </div>
                  <div class="txt-c">
                    <font size="2">
                     <div class="hang40 line-height40 zi-6b6b6b">我的乐乐币收益</div>
                    </font>
                  </div>
              </div>
   
    <div class="clear hang10 bg-f5f5f9 line-bottom-dddddd"></div>
    <div class="clear hang10"></div> 
     
</main>
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
<%@include file="/webcom/shop-foot3.jsp" %>
</body>
</html>