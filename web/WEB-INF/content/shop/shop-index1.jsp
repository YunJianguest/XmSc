<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>${title}</title>
    <script src="${ctx }/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet">  
    <script src="${ctx}/mvccol/js/fomatdate1.js"></script> 
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="${ctx }/mvccol/js/mtlb.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/bbsSwipe.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/swipe.js"></script>
    <link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/app/js/iosOverlay.js"></script>
    	<script src="${ctx}/app/js/spin.min.js"></script>
    	<script type="text/javascript" src="${ctx }/app/js/jquery.Spinner.js"></script>
    <script>
        $(function () {
            new Swipe(document.getElementById('banner_box'), {
                speed: 500,
                auto: 3000,
                callback: function () {
                    var lis = $(this.element).next("ol").children();
                    lis.removeClass("on").eq(this.index).addClass("on");
                }
            });
        });
        function xiaoshi() {
            $('#tanchu').hide();
        }
        function xianshi() {
            $('#tanchu').show();
        }
        function fl_xiaoshi() {
            $('#fl_tanchu').hide();
        }
        function fl_xianshi() {
            $('#fl_tanchu').show();
        }
        $(function () {
            $(".yListr3 li").click(function () {
                $(this).addClass("zhiding").siblings().removeClass("zhiding");
            })
        })
    </script>
    <script>
    var loadings="";
    function  loading(){
    var opts = {
	lines: 13, // The number of lines to draw
	length: 8, // The length of each line
	width: 4, // The line thickness
	radius: 13, // The radius of the inner circle
	corners: 1, // Corner roundness (0..1)
	rotate: 0, // The rotation offset
	color: '#FFF', // #rgb or #rrggbb
	speed: 1, // Rounds per second
	trail: 60, // Afterglow percentage
	shadow: false, // Whether to render a shadow
	hwaccel: false, // Whether to use hardware acceleration
	className: 'spinner', // The CSS class to assign to the spinner
	zIndex: 2e9, // The z-index (defaults to 2000000000)
	top: 'auto', // Top position relative to parent in px
	left: 'auto' // Left position relative to parent in px
}; 
   var target = document.createElement("div");
   document.body.appendChild(target);
   var spinner = new Spinner(opts).spin(target);
   loadings=iosOverlay({
	text: "Loading", 
	spinner: spinner
   });
 }
        var issend = true;
        var fypage = 0;
        var xszf = "";
        var type = "";
        var lx = "";
        var sel = "";
        function ajaxjz() {//加载
        	if(loadings==""){
        		loading();
        	} 
            if (!issend) {
                return;
            } 
            var submitData = { 
              cid:"${entity._id}"
            }; 
            issend = false;
            $.post('${ctx}/shop/shop!ajaxweb.action?custid=${custid}&agid=${agid}&fypage=' + fypage, submitData,
                    function (json) {
            	     loadings.hide();
                        var html = $('.recomend').html();
                        if (json.state == 0) {
                            var v = json.list;
                            /* html+='<div class="mui-content" style="padding-top: 44px;padding-bottom: 50px;background: #fff;">'
                                +'<div class="mui-row ">'
                            	+'<ul class="mui-table-view">'; */
                            for (var i = 0; i < v.length; i++) {
//                              if (i & 1 != 0) {
                                	
                                	html +='<li class="mui-table-view-cell mui-media mui-col-xs-6" >'
                						+'<div class="mui-card" style="margin: 2px;">'
                						+'<div class="mui-card-content">'
                						+'<a href="${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=' + v[i]._id + '">'
                						+'<img src="${filehttp}' + v[i].logo + '" />'
                						+'</a>'	
                						+'</div>'
                						+'<div class="mui-card-footer" style="padding: 10px 5px;display: block;">'
                						+'<span class="similar-product-text" style="height:40px;text-align: left;"><span class="shopping-name">${entity.name}</span>' + v[i].ptitle + ' </span>'
                						+'<div class="similar-product-info ">';
                						if(v[i].price!=null){
                							html+='<span class="similar-product-price"><span>￥</span>'+ v[i].price.toFixed(2)+'</span>';
                						} 
                						html+='<span class="virtualcoin">0.00</span><span class="similar-product-shopCar" onclick="cart('+v[i]._id+','+v[i].num+','+v[i].price+')"></span>'			
                						+'</div></div></div></li>';		
            		    	              
            					
//                              }
                            }
                          /*   html+='</ul></div></div>'; */
                            fypage++;
                            $('.recomend').html(html);
                            issend = true;
                        } else {
                        	//$('.recomend').html('暂无数据');
                        }
                       
                    }, "json")
              } 
        function goods_search(){
           	var goods_name = $("#sel").val();
           	if(goods_name!=""&&goods_name!=null){
           		window.location.href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&ptitle="+goods_name;
           	}
           }
    </script>
    <style type="text/css">
			.mui-table-view .mui-media-object{
				margin-top: 10px;
				max-width: 60px;
				height: 60px;
				vertical-align: middle;
				border-radius: 5px;
			}
			.mui-card .mui-card-content img {
				height: 124px;
			}
			.mui-table-view:after{
				display: none;
			}
			.similar-product-text {
				line-height: 33px;
				font-size: 12px;
				overflow: hidden;
				-o-text-overflow: ellipsis;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
				word-break: break-word;
				color: #232326;
				margin-top: 5px;
				line-height: 20px;
				margin-bottom: 3px;
				padding: 0 4px;
			}
			
			.similar-product-info {
				display: block;
				position: relative;
				overflow: hidden;
				height: 40px;
			}
			.similar-product-info span.similar-product-shopCar,.similar-product-info .similar-product-price{
				display: block;
				overflow: hidden;
			}
			.similar-product-shopCar {
				width: 18px;
				height: 18px;
				display: block;
				background: url(${ctx}/xmMobile/img/icon/icon-shopCar.png) no-repeat;
				background-size: 100% auto;
				position: absolute;
				right: 5px;
				top: 15px;
			}
			.similar-product-price{
				color: #fd0707;
				position: absolute;
				top: 0;
				left: 0px;
			}
			.virtualcoin{
				display: block;
			    position: absolute;
			    bottom: 0;
			    left: 0;
			    line-height: 20px;
			}
			.mui-table-view.mui-grid-view .mui-table-view-cell{
				padding-left: 5px;
			}
			.shopping-name{
				padding: 2px 5px;
				background: rgba(0,94,172,.5);
				color: #fff;
				margin-right: 5px;
				border-radius: 5px;
			}
		</style>
