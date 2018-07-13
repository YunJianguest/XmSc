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
        main span{
        	font-size: 14px;
        } 
    </style>
</head>
<body class="bg-bai">
	<header style="width: 100%;height: 40px;line-height: 40px;text-align: center;padding: 0 10px;background: #fff;">
		<a href="javascript:history.go(-1);" style="font-size: 18px;float: left;color: #000;width: 30px;display: inline-block;"><</a>
			关于我们
	</header>
	<main class="cmp640 img-100" style='padding-top: 40px;font-size: 14px;'>
	    ${entity.content}
	</main>
</body>
</html>
