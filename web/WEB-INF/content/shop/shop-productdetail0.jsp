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
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet">
    <link href="${ctx }/app/css/style_0.css" rel="stylesheet"> 
    <link href="${ctx}/mvccol/SweetAlert2/css/sweetalert2.min.css" rel="stylesheet"/>
    <link href="${ctx}/mvccol/SweetAlert2/css/animo.css" rel="stylesheet"/>
    <link href="${ctx}/mvccol/SweetAlert2/css/buttons.css" rel="stylesheet"/>
    <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
    <script src="${ctx}/mvccol/js/fomatdate1.js"></script>
    <script src="${ctx}/mvccol/SweetAlert2/js/sweetalert2.min.js"></script>
    <script src="${ctx}/mvccol/SweetAlert2/js/promise.js"></script>
    <script src="${ctx}/app/js/iosOverlay.js"></script>
    <script src="${ctx}/app/js/spin.min.js"></script>
    <!-- Resource style -->
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/bbsSwipe.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/swipe.js"></script>
    <script> 
    	var loading;
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
	  loading=iosOverlay({
		text: "Loading", 
		spinner: spinner
	   });
     }
        var state=0;
        var spec='';
        var price=0;
        function xiaoshi() {
            $('#tanchu').hide();
        }
        function xianshi() {
            $('#tanchu').show();
        }
        function gwc(v,count) {

            var submitData = {
                pid: '${entity._id}',
                spec:v,
                count:count,
                price:price
            };
            jQuery.post('${ctx}/shop/shop!ajaxshopcarsave.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
                    function (json) {
                        if (json.state == 0) {
                            $("#shopcount").html(json.value);
                        } else {
                            alert("添加失败！");
                        }
                    }, "json");
        }
        function setspec(){
        if('${entity.num}'<=0){
          alert('库存已完！');
         return;
        } 
        if($('#quantity').val()<=0){
          alert('购买次数已完');
         return;
        }
        if('${spelist}'!=''){ 
         if(spec==''){
           alert("请选择规格");
           return;
         }
        }else{ 
         if(spec==''){
         spec="默认";
         }
        } 
       
        var count=$("#quantity").val();
        if(state==1){
          if(price>0){
            window.location.href="${ctx}/shop/shop!orderconfirmation.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=${entity._id}&spec="+spec+"&count="+count+"&price="+price;
          }else{
            window.location.href="${ctx}/shop/shop!orderconfirmation.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=${entity._id}&spec="+spec+"&count="+count;
          }
         
        }else{
         gwc(spec,count);
         gmcs=gmcs-count;
         var pp='${entity.price}';
         if(gmcs>0){
         $("#quantity").val(1);
         $("#totalPrice").html("Y"+parseDoule(pp).toFixed(2)+"元"); 
         }else{
         $("#quantity").val(0);
         $("#totalPrice").html("0元"); 
         }
         
         hidespec();
        } 
        
        }
        function showspec(v){ 
         state=v;
         $('#specs').show();
        }
        function hidespec(){
         $('#specs').hide();
        }
      </script>
      <script>  
        $(function () {
            $(".yListr3 li").click(function () {
                $(this).addClass("zhiding").siblings().removeClass("zhiding");
                spec=$(this).find("div").html();
                var  p=$(this).find("div").attr("price");
                if(parseFloat(p)>0){ 
                 var Num = parseFloat(p)*parseInt($("#quantity").val());  
                 $("#totalPrice").html("Y"+Num.toFixed(2)+"元");
                 price=Num;
                }
            })
        })
       
         function getcom() {
			loading();
            var submitData = {
                gid: '${entity._id}', 
            };
            jQuery.post('${ctx}/shop/shopcom!getCom.action?lscode=${lscode}', submitData,
                    function (json) {
                    	loading.hide();
                        if (json.state == 0) {
                             var list=json.list;
                             var html='';
                             for(var i=0;i<list.length;i++){
                            	 html+='<li><div class="cmt_user"><span class="user">'+list[i].nickname+'</span><span class="date">'+list[i].createdate+'</span></div>'
                            	 +'<div class="cmt_cnt">'+list[i].content+'</div>';
                            	 if(list[i].sjreply!=null && list[i].sjreply!=""){
                            		 html+='<div class="reply">'+list[i].sjreply+'<span class="replybtn fa fa-commenting-o" onclick="replyBtn('+list[i]._id+','+list[i].reply_id+')"></span></div>';
                            	 } 
                            	 if(list[i].yhreply!=null && list[i].yhreply!=""){
                            		 html+='<div class="reply">'+list[i].yhreply+'</div>';
                            	 }
                            	 html+='</li>';
                             }
                             $('#cmt_list').html(html);
                        }  
                    }, "json");
        }
        
        
        
        
      //取消商品关注
