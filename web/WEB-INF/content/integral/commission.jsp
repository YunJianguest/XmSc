<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<script src="${contextPath}/UserInterface/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/media/js/DT_bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/mvccol/js/jquery.form.js"></script>
<script type="text/javascript">

 function page_submit(num){
	
	if(num==-1){
		$("#fypage").val(0);	
	}else if(num==-2){
		$("#fypage").val($("#fypage").val()-1);	
	}else{
		$("#fypage").val(num);	
	}

	$("#custinfoForm").submit();
}
	//审批
	function appro(id,state){ 
		  var submitData = {
				    id:id,
				    state:state
			};
			$.post('${ctx}/integral/commission!appro.action', submitData,
				function(json) {	
					if(json.state == 0){
						alert('审批成功');
					}else if(json.state == 1){
						alert('操作失败');
					}else if(json.state == 2){
						alert('该提现申请不存在');
					}else if(json.state == 3){
						alert('已审批请勿重复提交');
					}		
					window.location.reload();
			},"json")
	}
  //确认收款
  function resure(id){ 
	  var submitData = {
			  id:id
		};
		$.post('${ctx}/integral/commission!resureMoney.action', submitData,
			function(json) {	
				if(json.state == 0){
					alert('操作成功');
				}else if(json.state == 1){
					alert('操作失败');
				}else if(json.state == 2){
					alert('该提现申请不存在');
				}else if(json.state == 3){
					alert('已确认，请勿重复提交');
				}	
				window.location.reload();
		},"json")
  }

 </script>

 
</head>

<body>

<section>

  <%@include file="/webcom/header-bracket.jsp"%>

  <div class="mainpanel">
	<%@include file="/webcom/header-headerbar.jsp"%>
	<form  id="custinfoForm" name="custinfoForm" method="post"  action="${contextPath}/shop/orderform.action?comid=${comid}" >
    
    <div class="pageheader">
      
      <h2><i class="fa fa-user"></i> 微网站 <span>提现管理</h2>
    </div>
   <div class="panelss ">
   <div class="panel-body fu10">
        <div class="row-pad-5">
        <div class="pt-25 clear">
            <div class="form-group col-sm-2">
            <input type="text" name="no"  value="${no}" placeholder="提现编号"  class="form-control" />
            </div>
            <div class="form-group col-sm-2">
            <input type="text" name="title"  value="${title}" placeholder="提现账号"  class="form-control" />
            </div> 
           
            <div class="form-group col-sm-2">
            	<select  id="sel_state"  name="state" class="form-control "  data-placeholder="请选择">
            	                <option value="">请选择</option>
            	 				<option value="0">未审核</option>
                    			<option value="1">审核通过</option>
                    			<option value="2">审核拒绝</option>
                    			<option value="3">已打款</option>   			
                 </select>
            </div>
            
             <div class="form-group col-sm-2">
            	<select  id="sel_type"  name="type" class="form-control "  data-placeholder="请选择">
            	                <option value="">请选择</option>
                    			<option value="1">银行卡提现</option>
                    			<option value="2">支付宝提现</option>  			
                 </select>
            </div>
            </div>
           
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_insdate" name="sel_insdate" value="${sel_insdate}" placeholder="开始申请日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_enddate" name="sel_enddate" value="${sel_enddate}" placeholder="结束申请日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="pt-25 clear">
            <div class="form-group col-sm-2">
                 <input type="text" id="sel_insdate1" name="sel_insdate1" value="${sel_insdate1}" placeholder="开始审批日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_enddate1" name="sel_enddate1" value="${sel_enddate1}" placeholder="结束审批日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_insdate2" name="sel_insdate2" value="${sel_insdate2}" placeholder="开始打款日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
             <div class="form-group col-sm-2">
                 <input type="text" id="sel_enddate2" name="sel_enddate2" value="${sel_enddate2}" placeholder="结束打款日期检索"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" />
            </div>
           
            <a href="javascript:page_submit(-1);" class="btn btn-primary">搜&nbsp;&nbsp;索</a>
 </div>
        </div>
    </div><!-- panel-body -->
	</div><!-- panel -->

    <div class="panel-body">
      <div class="row">
		<div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-striped table-primary mb30" >
                    <thead>
                      <tr> 
                       	<th class="th5">账号/昵称</th>
                        <th class="th5">编号</th>
						<th class="th8">提现类型</th> 
				     	<th class="th2">提现账户</th>
				        <th class="th8">所属银行</th>
				        <th class="th6">提现金额</th>
                        <th class="th6">提现手续费</th>
						<th class="th4">备注</th>
						<th class="th4">当前状态</th>
						<th class="th4">申请时间</th>
						<th class="th4">审批时间</th>
						<th class="th4">打款时间</th>
						<th class="th5">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${list}" var="bean">
                      <tr>  
                       <td>${bean.acc}/${bean.nickname}</td>
                      	<td>${bean._id}</td>
						<td><c:if test="${bean.type==1}">银行卡</c:if>
						    <c:if test="${bean.type==2}">支付宝</c:if></td>
						<td>${bean.account}</td>
						<td>${bean.yname}</td>
						<td>
						<fmt:formatNumber value='${bean.price}' pattern="0.00"/></td>
						<td>
						<fmt:formatNumber value='${bean.cost}' pattern="0.00"/></td> 
						<td>${bean.remark}</td>	
						<td><c:if test="${bean.state == 0}">待审批</c:if>
                        	<c:if test="${bean.state == 1}">审批通过，等待打款</c:if>
                        	<c:if test="${bean.state==2}">审批拒绝</c:if>
							<c:if test="${bean.state==3}"><span style="color:#F00">已打款</span></c:if>
						</td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bean.createdate}" /></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bean.updatedate}" /></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bean.confirmdate}" /></td>
                        <td class="table-action">
                              <div class="btn-group1">
                                  <a data-toggle="dropdown" class="dropdown-toggle">
                                      <i class="fa fa-cog"></i>
                                  </a>
                                  <ul role="menu" class="dropdown-menu pull-right">
                                      <c:if test="${bean.state==0}"> 
                                      <li><a href="javascript:appro('${bean._id}',1);">
                                      		<i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;同意</a></li>
                                 	  <li><a href="javascript:appro('${bean._id}',2);">
                                 	  <i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;拒绝</a></li>
                                      </c:if> 
                                      <c:if test="${bean.state==1}">
                                      <li><a href="javascript:resure('${bean._id}');">
                                      		<i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;确认打款</a></li>
                                     </c:if>
                         
                                  </ul>
                              </div>
                          </td>
                      </tr>
                      </c:forEach>

                </table>
                <%@include file="/webcom/bracket-page.jsp"%>
                
            </div>
        </div>
      </div>
		
    </div><!-- contentpanel -->
	</form>
  </div><!-- mainpanel -->
</section>
<script type="text/javascript"> 
if('${sel_state}'!=0){ 
$('#sel_state').val('${sel_state}'); 
}
$('#sel_type').val('${sel_type}'); 
</script>

</body>
</html>
