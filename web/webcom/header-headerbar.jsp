<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>

<style type="text/css">
	.btn.btn-default.dropdown-toggle:hover{color: #3D3D3D;}
</style>
<div class="headerbar">

      <a class="menutoggle"><i class="fa fa-bars"></i></a>


      <div class="header-right">
        <ul class="headermenu">
          <li>
            <div class="btn-group">
              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="color: #fff;">
                <img src="${ctx }/bracket/images/photos/loggeduser.png" alt="" /><%=custName%>
                <span class="caret" style="color: #fff;"></span>
              </button>
              <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                <li><a href="#"><i class="glyphicon glyphicon-question-sign"></i> 使用帮助</a></li>
                <li><a href="${contextPath}/j_spring_security_logout"><i class="glyphicon glyphicon-log-out"></i> 退出后台</a></li>
              </ul>
            </div>
          </li>
          
        </ul>
      </div><!-- header-right -->

    </div><!-- headerbar -->