//  	function productAttentDel(productId)
//  	{
//  		var submitData = {
//                  "productId": productId,
//                  "_id": productId,
//          };
//  		
//  		 $.post('${ctx}/shop/productattention!ajaxproductattenById.action?agid=${agid}&lscode=${lscode}', submitData,
//                   function (json) {
//  			 		console.log(json);
//                    if(json.state =="1"){
//                  	  $.post('${ctx}/shop/productattention!ajaxdelete.action?agid=${agid}&lscode=${lscode}&productId='+productId,
//                                function (json1) {
//                  		 	 	alert("取消关注成功!");
//                            }, "json");
//                    }else{
//                 	   alert("该商品还未关注!");
//                    }
//                 }, "json");
//  	}
    
    	//商品关注
    	function productAttent(productId){
    		var submitData = {
                    "productId": productId
           };
        	if ($('.Attent').html()=='关注') {
        		$.post('${ctx}/shop/productattention!ajaxsave.action?agid=${agid}&lscode=${lscode}', submitData,
	                function (json) {
	                    if(json.state =="1"){
//	                 	   confirm("关注成功");
	                 	   $('.Attent').html('取消关注');
	                 	   $('.Attent').siblings('i.fa').removeClass('fa-heart-o').addClass('fa-heart');
	                 	   $('.Attent').parent('div.attentbox').addClass('on');
	                    }else{
	                 	   confirm("抱歉,添加过程中出现异常!");
	                    }
	            }, "json");
        	} else{
        		$.post('${ctx}/shop/productattention!ajaxdelete.action?agid=${agid}&lscode=${lscode}&productId='+productId,
                  	function (json1) {
//  		 	 	confirm("取消关注成功!");
    		 	 	$('.Attent').html('关注');
    		 	 	$('.Attent').siblings('i.fa').removeClass('fa-heart').addClass('fa-heart-o');
	                $('.Attent').parent('div.attentbox').removeClass('on');
              	}, "json");
        	}
    	}
    	
    	//商品收藏

       	function productCollect(productId){
       		var submitData = {
                "productId": productId,
                "_id": productId,
           };
	       	if ($('.Collect').html() == '收藏') {
            	$.post('${ctx}/shop/productcollect!ajaxsave.action?agid=${agid}&lscode=${lscode}', submitData,
                    function (json) {
                        if(json.state =="1"){
                        	console.log($('div.collectbox'))
//                   	   confirm("收藏成功");
                     	   $('.Collect').html('取消收藏')
                     	   $('.Collect').siblings('i.fa').removeClass('fa-heart-o').addClass('fa-heart');
                     	   $('.Collect').parent('div.collectbox').addClass('on');
                        }else{
                     	   confirm("抱歉,添加过程中出现异常!");
                        }
                }, "json");
	       	} else{
	        	$.post('${ctx}/shop/productcollect!ajaxdelete.action?agid=${agid}&lscode=${lscode}&productId='+productId,
	                function (json1) {
//	        		 	confirm("取消收藏成功!");
	        		 	$('.Collect').html('收藏');
	        		 	$('.Collect').siblings('i.fa').removeClass('fa-heart').addClass('fa-heart-o');
	        		 	$('.Collect').parent('div.collectbox').removeClass('on');
	            }, "json");
	       	}
       	}
       	
       	// 渲染页面判断是否已收藏
       	function applycollect(){
       		var submitData = {
                "productId": ${entity._id},
                "_id": ${entity._id},
            };
        	$.post('${ctx}/shop/productcollect!ajaxproductattenById.action?agid=${agid}&lscode=${lscode}', submitData,
		        function (json) {
		        	console.log(json.state)
		            if(json.state ==0){
		            	$('.Collect').html('收藏');
		            	$('.Collect').siblings('i.fa').removeClass('fa-heart').addClass('fa-heart-o');
		            	$('.Collect').parent('div.collectbox').removeClass('on');
		            }else{
		            	$('.Collect').html('取消收藏');
		            	$('.Collect').siblings('i.fa').removeClass('fa-heart-o').addClass('fa-heart');
		            	$('.Collect').parent('div.collectbox').addClass('on');
		        	}
		    	}, "json");
		    $.post('${ctx}/shop/productattention!ajaxproductattenById.action?agid=${agid}&lscode=${lscode}', submitData,
              function (json) {
              	console.log(json.state)
              	if(json.state ==0){
		            $('.Attent').html('关注');
		            $('.Attent').siblings('i.fa').removeClass('fa-heart').addClass('fa-heart-o');
		            $('.Attent').parent('div.attentbox').removeClass('on');
		        }else{
		            $('.Attent').html('取消关注');
		            $('.Attent').siblings('i.fa').removeClass('fa-heart-o').addClass('fa-heart');
		            $('.Attent').parent('div.attentbox').addClass('on');
		        }
              }, "json");
       	}
       	applycollect()
      </script>
       <style>
         .img-100 img{
        width: 100%;
        }
        .yqbtn {
            background-color: #ffffff;
            color: #ef8200;
        }

        .yqbtn:hover {
            background-color: #ef8200;
            color: #ffffff;
        }

        .yqbtn1 {
            background-color: #ffffff;
            color: limegreen;
        }

        .yqbtn1:hover {
            background-color: limegreen;
            color: #ffffff;
        }

        .bg-bai-8 {
            background-color: rgba(225, 225, 225, 0.9);
        }

        .line-bottom-c3c3c6 {
            border-bottom: 1px solid #c3c3c6;
        }
        .detail_row {
		    position: relative;
		    padding: 0 10px;
		    display: block;
		    font-size: 12px;
		}
		.detail_cmt .cmt_list {
		    font-size: 12px;
		    color: #333;
		    margin-bottom: -1px;
		}
		.detail_cmt .cmt_list li {
		    position: relative;
		    padding: 10px 5px;
		    margin-bottom: 10px;
		    background: #efefef;
		    border-radius: 5px;
		}
		
		
		.detail_cmt .cmt_cnt {
		    position: relative;
		    line-height: 1.3;
		    margin: 5px 0;
		    word-break: break-all;
		    overflow: hidden;
		}
		.detail_cmt .cmt_user .user {
		    display: inline-block;
		    color: #999;
		    max-width: 8.2em;
		    vertical-align: middle;
		}
		.detail_cmt .cmt_user .credit {
		    display: inline-block;
		    width: 75px;
		    margin-right: 8px;
		    height: 15px;
		    background-position: 0 -215px;
		    position: relative;
		    margin: -2px 0;
		    vertical-align: middle;
		}
		.detail_cmt .cmt_user .date {
		    float: right;
		    color: #999;
		    margin-left: -60px;
		}
		.detail_cmt .cmt_sku {
		    color: #999;
		}
        .tab-switch{
        	width: 100%;
        	height: 30px;
        	line-height: 30px;
        	display: flex;
        	padding: 0 10px;
        	position: relative;
        }
        .tab-switch::before{
        	position: absolute;
        	top: 0;
        	left: 0;
        	right: 0;
        	border-top: 1px solid #ddd;
        	content: '';
        	width: 100%;
        	height: 1px;
        }
        .tab-switch::after{
        	position: absolute;
        	bottom: 0;
        	left: 0;
        	right: 0;
        	border-bottom: 1px solid #ddd;
        	content: '';
        	width: 100%;
        	height: 1px;
        }
        .tab-switch li{
        	flex: 1;
        	width: 1%;
        	text-align: center;
        	line-height: 30px;
        }
        a{
        	text-decoration: none;
        	color: #000;
        }
        .tab-switch li.up a{
         	color: #000;
         	
        }
        .tab-switch li.on a{
        	color: #E4393C;
        }
        .goodsdetails{
        	display: none;
        }
        .goodsdetailson{
        	display: block;
        }
        .detail_shop_box_v3{
        	padding: 10px;
		    font-size: 14px;
		    color: #333;
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
        }
        .detail_shop_box_v3 .shop_info {
		    overflow: hidden;
		    margin-bottom: 10px;
		    width: 80px;
		    height: 50px;
		    vertical-align: middle;
		    
		}
		.detail_shop_box_v3 .shop_info .logo_wrap{
			width:100%;
			height: 100%;
			display: flex;
		    justify-content: center;
		}
		.detail_shop_box_v3 .shop_info .logo_wrap img{
			width: 40px;
			height: 40px;
			border-radius: 50%;
			margin-top: 5px;
		}
		.shop-go{
			width: 100px;
			height: 50px;
			justify-content: center;
			align-items: center;
			display: flex;
		}
		.shop-name{
			width: 100%;
			height: 50px;
			line-height: 50px;
			padding-left: 10px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			box-sizing: border-box;
		}
		.shop-go a{
			display: inline-block;
			width: 80px;
			height: 36px;
			line-height: 36px;
			margin-top: 7px;
			text-align: center;
		}
		.attentbox{
			width: 100%;
			height: auto;
			overflow: hidden;
			text-align: center;
		}
		.attentbox div{
			float: left;
			width: 50%;
			overflow: hidden;
			font-size: 12px;
			font-weight: 0;
		}
		.attentbox div span{
			display: block;
		}
		.reply{
			width: 100%;
			height: auto;
			line-height: 16px;
			padding: 0 30px 0 10px;
			overflow: hidden;
			position: relative;
		}
		.reply span.replybtn{
			font-size: 18px;
			float: right;
			margin-right: 5px;
			position: absolute;
			top: 5px;
			right: 0px;
		}
		.replyModal {
				width: 100%;
				height: 100%;
				position: fixed;
				top: 0;
				left: 0;
				right: 0;
				z-index: 1000;
				background: rgba(0, 0, 0, .1);
				display: none;
			}
			
			.replyModal-content {
				width: 80%;
				height: 200px;
				background: #fff;
				border-radius: 10px;
				box-sizing: border-box;
				margin-top: -200px;
				position: absolute;
				top: 50%;
				left: 10%;
			}
			
			.replyModal-content-tit {
				height: 34px;
				line-height: 34px;
				width: 100%;
				text-align: center;
				font-size: 18px;
				position: relative;
			}
			.replyModal-content-tit::after{
				content: '';
				width: 100%;
				height: 1px;
				background: #ececec;
				position: absolute;
				bottom: 0;
				left: 0;
			}
			.replyModal-content-cont {
				height: 126px;
				width: 100%;
				/*text-align: center;*/
				padding: 10px;
				box-sizing: border-box;
			}
			
			.replyModal-content-cont textarea {
				width: 100%;
				height: 106px;
				padding: 5px;
				box-sizing: border-box;
				resize: none;
				border-radius: 5px;
				border: 1px solid #ccc;
			}
			
			.replyModal-content-foot {
				width: 100%;
				display: flex;
				height: 40px;
			}
			
			.replyModal-content-foot span {
				flex: 1;
				width: 1%;
				text-align: center;
				border: none;
				color: #fff;
				line-height: 40px;
			}
			.replycancel{
				background: #E4393C;
				border-bottom-left-radius: 5px;
			}
			.replyOk{
				background: #007AFF;
				border-bottom-right-radius: 5px;
			}
			.virtualcoin{
				display: inline-block;
    			line-height: 24px;
    			color: #e4393c;
			}
			.collectbox.on,.attentbox.on{
				color: #e4393c;
			}
    </style>
