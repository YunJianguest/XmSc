<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>${title}</title>
    <!-- Resource style -->
    <script src="${ctx }/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
    <link href="${ctx }/app/css/style_0.css" rel="stylesheet"/> 
    <!-- Resource style -->
    <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
    <script src="${ctx}/app/js/iosOverlay.js"></script>
    <script src="${ctx}/app/js/spin.min.js"></script>
    <script type="text/javascript" src="${ctx }/app/js/swipe.js"></script>
    <script type="text/javascript" src="${ctx}/app/js/jquery.qrcode.js"></script>
    <script type="text/javascript" src="${ctx}/app/js/qrcode.js"></script> 
    <script type="text/javascript">
     
    </script>
    <style> 

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
          .bk {
            height: 70px;
            width: 70px;
        }
        .border-bottom-d9d9d9{
            border-bottom: solid 1px #d9d9d9;
            
        }
        .zi-353535{
            color: #353535;
        }
        .zi-bbbbbb{
            color: #bbbbbb;
        }
        .zi-9a9a9a{
            color: #9a9a9a;
        }
        .zi-d8d8d8{
            color: #d8d8d8;
        }
        .hang-sl-2{
            overflow: hidden;text-overflow:ellipsis;display: -webkit-box;-webkit-line-clamp:4;-webkit-box-orient:vertical;
        }
        .masks,
			.modal {
				width: 100%;
				height: 100%;
				background: rgba(0, 0, 0, .1);
				position: fixed;
				top: 0;
				left: 0;
				right: 0;
				bottom: 0;
				z-index: 100;
				display: none;
			}
			
			.mask-cont {
				width: 100%;
				height: auto;
				background: #fff;
				position: absolute;
				bottom: 50px;
				left: 0;
				padding: 0 10px;
				z-index: 101;
			}
			
			.mask-cont-tit {
				width: 100%;
				height: 30px;
				line-height: 30px;
				text-align: center;
				position: relative;
			}
			
			.mask-cont-tit::after {
				content: '';
				width: 100%;
				height: 0.5px;
				position: absolute;
				bottom: 0;
				left: 0;
				background: #ddd;
			}
			
			.mask-cont-cont {
				width: 100%;
				height: auto;
				padding-bottom: 10px;
			}
			
			.mask-cont-cont button {
				width: 100%;
				height: 34px;
				line-height: 34px;
				background: none;
				border: 1px solid #000;
				border-radius: 5px;
				display: block;
				margin-top: 10px;
			}
			
			.modal {
				z-index: 1001;
				background: rgb(127, 127, 127);
			}
			
			.modal-cont {
				width: 80%;
				height: auto;
				z-index: 1002;
				margin: 0 auto;
				text-align: center;
				background: #fefefe;
				border-radius: 5px;
				padding: 10px;
				overflow-y: auto;
				position: absolute;
			    top: 40px;
			    left: 10%;
			    right: 10%;
			}
			.modal-tit{
				line-height: 16px;
				position: relative;
				font-size: 12px;
				margin-top: 10px;
			}
			.modal-cont-tit{
				line-height: 16px;
				font-size: 11px;
				padding: 5px 10px;
				text-align: left;
				height: 80px;
				overflow-y: auto;
			}
			.modal-cont-tit p{
				margin: 0;
			}
			canvas{
				background: #fefefe;
				border-radius: 10px;
				padding: 10px;
			}
			.gopayBtn{
				width: 100px;
				height: 34px;
				line-height: 34px;
				color: #fff;
				background: #E4393C;
				display: inline-block;
				border-radius: 5px;
				margin-top: 15px;
				text-decoration: none;
			}
			.bturlBox{
				width: 100%;
				height: 24px;
			}
			input:focus{
				outline: none;
			}
			#bturl{
				width: 80%;
				margin: 0 auto;
				border: none;
				border-bottom: 1px solid #e3e3e3;
			}
    </style>
    <script>
    
    var issend=true;
    var fypage=0;
    var xszf="";
	var type="";
	 //loding
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
	 
	function ajaxjz(){//加载  
	    if(!issend){
	    	return;
	    }
	   loading()
	   	var submitData = {
	   		state:'${state}'
	    }; 
	   
	    issend=false; 
	    $.post('${ctx}/shop/shop!ajaxorders1.action?custid=${custid}&agid=${agid}&lscode=${lscode}&fypage='+fypage, submitData,
	       	function(json) { 
	       		loading.hide()
	    		var xszf=$('#ajaxdiv').html();  
	    		
		    	if(json.state=='0'){
		    		var v = json.list; 
		    		console.log(v);
		    		 for(var i=0;i<v.length;i++){  
		    		  xszf+='<div class="pl-10 pr-10 pt-10 overflow-hidden">'
		    		      +'<div class="bg-bai border-radius5">'
		    		      +'<div class="hang30 line-bottom-98 zi-hui-tq weight500 overflow-hidden line-height30 pl-5 pr-5 zi-353535">'
		    		      +'<font size="1"><div class="col-9 sl">订单编号:<i class="pl-5">'+v[i]._id+'</i></div>'
		    		      +'<div class="col-3 txt-r sl zi-cheng" onclick="del('+v[i]._id+')"><i class="fa fa-trash-o zi-hong line-height40"></i></div>'
							/* if(v[i].state==1){
		    		          	  xszf+='<div class="col-3 txt-r zi-bbbbbb">已下单</div>'
		    		        }else if(v[i].state==2){
		    		          xszf+='<div class="col-3 txt-r zi-bbbbbb">待发货</div>'
		    		        }else if(v[i].state==3){
		    		          xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="resure('+v[i]._id+')">确认收货</div>';
		    		        }else if(v[i].state==4){
		    		        	xszf+='<div class="col-3 txt-r zi-bbbbbb">订单完成</div>'
		    		        	   xszf+='<div class="col-3 txt-r zi-bbbbbb">订单完成</div>'
		    		          		 +'<div class="col-3 txt-r zi-bbbbbb" onclick="shopcom('+list[j]._id+','+list[j].pro._id+')">评价</div>'; 
		    		         }else if(v[i].state==5){
		    		          xszf+='<div class="col-3 txt-r zi-bbbbbb">已退款</div>';
		    		         } */
		    		      xszf+=''
		    		      +'</font></div>';
		    		      if(v[i].comlist!=null){
		    		    	   var comlist=v[i].comlist; 
		    		    	   for(var k=0;k<comlist.length;k++){
		    		    		   xszf+='<div style="line-height:30px"><span>'+comlist[k].shop.name+'</span>';
		    		    		    if(comlist[k].list!=null){
		    			    		       var list=comlist[k].list;
		    			    		       
		    			    		       if(list[0].goodstate == 1 || list[0].goodstate == 0){
		    			    		    	   xszf+='<div class="col-5 txt-r zi-bbbbbb" style="float: right;margin-right: 5px;" >待付款<span href="" style="color:#e4393c;margin-left: 5px;" onclick="choosePay('+v[i]._id+','+(v[i].zfmoney+v[i].kdprice)+','+comlist[k].type+')">去支付</span></div>'
		    			    		    		   
		    			    		       }
		    			    		       
		    			    		       if(list[0].goodstate == 2){
		    			    		    	   xszf+='<div class="col-3 txt-r zi-bbbbbb" style="float: right;margin-right: 5px;" >待发货</div>'
		    			    		       }
		    			    		       if(list[0].goodstate == 3){
		    			    		    	   xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="resure('+v[i]._id+','+comlist[k].shop._id+')" style="color:#e4393c;float: right;margin-right: 5px;">确认收货</div>'
		    			    		       }
		    			    		       
		    			    		       if(list[0].goodstate == 4){
		    			    		    	   xszf+='<div class="col-3 txt-r zi-bbbbbb" style="float: right;margin-right: 5px;">订单完成</div>'
		    			    		       }
		    			    		       
		    			    		       for(var j=0;j<list.length;j++){
		    			    		            xszf+='<div class="clear div-group-10 position-r  border-radius5" style="overflow:hidden;">'
		    			    		         +'<div class=" position-a"><div class="img-bj bk border-radius3" style="background-image:url(${filehttp}/'+list[j].pro.logo+');" onclick="prodetail('+list[j].pro._id+')"></div>'
		    			    		         +'</div>'
		    			    		         +'<div style="padding-left:80px;">'
		    			    		         +'<font size="2">'
		    			    		         +'<div class="zi-6 weight500 sl">'+list[j].pro.ptitle+'</div>'
		    			    		         +'</font>'
		    			    		         +'<div class=" pull-left weight500 width-10">'
		    			    		         +'<font size="1">';
		    			    		         if(v[i].kdcom!=null){
		    			    		           xszf+='<div class="clear sl hang30 weight100" style="line-height:35px;" onclick="getkd('+v[i].kdno+')">'
		    			    		               +'<span class="zi-hui">'+v[i].kdcom+'<i class="zi-lan pl-5">'+v[i].kdno+'</i><span class="zi-lan-tq pl-10">点击查看</span></span>'
		    			    		               +'</div>';
		    			    		         }else{
		    			    		           xszf+='<div class="clear sl hang30 weight100" style="line-height:35px;" onclick="getkd('+v[i].kdno+')">'
		    			    		               +'<span class="zi-hui"><span class="zi-lan-tq">暂无物流信息</span></span>'
		    			    		               +'</div>';
		    			    		         }
		    			    		         xszf+='<div class=" width-10 line-height20 zi-6">'  
		    			    		         +'<div class="col-9"><span>共'+list[j].count+'件商品<i class="pl-10 zi-hong">￥'+(list[j].pro.price*1+list[j].pro.kdprice*1).toFixed(2)+'元</i></span><span style="display:block;color:#e4393c;">PPB:0.00</span></div>';
		    			    		         if(list[j].goodstate<4){
		    				    		          if(list[j].state==1 || list[j].state==3){
		    				    		        	  xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="find('+v[i]._id+','+list[j].sid+')" style="color:#e4393c">退货查看</div>';
		    				    		          }else if(list[j].state==2 || list[j].state==4){
		    				    		        	  xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="find('+v[i]._id+','+list[j].sid+')" style="color:#e4393c">退货查看</div>';
		    				    		          }else {
		    				    		        	  xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="service('+list[j]._id+')" style="color:#e4393c">申请售后</div>';
		    				    		          }
		    			    		          
		    			    		         }else if(list[j].goodstate==2){
		    			    		          xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="service('+list[j]._id+')" style="color:#e4393c">申请售后</div>';
		    			    		         }else if(list[j].goodstate==3){
		    			    		          xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="service('+list[j]._id+')" style="color:#e4393c">申请售后</div>';
		    			    		          
		    			    		         }else if(list[j].goodstate==4){
		    			    		        	  if(list[j].states==0){
		    			    		        		 xszf+='<div class="col-3 txt-r zi-bbbbbb" onclick="shopcom('+list[j]._id+','+list[j].pro._id+')" style="color:#e4393c">评价</div>'; 
		    			    		        	 }else if(list[j].states==1){
		    			    		        		 xszf+='<div class="col-3 txt-r zi-bbbbbb" style="color:#e4393c">已评价</div>'; 
		    			    		        	 } 
		    			    		        	/*   xszf+='<div class="col-3 txt-r zi-bbbbbb">订单完成</div>'
		    			    		          		 +'<div class="col-3 txt-r zi-bbbbbb" onclick="shopcom('+list[j]._id+','+list[j].pro._id+')">评价</div>';  */
		    			    		         }
		    			    		         xszf+=''
		    			    		             +'</div></font></div></div></div>';
		    			    		       }
		    			    		     
		    			    		      }
		    		    		   
		    		    		   
		    		    		    xszf+='</div>';
		    		    	   }
		    		    	  
		    		      }
		    		  
		    		     xszf+='</div></div>';
		    	 
					}
					
				 
		    		fypage++;
	            }else{
	               // xszf='<font size="2"><div class="div-group-10 width-10 txt-c zi-hui-tq weight500 pt-40">您暂时还没有任何订单，<a href="${ctx}/shop/shop!index.action?lx=1&custid=${custid}&agid=${agid}&fromUserid=${fromUserid}"><i class="zi-green">去转转</a></div></font>';
	            }
	            issend=true;
				$('#ajaxdiv').html(xszf);
				 
		},"json")
		
	}
	 
