<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webcom/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>${title}</title>
    <script src="${ctx}/app/js/jquery-1.8.3.js"></script>
    <link href="${ctx}/app/css/YLui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/app/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/app/css/style_0.css" rel="stylesheet">
    <script src="${ctx}/mvccol/js/fomatdate.js"></script>
    <style>
        .img-100 img {
            width: 100%;
        }
        main span,main p span ,main p span span{
        	font-size: 14px;
        } 
    </style>
</head>
<body class="bg-bai">
	<header style="background: #fefefe;width: 100%;height: 44px;position: fixed;top: 0;left: 0;padding: 0 10px;line-height: 44px;text-align: center;border-bottom: 1px solid #ddd;">
			<a  href="javascript:history.go(-1);" style="display: inline-block;float: left;width: 30px;height: 30px;background: url('${ctx}/xmMobile/img/goback.png') no-repeat;background-size: 100% 100%;margin-top: 10px;"></a>
			<h1 class="mui-title">关于我们</h1>
	</header> 
	<main class="cmp640 img-100" style='padding: 0 10px;padding-top: 44px;font-size: 14px;'>
	    ${entity.content}
	</main>
</body>
</html>
