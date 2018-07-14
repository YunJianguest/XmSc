<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>我的关系</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/xmMobile/css/common.css" />
	<link href="${ctx}/app/css/iosOverlay.css" rel="stylesheet"/>
	<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
     <script src="${ctx}/app/js/iosOverlay.js"></script>
    <script src="${ctx}/app/js/spin.min.js"></script>
    <style type="text/css">
			.mui-table-view-cell {
				padding-right: 0;
				
			}
			.mui-table-view-cell.mui-active{
				background-color: #fff;
			}
			.mui-table-view-cell>.mui-navigate-right.link:after {
				content: '\e581';
			}
			
			.mui-table-view-cell.mui-active>.mui-navigate-right:after{
				content: '\e580';
			}
			.mui-table-view.mui-collapse{
				display: none;
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
            issend = false;
            
            var submitData = {
					fromUserid:'${lscode}'
				};
				$.post('${ctx}/user/fromuser!ajaxnexus.action', submitData,
	                    function (json) {
					console.log("111111");
					console.log(json);
					loadings.hide();
		    		var xszf=$('#ajaxdiv').html(); 
		    		xszf="";
			    		var v = json.son; 
			    		var user = json.user;
			    			 xszf+="<ul class='mui-table-view mui-collapse' id='accordion' style='display: block;'>"+
								"<li class='mui-table-view-cell' style='padding-right: 15px;'>"+
									"<a class='mui-navigate-right link'>"+
										"<span style='font-size:22px;margin-right:5px;'>+</span>"+user.account+""+user.tel+
									"</a>"+
										"<ul class='mui-table-view mui-collapse'>";
									for(var i=0;i<v.length;i++){ 
										var v1 = v[i].son;
										var user1 = v[i].user;
										console.log(user1);
										xszf+="<li class='mui-table-view-cell'>"+
											"<a class='mui-navigate-right link'>"+
												"<span style='font-size:22px;margin-right:5px;margin-left:10px;'>+</span>"+user1.account+""+user1.tel+
											"</a>"+
											"<ul class='mui-table-view mui-collapse'>";
											for(var j=0;j<v1.length;j++){ 
												var v2 = v1[j].son;
												var user2 = v1[j].user;
												xszf+="<li class='mui-table-view-cell'>"+
													"<a class='mui-navigate-right link' style='padding-left:40px;'>"+
														user2.account+""+user2.tel+sss
													"</a>"+
												"</li>";
											}
											xszf+="</ul>"+
											"</li>";
									}
									xszf+="</ul>"+
									"</li>";
							"</ul>";
			    		fypage++;
						 
			    	
			    	
			    	issend=true;
					$('#ajaxdiv').html(xszf);
					var Accordion = function(el, multiple) {
						console.log(this)
						console.log(el.find('a.link'))
						this.el = el || {};
						this.multiple = multiple || false;
						var links = this.el.find('a.link');
						links.on('click', {
							el: this.el,
							multiple: this.multiple
						}, this.dropdown)
					}
					Accordion.prototype.dropdown = function(e) {
						console.log(e)
						var $el = e.data.el;
						console.log($(this).next())
						$this = $(this), $next = $this.next();
						$next.slideToggle();
						$this.parent().toggleClass('mui-active');
						if(!e.data.multiple) {
							$this.find('.mui-table-view').not($next).slideUp().siblings().removeClass('mui-active');
						};
					}
					 var accordion = new Accordion($('#accordion'), false);
	               }, "json")
            
           
              }
		</script>
</head>
<body>

		<header class="mui-bar mui-bar-nav">
        <a class="mui-action-back mui-icon mui-icon-undo mui-pull-left" style="color: #000;" href="javascript:history.go(-1)"></a>
        <h1 class="mui-title">我的关系</h1>
    </header>
		<div class="mui-content">
			<div class="mui-card" id="ajaxdiv">
				<ul class="mui-table-view mui-collapse" id="accordion" style="display: block;">
					<!-- 第一级 -->
					<c:forEach items="${obj}" var="bean">
					<li class="mui-table-view-cell" style="padding-right: 15px;">
						<a class="mui-navigate-right link">
							<span style='font-size:22px;margin-right:5px;'>+</span>${bean.user.account }${bean.user.tel }
						</a>
						
						<!-- 第二级 -->
						<ul class="mui-table-view mui-collapse">
							<c:forEach items="${bean.son}" var="son">
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right link">
									<span style='font-size:22px;margin-right:5px;margin-left:10px;'>+</span>${son.user.account }${bean.user.tel }
								</a>
								
								<!-- 第三级 -->
								<ul class="mui-table-view mui-collapse">
									<c:forEach items="${son.son}" var="son1">
									<li class="mui-table-view-cell">
										<a class="mui-navigate-right link" style='padding-left:40px;'>
											${son1.account }${bean.user.tel }
										</a>
									</li>
									</c:forEach>
								</ul>
							</li>
							</c:forEach>
						</ul>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<script src="${ctx}/xmMobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				var Accordion = function(el, multiple) {
					console.log(this)
					console.log(el.find('a.link'))
					this.el = el || {};
					this.multiple = multiple || false;
					var links = this.el.find('a.link');
					links.on('click', {
						el: this.el,
						multiple: this.multiple
					}, this.dropdown)
				}
				Accordion.prototype.dropdown = function(e) {
					console.log(e)
					var $el = e.data.el;
					console.log($(this).next())
					$this = $(this), $next = $this.next();
					$next.slideToggle();
					$this.parent().toggleClass('mui-active');
					if(!e.data.multiple) {
						$this.find('.mui-table-view').not($next).slideUp().siblings().removeClass('mui-active');
					};
				}
				 var accordion = new Accordion($('#accordion'), false);
				
			});
		</script>
		
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
