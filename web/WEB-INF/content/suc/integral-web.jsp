<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
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
    <title>我的盼盼币</title>
    <script src="${ctx}/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet"/> 
     <script src="${contextPath}/mvccol/js/fomatdate2.js"></script>
    <script>
    var issend=true;
    var fypage=0;
    var xszf="";
	var type="";
	 
  function ajaxjz(){//加载 
    if(!issend){
    	return;
    }
   
   	var submitData = { 
     
    }; 
   
    issend=false; 
    $.post('${ctx}/suc/integral!ajaxweb.action?custid=${custid}&lscode=${lscode}&fypage='+fypage, submitData,
       	function(json) { 
    		var xszf=$('#ajaxdiv').html();
    		 
	    	if(json.state=='0'){
	    		var v = json.list; 
	    		 for(var i=0;i<v.length;i++){  
	    		    xszf+='<a href="#"><div class="line-bottom zi-6 overflow-hidden txt-c">'
	    		    +'<div class="col-3 pt-15 pb-10 sl">';
	    		  
	    		     if(v[i].type=='ps_account'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">开通账户</font></div>';
	    		    } 
	    		      if(v[i].type=='tj_account'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">推荐平台代理</font></div>';
	    		    } 
	    		      if(v[i].type=='ps_recovery'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">回本后待返</font></div>';
	    		    }   
	    		    if(v[i].type=='jfcz'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">积分充值</font></div>';
	    		    }
	    		    if(v[i].type=='shop_bmzt'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">商城收益</font></div>';
	    		    }   
	    		    if(v[i].type=='shop_jfdh'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">下单使用</font></div>';
	    		    } 
	    		    if(v[i].type=='jf_withdraw'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">积分提现</font></div>';
	    		    } 
	    		    if(v[i].type=='shop_order'){
	    		     xszf+='<div class="width-9 maring-a clear sl"><font size="2">订单收益</font></div>';
	    		    }
	    		    xszf+='</div><div class="col-7 pt-15 pb-10"><font size="2">'+Date.prototype.format(v[i].createdate)+'</font></div>';
	    		   if(v[i].state==1){
	    		    xszf+='<div class="col-2 pt-15 pb-10"><font size="2">-'+v[i].value+'</font></div></div></a>'; 
	    		   }else{ 
	    		    xszf+='<div class="col-2 pt-15 pb-10"><font size="2">+'+v[i].value+'</font></div></div></a>'; 
	    		   }
	    		   
				 }
	    		fypage++;
				 
	    		
	    	}else{
	    		
	    	}
	    	
	    	issend=true;
			$('#ajaxdiv').html(xszf);
			 
	},"json")
	
}
    </script>
</head>
<body>
 
<header style="width: 100%;height: 40px;line-height: 40px;text-align: center;padding: 0 10px;background: #fff;">
		<a href="javascript:history.go(-1);" style="font-size: 18px;float: left;color: #000;width: 30px;display: inline-block;height: 40px;line-height: 40px;" class="fa fa-angle-left"></a>
			商品详情
	</header>
<main class="lock cmp640" id="section1"> 
    <div class="top_foot bg-hui-92 zi-bailine-height40 cmp640 line-bottom">
        <a href="#">
            <div class="zi-hei weight500 pl-15">
            	<div><font size="2">我的${setting.name}总计：<i class="zi-cheng">${jf.value}</i></font></div>
            	<div><font size="2">可用${setting.name}：<i class="zi-cheng">${jf.prostore}</i></font></div>
            	<div><font size="2">冻结${setting.name}：<i class="zi-cheng">${jf.uservalue}</i></font></div>
            	<div><font size="2">我的${setting.name}：<i class="zi-cheng">${jf.uservalue}</i></font></div>
            </div>
        </a>
        <%-- <a href="${ctx}/suc/integral!blacklist.action?custid=${custid}&lscode=${lscode}">
            <div class="pull-right zi-green weight500 pr-15"><font size="2">充值</font></div>
        </a> --%>
    </div>

    <div class="txt-c zi-cheng  div-group-5">

        <div class="line-left1 line-top line-bottom line-right border-radius5">
            <!--<a href="#">-->
            <!--<div class="line-bottom  zi-6 overflow-hidden txt-c"-->
            <!--style="border-radius:5px 5px 0px 0px ;">-->
            <!--<div class="col-12 pt-15 pb-10 weight500"><font size="2">积分获得/消费明细</font></div>-->
            <!--</div>-->
            <!--</a>-->

            <div class="line-bottom  zi-6 overflow-hidden txt-c weight500" style="border-radius:5px 5px 0px 0px ;">
                <div class="col-3 pt-15 pb-10"><font size="2">名称</font></div>
                <div class="col-7 pt-15 pb-10"><font size="2">时间</font></div>
                <div class="col-2 pt-15 pb-10"><font size="2">积分</font></div>
            </div>
             
            <!--循环列表开始-->
            
            <div id="ajaxdiv"></div>
         
            <!--下面代码不要删除或者循环-->
            <a href="#">
                <div class="zi-hui zi-green overflow-hidden txt-c"
                     style="border-radius:5px 5px 0px 0px ;">
                    <div class="col-15 pt-15 pb-10"><font size="2">以上是您的PP币获得/消费明细</font></div>
                </div>
            </a>
        </div>
    </div>




</main>
 
<script type="text/javascript">
ajaxjz();
$(window).scroll(function () {

    var offsetY = $(window).scrollTop();

    var section1 = $("#section1").height();
	if(section1-offsetY<600){
		ajaxjz(); 
	}
   
});

</script>
</body>
</html>