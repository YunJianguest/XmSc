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
		<link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/app/css/font-awesome.min.css" rel="stylesheet"/>
        <link href="${ctx }/app/css/font-awesome-ie7.min.css" rel="stylesheet"/>
		<script src="${ctx}/app/js/iosOverlay.js"></script>
        <script src="${ctx}/app/js/spin.min.js"></script>
        <link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
        <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<style type="text/css">
			
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
			.mui-btn.miner-btnbuy{
				/*background-color:linear-gradient(to bottom top, #e2a60b,#facd04);*/
				background: linear-gradient(to bottom right, #e2a60b , #facd04);
				color: #fff;
			}
			.mui-table-view .mui-media-object{
				margin-top: 15px;
				max-width: 60px;
				height: 60px;
				vertical-align: middle;
			}
			.mui-table-view-cell.mui-media{
				margin-bottom: 2px;
				color:#fff;
				background:none;
			}
			.mui-table-view-cell.mui-media .miner-txt-cont{
				font-size:16px;
			}
			.mui-table-view-cell.mui-media p.mui-ellipsis{
				color:#ddd;
				font-size:12px;
			}
			.mui-content>.mui-table-view{
				padding-bottom: 0;
			}
			.coin{
				width: 100%;
				line-height: 40px;
				display: flex;
				justify-content: space-between;
				padding: 0 15px;
				color: #fff;
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
	   loading()
	   	var submitData = { 
	   			
	    }; 
	   
	    issend=false;  
	    $.post('${ctx}/integral/miners!showall.action?custid=${custid}&agid=${agid}&lscode=${lscode}&fypage='+fypage, submitData,
	       	function(json) { 
	       		loading.hide()
	    		var xszf=$('.mui-table-view').html(); 
		    	if(json.state=='0'){
		    		var v = json.list; 
		    		 for(var i=0;i<v.length;i++){  
		    		    xszf+='<li class="mui-table-view-cell mui-media miner-media">'
							+'<img class="mui-media-object mui-pull-left" src="${filehttp}/'+v[i].logo+'">'
						    +'<div class="mui-media-body miner-txt">'
							+'<div class="miner-txt-cont mui-col-xs-9">'+v[i].ptitle+''
							+'<p class="mui-ellipsis"><span>数量:</span><span>'+v[i].price+'</span></p>'
						    +'<p class="mui-ellipsis"><span>运行周期:</span><span>'+v[i].time+'天</span></p>'
							+'<p class="mui-ellipsis"><span>提成百分比:</span><span>'+v[i].percent+'</span></p>'	
							+'</div>'	
							+'<div class="miner-buyBtn mui-col-xs-3">'
							+'<button type="button" class="mui-btn miner-btnbuy" onclick="rechange('+v[i]._id+')">兑换</button>'
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
					console.log('${isfull}');
					if('${isfull}' != '1'){
						alert('您还未补全信息，请先补全信息，即可购买矿机');
						window.location.href = "${ctx}/user/fromuser!safePwd.action?custid=${custid}&agid=${agid}&lscode=${lscode}&url=/integral/miners!list.action";
					}else{
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
					}
					
				}else{
					mui.toast('你取消了购买')
				}
			},'div')
		  
	  }
		
		</script>
	</head>

	<body>
		 <header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-undo mui-pull-left"></a>
			<h1 class="mui-title">矿机列表</h1>
		</header> 
		<div class="mui-content" style="overflow: scroll;height: 100%;background: url('${ctx}/xmMobile/img/minerback.jpg') no-repeat;background-size: 100% 100%;">
			<div class="mui-row">
				<div class="coin">
					<span>消费累计:</span>
					<c:if test="${dbObject == null}">
					<span>0</span>
					</c:if>
					<c:if test="${dbObject != null}">
					<span>${dbObject.prostore}</span>
					</c:if>
				</div>
			</div>
			<div class="mui-row">
				<ul class="mui-table-view" style="background: none;margin-top: 0;"></ul>
			</div>
		</div>
		<%@include file="/webcom/shop-foot3.jsp" %>
		<script src="${ctx}/xmMobile/js/mui.min.js"></script>
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			mui.init();
			$('.miner-btnbuy').click(function(){
				
			})
			
			ajaxjz();
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
	</body>

</html>