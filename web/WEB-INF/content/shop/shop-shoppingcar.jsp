<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%> 
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <script src="${ctx}/app/js/iosOverlay.js"></script>
    <script src="${ctx}/app/js/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/app/js/jquery.qrcode.js"></script>
    <script type="text/javascript" src="${ctx}/app/js/qrcode.js"></script> 
    <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx }/app/js/jquery.Spinner.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet">
    <link href="${ctx }/app/css/style_0.css" rel="stylesheet"> 
    <%@ include file="/webcom/toast.jsp" %>
    <script type="text/javascript">
        var spec='';
        var zj=0;
        var kd=0;
        var total=0;
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
        function jfdh(){ 
         var oldjf=$('#jf').html();  
         var jf=$('#jfdh').val(); 
         if(jf!=""){
           var jz=zj;
           if(jf/100<=jz){
           jz=jz-jf/100;
           $('#totalPrice').html(jz.toFixed(2)); 
            }else{
            alert("超出兑换额度！");
           }
         }else{
         }
        
        }
     
        
        function check(v,id,price){
        if($(v).find('i').css('display')=="none"){
         $(v).find('i').show();
          var quantity =$('#'+id+'_lb').find('.count').attr('count'); 
          var price =Number($('#'+id+'_lb').find('.price').html());
          zj+=quantity*price;
         document.getElementById("totalPrice").innerHTML =zj.toFixed(2);
         
         ids+=id+",";
         prices+=price+",";
         counts+=$("#"+id+"_lb").find(".count").attr('count')+",";
         remarks+=$("#"+id+"_lb").find(".title").html()+",";
         kd=kd+parseFloat($("#"+id+"_lb").find(".kd").attr('kd'));
        }else{
         $(v).find('i').hide();
         var quantity =$('#'+id+'_lb').find('.count').attr('count');
         var price =Number($('#'+id+'_lb').find('.price').html());
         zj-=quantity*price;
         document.getElementById("totalPrice").innerHTML =zj.toFixed(2);
         ids=ids.replace(id+",","");
         prices=prices.replace(price+",", "");
         counts=counts.replace($("#"+id+"_lb").find(".count").attr('count')+",", "");
         remarks=remarks.replace($("#"+id+"_lb").find(".title").html()+" ","");
         kd=kd-parseFloat($("#"+id+"_lb").find(".kd").attr('kd'));
        }
        total=zj;
        if('${byprice}'<=0||totalPrice<'${byprice}'){ 
          var to=zj+kd;
          total=to;
         document.getElementById("totalPrice").innerHTML =to.toFixed(2)+'元  <i>快递:￥'+kd.toFixed(2)+'</i>';
         }    
        }
     function moneypay(){ 
     if('${address.tel}'==""){
         alert("请先设置收货地址");
          return ;
         } 
            var address='${address.province}'+"-"+'${address.city}'+"-"+'${address.county}'+" "+'${address.address}';
        	var submitData = { 
        			lx:0,
        			no:'0',
        			name:'${address.name}',
        			tel:'${address.tel}',
        			address:address,  
                	remoney:prices,
                	recordid:ids, 
        			price:total, 
        			comid:'${entity._id}',
        			num:counts,
        			remark:remarks.split(",")[0],
        			spec:spec,
        			kjid:kd
        	}; 
        	//loading();
        	/* $.post('${ctx}/shop/shop!wxcarpay.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
        		function(json) { 
        			if (json.state == 0) {
        				WeixinJSBridge.invoke('getBrandWCPayRequest',{
        			  		 "appId" : json.appId,"timeStamp" : json.timeStamp, "nonceStr" : json.nonceStr, "package" : json.packageValue,"signType" : json.signType, "paySign" : json.paySign 
        			   			},function(res){ 
        			   			  loading.hide(); 
        			   				if(res.err_msg == "get_brand_wcpay_request:ok"){ 
        			   					 var text='购买成功！';
        			   					 if(!jQuery.isEmptyObject(json.jffh)){
        			   					   text="购买成功！获得平台币"+json.jffh
        			   					 }
        			   					 noty({text: text,type:'alert', layout: "top", timeout: 1000,callback: { // 回调函数
                                              afterClose: function() {
                                        window.location.href="${ctx}/shop/shop!orderform.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
                                              } // 关闭之后
                                            },});
        			   				}else{
        			   				
        			   					alert(res.err_code+res.err_desc+res.err_msg);
        			   					 
        			   				}
        						}); 
        				return;
        			}else if(json.state == 1) {
        				alert("该账号没有开通支付"); 
        			}else if(json.state == 3){
        			  alert("没有登录");
        			}else if(json.state==10){
        			  alert("购买次数已完");
        			}
        		},
        		"json") */
        	$.post('${ctx}/shop/shop!COrderFromCar.action?agid=${agid}&lscode=${lscode}&isgwc=0', submitData,
            		function(json) { 
            		     loading.hide();
            		     alert(json.state);
            		 	if (json.state == 0) {
            				alert("购买成功！");
            				window.location.href="${ctx}/shop/shop!orderform.action?agid=${agid}&lscode=${lscode}";
            			}else if(json.state == 1) {
            				alert("该账号没有开通支付"); 
            			}else if(json.state == 3){
            			  alert("没有登录");
            			}else if(json.state==10){
            			  alert("购买次数已完");
            			}
            		},
            		"json")
    	 
           } 
     function popcode(val){ 
         if('${address.tel}'==""){
             alert("请先设置收货地址");
              return ;
             } 
                var address='${address.province}'+"-"+'${address.city}'+"-"+'${address.county}'+" "+'${address.address}';
            	var submitData = { 
            			lx:0,
            			no:'0',
            			name:'${address.name}',
            			tel:'${address.tel}',
            			address:address,  
                    	remoney:prices,
                    	recordid:ids, 
            			price:total, 
            			comid:'${entity._id}',
            			num:counts,
            			remark:remarks.split(",")[0],
            			spec:spec,
            			kjid:kd
            	}; 
            	//loading();
            	/* $.post('${ctx}/shop/shop!wxcarpay.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
            		function(json) { 
            			if (json.state == 0) {
            				WeixinJSBridge.invoke('getBrandWCPayRequest',{
            			  		 "appId" : json.appId,"timeStamp" : json.timeStamp, "nonceStr" : json.nonceStr, "package" : json.packageValue,"signType" : json.signType, "paySign" : json.paySign 
            			   			},function(res){ 
            			   			  loading.hide(); 
            			   				if(res.err_msg == "get_brand_wcpay_request:ok"){ 
            			   					 var text='购买成功！';
            			   					 if(!jQuery.isEmptyObject(json.jffh)){
            			   					   text="购买成功！获得平台币"+json.jffh
            			   					 }
            			   					 noty({text: text,type:'alert', layout: "top", timeout: 1000,callback: { // 回调函数
                                                  afterClose: function() {
                                            window.location.href="${ctx}/shop/shop!orderform.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
                                                  } // 关闭之后
                                                },});
            			   				}else{
            			   				
            			   					alert(res.err_code+res.err_desc+res.err_msg);
            			   					 
            			   				}
            						}); 
            				return;
            			}else if(json.state == 1) {
            				alert("该账号没有开通支付"); 
            			}else if(json.state == 3){
            			  alert("没有登录");
            			}else if(json.state==10){
            			  alert("购买次数已完");
            			}
            		},
            		"json") */
            	$.post('${ctx}/shop/shop!COrderFromCar.action?agid=${agid}&lscode=${lscode}&isgwc=0', submitData,
                		function(json) { 
                		     loading.hide();
                		     alert(json.state);
                		 	if (json.state == 0) {
                		 		alert("下单成功，请支付");
                				if(val == 0){
                					$('#bt').css('display','block')
                				}
                				if(val == 1){
                					$('#ytf').css('display','block')
                				}
                			}else if(json.state == 1) {
                				alert("该账号没有开通支付"); 
                			}else if(json.state == 3){
                			  alert("没有登录");
                			}else if(json.state==10){
                			  alert("购买次数已完");
                			}
                		},
                		"json")
        	 
               } 
 
    </script>
       <script>
    
    var issend=true;
    var fypage=0;
    var xszf="";
	var type="";
	var ids="";
	var counts="";
	var prices="";
	var remarks=""; 
