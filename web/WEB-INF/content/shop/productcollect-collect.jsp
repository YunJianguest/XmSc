<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <!-- Resource style -->
    <script src="${ctx }/app/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/app/css/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
      <style type="text/css">
			.fav_count {
				padding: 0 10px;
				height: 40px;
				line-height: 40px;
				font-size: 12px;
				color: #666;
				position: relative;
			}
			
			.fav_count em {
				margin: 0 5px;
				color: #e4393c;
			}
			
			.fav_count .fav_edit {
				float: right;
				color: #e93b3d;
			}
			
			.fav_select {
				display: block;
				width: 44px;
				height: 100%;
				vertical-align: middle;
				position: absolute;
				left: 0;
				top: 0;
				z-index: 2;
				background: #fff;
			}
			
			.fav_fixbar {
				z-index: 900;
				bottom: 0;
				left: 0;
				right: 0;
				background: hsla(0, 0%, 100%, .8);
				height: 50px;
				line-height: 50px;
				position: relative;
			}
			
			.fav_fixbar .fav_select {
				width: 100px;
				height: 50px;
				line-height: 50px;
			}
			
			.fav_fixbar .btn {
				background: #e4393c;
			}
			
			.fav_fixbar .btn {
				display: block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				font-size: 14px;
				color: #fff;
				text-align: center;
				border-radius: 3px;
				position: absolute;
				right: 10px;
				top: 50%;
				margin-top: -12px;
				color: #fff;
			}
			
			.checkbox input[type="checkbox"] {
				position: relative;
				margin: 0 10px;
				margin-top: 20px;
			}
			
			.checkbox input[type="checkbox"]:before {
				border: 2px solid transparent;
				content: "";
				cursor: pointer;
				display: block;
				height: 17px;
				position: absolute;
				-webkit-transition: all 200ms linear;
				transition: all 200ms linear;
				width: 17px;
				z-index: 1;
			}
			
			.checkbox input[type="checkbox"]:checked:before {
				border-color: #8e44ad;
				border-top-style: none;
				border-right-style: none;
				height: 10px;
				-webkit-transform: rotate(-45deg);
				transform: rotate(-45deg);
			}
			
			.checkbox input[type="checkbox"]:after {
				background: #FFFFFF;
				content: "";
				display: block;
				height: 17px;
				position: absolute;
				border: 2px solid rgba(189, 195, 199, 0.5);
				top: 0;
				width: 17px;
				z-index: 0;
			}
			
			.fav_items {
				clear: both;
				position: relative;
				background: #fff;
				overflow: hidden;
				margin-bottom: 10px;
			}
			
			.fav_item .fav_link {
				position: relative;
				z-index: 1;
				display: block;
				overflow: hidden;
				padding: 10px;
				background: #fff;
			}
			
			#shoplist,
			.shoplist {
				background-color: #f7f7f7;
			}
			
			.fav_item .fav_link .image {
				display: block;
				float: left;
				margin-right: 10px;
				margin-top: 5px;
			}
			
			a.fav_link.fav_link_shop .image {
				width: 90px;
				height: 30px;
			}
			
			.fav_item .fav_link .title {
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
			}
			
			.fav_item .fav_link .title {
				padding-right: 20px;
				height: 40px;
				line-height: 40px;
				font-size: 14px;
			}
			
			a.fav_link.fav_link_shop .title {
				height: 40px;
				line-height: normal;
				position: relative;
			}
			
			a.fav_link.fav_link_shop .title .title_center {
				position: absolute;
				top: 50%;
				left: 0;
				-webkit-transform: translateY(-50%);
				transform: translateY(-50%);
			}
			
			a.fav_link.fav_link_shop .title .title_center .title_shop_name {
				font-size: 14px;
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
				max-width: 130px;
			}
			
			.fav_link_goods .sku {
				color: #999;
				font-size: 12px;
			}
			
			.fav_link_goods .sku {
				line-height: 25px;
				height: 25px;
			}
			
			.fav_link_goods .price {
				font-family: arial;
			}
			
			.fav_link_goods .price {
				color: #e4393c;
				font-size: 18px;
				line-height: 18px;
			}
			
			.fav_item .fav_link .seperator {
				margin-top: 10px;
			}
			
			.fav_item .fav_link .des,
			.fav_link_goods .name {
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
			}
			
			img {
				width: 100px;
				height: 100px;
			}
			
			.fav_item .move_div {
				position: relative;
				z-index: 1;
				display: block;
				overflow: hidden;
				padding: 0;
				background: #fff;
			}
			
			.fav_items_edit .fav_link,
			.fav_items_edit .move_div {
				-webkit-transform: translate3d(34px, 0, 0);
			}
			
			.fav_items_edit .move_div .fav_link {
				-webkit-transform: translate3d(0, 0, 0);
			}
			.fav_item {
			    clear: both;
			    position: relative;
			    background: #fff;
			    overflow: hidden;
			    margin-bottom: 10px;
			}
			
		</style>
		
		<script> 
			//判断checkbox是否选中
			function checkboxClick()
			{
				if($("#allSelectCheck").is(":checked"))
				{
					 $("#shoplist").find("li").each(function(){
			     			$(this).find("input[name='procollectId']").prop("checked",true);
			     	 });
				}else{
					 $("#shoplist").find("li").each(function(){
			     			$(this).find("input[name='procollectId']").prop("checked",false);
			     	  });
				}
			}
		
			//取消收藏
	     	function deleGuanZhu()
	     	{
				var proCollectIds ="";
	     		$("#shoplist").find("li").each(function(){
	     			var obj = $(this).find("input[name='procollectId']");
	     			if(obj.is(":checked"))
	     			{
	     				proCollectIds += $(this).find("input[name='procollectId']").val()+",";
	     			}
	     		});
	     		
	     		if(proCollectIds!=null && proCollectIds!=""){
	     			proCollectIds = proCollectIds.substring(0,proCollectIds.length-1);
	     			var submitData = {
	                        "proCollectIds": proCollectIds
	                };
	     			 $.post('${ctx}/shop/productcollect!ajaxdeletecollect.action?agid=${agid}&lscode=${lscode}', submitData,
                        function (json) {
       			 		console.log(json);
       			 		if(json.state =="1")
       			 		{
       			 			alert("操作成功!");
       			 		 	window.location.href="${ctx}/shop/productcollect!productcollectList.action?custid=${custid}&lscode=${lscode}";
       			 		}else{
       			 			alert("抱歉,删除过程中出现异常!");
       			 		}
                      }, "json");
	     			
	     		}else{
	     			alert("请选择取消收藏的商品！");
	     		}
	     		
	     	}  
     </script>
