<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<%@include file="/webcom/chart.jsp"%>
<script type="text/javascript">
		var state='${state}'; 
		function pot(){ 
			if(state==''){
				state=0;
			}
			  var submitData = {
					  state:state
		        };
		        $.post('${contextPath}/suc/integral!linkChart.action', submitData,
		                function (json) {
		      	        Plot_init(json.Datalist1,json.Datalist2,1000000);
		        }, "json")      
		} 
</script>
</head>

<body>

<section>

  <%@include file="/webcom/header-bracket.jsp"%>

  <div class="mainpanel">
	<%@include file="/webcom/header-headerbar.jsp"%>
    
	
    
    <div class="pageheader">
      <h2><i class="fa fa-user"></i>统计图表<span>收入支出趋势对比图</span></h2>
    </div>
       <div class="panel-body">
        <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="row">
                <div class="col-sm-8">
                  <h5 class="subtitle mb5" style="overflow: hidden;">
                   </h5>  
                  <p>数量统计（数量/时间）</p>
                  <div id="basicflot" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
                </div><!-- col-sm-8 -->
              </div><!-- row -->
            </div><!-- panel-body -->
          </div><!-- panel -->
        </div><!-- col-sm-9 -->
 

      </div><!-- row -->
            </div> 
  </div><!-- mainpanel -->
</section>
 

	<script>
jQuery(".select2").select2({
    width: '100%'
});
$('#state').val('${state}').trigger("change");
pot();
</script>
</body>
</html>