function ajaxjz(){//加载 
    if(!issend){
    	return;
    }
   loading()
   	var submitData = { 
      sel:$('#sel').val()
    }; 
   
    issend=false; 
    
    $.post('${ctx}/shop/shop!ajaxshopcarweb.action?custid=${custid}&agid=${agid}&lscode=${lscode}&fypage='+fypage, submitData,
       	function(json) { 
       		loading.hide();
    		var xszf=$('#ajaxdiv').html(); 
	    	if(json.state=='0'){
	    		var v = json.list;  
	    		 for(var i=0;i<v.length;i++){ 
	    		  if(v[i].product!=null){
	    		    spec+=v[i].spec+",";
	    		    xszf+='<div id="'+v[i]._id+'_lb" class="line-bottom pt-10 pb-10 pr-10 pl-5 overflow-hidden position-r" style="height:90px;">'
	    		  +'<div class="col-1"><div class="mt-30 clear pr-5">'
	    		  +'<div onclick="check(this,'+v[i]._id+','+v[i].product.price.toFixed(2)+')" class="img-wh15 bg-bai-5 maring-a txt-c border-radius50" style="border: solid #45c01a 2px;">'
	    		  +'<font size="1"><i id="'+v[i]._id+'"  class="fa fa-check zi-green" style="line-height:11px; display:none"></i>'
	    		  +'</font></div></div></div>'
	    		  +'<div class="col-11"><div class=" position-a">'
	    		  +'<div class="img-bj bk border-radius3" style="background-image: url(${filehttp}/'+v[i].product.logo+');"></div>'
	    		  +'</div><div style="padding-left:100px;"><div class="zi-6 weight500">'
	    		  +'<font size="2"><div class="col-10 sl zi-hei title">'+v[i].product.ptitle+'</div>'
	    		  +'<a href="javascript:delcar(\''+v[i]._id+'\')"><div class="pull-right col-1 txt-c">'
	    		  +'<i class="fa fa-trash-o zi-hong"></i></div></a></font></div>'
	    		  +'<div class=" pull-left weight500"><font size="2">'
	    		  +'<div class="clear sl hang30" style="line-height:35px;">'
	    		  +'<span class="zi-hui  kd" kd='+v[i].product.kdprice+'>快递:'+v[i].product.kdprice.toFixed(2)+'元</span>&nbsp; 规格：'+v[i].spec+'</div>'
	    		  +'<div class="sl hang30" style="line-height:30px;">';
	    		  if(v[i].price==0){
	    		  xszf+='<span class="zi-cheng">￥<span class="price">'+v[i].product.price.toFixed(2)+'</span>元<i class="pl-10 zi-6 count" count='+v[i].count+'>';
	    		  }else{
	    		  xszf+='<span class="zi-cheng">￥<span class="price">'+v[i].price.toFixed(2)+'</span>元<i class="pl-10 zi-6 count" count='+v[i].count+'>';
	    		  } 
	    		  xszf+='数量:'+v[i].count+'件</i></span></div></font></div>'
	    		  +'</div></div></div>';
	    		   }
	    	     
	    		   	
				}
	    		fypage++;
				 
	    		
	    	}else{
	    		
	    	}
	    	
	    	issend=true;
			$('#ajaxdiv').html(xszf);
			 
	},"json")
	
}
function delcar(id){
  	var submitData = {
			id:id 
	};
	$.post('${ctx}/shop/shop!ajaxdelshopcar.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
		function(json) {
	    if(json.state==0){
	    alert("删除成功！");
	    window.location.reload();
	   }
				
	},"json")
}
 
    </script>
    <style>
        .bk {
            height: 70px;
            width: 90px;
        }

        .web-site {
            margin-left: 125px;
        }

        .button-kong {
            width: 30px;
            height: 30px;
            line-height: 26px;
            border-top: solid 2px #eee;
            border-bottom: solid 2px #eee;
        }

        .line-height33 {
            line-height: 33px;
        }
        .hang2-bj {
            height: 2px;
            background-image: url(${ctx}/img/scdz-xz.png);
        }
         .mask,.modal{
        	width: 100%;
        	height: 100%;
        	background: rgba(0,0,0,.1);
        	position: fixed;
        	top: 0;
        	left: 0;
        	right: 0;
        	bottom: 0;
        	z-index: 100;
        	display: none;
        }
        .mask-cont{
        	width: 100%;
        	height: auto;
        	background: #fff;
        	position: absolute;
        	bottom: 50px;
        	left: 0;
        	padding: 0 10px;
        	z-index: 101;
        }
        .mask-cont-tit{
        	width: 100%;
        	height: 30px;
        	line-height: 30px;
        	text-align: center;
        	position: relative;
        }
        .mask-cont-tit::after{
        	content: '';
        	width: 100%;
        	height: 0.5px;
        	position: absolute;
			bottom: 0;
			left: 0;
			background: #ddd;
        }
        .mask-cont-cont{
        	width: 100%;
        	height: auto;
        	padding-bottom: 10px;
        }
        .mask-cont-cont button{
        	width: 100%;
        	height: 34px;
        	line-height: 34px;
        	background: none;
        	border: 1px solid #000;
        	border-radius: 5px;
        	display: block;
        	margin-top: 10px;
        }
        .modal{
        	z-index: 1001;
        	background: rgba(0,0,0,.3);
        	
        }
        .modal-cont{
        	width: 100%;
        	height: 100%;
        	display: flex;
        	justify-content: center;
        	align-items: center;
        	z-index: 1002;
        }
        #qrcode{
        	width: auto;
        	height: auto;
        	border-radius: 5px;
        	background: #fff;
        	padding: 10px;
        }
    </style>