</head>
<body>
	<header class="mui-bar mui-bar-nav" style="background: #fff;box-shadow: none;">
		    <a class=" mui-icon mui-icon-undo mui-pull-left" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">分享</h1>
		    <!--<span class="mui-pull-right">保存到手机</span>-->
		</header>
	<div class="mui-content">
			<p class="fav_count">
				<span class="dianpushoucang">您收藏了</span><em id="shoplist_count">${fycount }</em>个商品
				<span class="fav_edit" id="shoplist_edit" style="color:#E93B3D;">编辑</span>
			</p>
			<!-- 商品列表 -->
			<div class="mui-row">
				<ul class="fav_items" id="shoplist">
				<c:forEach items="${list }" var="obj">
						<li class="fav_item">
							<span class="fav_select checkbox" style="display: none;">
								<input type="checkbox" class="" name="procollectId" id="" value="${obj.productcollect._id}" />
							</span>
							<div class="move_div">
								<a href="${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=${obj.productobj._id}" class="fav_link fav_link_goods">
									<img class="image" src="${filehttp}${obj.productobj.logo}" />
									<p class="name">${obj.productobj.ptitle}</p>
									<p class="sku"></p>
									<p class="price">￥<fmt:formatNumber value='${obj.productobj.price}' pattern="0.0#"/></p>
								</a>
							</div>
						</li>
				</c:forEach>
				</ul>
			</div>
			<!-- 页脚取消关注 -->
			<div class="fav_fixbar" style="position: fixed;display: none;">
				<span class="fav_select checkbox" id="allSelect">
					<input type="checkbox" onclick="checkboxClick();" class="" name="allSelect" id="allSelectCheck" value="" />
				</span>
				<a href="javascript:void(0);" onclick="deleGuanZhu();" class="btn" style="color: #fff !important;">取消收藏</a>
			</div>
		</div>
		<script src="${ctx }/app/js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$('#shoplist_edit').click(function() {
				if($(this).text() == '编辑') {
					$(this).text('完成');
					$('.fav_select').css('display', 'block');
					$('.fav_fixbar').css('display', 'block');
					$('#shoplist').addClass('fav_items_edit');
				} else {
					$(this).text('编辑');
					$('.fav_select').css('display', 'none');
					$('.fav_fixbar').css('display', 'none');
					$('#shoplist').removeClass('fav_items_edit');
				}
			})
		</script>

</body>
</html>