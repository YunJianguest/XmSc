<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>币兑换</title>
		<link rel="stylesheet" href="${ctx}/xmMobile/css/mui.min.css" />
		<script src="${ctx}/xmMobile/js/mui.min.js"></script><link href="${ctx}/xmMobile/css/mui.min.css" rel="stylesheet" />
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<style>
			* {
				margin: 0;
				padding: 0;
				box-sizing: border-box;
			}
			
			html {
				width: 100%;
				height: 100%;
			}
			
			body {
				width: 100%;
				height: 100%;
				background: #f1eff1;
			}
			
			li {
				list-style: none;
			}
			
			a {
				text-decoration: none;
			}
			
			.top {
				width: 100%;
				height: auto;
				overflow: hidden;
			}
			
			.top>img {
				width: 100%;
			}
			
			.content {
				width: 100%;
				height: auto;
				overflow: hidden;
				padding: 0 10px;
				margin-top: 20px;
			}
			
			.content>ul {
				width: 100%;
				height: auto;
				overflow: hidden;
			}
			
			.content>ul>li {
				width: 100%;
				line-height: 20px;
				font-size: 12px;
				font-family: "微软雅黑";
				text-align: left;
				overflow: hidden;
				text-overflow: ellipsis; 
				display: -webkit-box;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
				margin-bottom: 10px;
			}
			
			.btnClass {
				width: 50%;
				height: 40px;
				font-size: 16px;
				font-family: "微软雅黑";
				font-weight: bold;
				color: #fff;
				margin: 20px 0 0 25%;
				border-radius: 20px;
				border: none;
				background: linear-gradient(#edeb21, #f39547, #fecb12);
			}
		</style>
	</head>

	<body style="padding-bottom:20px;">
		<header class="mui-bar mui-bar-nav">
		    <a class=" mui-icon mui-icon-undo mui-pull-left" style="color: #000;" href="javascript:history.back(-1)"></a>
		    <h1 class="mui-title">币兑换</h1>
		</header>
		<div class="top" style="padding-top: 44px;"><img src="${ctx}/xmMobile/img/banner1.jpg" alt="" /></div>
		<div class="content">
			<ul>
				<span>熊猫商城报单中心招商政策
一、招商对象
1.	年满25周岁以上的中国公民；
2.	了解区块链行业及数字资产领域的企业主；
3.	企业、团体及行业退队领导人。
二、招商范围
1.	中国23个省5个自治区4个直辖市2个特别行政区下设的所有区域。
三、招商要求
1.	固定资产不低于100万；
2.	流动资金不低于20万；
3.	业务团队不低于5人；
4.	办公场地不低于50平。
四、报单中心权益
1.	报单中心推荐报单中心，享有其加盟费（3万）的35%作为佣金（30%现金+3%旅游基金+2%教育基金）；
2.	成为报单中心代理商，享有其加盟费（3万）3倍价值的矿机（主流货币、盼盼币任选一种），使用时限3年；
3.	报单中心享有所属下级市场消费额总和10%的分红；
4.	报单中心推荐商家入驻，享有该商家销售额2%的分红；
5.	报单中心推荐注册会员，享有会员注册资金的35%作为收益。同时享有注册会员矿机日产量10%的收益。
五、激励政策
    备注：除大市场之外的其他市场我们称为小区域市场（报单中心及往下）
小区域市场业绩总和每增加20万送一台价值2万的矿机，以此类推。
第一次到达20万  送Iphone X一部
第一次达到100万  送10万的车全款
累计达到300万  送BMW 5系
累计达到1000万  送100万房子一套 
特设熊猫商城14%股份分给前200名达到业绩100万的下级区域市场：
前20名：每名0.25%熊猫商城股份；
后180名：每名0.05%熊猫商城股份。
 
以上激励政策截止于2018年12月31日
				</span>
			</ul>
		</div>
		<button class="btnClass" onclick="exchange()">立即兑换></button>
		<script type="text/javascript">
			function exchange() {
				var address="${address}";
				var btn = ["取消", "确认"];
				var flag = mui.confirm("是否进行兑换", "兑换提示", btn, function(e) {
					if(e.index==1){
						$.ajax({
							type:"post",
							url:"${ctx}/integral/miners!dhZsb.action?lscode=${lscode}&custid=${custid}",
							async:true,
							data:{
								address:address
							},
							success:function(json){
								if(json.state == 0){
									alert("兑换成功");
								}else if(json.state == 1){
									alert("抱歉兑换过程异常");
								}else if(json.state == 2){
									alert("服务器未开通该功能");
								}
								else if(json.state == 3){
									alert("余额不足");
								}else if(json.state == 4){
									alert("未选择所属县域");
								}
							}
						});
						
					}
					  
				})
			}
		</script>
	</body>
</html>