</head>
<body>
 
<main class="clear cmp640 lock">
 <c:if test="${not empty address}">
     <a href="${ctx}/shop/shop!useraddress.action?lscode=${lscode}&custid=${custid}&agid=${agid}&address=ok&backurl=/shop/shop!shoppingcar.action?1=1">
         
        <font size="2">
            <div class="div-group-10 overflow-hidden zi-6 weight100 col-11">
                <div class="">
                    <div class="pull-left">收货人:<i>${address.name}</i></div>
                    <div class="pull-right">${address.tel}</div>
                </div>
                <div class="clear pt-5">
                    <div class="pull-left sl width-10">收货地址:<i>${address.address}</i></div>
                </div>
            </div>
            <div class="col-1 div-group-5 txt-c zi-hui hang40 overflow-hidden">
                <i class="fa fa-chevron-right fa-1dx" style="line-height:50px;"></i>
            </div>
            <div class="hang10  clear"></div>
        </font>
        <div class="hang2-bj"></div>
        <div class="hang10 bg-hui clear"></div>
     </a>
    
    </c:if>
    <c:if test="${empty address}">
    <a href="${ctx}/shop/shop!useraddress.action?lscode=${lscode}&custid=${custid}&agid=${agid}&address=err&backurl=/shop/shop!shoppingcar.action?1=1">
       <div class="hang60 div-group-10">
        <div class="hang40 line-height40">
            <div class="img-wh40 pull-left txt-c line-height40 bj-lan3 zi-bai border-radius5"><i
                    class="fa fa-plus fa-1dx line-height40"></i></div>
            <div class="zi-hui pl-15 pull-left">新增收货地址</div>
            <div class="zi-hui pull-right"><i class="fa fa-1dx fa-angle-right line-height40"></i></div>
       </div>
      </div>
      <div class="hang2-bj"></div>
      <div class="hang10 bg-hui clear"></div>
    </a>
    </c:if>
    <div class="clear"></div>
    <div id="ajaxdiv"></div>

    <div class="hang40 clear"></div>


    <input type="hidden"  id="price"/><!--单价-->
   
