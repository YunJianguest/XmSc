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
    <script type="text/javascript" src="${ctx}/media/js/DT_bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/wn/js/jquery.form.js"></script>
    <script type="text/javascript"> 
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
        function share(url) {
            window.open("${contextPath}/weixin/share.action?method=" + encodeURIComponent(url));
        }
        var tgid = '';
        var tgcomid = '';
        function add(id, comid) {
            tgid = id;
            tgcomid = comid;
            $('#inszc').modal({
                show: true
            });
        }
      
    </script>
</head>
<body>
<section>
    <%@include file="/webcom/header-bracket.jsp" %>
    <div class="mainpanel">
        <%@include file="/webcom/header-headerbar.jsp" %>
        <form id="custinfoForm" name="custinfoForm" method="post"
              action="${contextPath}/suc/activity!yd.action?id=${id}">
            <div class="pageheader">
                <h2><i class="fa fa-user"></i>微农场<span>农产品预订管理</span></h2>
                
            </div>
           
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-primary table-action mb30">
                                <thead>
                                <tr>
                                    <th class="th5 table-action">LOGO</th>
                                    <th class="th8 table-action">标题</th>
                                    <th class="th5 table-action">总长值</th> 
                                    <th class="th5 table-action">当前成长值</th>
                                    <th class="th5 table-action">头像</th>
                                    <th class="th5 table-action">昵称</th> 
                                    <th class="th5 table-action">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="bean">
                                    <tr class="table-action">

                                        <td><img src="${filehttp}${bean.obj.picurl}" style="height:25px;"></td>
                                        <td title="${bean.obj.title}">
                                            <div class="sl">${bean.obj.title}</div>
                                        </td>
                                        <td>${bean.obj.growth}</td>
                                        <td>${bean.obj.nowgrowth}</td>
                                        <td><img src="${filehttp}${bean.headimgurl}" style="height:25px;"></td>
                                        <td>${bean.nickname}</td>
                                         
                                        <td class="table-action">

                                            <div class="btn-group1">
                                                <a data-toggle="dropdown" class="dropdown-toggle">
                                                    <i class="fa fa-cog"></i>
                                                </a>
                                                <ul role="menu" class="dropdown-menu pull-right">

                                                    <li>
                                                        <a href="${contextPath}/suc/farm!detail.action?id=${bean._id}">
                                                            <i class="fa fa-pencil "></i>&nbsp;&nbsp;&nbsp;&nbsp;活动详情</a>
                                                    </li>
 
                                                </ul>
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
 
</body>
</html>
