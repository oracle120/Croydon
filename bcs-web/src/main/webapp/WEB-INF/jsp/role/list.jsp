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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/admin/main.js"></script>
</head>
<body>


	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation"> <jsp:include
					page="/jsp/admin/top_inc.jsp"></jsp:include> </nav>

				<br>
				<h4 class="pull-left">用户管理</h4>
				<a href="add" class="btn btn-sm btn-info pull-right">Add</a> <br>
				<hr>
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>角色标识</th>
							<th>角色名称</th>
							<th>用户类型</th>
							<th>角色操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roles}" var="role">
							<tr>
								<td>${role.id }&nbsp;</td>
								<td><a href="${role.id }" class="list_link">${role.name }</a></td>
								<td>${role.roleType }&nbsp;</td>
								<td>
									<a href="delete/${role.id }" class="btn btn-sm btn-info" onclick="confirmdelete()">删除</a>
									<a href="update/${role.id }" class="btn btn-sm btn-info">更新</a> 
									<a href="clearUsers/${role.id }" class="btn btn-sm btn-info" onclick="confirmdelete()">清空用户</a>
									&nbsp;
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- role角色不多，不用分页，后面如果role过多了需要分页时，先添加一个findRole方法，打开下述注释即可 -->
				<%-- <jsp:include page="/jsp/pager.jsp">
					<jsp:param value="${roles.total}" name="totalRecord" />
					<jsp:param value="users" name="url" />
				</jsp:include> --%>
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