</main>

<div class=" button_foot shadow-wai overflow-hidden bg-bai cmp640">
 

    <div class="div-group-5 hang50 overflow-hidden line-top">
        <div class="col-8 zi-hong weight500 pl-15" style="line-height:42px;">
            <font size="2">
                总计:<i id="totalPrice" class="fa fa-cny pl-5 weight500">0</i>元
            </font>
        </div>
        <div class="col-4 zi-bai size14 weight500 txt-c pull-right">
            <!--<a href="javascript:moneypay()">-->
                <div class=" hang40 ">
                    <div class="hang40 line-height40 btn-lu border-radius3" id="ConfirmPay">确认付款</div>
                </div>
            <!--</a>-->
        </div>
    </div>
</div>
<div class="mask">
	<div class="mask-cont">
		<div class="mask-cont-tit">
			付款方式
			<i class="fa fa-close pull-right" style="font-size: 16px;padding-right: 5px;padding-top: 5px;" id="close"></i>
		</div>
		<div class="mask-cont-cont">
			<button onclick="popcode(0)" class="currency">比特币</button>
			<button onclick="popcode(1)" class="currency">以太坊</button>
			<button onclick="moneypay()" class="currency">盼盼币</button>
		</div>
	</div>
</div>
<div class="modal" id="bt">
	<div class="modal-cont" >
		<div id="qrcode">	
		</div>
		 <div >普通URL</div>
            <div class="pt-15">
                <input type="text"id="bturl"/>
            </div>
	</div>
</div>
<div class="modal" id="ytf">
	<div class="modal-cont" >
		<div id="qrcodes">
			
		</div>
		 <div >普通URL</div>
            <div >
                <input  type="text"id="ytfurl"/>
            </div>
	</div>
</div>
</body>
<script>
ajaxjz();
//获取屏幕宽度
	var w= $(window).width()/2; 
$(window).scroll(function () {

        var offsetY = $(window).scrollTop();

        var section1 = $("#section1").height();
        if (section1 - offsetY < 600) {
          ajaxjz();
        }

});
$('#ConfirmPay').click(function(){
		$('.mask').css('display','block')
	})
	$('#close').click(function(){
		$('.mask').css('display','none')
	})
//	$('.modal').click(function(){
//		$('.modal').css('display','none')
//	})
//	
	
	//二维码生成
	$('#qrcode').qrcode({ 
	  width : w,
      height : w,
      text	: '1GTapaVtP9JgS4GHtnxZbcoFTxdKXECuKu'
    });
    $("#bturl").val('1GTapaVtP9JgS4GHtnxZbcoFTxdKXECuKu'); 
	//二维码生成
	$('#qrcodes').qrcode({ 
	  width : w,
      height : w,
      text	: '0x842B0afCaA759ea325A915D2a5e5963B618DcEf1'
    });
	$("#ytfurl").val('0x842B0afCaA759ea325A915D2a5e5963B618DcEf1'); 
</script> 
</html>