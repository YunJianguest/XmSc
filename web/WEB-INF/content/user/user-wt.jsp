<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/webcom/meta.jsp"%>
<%@include file="/webcom/bracket.jsp"%>
<%@include file="/webcom/jquery.validate_js.jsp"%>
<script src="${contextPath}/UserInterface/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${contextPath}/zTree/js/jquery.ztree.core-3.5.min.js"></script>  
<script type="text/javascript" src="${contextPath}/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>  
<script type="text/javascript" src="${contextPath}/zTree/js/jquery.ztree.exedit-3.5.min.js"></script>  
<link rel="stylesheet" href="${contextPath}/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
<script type="text/javascript">
 
</script>
<script type="text/javascript"> 
	        var setting = {
	            async: {
	                enable: true,//异步处理
	 
	                //contentType: "application/json",//提交参数方式，这里 JSON 格式，默认form格式
	                 
	                url: '${ctx}/user/user!getwt.action',//异步获取json格式数据的路径
	 
	                autoParam: ["id","name"]//异步加载时需要提交的参数，多个用逗号分隔
	                             
	            },
	            callback: {//回调函数，在这里可做一些回调处理
	                //beforeAsync: zTreeBeforeAsync
	            },
	            check: {//设置 zTree 的节点上是否显示 checkbox / radio ,默认为false
	                enable: true
	            },
	            data: {
	                simpleData: {
	                    /**
 	                    如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId
	                    并且让数据满足父子关系。*/                 
	                    enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
	                    idKey: "id",
	                    pIdKey: "pId",
	                    rootPId: 0                         
	                }
	            }
	        };
	 
	        var zNodes =[];//树节点，json格式，异步加载可设置为null或[]
	        var zTreeObj;//树对象
	      
	        $(document).ready(function(){
	             
	            zTreeObj = $.fn.zTree.init($("#permission_tree"), setting, zNodes);//实例化后直接返回树对象
	  
	             
	        });
	  
	    </script>
</head>

<body>

	<section>

		<%@include file="/webcom/header-bracket.jsp"%>

		<div class="mainpanel">
			<%@include file="/webcom/header-headerbar.jsp"%>
 
		</div>
		<!-- mainpanel -->
	</section> 
	<ul id="permission_tree" class="ztree" style="border: 0px; background-color: #f7f7f7;"></ul> 
</body>
</html>
