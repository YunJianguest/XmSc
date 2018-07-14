<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 

	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
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
			.fav_item {
			    clear: both;
			    position: relative;
			    background: #fff;
			    overflow: hidden;
			    margin-bottom: 10px;
			}
			.fav_items_edit .fav_link,
			.fav_items_edit .move_div {
				-webkit-transform: translate3d(34px, 0, 0);
			}
		</style>
		
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" style="background: #fff;box-shadow: none;">
		    <a class="mui-icon mui-icon-undo mui-pull-left" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">分享</h1>
		    <!--<span class="mui-pull-right">保存到手机</span>-->
		</header>
		<div class="mui-content">
			<p class="fav_count">
				<span class="dianpushoucang">您关注了</span><em id="shoplist_count">${obj.state }</em>个店铺
				<span class="fav_edit" id="shoplist_edit" style="color:#E93B3D;">编辑</span>
			</p>
			<div class="mui-row">
				
				<ul class="fav_items" id="shoplist">
				<c:forEach items="${obj.list}" var="bean">
					<li class="fav_item">
						<span class="fav_select checkbox" style="display: none;">
							<input type="checkbox" class="" name="shopId" id="" value="${bean.list.shopId }" />
						</span>
						<a href="${ctx}/shop/shop!index.action?custid=${custid}&agid=${agid}&lscode=${lscode}&comid=${bean.shop._id}" class="fav_link fav_link_shop">
							<img class="image" src="${filehttp}${bean.shop.logo}" />
							<div class="title">
								<div class="title_center">
									<p class="title_shop_name">${bean.shop.name}</p>
								</div>
							</div>
						</a>
					</li>
					</c:forEach>
				</ul>
				
			</div>
			<!-- 页脚取消关注 -->
			<div class="fav_fixbar" style="position: fixed;display: none;">
				<span class="fav_select checkbox" id="allSelect">
					<input type="checkbox" class="" name="" id="check_all" value="" onclick="all_check()"/>
				</span>
				<a href="" class="btn" style="color: #fff !important;" onclick="delete_collect()">取消关注</a>
			</div>
		</div>
	</body>
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
		
		//取消关注
		function delete_collect(){
			var shopId ="";
	        $("#shoplist").find("li").each(function(){
	        var obj = $(this).find("input[name='shopId']");
	         	if(obj.is(":checked")){
	         		shopId += $(this).find("input[name='shopId']").val()+",";
	         	}
	       	});
	        
	        var submitData;
	        $.post("${ctx}/shop/shopattention!del.action?shopId="+shopId, submitData,
            	function (json) {
                	console.log(json); 	       
                }, "json")
		}
		
		//全选
		function all_check(){
			var flag = $("#check_all").is(":checked");
			if(flag){
				$("#shoplist").find("li").each(function(){
			        $(this).find("input[name='shopId']").prop("checked",true);
			    });
			}else{
				$("#shoplist").find("li").each(function(){
			        $(this).find("input[name='shopId']").prop("checked",false);
			    });
			}
			
		}
	</script>
</html>