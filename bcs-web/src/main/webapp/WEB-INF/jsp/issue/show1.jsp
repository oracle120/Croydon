<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">问题展示：用户id[${issue.id }]</td></tr></thead>
		<tr>
			<td class="rightTd" width="200px">问题标题:</td><td class="leftTd">${issue.title }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">问题描述:</td><td class="leftTd">${issue.description }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">解决方案:</td><td>${issue.solution}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">提交人:</td><td>${issue.userid }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">提交时间:</td>
			<td>
				<fmt:formatDate value="${issue.date }" pattern="yyyy-MM-dd HH:mm:ss"/>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><a href="update/${issue.id }" class="list_op">修改问题</a></td>
		</tr>
	</table>
</div>
</body>
</html>