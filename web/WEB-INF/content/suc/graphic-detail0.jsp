<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<link href="${ctx }/cmp/css/cmp_js_yangshibiao.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${contextPath}/js/upload/swfobject.js"></script>
<script type="text/javascript" src="${contextPath}/js/upload/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${contextPath}/js/upload/upload.js"></script>
<style>
        .img-100 img {
            width: 100%;
        }
 </style>
    
</head>
<body class="bg-bai">
<main class="cmp640 img-100">
    ${entity.context}
</main>
</body>
</html>