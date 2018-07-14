<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<link href="${ctx }/cmp/css/cmp_js_yangshibiao.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${contextPath}/js/upload/swfobject.js"></script>
<script type="text/javascript" src="${contextPath}/js/upload/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${contextPath}/js/upload/upload.js"></script>
<script src="${contextPath}/UserInterface/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
 
function del(id) {
	if(confirm('确实要删除吗?')) {
		location.href = "${ctx}/suc/integral!delete.action?_id="+ id;
	}		
}
function add(){
	$('#_id').val('');
	$('#title').val('');
	$('#summary').val('');
    $('#uploadresultFour').val('');
	$('#sort').val(0);
	$('#url').val('');
	 

	$('#insadd').modal({
			show : true
		});
}
function upd(id){
   var submitData = {
		id : id
	};
	$.post('${ctx}/suc/integral!upd.action', submitData, function(json) {
		$('#_id').val(json._id);
		$('#title').val(json.title);
		$('#summary').val(json.summary);
		$('#url').val(json.url);
		$('#sort').val(json.sort);
		$('#type').val(json.type);
		$('#uploadresultFour').val(json.picurl);
		

	}, "json")
	 

	$('#insadd').modal({
			show : true
		});
}
 
function updfx(type) {
	var submitData = {
		fxtype : type
	};
	$.post('${ctx}/weixin/sharefx!upd.action', submitData, function(json) {
		$('#fxtype').val(type);
		$('#fxtitle').val(json.fxtitle);
		$('#fxsummary').val(json.fxsummary);
		$('#oldurl').val(json.oldurl);
		$('#fxurl').val(json.fxurl);
		$('#type').val(json.type);
		$('#uploadresultFour').val(json.fximg);
		

	}, "json")
	$('#insfx').modal({
		show : true
	});

}
function savefx() {
	var submitData = {
		fxtype : $('#fxtype').val(),
		fxtitle : $('#fxtitle').val(),
		fxsummary : $('#fxsummary').val(),
		oldurl : $('#oldurl').val(),
		fxurl : $('#fxurl').val(),
		fximg : $('#uploadresultFour').val()
	};
	$.post('${ctx}/weixin/sharefx!ajaxsave.action', submitData, function(json) {
		window.location.href='${ctx}/whd/wxmatrix.action'; 
		

	}, "json")
	$('#inszc').modal({
		show : true
	});

}
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
function share(url) {
	window.open("${contextPath}/weixin/share.action?method="+ encodeURIComponent(url));
}

function exp() {
	var sel_state=$("#sel_state").val();
	var sel_type=$("#sel_type").val();
	var sel_insdate=$("#sel_insdate").val();
	var sel_enddate=$("#sel_enddate").val(); 
	location.href = "${ctx }/suc/integral!integeralfromexp.action?comid=${comid}&sel_state="+sel_state+"&sel_insdate="+sel_insdate+"&sel_enddate="+sel_enddate+"&sel_type="+sel_type;
	
}
</script>
</head>

<body>

<section>

  <%@include file="/webcom/header-bracket.jsp"%>

  <div class="mainpanel">
	<%@include file="/webcom/header-headerbar.jsp"%>
    
	<form  id="custinfoForm" name="custinfoForm" method="post"  action="${contextPath}/suc/integral.action" >
    
    <div class="pageheader">
      <h2><i class="fa fa-user"></i>系统管理 <span>盼盼币管理</span></h2>
      
      <div class="breadcrumb-wrapper1">
          <div class="input-group ">
              <div style="border-radius:3px; height:40px;padding-left:10px;" class="btn-primary">
                  <a href="javascript:exp();"style="color: #ffffff;line-height:25px;">
                      导出&nbsp;<i class="fa fa-mail-reply-all"style="line-height:30px;font-size: 14px;"></i>
                  </a>
              </div>
          </div>
      </div>
      
    </div>
    
   <div class="panelss ">
                <div class="panel-body fu10">
                    <div class="row-pad-5">
                       <div class="form-group col-sm-2">
			            	<select  id="sel_state"  name="state" class="form-control "  data-placeholder="请选择收入\支出 ">
			            	                <option value="2">收入\支出</option>
			            	 				<option value="0">收入</option>
			                    			<option value="1">支出</option>
			                 </select>
			            </div>
			            <div class="form-group col-sm-2">
			            	<select  id="sel_type"  name="type" class="form-control "  data-placeholder="请选择盼盼币类型 ">
			            	                <option value="">请选择盼盼币类型</option>
			            	 				<option value="ps_account">开通账户收益</option>
			                    			<option value="tj_account">推荐收益</option>
			                    			<option value="ps_recovery">回本后待返收益</option>
			                    			<option value="shop_bmzt">利润提成</option>
			                    			<option value="shop_jfdh">下单支出</option>
			                    			<option value="jfcz">盼盼币充值</option>
			                    			<option value="jf_withdraw">盼盼币提现</option>
			                 </select>
			            </div>
			            <div class="form-group col-sm-2">
			                 <input type="text" id="sel_insdate" name="sel_insdate" value="${sel_insdate}" placeholder="开始日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" />
			            </div>
			             <div class="form-group col-sm-2">
			                 <input type="text" id="sel_enddate" name="sel_enddate" value="${sel_enddate}" placeholder="结束日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" />
			            </div>
                        <a href="javascript:page_submit(-1);" class="btn btn-primary">搜&nbsp;&nbsp;索</a>
                        
                    </div>
                    
                </div>
            </div>

    <div class="panel-body">
      <div class="row">
		<div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-primary mb30" >
                    <thead>
                      <tr>
                        <th>id</th>
                      	<th>状态</th>
                      	<th>类型</th>
                      	<th>时间</th>
						<th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${integralList}" var="bean">
                      <tr>
                      	<td>${bean._id}</td>
                      	<c:if test="${bean.value>0}"><td><span style="color: red;">+${bean.value}</span></td>
                      	</c:if>
                      	<c:if test="${bean.value<0}"><td><span style="color:green;">-${bean.value}</span></td>
                      	</c:if>
                      	<td>
                      	<c:choose>
                      	  <c:when test="${bean.type == 'ps_account'}">开通账户收益</c:when>
                      	  <c:when test="${bean.type == 'tj_account'}">推荐收益</c:when>
                      	  <c:when test="${bean.type == 'ps_recovery'}">回本后待返收益</c:when>
                      	  <c:when test="${bean.type == 'shop_bmzt'}">利润提成</c:when>
                      	  <c:when test="${bean.type == 'shop_jfdh'}">下单支出</c:when>
                      	  <c:when test="${bean.type == 'jfcz'}">盼盼币充值</c:when>
                      	  <c:when test="${bean.type == 'jf_withdraw'}">盼盼币提现</c:when>
                      	</c:choose>
                      	</td>
                      	<td><fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.createdate}'/>${bean.fromUserid}</td>
                        <td class="table-action">
                              
                              <div class="btn-group1">
                                  <a data-toggle="dropdown" class="dropdown-toggle">
                                      <i class="fa fa-cog"></i>
                                  </a>
                                  <ul role="menu" class="dropdown-menu pull-right">
                                      <li><a href="javascript:upd('${bean._id}');">
                                      		<i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;充值</a></li>
                                       
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
	<script>
jQuery(".select2").select2({
    width: '100%'
});
$('#sel_state').val('${state}').trigger("change"); 
$('#sel_type').val('${type}').trigger("change"); 
</script>
</body>
</html>
