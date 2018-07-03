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
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/swiper.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/jfyMobilePro.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mediaJfyPro.css" />
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
					    				  html+='<li><a href="javascript:detail('+list[i]._id+')">'+list[i].name+'</a></li>';
					    			  }  
					    		  }
					    		$('.category-tab-list').html(html); 
					    		 detail(ttid); 
					    		} 
	                    }, "json")       
	        }
		 function detail(id) { 
			 $('.category-branch-content').html('');
			 var html= '';
	            var submitData = {
	            		parentId:id
	                };
	            $.post('${ctx}/shop/protype!gettype.action', submitData,
	                    function (json) {
			            	if(json.state == 0){
			            		var list=json.list;
					    		html+='<ul class="category-branch-list">' 
					    		 for(var i=0;i<list.length;i++){
					    			html+='<li><a href="${ctx}/shop/shoppro!promain.action?mintypeid='+list[i]._id+'"><img src="${filehttp}/'+list[i].picurl+'"/><span>'+list[i].name+'</span></a></li>';	
					    		  } 
					    		 html+='</ul>';
					    	    $('.category-branch-content').html(html);
			            	}else{
			            		$('.category-branch-content').html('<div>暂无数据</div>');
			            	}
					    		 
	                    }, "json")       
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
			<div class="search search-h">

				<div class="search-box">
					<div class="search-back on-blur"></div>
					<div class="search-box-tb ">
						<div class="search-box-icon" style="display: none;">

						</div>
						<div class="search-box-urlBack" onclick="javascript:window.history.back(-1)">
							<span class="search-box-urlBack-back iconLeft">返回</span>
						</div>
					</div>
				</div>
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
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/swiper.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/jfyMobilePro.js" type="text/javascript" charset="utf-8"></script>
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
		</script>
	</body>

</html>