</head>
<body class="cmp640">
	<header style="background: #fefefe;width: 100%;height: 44px;position: fixed;top: 0;left: 0;padding: 0 10px;line-height: 44px;text-align: center;">
			<a  href="javascript:history.back(-1);" style="display: inline-block;float: left;width: 30px;height: 30px;background: url('${ctx}/xmMobile/img/goback.png') no-repeat;background-size: 100% 100%;margin-top: 10px;"></a>
			<h1 class="mui-title">商品详情</h1>
	</header> 
			
	
<main style="position: relative;padding-top: 44px;">
    <c:if test="${not empty slide}">
        <div id="banner_box" class="box_swipe overflow-hidden position-r" style="width:100%">
            <ul>
                <c:forEach items="${slide}" var="bean">
                    <li>
                        <a href="${bean.url}">
                            <img src="${filehttp}/${bean.picurl}" alt="1" style="width:100%;"/>
                        </a>
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
            });
        </script>
    </c:if>

    <div class="hang10 mb-5 clear bg-hui-92"></div>

    <a href="#">
        <div class="col-12 clear div-group-5 pt-10 bg-huang zi-6 weight500">
            <font size="1">
                <font size="3">
                    <div class="clear zi-6 weight500 line-height25" style="color:#333333;overflow: hidden;">
                      <div id="" style="width: 70%;float: left;">
                      	${entity.ptitle} 
                      </div>          
                      <div class="attentbox" style="width: 30%;float: right;">
                      	
                      	<div class="collectbox"  onclick="productCollect(${entity._id});">
                      		<i class="fa fa-heart-o"></i>
                      		<span class="Collect"></span>
                      	</div>
                      	<div class="attentbox" onclick="productAttent(${entity._id})">
                      		<i class="fa fa-heart-o"></i>
                      		<span class="Attent" ></span>  
                      	</div>
                      	<!--<span onclick="productCollectDel(${entity._id});">取消收藏</span> <span onclick="productAttentDel(${entity._id})">取消关注</span>-->
                      </div>   
                    </div>
                </font>


                <div class="pt-10">
                    <font size="3">
                        <i class="zi-hong" style="display: block;">￥<fmt:formatNumber value='${entity.price}' pattern="0.0#"/></i>
                        <span class="virtualcoin">PPB:0.00</span>
                    </font>
                </div>
                <div class="clear pt-10 pb-5 zi-hui overflow-hidden">
                    <font size="2">
                        <div class="txt-l col-4">
                            原价:<i style="text-decoration: line-through;"><fmt:formatNumber value='${entity.oldprice}' pattern="0.0#"/></i>
                        </div>
                        <div class="txt-c col-4">
                            销量:<i>${entity.gmnum}</i>件
                        </div>
                        <div class="txt-r col-4">
                        <c:if test="${not empty entity.kdprice }">
                            <i>快递:${entity.kdprice }元</i>
                        </div>
                        </c:if> 
                        <c:if test="${empty entity.kdprice }">
                            <i>快递:0元</i>
                        </div>
                        </c:if> 
                    </font>
                </div>
            </font>
        </div>
    </a>
   	<div class="hang10 clear bg-hui-92"></div>
   	<div class="detail_shop_box_v3">
   		<div class="shop_info">
   			<span class="logo_wrap "><img class="shopLogo" src="${ctx}/xmMobile/img/Public-banner.jpg" /></span>
   		</div>
   		<div class="shop-name">
   			${entity.comname}
   		</div>
   		<div class="shop-go">
   			<a href="${ctx}/shop/shop!index.action?lscode=${lscode}&comid=${entity.comid}">进入店铺</a>
   		</div>
   	</div>
	<ul class="tab-switch">
		<li class="on"><a href="#contxt">商品详情</a></li>
		<li class="up"><a href="#assess">商品评价</a></li>
	</ul>
    <div class="width-10 clear pl-5 pr-5 pt-5 img-100 goodsdetails goodsdetailson" id="contxt">
        ${entity.context}
    </div>
    <div class="width-10 clear pt-5 goodsdetails" id="assess">
    	<div class="detail_row detail_cmt">
    		<div class="cmt_list_wrap">
    			<ul id="cmt_list">
    				
    			</ul>
    		</div>
    	</div>
    </div>
