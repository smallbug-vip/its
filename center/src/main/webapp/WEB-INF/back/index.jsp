<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/back/public/header.jspf" %>
</head>
<body class="easyui-layout">
<input type="hidden" id="application_name" value="${pageContext.request.contextPath }"/>
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">智能交通后台管理系统</div>
	<div data-options="region:'west',split:true,title:'菜单',href:'${pageContext.request.contextPath }/admin/menu.do'" style="width:200px;"></div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">版本声明</div>
	<div data-options="region:'center',title:'操作区',href:'${pageContext.request.contextPath }/admin/main.do'"></div>
</body>
</html>