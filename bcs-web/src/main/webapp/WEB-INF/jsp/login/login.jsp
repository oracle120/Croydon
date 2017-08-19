<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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

<title>Login</title>
</head>
<body>

	<div class="container">
		<div class="row clearfix">

			<!-- 页头 -->
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation"> <jsp:include
					page="/jsp/admin/top_inc.jsp"></jsp:include> </nav>
				<br />
			</div>

			<!-- 左，为空，保证登录框在中间 -->
			<div class="col-md-4 column"></div>

			<!-- 登录框，位于中间部分 -->
			<div class="col-md-4 column">
				<sf:form method="post" modelAttribute="userDto" id="addForm"
					role="form">
					<div class="form-group">
						<label for="exampleInputEmail1">Login Name</label><input
							type="text" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label><input
							type="password" class="form-control" id="exampleInputPassword1" />
					</div>
					<br>
					<button type="submit" class="btn btn-default btn-block">Submit</button>
				</sf:form>
			</div>

			<!-- 右，为空，保证登录框在中间 -->
			<div class="col-md-4 column"></div>
		</div>
	</div>
</body>
</html>