</main>
 

<!--<%@include file="/webcom/foot.jsp" %>-->

<div class="hang50 clear"></div>

<div class=" button_foot hang40 bg-bai zi-bai shadow-wai cmp640">
 <div class="col-5">
     <div class="col-6 txt-c zi-6 hang40" onclick="window.location.href='${ctx}/shop/shop!index.action?&custid=${custid}&agid=${agid}&lscode=${lscode}&comid=${entity.comid}'">
         <font size="4">
             <div class="hang20">
                 <i class=" fa fa-home line-height25"></i>
             </div>
         </font>

         <div class="hang20 line-height20">
             首页
         </div>
     </div>
     <div class="col-6 txt-c zi-6 hang40 position-r" onclick="window.location.href='${ctx}/shop/shop!shoppingcar.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
         <font size="4">
             <div class=" hang20">
                 <i class=" fa fa-shopping-cart line-height25"></i>
             </div>
         </font>
         <div class="hang20 line-height20">
             购物车
         </div>

         <div class="border-radius50 bg-hong position-a" style=" width:15px; height:15px;top:2px; right: 8px;">
             <font size="1">
                 <i class="zi-bai" style="line-height:15px;" id="shopcount">
                     ${shopcount}
                 </i>
             </font>
         </div>
     </div>
 </div>
    <div class="col-7">
        <div class="col-6" onclick="showspec(0)">
            <div class="btn-cheng hang40 line-height40 txt-c size14">
                加入购物车
            </div>
        </div>
        <div class="col-6" onclick="showspec(1)">
            <div class="btn-lu hang40 line-height40 txt-c size14">
                立即购买
            </div>
        </div>
    </div>
