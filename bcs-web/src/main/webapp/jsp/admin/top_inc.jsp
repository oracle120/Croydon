<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" id="contextPath"
	value="<%=request.getContextPath()%>" />


<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target="#bs-example-navbar-collapse-1">
		<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
			class="icon-bar"></span><span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="#">Brand</a>
</div>

<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	<ul class="nav navbar-nav">
		<li class="active"><a
			href="<%=request.getContextPath()%>/admin/issue/issues">Home</a></li>
		<li><a href="#">Link</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
			<ul class="dropdown-menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
				<li class="divider"></li>
				<li><a href="#">One more separated link</a></li>
			</ul></li>
	</ul>
	<form class="navbar-form navbar-left" role="search">
		<div class="form-group">
			<input type="text" class="form-control" />
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<ul class="nav navbar-nav navbar-right">
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<li><a href="<%=request.getContextPath()%>/admin/login/login">Login</a></li>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<li><a href="#">${pageContext.request.userPrincipal.name }</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/login/logout">Logout</a></li>
		</c:if>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
			<ul class="dropdown-menu">
				<li><a href="<%=request.getContextPath()%>/admin/issue/issues">Topics</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/issue/add">Add</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="divider"></li>
					<li><a href="<%=request.getContextPath()%>/admin/user/users">Users</a></li>
					<li class="divider"></li>
					<li><a href="<%=request.getContextPath()%>/admin/role/roles">Roles</a></li>
				</sec:authorize>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul></li>
	</ul>
</div>