function find(oid,sid){
	window.location.href="${ctx}/shop/service!find.action?custid=${custid}&agid=${agid}&lscode=${lscode}&orderid="+oid+"&sid="+sid;
}

function service(orderproId){
	window.location.href="${ctx}/shop/service!serviceadd.action?custid=${custid}&agid=${agid}&lscode=${lscode}&orderproId="+orderproId;
}

function shopcom(oid,gid){
	window.location.href="${ctx}/shop/shopcom!shopcomadd.action?custid=${custid}&agid=${agid}&lscode=${lscode}&oid="+oid+"&gid="+gid;
}
function del(id) {
	  
    var submitData = {
    	 id:id
    };

    $.post('${ctx}/shop/shop!ajaxdelorder.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
    	function (json) {
    	
        	if(json.state==0){ 	
        	 alert("删除成功！");
        	 window.location.reload();
        	}
        },"json");
  
} 
  function  getkd(id){
   window.location.href='http://m.kuaidi100.cn/result.html#com=auto&no='+id;
  }
  function resure(oid,comid){
	  var submitData = {
			  oid:oid,
			  comid:comid
		    };

		    $.post('${ctx}/shop/shop!delivery1.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
		    	function (json) {
		    	
		        	if(json.state==0){ 	
		        	 alert("收货成功！");
		        	 $.post('${ctx}/shop/shop!getJf.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
		     		    	function (json) {
		     		    	
		     		        	if(json.state==0){ 	 
		     		        	   if(json.data.prostore>=3000){
		     		        		   $("#ppbjf").html("您有"+json.data.prostore+"盼盼币，可兑换一个矿机！");
		     		        		  friedtx_show();
		     		        	   }
		     		        	  
		     		        	}else{
		     		        		alert('操作失败');
		     		        	}
		     		        },"json");
		        	 //window.location.reload();
		        	}else{
		        		alert('操作失败');
		        	}
		        },"json");
  }
  function popcode(val){ 
	  var txt="1GTapaVtP9JgS4GHtnxZbcoFTxdKXECuKu";
   	 if(val == 0){ 
   		 if(shoptype==3){
      		  txt="3AYHYGbC9uE5vBWfbQutyrEmyUd3cAwEdY";
      	  }else if(shoptype==5){
      		  txt="3MKiB7ZX16zgwAwbXxM4LugGXm3Co1fFJe";
      	  }else if(shoptype==4){
      		  txt="3CS2WvYFnrwL1G3e9jKg6o4guGh4boP4Pr";
      	  }
   		 $('#bt').css('display','block');
				pay_bt();
				//二维码生成
				$('#qrcode').qrcode({ 
				  width : 200,
			      height : 200,
			      text	: '1GTapaVtP9JgS4GHtnxZbcoFTxdKXECuKu'
			    });
			    $("#bturl").text('1GTapaVtP9JgS4GHtnxZbcoFTxdKXECuKu'); 
   	 }
   	 if(val == 1){
   		 if(shoptype==3){
      		  txt="0xefa03a9B480A9890C5541065A398F43f82F32832";
      	  }else if(shoptype==5){
      		  txt="0xc3d8d8fBDb34dA5930d95869b692D80B668d4b98";
      	  }else if(shoptype==4){
      		  txt="0x842B0afCaA759ea325A915D2a5e5963B618DcEf1";
      	  }
   		 $('#ytf').css('display','block');
				pay_ytf();
				//二维码生成
				$('#qrcodes').qrcode({ 
				  width : 200,
			      height : 200,
			      text	: '0x842B0afCaA759ea325A915D2a5e5963B618DcEf1'
			    });
				$("#ytfurl").text('0x842B0afCaA759ea325A915D2a5e5963B618DcEf1'); 
   	 }
   	 if(val == 2){
   		 var submitData = { 
   				 orid:$('#oid').val()
        	};
   		 $.post('${ctx}/shop/shop!OrderPayJf.action?lscode=${lscode}', submitData,
            		function(json) {
					//alert(json.state);
    					if(json.state == 0){
    						alert("支付成功！");
    						window.location.href="${ctx}/shop/shop!orderform.action?agid=${agid}&lscode=${lscode}";
    					}else if(json.state == 1){
    						alert("操作失败");
    					}else{
    						alert("支付失败，余额不足！");
    					}
            		},
            		"json");
   	 }
      } 
  var shoptype;
  function choosePay(v,p,f){
	  if(confirm("是否支付?")){
		  shoptype=f;
		  $("#totalPrice").val(p); 
		  $("#oid").val(v);
		  $(this).click(function(){
				$('.masks').show("slow");
				 
			})
	  }
  }
   
	
	 function pay_ytf(){
	    	var totalPrice = $('#totalPrice').val(); 
	    	var submitData = {
	    	};
	    	$.post('${ctx}/integral/miners!getETHSrice.action?lscode=${lscode}', submitData,
	    		function(json) {
	    	    if(json.state==0){
	    	       if(totalPrice != '0.00'){
	    	    	   $('#ytfnum').html(parseFloat(totalPrice)/json.data); 
	    	       }else{
	    	    	   $('#ytfnum').html(0.00);
	    	       }
	    	   }			
	    	},"json")
	    }
	 
    function pay_bt(){
    	var totalPrice = $('#totalPrice').val(); 
    	var submitData = {
    	};
    	$.post('${ctx}/integral/miners!getBTCSrice.action?lscode=${lscode}', submitData,
    		function(json) {
    	    if(json.state==0){
    	       if(totalPrice != '0.00'){
    	    	   $('#btnum').html(parseFloat(totalPrice)/json.data); 
    	       }else{
    	    	   $('#btnum').html(0.00);
    	       }
    	    	
    	   }
    				
    	},"json")
    }
    function prodetail(id){
    	window.location.href='${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid='+id;
    }
  
