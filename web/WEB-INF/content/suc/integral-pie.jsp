<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<%-- <link href="${ctx }/cmp/css/cmp_js_yangshibiao.css" rel="stylesheet" type="text/css"/> --%>
<%@include file="/webcom/chart.jsp"%>
<script type="text/javascript">
 
	var state='${state}'; 
	function chart(){ 
	  if(state==''){
		  state=0;
	  }
	  var submitData = {
	      state:state
      };
        $.post('${contextPath}/suc/integral!pieChart.action', submitData,
                function (json) {
        	     Piechat("chart",json.Datalist1);
        }, "json")      
	} 
</script>
</head>

<body>

<section>

  <%@include file="/webcom/header-bracket.jsp"%>

  <div class="mainpanel">
	<%@include file="/webcom/header-headerbar.jsp"%>
    
	<form  id="custinfoForm" name="custinfoForm" method="post"  action="${contextPath}/suc/integral!day.action" >
    
    <div class="pageheader">
      <h2><i class="fa fa-user"></i>系统管理 <span>积分管理</span></h2>
    </div>
   <div class="panel-body">
        <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="row">
                <div class="col-sm-8">
                <input type="radio" id="search1" name="specialType" onclick="window.location.href='${contextPath}/suc/integral!pie.action?state=0'" value="0">收入
                <input type="radio" id="search2" name="specialType" onclick="window.location.href='${contextPath}/suc/integral!pie.action?state=1'" value="1">支出
                  <h5 class="subtitle mb5" style="overflow: hidden;">
                   </h5>  
                  <p>资金统计（百分比）</p>
                  <div id="chart" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
                </div><!-- col-sm-8 -->
              </div><!-- row -->
            </div><!-- panel-body -->
          </div><!-- panel -->
        </div><!-- col-sm-9 -->
 

      </div><!-- row -->
            </div> 
	</form>
  </div><!-- mainpanel -->
</section>
	<script>
jQuery(".select2").select2({
    width: '100%'
});
chart();  
$('#sel_type').val('${type}').trigger("change"); 
</script>
</body>
</html>
