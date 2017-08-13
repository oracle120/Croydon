<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation"> <jsp:include
					page="/jsp/admin/top_inc.jsp"></jsp:include> </nav>
				<br />
				<c:forEach items="${issues.datas }" var="issue">
					<h2>${issue.title }</h2>
					<p>${issue.description }</p>
					<p>
						<a class="btn" href="${issue.id }">View details »</a>
					</p>
					<hr>
				</c:forEach>
				

				<jsp:include page="/jsp/pager.jsp">
					<jsp:param value="${issues.total}" name="totalRecord" />
					<jsp:param value="issues" name="url" />
				</jsp:include>

			</div>
		</div>
	</div>
</body>
</html>