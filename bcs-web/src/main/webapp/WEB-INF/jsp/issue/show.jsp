<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/marked.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/prettify.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/raphael.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/underscore.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/sequence-diagram.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/flowchart.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/lib/jquery.flowchart.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/editor.md-master/editormd.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation"> <jsp:include
					page="/jsp/admin/top_inc.jsp"></jsp:include> </nav>
				<div class="page-header">
					<h1>
						${issue.title } <small>${issue.userid } <small>[<a
								href="update/${issue.id }" class="list_op">Edit</a>]
						</small></small>
					</h1>
				</div>
				<div id="doc-content"  name="editormd-markdown-doc">
					<textarea style="display: none;">${issue.solution }</textarea>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var testEditor;
		$(function() {
			testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
				 htmlDecode      : "style,script,iframe",  // you can filter tags decode
		            emoji           : true,
		            taskList        : true,
		            tex             : true,  // 默认不解析
		            flowChart       : true,  // 默认不解析
		            sequenceDiagram : true,  // 默认不解析
			});
		});
	</script>

</body>
</html>