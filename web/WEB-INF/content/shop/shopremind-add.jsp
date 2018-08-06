<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/webcom/meta.jsp" %>
    <%@include file="/webcom/bracket.jsp" %>
    <%@include file="/webcom/jquery.validate_js.jsp" %>
    <script src="${contextPath}/bracket/js/jquery.tagsinput.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/bracket/css/jquery.tagsinput.css"/>
    <script src="${contextPath}/UserInterface/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/app/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(document).ready(function () {
            /* var validator = $("#custinfoForm").validate({
                rules: { 
                },
                messages: {},
                highlight: function (element) {
                    jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
                },
                success: function (element) {
                    jQuery(element).closest('.form-group').removeClass('has-error');
                }
            }); */
        });
       
    </script>
</head>
<body>
<section>
    <%@include file="/webcom/header-bracket.jsp" %>
    <div class="mainpanel">
        <%@include file="/webcom/header-headerbar.jsp" %> 
        <div class="pageheader">
            <h2><i class="fa fa-user"></i>系统设置 <span>提醒设置</span></h2>
        </div>
        <form id=custinfoForm action="${ctx}/shop/shopremind!save.action" class="form-horizontal"  method="post" >
       	<div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden"> 
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25 pr-10">
                        <div class="col-6 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">确认收款提示（请输入需要提醒的电话，多个电话用英文逗号隔开）:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.zftxTel}" name="zftxTel" id="zftxTel" placeholder="请输入">
                            </div>
                        </div>
                         <div class="col-6 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">提醒发货提示（请输入需要提醒的电话，多个电话用英文逗号隔开）:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.fhtxTel}" name="fhtxTel" id="fhtxTel" placeholder="请输入">
                            </div>
                        </div>
                        <div class="col-6 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">确认收款提示提示语(如您有一条新订单，请确认收款)</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.zftxContent}" name="zftxContent" id="zftxContent" placeholder="请输入">
                            </div>
                        </div>
                         <div class="col-6 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">提醒发货提示语(如您有一条新订单，请尽快发货)</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.fhtxContent}" name="fhtxContent" id="fhtxContent" placeholder="请输入">
                            </div>
                        </div>
                      
                    </div>
                </div>
            </div>
      	</div>
       
       
      <div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    
                    <div class="pt-10 clear">
                        <a href="javascript:checksubmit()">
                            <div class="pt-20 pb-20 clear">
                                <div class="border-radius5 div-group-5 btn-lan zi-bai">
                                    <div class="hang40 txt-c weight500 line-height40">
                                        <font size="3">
                                          	  提交
                                        </font>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
      </div>
      </form>
  </div> 
</section> 
<script type="text/javascript"> 
  
 function checksubmit() {  
  $('#custinfoForm').submit();
 }  
</script>
</body>
</html>
