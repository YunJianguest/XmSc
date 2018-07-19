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
            <h2><i class="fa fa-user"></i>系统设置 <span>平台数据</span></h2>
        </div>
       	<div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25 pr-10">
                         <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.name}总发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.num}" name="num" id="num" placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                         <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.name}现发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.nownum}" name="num" id="num" placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                       <c:if test="${db.nownum != null }">
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.name}未发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.num-db.nownum}" name="nums" id="nums" placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${db.nownum == null }">
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.name}未发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.num}" name="nums" id="nums" placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                        </c:if>
                       
                    </div>
                </div>
            </div>
      	</div>
      	<div class="div-group-10 overflow-hidden">
            <div class="overflow-hidden">
                <div class=" div-group-10 pb-25 bg-bai border-radius5 overflow-hidden">
                    <div class="clear pt-25 pr-10">
                      
                       
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.names}总发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.nums}" name="nums" id="nums" placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.names}现发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.nownums}" name="nums"  placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                         <c:if test="${db.nownums != null }">
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.names}未发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.nums-db.nownums}" name="nums"  placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${db.nownums == null }">
                        <div class="col-2 pl-10">
                            <div class="size14 line-bottom weight500 pt-10 pb-10" style="padding-left: 2px;">${db.name}未发行量:</div>
                            <div class="line-bottom line-right line-left1 hang40">
                                <input class="width-10 size14 zi-hui hang30 pt-10 pl-10 pr-10 weight100" type="text"
                                       value="${db.nums}" name="nums"  placeholder="请输入" readonly="readonly">
                            </div>
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
      	</div>
       

  </div> 
</section> 

</body>
</html>
