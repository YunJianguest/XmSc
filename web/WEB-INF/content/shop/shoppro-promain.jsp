<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<style type="text/css">
			.mui-table-view .mui-media-object{
				margin-top: 10px;
				max-width: 60px;
				height: 60px;
				vertical-align: middle;
				border-radius: 5px;
			}
		</style>
		<script type="text/javascript">
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
                "goodstype": '${goodstype}',
                "typeid": '${typeid}',
                "mintypeid": '${mintypeid}',
                "ptitle": '${ptitle}'
            };
            issend = false;
            $.post('${ctx}/shop/shoppro!prolist.action?custid=${custid}&agid=${agid}&fypage=' + fypage, submitData,
                    function (json) {
                        var html = $('.mui-table-view').html();
                        alert(json.state)
                        if (json.state == 0) {
                            var v = json.list;
                            for (var i = 0; i < v.length; i++) {
                                if (i & 1 != 0) {
                                	
                                	html+='<li class="mui-table-view-cell mui-media">'
                                	    +'<a href="${ctx}/shop/shop!shopproduct.action?custid=${custid}&agid=${agid}&lscode=${lscode}&pid=' + v[i]._id + '">'
            		    	            +'<img class="mui-media-object mui-pull-left" src="img/banner-01.jpg">'
            		    	            +'<div class="mui-media-body">'
            		    	            +'<p class="mui-ellipsis">'+ v[i].ptitle+'</p>'
            		    	            +'</div></a></li>';
            		    	              
            					
                                }
                            }
                            fypage++;
                            $('.mui-table-view').html(html);
                            
                        } else {
                        	$('.mui-table-view').html('暂无数据');
                        }
                        issend = true;
                    }, "json")
              }
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color: #000;" href="javascript:history.go(-1)"></a>
		    <h1 class="mui-title">商品列表</h1>
		</header>
		<div class="mui-content" style="padding-top: 44px;padding-bottom: 50px;background: #fff;">
		    <div class="mui-row">
		    	<ul class="mui-table-view">
		    	  
		    	   
		    	</ul>
		    </div>
		</div>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
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