</div>
		<!-- 评论回复弹出框 -->
		 <div class="replyModal">
			<div class="replyModal-content">
				<div class="replyModal-content-tit">
					回复
				</div>
				<div class="replyModal-content-cont">
					<textarea name="" rows="" cols="" id="yh_reply"></textarea>
				</div>
				<div class="replyModal-content-foot">
					<span class="replycancel" onclick="replyCancel()">取消</span>
					<span class="replyOk" onclick="reply_Ok()">提交</span>
				</div>
			</div>
		</div>
<script>
	var comid;
	var parentid;
	function replyBtn(cid,pid){
		comid=cid;
		parentid=pid;
		$(this).click(function(){
			$('.replyModal').show();
		});
	}
	function reply_Ok(){
		var submitData = { 
				comid:comid,
				parentid:parentid,
				content:$("#yh_reply").val()
        };
		 $.post('${ctx}/shop/shopcom!ajaxReplayUser.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,

                    function (json) { 
			 			alert(json);
                        if (json.state == 0) {
                        	 window.location.reload();
                        }  
                    }, "json");
	}
	
	
	function replyCancel(){
		$(this).click(function() {
			$('.replyModal').hide()
		})
	}
	
	$('.tab-switch li a').click(function(){
		$(this).parent().addClass('on').siblings('.on').removeClass('on');
		var id = $(this).attr('href');
		console.log(id)
		$(id).addClass('goodsdetailson').siblings('.goodsdetailson').removeClass('goodsdetailson')
	})
    function  check_task(){
       var submitData = { 
                type:"allshare",
            };
            $.post('${ctx}/suc/bbs!ajaxCheckTask.action?custid=${custid}&agid=${agid}&&lscode=${lscode}', submitData,

                    function (json) { 
                        if (json.state == 0) {
                            var text='分享成功!'; 
                            if(json.expreward>0){
                                text+="经验+"+json.expreward+" "
                            }
                            if(json.jfreward>0){
                                text+="平台币+"+json.jfreward
                            } 
                          swal({
                                text: text,
                                timer: 2000,
                                type: 'success',
                                showConfirmButton: false
                            }).then(function () {
                                    },
                                    function (dismiss) {
                                        if (dismiss === 'timer') {

                                        }
                                    }
                            );
                        }  
                    }, "json");
     
     }
</script> 
<%@ include file="/webcom/shop-spec.jsp" %>  
<script>
	
getcom();
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
<c:if test="${not empty com.ewmurl}">
 <c:if test="${com.ewmxs==0}">
  <%@ include file="/webcom/focus-page.jsp" %>
 </c:if>
</c:if>
<%@ include file="/webcom/toast.jsp" %>
<c:if test="${com.zsjf>0}">
  <c:if test="${sczs==1}">
  <%@ include file="/webcom/jfts-page.jsp" %>
  </c:if> 
</c:if> 
<!--客服-->
   <%--  <div class="position-f img-wh35 txt-c bj-lan1 zi-bai border-radius50"style="bottom:60px; right: 3px;" onclick="window.location.href='${ctx}/android/reply!index.action?custid=${custid}&lscode=${lscode}&id=${entity.comid}&pid=${entity._id}'">
        <i class="fa fa-commenting"style="line-height: 35px;"></i>
    </div> --%>
<!--客服结束--> 
</body>
</html>