<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ include file="/webcom/limit.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>祭奠-悼文-内文</title>
    <script src="${ctx}/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>
<main class="cmp640">
    <font size="2">
        <div class="div-group-10 pt-15 overflow-hidden">
            <div class=" weight500 zi-hei-tq pb-10 line-height20 line-bottom-92">
                <font size="2">
                    ${entity.title}
                </font>
            </div>
            <font size="1">
                <div class="clear pull-right zi-hui-tq pt-5 pr-5">作者：<i class="pr-15">${entity.nickname }</i>创建时间：<i><fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${entity.createdate}'/></i></div>
            </font>
            <div class="line-height25 pt-10 clear zi-hei-tq">
                ${entity.content}
            </div>
        </div>
    </font>
</main>
</body>
</html>