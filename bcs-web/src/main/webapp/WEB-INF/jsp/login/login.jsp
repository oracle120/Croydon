<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link
	href="<c:url value='https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>
	<div class="container">
		<div class="row clearfix">

			<!-- 页头 -->
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<jsp:include page="/jsp/admin/top_inc.jsp"></jsp:include>
				</nav>
				<br />
			</div>
			
			<!-- 没任何作用，只是用来吧页头和登录框隔开 -->
			<div class="col-md-12 column"><hr></div>

			<!-- 左，为空，保证登录框在中间 -->
			<div class="col-md-4 column"></div>

			<!-- 登录框，位于中间部分 -->
			<div class="col-md-4 column">
				<div id="mainWrapper">
					<div class="login-container">
						<div class="login-card">
							<div class="login-form">
								<c:url var="loginUrl" value="/login" />
								<form action="${loginUrl}" method="post" class="form-horizontal">
									<c:if test="${param.error != null}">
										<div class="alert alert-danger">
											<p>Invalid username and password.</p>
										</div>
									</c:if>
									<c:if test="${param.logout != null}">
										<div class="alert alert-success">
											<p>You have been logged out successfully.</p>
										</div>
									</c:if>
									<div class="input-group input-sm">
										<label class="input-group-addon" for="username"><i
											class="fa fa-user"></i></label> <input type="text"
											class="form-control" id="username" name="username"
											placeholder="Enter Username" required>
									</div>
									<div class="input-group input-sm">
										<label class="input-group-addon" for="password"><i
											class="fa fa-lock"></i></label> <input type="password"
											class="form-control" id="password" name="password"
											placeholder="Enter Password" required>
									</div>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />

									<div class="form-actions">
										<input type="submit"
											class="btn btn-block btn-primary btn-default" value="Log in">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 右，为空，保证登录框在中间 -->
			<div class="col-md-4 column"></div>
		</div>
	</div>

</body>
</html>