</head>
<body class="cmp640 bg-hui-98 lock">
<main style="position: relative"> 
    <header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color: #000;" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">${entity.name}</h1>
	</header>
    <c:if test="${not empty slide}">
        <div id="banner_box" class="box_swipe overflow-hidden position-r" style="width:100%">
            <ul>
                <c:forEach items="${slide}" var="bean">
                    <li>
                        <c:if test="${empty bean.mp4url}">
                         <a href="${bean.url}">
                            <img src="${filehttp}/${bean.picurl}" alt="1" style="width:100%;"/>
                         </a>
                        </c:if>
                        <c:if test="${not empty bean.mp4url}">
                          <iframe frameborder="0" width="500" height="375" src="${bean.mp4url}" allowfullscreen></iframe>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <script>
            $(function () {
                new Swipe(document.getElementById('banner_box'), {
                    speed: 500,
                    auto: 3000,
                    callback: function () {
                        var lis = $(this.element).next("ol").children();
                        lis.removeClass("on").eq(this.index).addClass("on");
                    }
                });
                  $('#banner_box').find('iframe').load(function(){
                  var mainwidth =$(document.body).width();
                  var mainheight=mainwidth*9/16;
                  $(this).width(mainwidth);
                  $(this).height(mainheight);
                 });
            });
        </script>
    </c:if>
    <c:if test="${not empty typelist}">
	<div class="page--index export  pb-15" >
    	<div class="hero-gallery js-flickity pb-5" data-js-module="hero-gallery">
            <div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
                <div class="width-10">
                    <div class="overflow-hidden border-radius5">
                    	<a href="javascript:fxsel('')">
                              <div class="col-3 mt-10">
                                <div class=" maring-a clear img-wh40 bg-cheng zi-bai txt-c border-radius50 overflow-hidden">
                                      <i class="fa fa-bars fa-1dx line-height40"></i>
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">全部</div>
                            </div>
                        </a>
           				<c:forEach items="${typelist}" var="bean"  begin="0" end="2">
                         <c:if test="${not empty bean.url}">
                        <div class="col-3 mt-10" onclick="window.location.href='${bean.url}'"> 
                                <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                       </c:if>
                      <c:if test="${empty bean.url}"> 
                         <div class="col-3 mt-10" onclick="fxsel('${bean.type}')">
                               <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                     </c:if>
                     </c:forEach>
            		</div> 
                </div>
            </div>
            
            <c:if test="${fn:length(typelist)>3}">
             <div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
                <div class="width-10">
                    <div class="overflow-hidden border-radius5"> 
           				<c:forEach items="${typelist}" var="bean"  begin="3" end="6">
                         <c:if test="${not empty bean.url}">
                        <div class="col-3 mt-10" onclick="window.location.href='${bean.url}'"> 
                                <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                       </c:if>
                      <c:if test="${empty bean.url}"> 
                         <div class="col-3 mt-10" onclick="fxsel('${bean.type}')">
                               <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                     </c:if>
                     </c:forEach>
            		</div> 
                </div>
            </div>
            </c:if>
            <c:if test="${fn:length(typelist)>6}">
             <div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
                <div class="width-10">
                    <div class="overflow-hidden border-radius5"> 
           				<c:forEach items="${typelist}" var="bean"  begin="6" end="9">
                         <c:if test="${not empty bean.url}">
                        <div class="col-3 mt-10" onclick="window.location.href='${bean.url}'"> 
                                <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                       </c:if>
                      <c:if test="${empty bean.url}"> 
                         <div class="col-3 mt-10" onclick="fxsel('${bean.type}')">
                               <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                     </c:if>
                     </c:forEach>
            		</div> 
                </div>
            </div>
            </c:if>
              <c:if test="${fn:length(typelist)>9}">
             <div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
                <div class="width-10">
                    <div class="overflow-hidden border-radius5"> 
           				<c:forEach items="${typelist}" var="bean"  begin="9" end="12">
                         <c:if test="${not empty bean.url}">
                        <div class="col-3 mt-10" onclick="window.location.href='${bean.url}'"> 
                                <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                       </c:if>
                      <c:if test="${empty bean.url}"> 
                         <div class="col-3 mt-10" onclick="fxsel('${bean.type}')">
                               <div class=" maring-a clear img-wh40 zi-bai txt-c overflow-hidden border-radius50">
                                    <i class="fa ${bean.ioc} mt-5 fa-2x"></i> 
                                </div>
                                <div class="txt-c size12 zi-hei mt-5 width-10">${bean.name}</div>
                         </div>
                     </c:if>
                     </c:forEach>
            		</div> 
                </div>
            </div>
            </c:if>
    	</div>
	</div> 
	</c:if>
    	<div class='mui-content' style="height: 100%;background-color: #fff;">
		    <div class="mui-input-row" style="position: relative;margin-top:6px;width:85%;margin-left:7.5%;">
				<span class="mui-icon mui-icon-search" style="position: absolute;top: 5px;right: 5px;" onclick="goods_search()"></span>
				<input type="search" name=""  placeholder="Search" style="padding-left: 30px;margin-bottom:6px;background:#eee;text-align: left;" id="sel">
			</div>
			
			
			<div class='mui-row'>
				<ul class="mui-table-view mui-grid-view goods recomend" style="padding: 0;padding-bottom:50px;">
					
				</ul>
			</div>
		</div>
    <!-- 店铺关注收藏 -->
	<div class="collectbox">
		<span id="attention_span"><i class="mui-icon mui-icon-star " id="attention_i"></i>关注</span>
		<span class="on" id="collect_span">
			<i class="mui-icon mui-icon-star-filled " id="collect_i"></i>收藏
		</span>
	</div>
	<script>
	var status;
	var status1;
	var submitData;
	function collect(){
		//查询店铺是否收藏
		$.post('${ctx}/shop/shopcollect!ajaxbycid.action?shopId=${cid}&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
	            function (json) { 
					status = json.status;
					if(status==1){//已收藏
						$("#collect_span").addClass("on");
						$("#collect_i").removeClass("mui-icon-star");
						$("#collect_i").addClass("mui-icon-star-filled");
					}else if(status==2){//未收藏
						$("#collect_span").removeClass("on");
						$("#collect_i").removeClass("mui-icon-star-filled");
						$("#collect_i").addClass("mui-icon-star");
					}
					
	            }, "json");
	}
	
	$("#collect_span").click(function(){
		if(status==1){//已收藏   点击后取消收藏
			$.post('${ctx}/shop/shopcollect!del.action?shopId=${cid},&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
		            function (json) { 
						alert(json.submap);
						collect();
		            }, "json");
		}else if(status==2){//未收藏   点击后收藏
			$.post('${ctx}/shop/shopcollect!ajaxsave.action?shopId=${cid}&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
		            function (json) { 
						alert(json.submap);
						collect();
		            }, "json");
		}
	});
	
	function attention(){
		//查询店铺是否收藏
		$.post('${ctx}/shop/shopattention!ajaxbycid.action?shopId=${cid}&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
	            function (json) {
					status1 = json.status;
					if(status1==1){//已收藏
						$("#attention_span").addClass("on");
						$("#attention_i").removeClass("mui-icon-star");
						$("#attention_i").addClass("mui-icon-star-filled");
					}else if(status1==2){//未收藏
						$("#attention_span").removeClass("on");
						$("#attention_i").removeClass("mui-icon-star-filled");
						$("#attention_i").addClass("mui-icon-star");
					}
	            }, "json");
	}
	
	$("#attention_span").click(function(){
		if(status1==1){//已关注   点击后取消关注
			$.post('${ctx}/shop/shopattention!del.action?shopId=${cid},&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
		            function (json) { 
						alert(json.submap);
						attention();
		            }, "json");
		}else if(status1==2){//未关注   点击后关注
			$.post('${ctx}/shop/shopattention!ajaxsave.action?shopId=${cid}&custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,
		            function (json) { 
						alert(json.submap);
						attention();
		            }, "json");
		}
	});
	
	$(function(){ 
		collect();
		attention();
	});
	</script>
    <%@include file="/webcom/foot.jsp" %>
</main>
<%@include file="/webcom/return-top.jsp" %>
<div class="hang50 clear"></div>
<%@ include file="/webcom/shop-foot.jsp"%> 
 
<script>
    ajaxjz();
    $(window).scroll(function () {
        var offsetY = $(window).scrollTop();
        var section1 = $("#section1").height();
        if (section1 - offsetY < 600) {
            ajaxjz();
        }
    });
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
             check_task();
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
</body>
</html> 