<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>矿机购买</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
		<script src="${ctx}/app/js/iosOverlay.js"></script>
        <script src="${ctx}/app/js/spin.min.js"></script>
        <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
		<style type="text/css">
			.mui-content {
				padding-top: 44px;
			}
			
			.miner-txt{
				display: flex;
				justify-content: space-between;
			}
			.miner-txt-cont{
				overflow: hidden;
			}
			.miner-buyBtn{
				display: flex;
				justify-content: center;
				align-items: center;
			}
			
			.mui-table-view .mui-media-object{
				margin-top: 15px;
				max-width: 60px;
				height: 60px;
				vertical-align: middle;
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
	    var issend=true;
	    var fypage=0;
	    var xszf=""; 
		 
	  function ajaxjz(){//加载 
	    if(!issend){
	    	return;
	    }
	   
	   	var submitData = { 
	   			
	    }; 
	   
	    issend=false;  
	    $.post('${ctx}/integral/miners!showall.action?custid=${custid}&agid=${agid}&lscode=${lscode}&fypage='+fypage, submitData,
	       	function(json) { 
	    		var xszf=$('.mui-table-view').html(); 
		    	if(json.state=='0'){
		    		var v = json.list; 
		    		 for(var i=0;i<v.length;i++){  
		    		    xszf+='<li class="mui-table-view-cell mui-media miner-media">'
							+'<img class="mui-media-object mui-pull-left" src="http://placehold.it/40x30">'
						    +'<div class="mui-media-body miner-txt">'
							+'<div class="miner-txt-cont mui-col-xs-9">'+v[i].ptitle+''
							+'<p class="mui-ellipsis"><span>积分数量:</span><span>'+v[i].price+'</span></p>'
						    +'<p class="mui-ellipsis"><span>运行周期:</span><span>'+v[i].time+'年</span></p>'
							+'<p class="mui-ellipsis"><span>提成百分比:</span><span>'+v[i].percent+'</span></p>'	
							+'</div>'	
							+'<div class="miner-buyBtn mui-col-xs-3">'
							+'<button type="button" class="mui-btn mui-btn-green miner-btnbuy" onclick="rechange('+v[i]._id+')">兑换</button>'
							+'</div></div></li>';
					 }
		    		fypage++;
					 
		    		
		    	}else{
		    		
		    	}
		    	issend=true;
				$('.mui-table-view').html(xszf);
				 
		},"json")
		
	}
	  function rechange(id){
		  mui.confirm('你确定要购买矿机吗','提示',['取消','确认'],function (e) {
				console.log(e.index)
				if (e.index == 1) {
					var submitData = { 
					   		id:id	
					    }; 
					  $.post('${ctx}/integral/miners!saveMiner.action?custid=${custid}&agid=${agid}&lscode=${lscode}', submitData,
				        	function (json) {
				            	if(json.state==0){
				            		alert('兑换成功');
				            		window.location.href="${ctx}/integral/miners!ownminer.action?custid=${custid}&agid=${agid}&lscode=${lscode}";
				            	}else if(json.state==2){
				            		alert('PP币不足');
				            	}else{
				            		alert('操作失败');
				            	}
					  },"json")
				}else{
					mui.toast('你取消了购买')
				}
			},'div')
		  
	  }
		
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">矿机购买</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view">
				
			</ul>
		</div>
		<%@include file="/webcom/shop-foot.jsp" %>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			mui.init();
			$('.miner-btnbuy').click(function(){
				
			})
			
			ajaxjz();
		</script>
	</body>

</html>