</script>
</head>
<body>
    <input type="hidden" id="totalPrice" />
    <input type="hidden" id="oid" />
	<header style="background: #fefefe;width: 100%;height: 44px;position: fixed;top: 0;left: 0;padding: 0 10px;line-height: 44px;text-align: center;">
			<a  href="javascript:history.go(-1);" style="display: inline-block;float: left;width: 30px;height: 30px;background: url('${ctx}/xmMobile/img/goback.png') no-repeat;background-size: 100% 100%;margin-top: 10px;"></a>
			<h1 class="mui-title">订单列表</h1>
	</header> 
 
<main class="clear cmp640 lock bg-hui-92" style='padding-top: 44px;'>

    <div id="ajaxdiv"></div>
    <c:if test="${ordercount==0}">
    <font size="2"><div class="div-group-10 width-10 txt-c zi-hui-tq weight500 pt-40">您暂时还没有任何订单，<a href="${ctx}/shop/shop!index.action?lx=1&custid=${custid}&agid=${agid}&lscode=${lscode}"><i class="zi-green">去转转</a></div></font>
    </c:if>
    <div class="hang50"></div>

</main>

<!--底部三个按钮-->
<%@include file="/webcom/shop-foot.jsp" %> 
 <div class="fullscreen cmp640 bg-hei-5 lock" id="friedtx">
    <div class="overflow-hidden width-10">
        <a href="javascript:friedtx_hide()">
            <div class="width-10 overflow-hidden" style="height:1000px;"></div>
        </a> 
        <div class=" cmp640 position-f position-r" style="top:15%;left:0px;z-index: 99;">

            <div class="position-a hang60 width-10" style="top:-35px;z-index: 100;">
                <div class="img-wh70 border-radius50 maring-a">
                    <img src="${filehttp}/${user.headimgurl}" width="100%" class=" border-radius50">
                </div>
            </div>

            <div class="maring-a position-r border-radius5 div-group-15 pt-45 bg-bai" style="width:280px;">
                <a href="javascript:friedtx_hide()">
                    <div class="position-a" style="right:-7px;top:-7px;z-index: 101;">
                        <div class="img-wh20 border-radius50 bg-hui-tx txt-c">
                            <font size="2">
                                <i class="fa fa-remove zi-green" style="line-height:22px"></i>
                            </font>
                        </div>
                    </div>
                </a>

                <div class=" width-10 border-radius5 zi-hui-tq overflow-hidden">
                    <font size="2">
                        <div class="weight500">您好:</div>
                        <div class="weight500 line-height25">&nbsp&nbsp&nbsp尊敬的：<i class="zi-green">${user.nickname}</i><i class="zi-green" id="ppbjf">您有${jf.prostore}可兑换</i></div>
                    </font>
                </div>

                <div class="width-10 pt-15">
                    <div class="button_group1" onclick="window.location.href='${ctx}/user/fromuser!basemsg.action?custid=${custid}&agid=${agid}&lscode=${lscode}'">
                        <div class="width-8 maring-a border-radius3 btn-green zi-bai txt-c hang30 line-height30">
                          立即兑换
                        </div>
                    </div>
                    <div class="button_group1" onclick="friedtx_hide()">
                        <div class="width-8 maring-a border-radius3 btn-green zi-bai txt-c hang30 line-height30">
                           稍后查看
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="masks" id="masks">
	<div class="mask-cont">
		<div class="mask-cont-tit">
			付款方式
			<i class="fa fa-close pull-right" style="font-size: 16px;padding-right: 5px;padding-top: 5px;" id="closes"></i>
		</div>
		<div class="mask-cont-cont">
			<button onclick="popcode(0)" class="currency">比特币</button>
			<button onclick="popcode(1)" class="currency">以太坊</button>
			<button onclick="popcode(2)" class="currency">盼盼币</button>
		</div>
	</div>
