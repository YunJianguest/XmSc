<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="toTop" content="true">
		<!-- 强制让文档宽度与设备的宽度保持1：1，且文档最大宽度为1.0，并且不允许用户点击屏幕放大浏览 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
		<!-- IE -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 兼容国产浏览器的高速模式 -->
		<meta name="renderer" content="webkit">
		<!-- WebApp全屏模式   隐藏地址栏 -->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!-- 禁止百度转码显示。-->
		<meta http-equiv="Cache-Control" content="no-siteapp">
		<!-- 制定iPhone中Safari顶端状态条样式（default:白色，black:黑色，black-translucent：半透明） -->
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
		<!-- 添加到IOS主屏后的标题 -->
		<meta name="apple-mobile-web-app-title" content="熊猫商城">
		<title>熊猫商城</title>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet"/> 
		<link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet">
		<style type="text/css">
			.category {
			overflow: hidden;
			position: relative;
		}
		
		
		/* 分类页左侧导航 */
		
		.category-tab {
			float: left;
			min-width: 76px;
			width: 76px;
			height: auto;
			overflow-y: scroll;
			height: 100%;
			position: fixed;
		}
		
		.category-tab-box {
			overflow: hidden;
		}
		
		.category-tab-list {
			width: 75px;
		}
		
		.category-tab-list li {
			background: #fff;
			height: 46px;
			line-height: 46px;
			text-align: center;
			position: relative;
		}
		
		.category-tab-list li:last-child {
			margin-bottom: 53px;
		}
		
		.category-tab-list li::before {
			content: '';
			height: 92px;
			width: 1px;
			position: absolute;
			left: auto;
			top: 0px;
			right: 0px;
			bottom: auto;
			background-color: #e5e5e5;
			border: 0px solid transparent;
			border-radius: 0px;
			-webkit-border-radius: 0px;
			transform: scale(0.5);
			-webkit-transform: scale(0.5);
			-moz-transform: scale(0.5);
			-ms-transform: scale(0.5);
			-o-transform: scale(0.5);
			transform-origin: top left;
			-webkit-transform-origin: top left;
			-moz-transform-origin: top left;
			-ms-transform-origin: top left;
			-o-transform-origin: top left;
		}
		
		.category-tab-list li::after {
			content: '';
			height: 1px;
			width: 200%;
			position: absolute;
			left: 0px;
			top: auto;
			right: auto;
			bottom: 0px;
			background-color: #e5e5e5;
			border: 0px solid transparent;
			border-radius: 0px;
			-webkit-border-radius: 0px;
			transform: scale(0.5);
			-webkit-transform: scale(0.5);
			-moz-transform: scale(0.5);
			-ms-transform: scale(0.5);
			-o-transform: scale(0.5);
			transform-origin: top left;
			-webkit-transform-origin: top left;
			-moz-transform-origin: top left;
			-ms-transform-origin: top left;
			-o-transform-origin: top left;
		}
		
		.category-tab-list li.opt {
			background: #fff;
		}
		
		.category-tab-list li a {
			display: block;
			width: 100%;
			height: 46px;
			line-height: 46px;
			text-decoration: none;
			font-size: 11px;
			color: #232326;
			overflow: hidden;
			-o-text-overflow: ellipsis;
			   text-overflow: ellipsis;
			white-space: nowrap;
		}
		
		.category-tab-list li.opt a {
			color: #f23030;
			border: 1px solid #F23030;
		}
		
		
		/* 分类页内容 */
		
		.category-content {
			width: 100%;
			min-height: 100%;
			height: auto;
			font-size: 12px;
			color: #252525;
			background: #f3f5f7;
		}
		
		.category-content-wrapper {
			margin-left: 76px;
			/*width: 100%;*/
		}
		
		
		/* 加载失败 */
		
		.category-content-loadFail {
			width: 100%;
			display: table;
			vertical-align: middle;
			text-align: center;
		}
		
		.loadFail-content {
			padding: 25px;
			vertical-align: middle;
			text-align: center;
		}
		
		.fail {
			background: url(../img/spirit.png) no-repeat;
			background-position: -60px -32px;
			width: 82px;
			height: 62px;
			display: block;
			text-align: center;
			margin: 0 auto;
			background-size: 300px 300px;
		}
		
		.loadFail-content span {
			margin-top: 38px;
			display: block;
			font-size: 17px;
		}
		
		.btn-fail {
			display: block;
			width: 120px;
			height: 30px;
			line-height: 20px;
			margin: 10px auto;
			border: 1px solid #ddd;
			background: #fff;
			border-radius: 5px;
			font-size: 1rem;
			padding: 5px 10px;
		}
		
		
		/* 分类右侧 */
		
		.category-content-branch {
			height: 100%;
			overflow-y: scroll;
		}
		
		.category-content-branchList {
			overflow: hidden;
			padding-bottom: 10px;
			background: #f3f5f7;
		}
		
		.category-branch-content {
			margin: 19px 7px 0 7px;
		}
		
		.category-branch-content h4 {
			font-size: 1.2em;
			line-height: 1em;
			color: #232326;
			font-weight: normal;
		}
		
		.category-branch-content ul {
			margin-top: 9px;
			background: #fff;
		}
		
		.category-branch-list {
			border: 0;
			/*font-size: 0;*/
			padding: 7px 10px 0 10px;
			overflow: hidden;
		}
		
		.category-branch-list li {
			width: 32.8%;
			float: left;
			text-align: center;
		}
		
		.category-branch-list li img {
			width: 55px;
			height: 55px;
		}
		
		.category-branch-list li span {
			font-size: 10px;
			height: 31px;
			color: #686868;
			width: 100%;
			overflow: hidden;
			-o-text-overflow: ellipsis;
			   text-overflow: ellipsis;
			display: box;
			display: -webkit-box;
			display: -moz-box;
			display: -ms-box;
			display: -o-box;
			line-clamp: 2;
			-webkit-line-clamp: 2;
			-moz-line-clamp: 2;
			-ms-line-clamp: 2;
			-o-line-clamp: 2;
			box-orient: vertical;
			-webkit-box-orient: vertical;
			-ms-box-orient: vertical;
			-o-box-orient: vertical;
			word-break: break-all;
			box-align: center;
			-webkit-box-align: center;
			-moz-box-align: center;
			-ms-box-align: center;
			-o-box-align: center;
			box-pack: center;
			-webkit-box-pack: center;
			-moz-box-pack: center;
			-ms-box-pack: center;
			-o-box-pack: center;
			z-index: 2;
			position: relative;
		}
		
		.search {
			/*position: fixed;
			left: 0;
			top: 0;*/
			width: 100%;
			padding-left: 0;
			padding-right: 0;
			max-width: 640px;
			z-index: 999;
			position: fixed;
			height: 40px;
			/*top: -4px;*/
			background: #fff;
			border: none;
		}
		
		.search-h {
			height: 40px;
			text-align: center;
		}
		
		.search-back {
			opacity: 0;
			height: 40px;
			width: 100%;
			position: absolute;
			/*z-index: 10;*/
		}
		
		.search-box {
			height: 40px;
			position: relative;
			width: 100%;
			line-height: 40px;
			z-index: 20;
			/*position: fixed;*/
			max-width: 640px;
			text-align: center;
		}
		.search-box-urlBack {
			position: absolute;
			width: 37px;
			height: 44px;
		}
		
		.search-box-urlBack-back {
			text-indent: -100px;
			overflow: hidden;
			left: 10px;
			top: 13px;
			display: block;
		}
		
		.search-form-seek {
			display: block;
			padding: 0;
			margin-left: 30px;
			height: 44px;
			overflow: hidden;
		}
		.iconLeft {
			/*content: " ";*/
			display: inline-block;
			-webkit-transform: rotate(225deg);
			-ms-transform: rotate(225deg);
			    transform: rotate(225deg);
			border-width: 1px 1px 0 0;
			border-color: #666666;
			border-style: solid;
			/*position: relative;*/
			/*top: -2px;*/
			/*position: absolute;*/
			/*top: 50%;*/
			/*right: 18px;*/
			/*margin-top: -3px;*/
			/*top: 43%;*/
			font-size: 16px;
			position: relative;
			left: 17px;
			top: 15px;
			height: 14px;
			width: 14px;
			float: left;
		}
		.bottom-bai font .fa{
			display: block;
		}
		.bottom-bai div{
			padding: 0;
		}
		</style>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		   
		var ttid='';
		 function getClassify() { 
	            var submitData = {
	            		parentId:0       
	                };
	            $.post('${ctx}/shop/protype!gettype.action', submitData,
	                    function (json) {
					    	 if(json.state==0){
					    		var list=json.list;
					    		var html=$('.category-tab-list').html();
					    		 ttid=list[0]._id; 
					    		  for(var i=0;i<list.length;i++){ 
					    			  if(list[i].parentid==0){
					    				  html+='<li id="li_'+list[i]._id+'"><a href="javascript:detail('+list[i]._id+')">'+list[i].name+'</a></li>';
					    			  }  
					    		  }
					    		$('.category-tab-list').html(html); 
					    		 detail(ttid); 
					    		} 
	                    }, "json")       
	        }
		 function detail(id) { 
			 $('.category-tab-list').find('li').removeClass('opt');
			 $('#li_'+id).addClass('opt');
			 $('.category-branch-content').attr('href');
			 var html= '';
	            var submitData = {
	            		parentId:id
	                };
	            $.post('${ctx}/shop/protype!gettype.action', submitData,
	                    function (json) {
			            	if(json.state == 0){
			            		var list=json.list;
			            		 for(var j=0;j<list.length;j++){ 
					            		html+='<li style="margin-top:10px">'+list[j].name+'</li>';
					            		html+='<ul class="category-branch-list">';
					            		     var obj=list[j].list;
					            		     if(obj!=null){
					            		    	 for(var i=0;i<obj.length;i++){
										    			html+='<li><a href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&thirdtypeid='+obj[i]._id+'&goodstype=${goodstype}"><img src="${filehttp}/'+obj[i].picurl+'"/><span>'+obj[i].name+'</span></a></li>';	
										    		  } 	
					            		     }
					            		     html+='</ul>'; 
			            		 }  
			            		 html+='<div class="hang40"></div>';
					    	
					    	    $('.category-branch-content').html(html);
			            	}else{
			            		$('.category-branch-content').html('<div>暂无数据</div>');
			            	}
					    		 
	                    }, "json")       
	        }
		 
		 function goods_search(){
	        	var goods_name = $("#sel").val();
	        	if(goods_name!=""&&goods_name!=null){
	        		window.location.href="${ctx}/shop/shoppro!promain.action?custid=${custid}&agid=${agid}&lscode=${lscode}&ptitle="+goods_name;
	        	}
	        }
		/*  function sel(){
		    	window.location.href="${ctx}/shop/store!sel.action?custid=${custid}&fxid=${fxid}&value="+$('#searchInput').val();
		 }
		 function cgoods(mintype){
			 window.location.href="${ctx}/shop/store!cgoodsme.action?custid=${custid}&fxid=${fxid}&mintype="+mintype
		 } */
		
		</script>
	</head>
	

	<body>
		<div class="page">
		
		
			 <div class="search search-h" style="position:static">
				<div class="search-box">
					<div class="search-back on-blur"></div>
					<div class="search-box-tb ">
						<div class="search-box-urlBack" onclick="javascript:window.history.back(-1)">
							<span class="search-box-urlBack-back iconLeft">返回</span>
						</div>
					</div>
					分类
				</div>
			</div> 
			
			<div class="mui-input-row" style="position: relative;margin-top:6px;width:85%;margin-left:7.5%;">
				<span class="mui-icon mui-icon-search" style="position: absolute;top: 5px;right: 5px;" onclick="goods_search()"></span>
				<input type="search" name=""  placeholder="Search" style="padding-left: 30px;margin-bottom:6px;background:#fff;" id="sel">
			</div>
			
			<div class="category">
			
							<!-- 左侧总分类 -->
			    <div class="category-tab">
					<div class="category-tab-box">
						<ul class="category-tab-list">
						</ul>
					</div>
				</div>
				<!-- 右侧细分类 -->
				<div class="category-content">
					<div class="category-content-wrapper">
						<div class="category-content-branch">
							<div class="category-content-branchList">
								<div class="category-branch-content">
								</div>
							</div>
						</div>
						<!-- 加载失败重新加载 -->
						<div class="category-content-loadFail" style="display: none;">
							<div class="loadFail-content">
								<div class="fail"></div>
								<span>加载失败</span>
								<a href="javascript:void(0)" class="btn-fail">重新加载</a>
							</div>
						</div>
					</div>
				</div>
			</div>
          
		</div>
		<%@include file="/webcom/shop-foot.jsp" %>
	
		<script type="text/javascript">
		/* 	$('.search-form-btn').click(function() {
				console.log($(this))
			})

			function category() {
				$('.category-tab-list li a').click(function(event) {
					event.preventDefault();
					$(this).parent().addClass('opt').siblings('.opt').removeClass('opt');
				})
			}
 */
			/* category(); */
			getClassify();
			/* noneNav(); */
			function blackUp(){
        if($("meta[name=toTop]").attr("content")=="true"){
            $("<div id='toTop'><img src='${ctx}/xmMobile/img/blackTop.png'></div>").appendTo('body');
            $("#toTop").css({
                width: '30px',
                height: '30px',
                bottom:'60px',
                borderRadius:'50%',
                right:'15px',
                position:'fixed',
                cursor:'pointer',
                zIndex:'999999'
            });
            $("#toTop img").css({
                width: '100%',
                height: '100%'
            });
            if($(this).scrollTop()==0){
                $("#toTop").hide();
            }
       		$(window).scroll(function(event) {
                /* Act on the event */
                if($(this).scrollTop()==0){
                    $("#toTop").hide();
                }
                if($(this).scrollTop()!=0){
                    $("#toTop").show();
                }
            });
            $("#toTop").click(function(event) {
                /* Act on the event */
                $("html,body").animate({
                    scrollTop:"0px"},
                    666
                )
            });
        }
    }
    blackUp();
		</script>
	</body>

</html>