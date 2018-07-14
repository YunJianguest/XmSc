<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 

	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>首页</title>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/swiper.css" />
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		 <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
         <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		 <script src="${ctx }/mvccol/js/mtlb.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/bbsSwipe.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/swipe.js"></script>
		<style type="text/css">
			.mui-bar-nav {
				box-shadow: none;
				padding: 0 26px;
				/*display: flex;
				justify-content: space-between;*/
			}
			.flickity-page-dots{
			  display: none;
			}
			
			.mui-bar.mui-bar-nav.mui-bar-transparent.mui-active {
				background-color: rgba(255,255,255,0.6) !important;
			}
			.mui-bar.mui-bar-nav.mui-bar-transparent.mui-active .mui-search{
				background: #e2e2e2;
			}
			.mui-pull-left span {
				width: 22px;
				height: 22px;
				display: block;
				background: url('${ctx}/xmMobile/img/icon/icon-scan.png') no-repeat;
				background-size: 100% auto;
				margin-top: 12px;
			}
			.mui-bar.mui-bar-nav.mui-bar-transparent.mui-active .mui-pull-left span{
				background: url('${ctx}/xmMobile/img/icon/icon-scan-f.png') no-repeat;
				background-size: 100% auto;
			}
			.mui-bar.mui-bar-nav.mui-bar-transparent.mui-active .mui-pull-right span{
				background: url('${ctx}/xmMobile/img/icon/icon-msg-f.png') no-repeat;
				background-size: 100% auto;
			}
			.mui-pull-right span {
				width: 22px;
				height: 22px;
				display: block;
				background: url('${ctx}/xmMobile/img/icon/icon-msg.png') no-repeat;
				background-size: 100% auto;
				margin-top: 12px;
			}
			.mui-bar .mui-title{
				padding: 0 20px;
			}
			.mui-bar .mui-title .mui-search {
				background-color: #fff;
				border-radius: 14px;
				padding-right: 30px;				
			}
			
			.mui-indicator.mui-active {
				width: 10px;
				height: 10px;
				margin: -1px 6px;
			}
			
			.mui-grid-view.mui-grid-9 {
				background: #fff;
				border: none;
			}
			
			.mui-grid-view.mui-grid-9 .mui-table-view-cell {
				border: none;
				padding: 0 5px;
			}
			
			.mui-grid-view.mui-grid-9 .mui-table-view-cell a:not(.mui-btn) {
				padding: 5px 0;
			}
			
			.mui-icon img {
				width: 38px;
				height: 38px;
				border-radius: 50%;
			}
			
			.news {
				width: 100%;
				height: 40px;
				position: relative;
				/*display: flex;
				justify-content: space-between;
				align-items: center;*/
				border:1px solid #eee;
		        border-radius:10px;
		        padding:0 10px; 
			}
			
			/*.news::after {
				content: '';
				width: 100%;
				height: 0.5px;
				background: #E1DFDD;
				position: absolute;
				top: 0;
				left: 0;
			}
			
			.news::before {
				content: '';
				width: 100%;
				height: 1px;
				background: #E1DFDD;
				position: absolute;
				bottom: 0;
				left: 0;
			}*/
			
			.news-cont {
				display: flex;
				justify-content: flex-start;
			}
			
			.news-cont .mui-col-xs-10 ul li {
				float: left;
				font-size: 11px;
				line-height: 18px;
			}
		
			#newsmore {
				position: relative;
				text-align: center;
				line-height: 40px;
			}
			
			#newsmore::after {
				content: '';
				width: 1px;
				height: 30px;
				background: #eee;
				position: absolute;
				top: 5px;
				left: 0;
			}
			
			.icon-logo {
				width: 36px;
				height: 36px;
				background: url(${ctx}/xmMobile/img/icon/icon-logo.png) no-repeat;
				background-size: 100% auto;
				/*margin-top: 10px;*/
				margin-right: 3px;
			}
			
			.public::before,
			.public::after,
			.goods:before {
				display: none;
			}
			
			.mui-table-view.mui-grid-view .mui-table-view-cell .mui-media-object {
				height: 180px;
			}
			
			.title-txt {
				position: relative;
				display: -webkit-box;
				margin: 14px 0px;
				font-size: 14px;
				-webkit-box-pack: center;
			}
			
			.title-txt::after {
				content: '';
				width: 100%;
				height: 1px;
				position: absolute;
				top: 50%;
				left: 0;
				background: #E1DFDD;
			}
			
			.title-txt .title-layout {
				background-color: #fff;
				z-index: 10;
				position: relative;
				display: block;
			}
			
			.title-txt .title-layout .title-txt-imgl {
				position: relative;
				top: 10px;
				float: left;
				display: block;
				margin-right: 10px;
				width: 16px;
				height: 16px;
				background: url('${ctx}/xmMobile/img/icon/icon-iconl.png') no-repeat;
				background-size: 100% auto;
			}
			
			.title-txt .title-layout .title-txt-imgr {
				position: relative;
				top: 0px;
				float: right;
				display: block;
				margin-left: 10px;
				width: 20px;
				height: 20px;
				background: url('${ctx}/xmMobile/img/icon/icon-iconr.png') no-repeat;
				background-size: 100% auto;
			}
			
			.title-txt .title-layout .title-txt-txt {
				background: linear-gradient(to left, #FF0000, #D440CD);
				-webkit-background-clip: text;
				color: transparent;
				font-size: 600;
			}
			.goods.mui-table-view.mui-grid-view .mui-table-view-cell>a:not(.mui-btn) {
				padding: 0 5px;
				white-space: normal;
			}
			.goods.mui-table-view.mui-grid-view li.mui-table-view-cell {
				padding-left: 5px;
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
				line-height: 17px;
				margin-bottom: 3px;
				padding: 0 4px;
			}
			
			.similar-product-info {
				display: block;
				position: relative;
				overflow: hidden;
				display: flex;
				justify-content: space-between;
			}
			
			.similar-product-shopCar {
				width: 18px;
				height: 18px;
				display: block;
				background: url(${ctx}/xmMobile/img/icon/icon-shopCar.png) no-repeat;
				background-size: 100% auto
			}
			.similar-product-price{
				color: #fd0707;
			}
			.mui-card .mui-card-content {
				height: 124px;
			}
			
			.mui-card .mui-card-content img {
				height: 180px;
			}
			
			#slider .swiper-container .swiper-wrapper img{
				height: 200px;
				width: 100%;
			}
			/* .swiper-pagination-bullet{
				background: #999;
			} */
			.swiper-pagination-bullet.swiper-pagination-bullet-active{
				background: #fff;
			}
			.mui-table-view.mui-grid-view .mui-table-view-cell .mui-media-body{
				text-overflow: initial;
				font-size:12px;
			}
			.collectbox{
				position: fixed;
				right: 15px;
				bottom: 70px;
				z-index: 999;
			}
			.collectbox span{
				cursor: pointer;
				display: block;
				width: 50px;
				height: 30px;
				font-size: 12px;
				margin-top: 10px;
				/*border: 1px solid #e3e3e3;*/
				text-align: center;
			}
			.collectbox span i{
				font-size: 18px;
			}
			.collectbox span.on{
				color: #E4393C;
			}
		</style>
		<script type="text/javascript">
		var issend = true;
        var fypage = 0;
        var xszf = "";
        var type = "";
        var lx = "";
        var sel = "";
        function ajaxjz() {//加载

            if (!issend) {
                return;
            }
            var submitData = {
                "cid": '${entity._id}',
                "lx": lx,
                "sel": sel
            };
            issend = false;
            $.post('${ctx}/shop/shop!ajaxweb.action?custid=${custid}&agid=${agid}&fypage=' + fypage, submitData,
                    function (json) {
                        var xszfleft = $('.recomend').html();
                        
                        if (json.state == 0) {
                            var v = json.list;
                            for (var i = 0; i < v.length; i++) { 
                                	xszfleft +='<li class="mui-table-view-cell mui-media mui-col-xs-6" >'
            						+'<div class="mui-card" style="margin: 2px;">'
            						+'<div class="mui-card-content">'
            						+'<a href="${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=' + v[i]._id + '">'
            						+'<img src="${filehttp}' + v[i].logo + '" />'
            						+'</a>'	
            						+'</div>'
            						+'<div class="mui-card-footer" style="padding: 10px 5px;display: block;">'
            						+'<span class="similar-product-text" style="height:40px;text-align: left;">' + v[i].ptitle + ' </span>'
            						+'<div class="similar-product-info ">'	
            						+'<span class="similar-product-price"><span>￥</span>'+ v[i].price.toFixed(2)+'</span>'
            						+'<span class="similar-product-shopCar"></span>'			
            						+'</div></div></div></li>';	 
                            }
                            fypage++;
                            $('.recomend').html(xszfleft);
                            
                        } else {
                        }
                        issend = true;
                    }, "json")
              }
        function fxsel(v) {
            lx = v;
            fypage = 0;
            $('.recomend').html('');
            ajaxjz();
        }
        function ajaxsel() {
            sel = $('#sel').val().replace('搜索', '');
            fypage = 0;
            $('.recomend').html('');
            ajaxjz();
        }
        
        function goods_search(){
        	var goods_name = $("#sel").val();
        	if(goods_name!=""&&goods_name!=null){
        		window.location.href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&ptitle="+goods_name;
        	}
        }
		</script>
		

	</head>

	<body>
		<div class="mui-bar mui-bar-nav mui-bar-transparent" style="position: fixed !important;">
			<!-- <div class="mui-pull-left">
				<span></span>
			</div> -->
			<div class="mui-title" style="left: 10px;right:10px;">
				<span class="mui-icon mui-icon-search" style="position: absolute;top: 10px;right: 25px;" onclick="goods_search()"></span>
				<input type="search" class="mui-search" name="" id="sel" value="" />
			</div>
			<!-- <div class="mui-pull-right">
				<span></span>
			</div> -->
		</div>
		<!-- 轮播 -->
		
		<div class="mui-content" style="padding-top: 0; padding-bottom: 50px;background: #fff;">
			<c:if test="${not empty slide}">
			<div id="slider">
				<div class="swiper-container">
					<div class="swiper-wrapper">
					
					<c:forEach items="${slide}" var="bean">
						<div class="swiper-slide">
							<a href="${bean.url}">
								<img src="${filehttp}/${bean.picurl}"/>
							</a>
						</div>
					</c:forEach>	
					</div>
					<div class="swiper-pagination"></div>
				</div>
			</div>
			</c:if>
			<!-- 分类 -->
			<div class="hero-gallery js-flickity pb-5" data-js-module="hero-gallery">
				<ul class="mui-table-view mui-grid-view mui-grid-9" style="padding:0 10px;">
					<div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
						<c:if test="${not empty typelist}">
							<c:forEach items="${typelist}" var="bean"  begin="0" end="9">
				                <c:if test="${not empty bean.url}">
									<li class="mui-table-view-cell mui-media" style="width: 20%">
										<a href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&goodstype=3&typeid=${bean._id}">
											<span class="mui-icon"><img src="${filehttp}${bean.picurl}"/></span>
											<div class="mui-media-body">${bean.name}</div>
										</a>
									</li>
						   		</c:if>
				            	<c:if test="${empty bean.url}"> 
									<li class="mui-table-view-cell mui-media" style="width: 20%">
										<a href="javascript:fxsel('${bean.type}')">
											<span class="mui-icon"><img src="${filehttp}${bean.picurl}"/></span>
											<div class="mui-media-body">${bean.name}</div>
										</a>
									</li>
							  	</c:if>
				            </c:forEach>
			             
			            </c:if> 
		            </div>
	            </ul>
	            <ul class="mui-table-view mui-grid-view mui-grid-9" style="padding:0 10px;">
		            <div class="hero-gallery__cell hero-gallery__cell overflow-hidden">
						<c:if test="${not empty typelist}">
							<c:forEach items="${typelist}" var="bean"  begin="10" end="19">
				                <c:if test="${not empty bean.url}">
									<li class="mui-table-view-cell mui-media" style="width: 20%">
										<a href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&goodstype=3&typeid=${bean._id}">
											<span class="mui-icon"><img src="${filehttp}${bean.picurl}"/></span>
											<div class="mui-media-body">${bean.name}</div>
										</a>
									</li>
						   		</c:if>
				            	<c:if test="${empty bean.url}"> 
									<li class="mui-table-view-cell mui-media" style="width: 20%">
										<a href="javascript:fxsel('${bean.type}')">
											<span class="mui-icon"><img src="${filehttp}${bean.picurl}"/></span>
											<div class="mui-media-body">${bean.name}</div>
										</a>
									</li>
							  	</c:if>
				           </c:forEach>
			            </c:if> 
		            </div>
				</ul> 
			</div>
			<!-- 新闻 -->
			<div class="mui-row" style="padding: 0 13px;background: #fff;">
				<div class="news mui-col-xs-12 mui-row">
					<div class="news-cont mui-col-xs-10">
						<div class="mui-col-xs-2" style="color: #F83111;font-size: 12px;"><img src="${ctx}/xmMobile/img/newsimg.jpg" style="width: 24px;height:24px;margin-top: 8px;"/></div>
						<div class="mui-col-xs-10" style="color: #000;display: flex;overflow: hidden;" id='news'>
							<span class="mui-icon icon-logo"></span>
							
							<ul>
								<c:forEach items="${roll}" var="bean">
								   <c:if test="${not empty bean.url}">
								   <li style="height: 50px;">
								   		<p><a href="${bean.url}" style="text-decoration: underline;">${bean.title}</a></p>
								   		<p><a href="${bean.url}" style="text-decoration: underline;">${bean.title}</a></p>
								   </li>
								   </c:if>
								   <c:if test="${empty bean.url}">
								   		<li style="height: 50px;">
								   			<p><a href="javascript:void(0);" style="text-decoration: underline;">${bean.title}</a></p>
								   			<p><a href="javascript:void(0);" style="text-decoration: underline;">${bean.title}</a></p>								   			
								   		</li>
								   </c:if> 
								 </c:forEach>  
							</ul>
						</div>
					</div>
					<div class="mui-col-xs-2" id="newsmore">
						<a href="${ctx}/suc/roll!kuaibaoList.action?custid=${custid}&agid=${agid}&lscode=${lscode}" style="font-size: 12px;">更多</a>
					</div>
				</div>
			</div>
			<!-- 大众、特约区 -->
			<div class="mui-row ">
				<ul class="mui-table-view mui-grid-view public" style="padding-left: 10px;">
					<li class="mui-table-view-cell mui-media mui-col-xs-12">
						<a href="${ctx}/shop/protype!classme.action?custid=${custid}&agid=${agid}&lscode=${lscode}&goodstype=3" style="padding: 0;">
							<!--<div class="mui-media-body" style="line-height: 30px;font-weight: 600;height: 30px;font-size: 16px;">-->
								<!-- 标题 -->
								<div class="title-txt">
									<span class="title-layout">
									 
									<span class="title-txt-txt" style="font-weight: 900;font-size: 16px;">大众区</span> 
									</span>
								</div>
							<!--</div>-->
							<img class="mui-media-object" src="${ctx}/xmMobile/img/Public-banner.jpg">
						</a>
					</li>
					
					<li class="mui-table-view-cell mui-media mui-col-xs-12" >
						<a href="${ctx}/shop/shopmb!shoplist.action?custid=${custid}&agid=${agid}&lscode=${lscode}&goodstype=5"style="padding: 0;">
							<!--<div class="mui-media-body" style="line-height: 30px;font-size: 16px;font-weight: 600;height:30px;">会员区</div>-->
							<div class="title-txt">
								<span class="title-layout"> 
								<span class="title-txt-txt" style="font-weight: 900;font-size: 16px;color: #c7c729;">会员区</span> 
								</span>
							</div>
							<img class="mui-media-object" src="${ctx}/xmMobile/img/vip-banner.jpg">
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-12">
						<a href="${ctx}/shop/protype!classme.action?custid=${custid}&agid=${agid}&lscode=${lscode}&goodstype=4"style="padding: 0;">
							<!--<div class="mui-media-body" style="line-height: 30px;font-size: 16px;font-weight: 600;height: 30px;">特约区</div>-->
							<div class="title-txt">
								<span class="title-layout"> 
								<span class="title-txt-txt" style="font-weight: 900;font-size: 16px;color: #3acc21;">特约区</span> 
								</span>
							</div>
							<img class="mui-media-object" src="${ctx}/xmMobile/img/member-banner.jpg">
						</a>
					</li>
					
				</ul>
			</div>
			<!-- 推荐商品 -->
			<!--<div class="mui-row">-->
				<!-- 标题 -->
				<!--<div class="title-txt">
					<span class="title-layout">
						<span class="title-txt-imgl"></span>
					<span class="title-txt-txt">为你推荐</span>
					<span class="title-txt-imgr"></span>
					</span>
				</div>-->
				<!-- 商品 -->
				<!--<ul class="mui-table-view mui-grid-view goods recomend" style="padding: 0;">
					
				</ul>
			</div>-->
			<!-- 店铺关注收藏 -->
			<!--<div class="collectbox">
				<span ><i class="mui-icon mui-icon-star "></i>关注</span>
				<span class="on">
					<i class="mui-icon mui-icon-star-filled "></i>收藏
				</span>
			</div>-->
			 <%@include file="/webcom/shop-foot.jsp" %>
		</div>
		
		<script src="${ctx}/xmMobile/js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
			var mySwiper = new Swiper('.swiper-container', {
				autoplay: true, //可选选项，自动滑动
				pagination: {
			   		el: '.swiper-pagination',
			  	},
				loop : true,
			})
			console.log(mySwiper)
		</script>
		<script type="text/javascript">
		
		 $(document).ready(function() {
				setInterval('autoScroll("#news")', 2000)
		}); 
		function autoScroll(obj) {
			$(obj).find('ul:first').animate({
				marginTop: -50+'px'
			}, 500, function() {
				$(this).css({
					marginTop: 0
				}).find('li:first').appendTo(this)
			})
		}  
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
	</body>
</html>