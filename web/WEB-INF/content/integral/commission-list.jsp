<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>提现记录</title>
		<link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
        <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
        
        <script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		
		<script src="${ctx}/app/js/iosOverlay.js"></script>
    	<script src="${ctx}/app/js/spin.min.js"></script>
    	<script src="${ctx}/mvccol/js/fomatdate2.js"></script>
    	<script type="text/javascript" src="${ctx }/app/js/jquery.Spinner.js"></script>
		<style>
			*{
				margin: 0;
				padding: 0;
				box-sizing: border-box;
			}
			a{
				text-decoration:none;
			}
			li{
				list-style: none;
			}
			body{
				width: 100%;
				height: 100%;
				background: #eee;
				font-family: "微软雅黑";
				font-size: 14px;
			}
			.record{
				width: 100%;
				line-height: 40px;
				padding: 0 25px;
				background: #fff;
				margin-bottom: 5px;
				text-align: center;
				color:#555;
			}
			.water{
				width: 100%;
				height: 100%;
				padding: 25px;
				background: #fff;
			}
			.water>p{
				width: 100%;
				height: 25px;
				line-height: 25px;
				font-size: 12px;
				color: #777;
			}
			.water>p:first-of-type>span{
				color:#000;
				font-weight: bold;
			}
			.Currency>span,.water>p>span{
				float: left;
			}
			.top>p>span:last-of-type,.Currency>span:last-of-type,.water>p>span:last-of-type,.water>p>span:nth-of-type(2){
				float:right;
			}
			.water>p>span:last-of-type{
			    margin-right: 20px;
			}
			.Currency_list-color{
				color:red;
			}
		</style>
		<script type="text/javascript">
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
                
            }; 
            issend = false;
            $.post('${ctx}/integral/commission!ajaxlist.action?custid=${custid}&lscode=${lscode}&agid=${agid}&fypage=' + fypage, submitData,
                    function (json) {
            	     loadings.hide();
                        var html = $('.water').html();
                        html += '<p><span>日期时间</span><span>金额</span><span>当前状态</span></p>';
                        if (json.state == 0) {
                            var v = json.list;
                            for (var i = 0; i < v.length; i++) {
                                	
                                	html +='<p><span>'+Date.prototype.format(v[i].createdate)+'</span>'
                                	     +'<span>'+v[i].price.toFixed(2)+'</span>'
                              	     if(v[i].state == 0){
                              	    	 html += '<span>待处理</span>';
                              	     }
                                	if(v[i].state == 1){
                             	    	 html += '<span>申请通过，等待打款</span>';
                             	     }
                                	if(v[i].state == 2){
                            	    	 html += '<span>申请拒绝</span>';
                            	     }
                                	if(v[i].state == 3){
                            	    	 html += '<span>已到账</span>';
                            	     }
                                	html+='</p>';	
                            }
                            fypage++;
                            $('.water').html(html);
                            issend = true;
                        } else {
                        }
                       
                    }, "json")
              }
        </script>
	
	</head>
	<body>
		<p class="record">提现记录</p>
		<div class="water">
		
		</div>
		<%@include file="/webcom/shop-foot.jsp" %>
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
