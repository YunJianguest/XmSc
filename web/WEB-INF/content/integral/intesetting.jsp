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
            <h2><i class="fa fa-user"></i>系统设置 <span>基础设置</span></h2>
        </div>
        <form id=custinfoForm action="${ctx}/integral/intesetting!save.action" class="form-horizontal"  method="post" >
       	<div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
            <input id="_id" name="_id" value="${entity._id}" type="hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25">
                        <div class="col-2">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">币种一:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.name}" name="name" id="name">
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">币种二:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.names}" name="names" id="names">
                            </div>
                        </div>
                        <div class="col-2 pl-10" id="app_appid">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">备注:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.remark}"
                                       name="remark" id="remark">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      	</div>
      
        <div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25">
                        
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">任意推荐:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.any}"
                                       name="any" id="any">
                            </div>
                        </div>
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">直推:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.direct}"
                                       name="direct" id="direct">
                            </div>
                        </div>
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">二级推荐:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.between}"
                                       name="between" id="between">
                            </div>
                        </div>
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">异地推荐省级:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.diffProvince}"
                                       name="diffProvince" id="diffProvince">
                            </div>
                        </div>
                        
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">异地推荐市级:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.diffCity}"
                                       name="diffCity" id="diffCity">
                            </div>
                        </div>
                        
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">异地推荐县级:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.diffCounty}"
                                       name="diffCounty" id="diffCounty">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      </div>
      
       <div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25">
                        
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">省级预返值:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.returnProvince}"
                                       name="returnProvince" id="returnProvince">
                            </div>
                        </div>
                        
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">市级预返值:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.returnCity}"
                                       name="returnCity" id="returnCity">
                            </div>
                        </div>
                        <div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">县级预返值:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.returnCounty}"
                                       name="returnCounty" id="returnCounty">
                            </div>
                        </div>
						<div class="col-2 pl-10" id="app_secret">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">部门预返值:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${entity.returnDept}"
                                       name="returnDept" id="returnDept">
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
