<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<!doctype html>
<html>
<head>
		<meta charset="UTF-8">
		<%@include file="/webcom/meta.jsp" %>
    <%@include file="/webcom/bracket.jsp" %>
    <%@include file="/webcom/jquery.validate_js.jsp" %>
		<title></title>
		<style type="text/css">
			html,body{
				width: 100%;
				height: 100%;
			}
			.miner{
				width: 100%;
				height: 100%;
				position: relative;
			}
			.animation-back {
				width: 180px;
				height: 180px;
				margin: 0 auto;
				margin-top: -150px;
				background: url(${ctx}/xmMobile/img/minerdetailback.jpg) no-repeat;
				background-size: 100% 100%;
				border-radius: 50%;
				-webkit-animation: rotation 6s linear infinite;
				animation: rotation 6s linear infinite;
				position: absolute;
				top: 50%;
				left: 0;
				right: 0;
				bottom: 0;
				
			}
			
			@-webkit-keyframes rotation {
				from {
					-webkit-transform: rotate(0deg);
					transform: rotate(0deg);
				}
				to {
					-webkit-transform: rotate(360deg);
					transform: rotate(360deg);
				}
			}
			
			@keyframes rotation {
				from {
					-webkit-transform: rotate(0deg);
					transform: rotate(0deg);
				}
				to {
					-webkit-transform: rotate(360deg);
					transform: rotate(360deg);
				}
			}
			.animation-cont{
				width: 160px;
				height: 160px;
				margin:  0 auto;
				margin-top: -140px;
				background: url(${ctx}/xmMobile/img/minerdetails.jpg)  no-repeat;
				background-size: 100% 100%;
				position: absolute;
				border-radius: 50%;
				line-height: 160px;
				text-align: center;
				color: #fff;
				font-size: 32px;
				top: 50%;
				left: 0;
				right: 0;
				bottom: 0;
				opacity: .8;
			}
			.miner-txt{
				color: #fff;
			}
			.miner-tit{
				font-size: 28px;
			}
			.miner-txt p{
				color: #fff;
			}
			#matrix{
				position: fixed;
				top: 0;
				bottom: 0;
				left: 0;
				right: 0;
				display: block;
			}
			.miner-txt{
				position: absolute;
				top: 0px;
				right: 20px;
			}
		</style>
	</head>
	<body>
	<section>
    <%@include file="/webcom/header-bracket.jsp" %>
    <div class="mainpanel">
       
           <div class="" style="width: 100%;height: 100%;">
			<canvas id="matrix" width="" height=""></canvas>
			<div class="miner">
				<div class="animation-back"></div>
				<div class="animation-cont">
					运行中
				</div>
				<div class="miner-txt">
					<p class="miner-tit"></p>
					<p class="miner-tit">${db.money}</p>
					<p>我的算力：<span id="mycount"></span>GH/d</p>
					<!-- <p>累计获得：305.4664646JRL</p> -->
					<p>全网算力：${setting.num}GHS</p>
					<p>结束时间：${db.end}</p>
				</div>
			</div>
		</div>
    </div>
</section>
		
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		$(function(){
			/* var time = 0;
			if('${db.time}' == ''){
				$("#mycount").html(${db.money}/time);
			}else{
				$("#mycount").html(${db.money}/${db.time});
			} */
			
		}); 
			canvas()
			function canvas(){
				var c = document.getElementById("matrix");
				var ctx = c.getContext("2d");
				
				c.height = window.innerHeight;
				c.width = window.innerWidth;
//				var chinese = "我们共同为建站事业而奋斗！";
//				chinese = chinese.split("");
				
				var font_size = 10;
				var columns = c.width/font_size;
				//an array of drops - one per column
				var drops = [];
				for(var x = 0; x < columns; x++)
					drops[x] = 1; 
				//drawing the characters
				function draw()
				{
					ctx.fillStyle = "rgba(0, 0, 0, 0.05)";
					ctx.fillRect(0, 0, c.width, c.height);
					
					ctx.fillStyle = "#0F0"; //green text
					ctx.font = font_size + "px arial";
					//looping over drops
					for(var i = 0; i < drops.length; i++)
					{
						var text = Math.floor(Math.random()*10);
						ctx.fillText(text, i*font_size, drops[i]*font_size);
						
						if(drops[i]*font_size > c.height && Math.random() > 0.975)
							drops[i] = 0;
						
					drops[i]++;
					}
				}
				setInterval(draw, 33);
			}
		</script>
	</body>
</html>
