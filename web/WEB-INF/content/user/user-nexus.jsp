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
</head>
<body>
<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">我的关系</h1>
		</header>
		<div class="mui-content">
			<div class="mui-card">
				<ul class="mui-table-view mui-collapse" id="accordion" style="display: block;">
					<!-- 第一级 -->
					<c:forEach items="${obj}" var="bean">
					<li class="mui-table-view-cell" style="padding-right: 15px;">
						<a class="mui-navigate-right link">
							${bean.user.account }
						</a>
						
						<!-- 第二级 -->
						<ul class="mui-table-view mui-collapse">
							<c:forEach items="${bean.son}" var="son">
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right link">
									${son.user.account }
								</a>
								
								<!-- 第三级 -->
								<ul class="mui-table-view mui-collapse">
									<c:forEach items="${son.son}" var="son1">
									<li class="mui-table-view-cell">
										<a class="mui-navigate-right link">
											${son1.account }
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
		<script src="${ctx}/xmMobile/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/xmMobile/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				var Accordion = function(el, multiple) {
					this.el = el || {};
					this.multiple = multiple || false;
					var links = this.el.find('a.link');
					links.on('click', {
						el: this.el,
						multiple: this.multiple
					}, this.dropdown)
				}
				Accordion.prototype.dropdown = function(e) {
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
</body>
</html>
