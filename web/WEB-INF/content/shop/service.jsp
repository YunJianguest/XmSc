<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/webcom/meta.jsp" %>
    <%@include file="/webcom/bracket.jsp" %>
    <%@include file="/webcom/jquery.validate_js.jsp" %>
    <script src="${contextPath}/UserInterface/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript" src="${contextPath}/mvccol/color/jscolor.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var validator = $("#inscxForm").validate({
                rules: {
                	account: {
                        required: true
                    },
                    cid: {
                        required: true
                    },
                    mb: {
                        required: true
                    }
                },
                messages: {},
                highlight: function (element) {
                    jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
                },
                success: function (element) {
                    jQuery(element).closest('.form-group').removeClass('has-error');
                }
            });
        });
       
        //审批
        function apply(id,state) {
        	var	submitData = {
                    "id": id,
                    "state": state,
                };
        	$.post('${ctx}/shop/service!approval.action',submitData,
                    function (json) {
        			if(json.state==0){
        				location.reload();
        			}else{
        				alert('操作失败');
        			}
              }, "json");
        }
        
        
        function page_submit(num) {
            if (num == -1) {
                $("#fypage").val(0);
            } else if (num == -2) {
                $("#fypage").val($("#fypage").val() - 1);
            } else {
                $("#fypage").val(num);
            }
            $("#custinfoForm").submit();
        }
       
    </script>
</head>
<body>
<section>
    <%@include file="/webcom/header-bracket.jsp" %>
    <div class="mainpanel">
        <%@include file="/webcom/header-headerbar.jsp" %>
        <form id="custinfoForm" name="custinfoForm" method="post" action="${contextPath}/shop/service.action?comid=${comid}">
            <div class="pageheader">
                <h2><i class="fa fa-user"></i> 退换货管理 <span>退换货列表</span></h2>
                  <!-- <div class="breadcrumb-wrapper1">
                    <div class="input-group ">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                         		 菜单 <span class="caret"style="color: white;"></span>
                        </button>
                        <ul class="dropdown-menu pull-right" role="menu">
                            <li> <a href="javascript:add();">
                                 <i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp; 添加账户</a>
                            </li>
                        </ul>
                    </div>
                </div> -->
             
            </div>
             <div class="panelss ">
   <div class="panel-body fu10">
        <div class="row-pad-5">
            
           <div class="form-group col-sm-2">
            	<select  id="sel_type"  name="type" class="form-control "  data-placeholder="请选择售后类型">
            	                <option value="0">请选择售后类型</option>
                    			<option value="1">退货</option>
                    			<option value="2">换货</option>
                 </select>
            </div>
             <div class="form-group col-sm-2">
            	<select  id="sel_state"  name="state" class="form-control "  data-placeholder="请选择售后类型">
            	                <option value="4">请选择售后状态</option>
            	                <option value="0">待审核</option>
                    			<option value="1">同意</option>
                    			<option value="2">拒绝</option>
                    			<option value="3">取消</option>
                 </select>
            </div>
           
            <div class="form-group col-sm-2">
            <input type="text" name="no"  value="${no}" placeholder="订单编号"  class="form-control" />
            </div> 
            <div class="form-group col-sm-2">
                 <input type="text" id="sel_insdate" name="sel_insdate" value="${sel_insdate}" placeholder="开始日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_enddate" name="sel_enddate" value="${sel_enddate}" placeholder="结束日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
            <a href="javascript:page_submit(-1);" class="btn btn-primary">搜&nbsp;&nbsp;索</a>

        </div>
    </div><!-- panel-body -->
	</div><!-- panel -->
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-action table-primary mb30 table-bordered">
                                <thead>
                                <tr>
                                    <th class="table-action">退货人</th>
                                    <th class="table-action">售后编号</th>
                                    <th class="table-action">订单编号</th>
                                    <th class="table-action">商品名称</th>
                                    <th class="table-action">商品数量</th>
                                    <th class="table-action">售后类型</th>
                                    <th class="table-action">售后状态</th>
                                    <th class="table-action">售后原因</th>
                                    <th class="table-action">金额</th>
                                    <th class="table-action">添加时间</th>
                                    <th class="table-action">备注</th>
                                    <th class="table-action">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="bean">
                                    <tr>
                                        <td>${bean.nickname}</td>
                                        <td>${bean._id}</td>
                                        <td>${bean.oid}</td>
                                        <td>${bean.product.ptitle}</td>
                                        <td>${bean.num}</td>
                                        
                                        <td>
                                        	<c:if test="${bean.type==1}">退货</c:if>
                                        	<c:if test="${bean.type==2}">换货</c:if>
                                        </td>
                                        <td><c:if test="${bean.state==0}">申请</c:if>
                                        	<c:if test="${bean.state==1}">同意</c:if>
                                        	<c:if test="${bean.state==2}">拒绝</c:if>
                                        	<c:if test="${bean.state==3}">取消</c:if>
                                        </td>
                                        <td>${bean.resource}</td>
                                        <td>${bean.price}</td>
                                        <td><fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.createdate}'/></td>
                                        <td>${bean.remark}</td>
                                        <td class="table-action">
                                            <div class="btn-group1">
                                            <c:if test="${bean.state==0 }">
                                                <a data-toggle="dropdown" class="dropdown-toggle">
                                                    <i class="fa fa-cog"></i>
                                                </a>
                                                <ul role="menu" class="dropdown-menu pull-right">
                                                    <li><a href="javascript:apply('${bean._id}',1);">
                                                        <i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;同意</a>
                                                    </li>
                                                    
                                                    <li><a href="javascript:apply('${bean._id}',2);"><i
                                                            class="fa fa-trash-o "></i>&nbsp;&nbsp;&nbsp;&nbsp;拒绝</a>
                                                    </li>
                                                </ul>
                                             </c:if>
                                            </div>
                                        </td>
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
<script type="text/javascript">
	   $('#sel_state').val('${state}').trigger("change"); 
	   $('#sel_type').val('${type}').trigger("change"); 

</script>
</body>
</html>
