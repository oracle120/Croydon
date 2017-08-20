<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 包含头部信息用于适应不同设备 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/editor.md-master/css/editormd.css" />
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/editormd.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/admin/main.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/core/jquery.cms.validate.js"></script>

</head>
<body>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation"> <jsp:include
					page="/jsp/admin/top_inc.jsp"></jsp:include> </nav>

				<br >
				<h4 class="pull-left">用户管理</h4>
				<a href="add" class="btn btn-sm btn-info pull-right">Add</a>
				<br >
				<hr>
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>用户标识</th>
							<th>用户名称</th>
							<th>用户昵称</th>
							<th>用户状态</th>
							<th>用户邮箱</th>
							<th>用户操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="user">
							<tr>
								<td>${user.id }&nbsp;</td>
								<td><a href="${user.id }" class="list_link">${user.username }</a></td>
								<td>${user.nickname }&nbsp;</td>
								<td>
									<c:if test="${user.status eq 0 }">
										<span class="emp">停用</span>
										<%-- <a href="updateStatus/${user.id }" class="btn btn-sm btn-primary">启用</a> --%>
									</c:if> 
									<c:if test="${user.status eq 1 }">
										<span>启用</span>
										<%-- <a href="updateStatus/${user.id }" class="btn btn-sm btn-primary">停用</a> --%>
									</c:if> &nbsp;
								</td>
								<td><a href="mailto:${user.email }" class="list_link">${user.email }</a>
									&nbsp;</td>
								<td>
									<c:if test="${user.status eq 0 }">
										<a href="updateStatus/${user.id }" class="btn btn-sm btn-info">启用</a>
									</c:if> 
									<c:if test="${user.status eq 1 }">
										<a href="updateStatus/${user.id }" class="btn btn-sm btn-info">停用</a>
									</c:if>
									<a href="update/${user.id }" class="btn btn-sm btn-info">更新</a> 
									<a href="delete/${user.id }" title="${user.id }" class="btn btn-sm btn-info" onclick="confirmdelete()">删除</a> 
									<%-- <a href="<%=request.getContextPath() %>/admin/channel/userchannels/${user.id }"	class="btn btn-sm btn-primary">管理栏目</a> --%> &nbsp;
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<jsp:include page="/jsp/pager.jsp">
					<jsp:param value="${datas.total}" name="totalRecord" />
					<jsp:param value="users" name="url" />
				</jsp:include>
			</div>
		</div>
	</div>

	<script type="text/JavaScript">
		function confirmdelete() {
			if (!confirm("该操作不可逆，确认执行该操作吗？")) {
				window.event.returnValue = false;
			}
		}
	</script>

</body>
</html>