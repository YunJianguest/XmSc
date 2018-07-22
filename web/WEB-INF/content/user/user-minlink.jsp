<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				var validator = $("#inscxForm").validate(
						{
							rules : {  
								name : {
									required : true
								},
								sort : {
									digits : true,
									required : true
								}

							},
							messages : {
							},
							highlight : function(element) {
								jQuery(element).closest('.form-group')
										.removeClass('has-success').addClass(
												'has-error');
							},
							success : function(element) {
								jQuery(element).closest('.form-group')
										.removeClass('has-error');
							}
						});
			});
	function del(id) {
		if (confirm('确实要删除吗?')) {
			location.href = "${ctx}/user/area!delete.action?parentId=${parentId}&_id=" + id;
		}
	}
	function add() {
		$('#_id').val('');
		$('#area').val('');
		$('#sort').val(0);  
		
		ps_show('inszc');
	}
	function upd(id) {
		var submitData = {
			_id : id
		};
		$.post('${ctx}/user/area!upd.action', submitData, function(json) {
			$('#_id').val(json._id);
			$('#area').val(json.area);  
			$('#sort').val(json.sort);
			$('#agentId').val(json.agentId);

		}, "json")
		ps_show('inszc');
	} 
</script>
</head>
<body>
	<section>
		<%@include file="/webcom/header-bracket.jsp"%>
		<div class="mainpanel">
			<%@include file="/webcom/header-headerbar.jsp"%>
			<form id="custinfoForm" name="custinfoForm" method="post"
				action="${contextPath}/user/user!minlink.action">
				<div class="pageheader">
					<h2>
						<i class="fa fa-user"></i> 微官网 <span>代理关系</span>
					</h2>
					<div class="breadcrumb-wrapper1">
						<div class="input-group ">
							<div style="border-radius:3px; height:40px;padding-left:10px;" class="btn-primary">
								<a href="javascript:add();"style="color: #ffffff;line-height:25px;">
									新增&nbsp;<i class="fa fa-plus"style="line-height:30px;font-size: 14px;"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-striped table-action table-primary mb30">
									<thead>
										<tr>
										    <!-- <th class="table-action">ID</th>  -->
										    <th class="table-action">部门账户</th> 
											<th class="table-action">地区</th> 
											<!-- <th class="table-action">父id</th> -->
											<!-- <th class="table-action">代理商等级</th>  -->
											<th class="table-action">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="bean">
											<tr>
											   <%--  <td>${bean._id}</td>  --%>
											    <td>${bean.account}</td> 
												<td>${bean.deptcounty}</td> 
												<%-- <td>${bean.parentId}</td>  --%>
												<%-- <td>${bean.agentLevel}</td>  --%>
												<td class="table-action">
													<div class="btn-group1">
														<a data-toggle="dropdown" class="dropdown-toggle"> <i
															class="fa fa-cog"></i> </a>
														<ul role="menu" class="dropdown-menu pull-right">
															<%-- <li><a href="${contextPath}/user/user!link.action?parentId=${bean._id}"> <i
																	class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;查看下级</a>
															</li> --%>
															
														</ul>
													</div></td>
											</tr>
										</c:forEach>
								</table>
								  <%@include file="/webcom/bracket-page.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
 
<%--弹出层新--%>
<div class="fullscreen bg-hei-8 display-none" id="inszc" style="height: 100%;">
    <div style="padding-top:2%">
        <div class="pl-10 pr-10 maring-a cmp500"
             style="width: 100%;max-width: 500px;min-width: 320px;margin: 0px auto;right: 0px;">
            <div class=" bg-bai border-radius3 overflow-hidden">
                <div class="overflow-hidden line-height40 bg-bai line-bottom">
                    <div class="hang50 pull-left zi-hui-tq">
                        <i class="weight500 size14 pl-10 line-height50">内容管理</i>
                    </div>
                    <a href="javascript:ps_hide('inszc')">
                        <div class="hang40 pull-right zi-hui-tq">
                            <i class="weight500 size14 pl-10 pr-10 fa-1dx fa fa-remove" style="line-height: 50px;"></i>
                        </div>
                    </a>
                </div>
                <form id="inscxForm" action="${ctx}/user/area!save.action?"
                      class="form-horizontal" method="post">
                    <input type="hidden" id="_id" name="_id"/>
                    <input type="hidden" id="parentId" name="parentId" value="${parentId}"/> 
                    <input type="hidden" id="agentId" name="agentId"/> 
                    <%--row--%>

                    <div class="pt-15 pl-15 pr-15 overflow-auto" style="height:490px;">

                        <div class="col-sm-6">
                            <div class="mb-20">
                                <label class="control-label">名称：</label>
                                <input type="text" id="area" name="area"
                                       class="form-control" placeholder="请输入"/>
                            </div>
                        </div> 
                         <div class="col-sm-6">
                            <div class="mb-20">
                                <label class="control-label">序号：</label>
                                <input type="text" id="sort" name="sort"
                                       class="form-control" placeholder="请输入"/>
                            </div>
                        </div> 
                       
                    </div>
                    <div class="div-group-10 line-top" style="padding-left: 40px; padding-right: 40px;">
                        <button class="btn btn-primary width-10 maring-a clear weight500 hang40">提 交
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>	 
	<%@ include file="/webcom/preview.jsp"%>
	<%@include file="/webcom/cut-img.jsp" %> 
</body>
</html>