</div>
<div class="modal" id="bt">
	<div class="modal-cont">
		<div style="height: 34px;font-size: 18px;text-align: center;">
			BitCoin
		</div>
		<div id="qrcode"></div>
		<div class="bturlBox" id="bturl" style="height: 34px;line-height: 34px;font-size: 10px;">
			<!--<input type="text" id="bturl" style="height: 34px;line-height: 34px;"/>-->
		</div>
		<div class="modal-tit">
			提示
		</div>
		<div class="modal-cont-tit">
			<p>1.您需要支付的比特币:<span id="btnum" style="display: block; font-size: 16px;line-height: 34px;"></span></p>
			<p>2.请备注你的手机号码;</p>
			<p>3.请备注你的会员号码;</p>
			<p>4.请交易手续费最大化;</p>
			<p>5.平台确认收币后发货，若由此造成的延误由个人承担</p>
			<p>6.请复制上方的付款码</p>
		</div>
		<a href="${ctx}/shop/shop!orderform.action?agid=${agid}&lscode=${lscode}" class="gopayBtn">去付款</a>
	</div>
</div>
<div class="modal" id="ytf">
	<div class="modal-cont" >
		<div style="height: 34px;font-size: 18px;text-align: center;">
			ETH
		</div>
		<div id="qrcodes"></div>
		<div class="bturlBox" id="ytfurl" style="height: 34px;line-height: 34px;font-size: 10px;text-align:center;width:100%;">
			<!--<input type="text" id="ytfurl"  style="height: 34px;line-height: 34px;"/>''-->
		</div>
		<div class="modal-tit">
			提示
		</div>
		<div class="modal-cont-tit">
			<p>1.您需要支付的以太坊币:<span id="ytfnum" style="display: block; font-size: 16px;line-height: 34px;text-align:center;width:100%;"></span></p>
			<p>2.请备注你的手机号码;</p>
			<p>3.请备注你的会员号码;</p>
			<p>4.请交易手续费最大化;</p>
			<p>5.平台确认收币后发货，若由此造成的延误由个人承担</p>
			<p>6.请复制上方的付款码</p>
		</div>
		<a href="${ctx}/shop/shop!orderform.action?agid=${agid}&lscode=${lscode}" class="gopayBtn">去付款</a>
	</div>
</div>

<script>
function  friedtx_hide(){
 $("#friedtx").hide();
 window.location.reload();
}
$("#friedtx").hide();
function  friedtx_show(){
 $("#friedtx").show();
}
 $("#closes").on('click',function(){
		 console.log($('.masks').hide("slow"))
			$('.masks').hide("slow")
		}) 
 
</script>
</body>
<script>
ajaxjz();
$(window).scroll(function () {

    var offsetY = $(window).scrollTop();

    var section1 = $("#section1").height();
	if(section1-offsetY<600){
		ajaxjz();
	}
   
});

</script>


</html>