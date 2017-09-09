<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/editor.md-master/css/editormd.css" />
<script src="<%=request.getContextPath() %>/resources/editor.md-master/editormd.min.js"></script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>



<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<jsp:include page="/jsp/admin/top_inc.jsp"></jsp:include> 
			</nav>
			
			<br />
			<div class="page-header">
			<h3>撰写新文章</h3>
			</div>
			
			<sf:form method="post" modelAttribute="issueDto" id="addForm" role="form">
				<div class="form-group">
					<label>Title</label><sf:input type="text" class="form-control" path="title" />
				</div>
				<br>
				
				<div class="form-group">
					<div class="editormd" id="test-editormd">
							<sf:textarea class="editormd-markdown-textarea"
								name="test-editormd-markdown-doc"
								id="test-editormd-markdown-doc" path="solution"></sf:textarea>
							<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
							<textarea class="editormd-html-textarea" name="text" id="text" ></textarea>
					</div>
				</div>
				<br>
				
				<div class="form-group">
					<label>Roundup</label><sf:input type="text" class="form-control" path="description" />
				</div>
				<br>
				
				<div class="form-group">
					<label>Author</label><sf:input type="text" class="form-control" path="userid" value="${pageContext.request.userPrincipal.name }" readonly="true" />
				</div>
				<br>
				
				<%-- <div class="form-group">
					 <label for="exampleInputEmail1">Email address</label><sf:input type="email" class="form-control" id="exampleInputEmail1" path="userid" />
				</div>
				<br>
				
				<div class="form-group">
					 <label for="exampleInputPassword1">Password</label><sf:input type="password" class="form-control" id="exampleInputPassword1" path="" />
				</div>
				<div class="form-group">
					 <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile" />
					<p class="help-block">
						Example block-level help text here.
					</p>
				</div> 
				<div class="checkbox">
					 <label><input type="checkbox" />Check me out</label>
				</div> --%>
				
				<sf:button type="submit" class="btn btn-default">Submit</sf:button>
			</sf:form>
			
		</div>
	</div>
</div>

<script type="text/javascript">
     $('#add_user').click(function() {
       //获取第二个textarea的值，即生成的HTML代码   实际开发中此值存入后台数据库
       var editorhtml=$("#test-editormd").val();
       $("#p1").text(editorhtml+" editorhtml");
       //获取第一个textarea的值，即md值  实际开发中此值存入后台数据库
        var editormd2=$("#text").val();
       $("#solution").text(editormd2+" editormd");
       $("#addForm").submit();
     });

     //刚进入文档的时候，可以给第一个textarea设置初始md。  实际开发中从后台获取
     $(document).ready(function() {
      var md="**t**";
       $("#editormd").text(md);
     });
     </script>

	<script type="text/javascript">
			var testEditor;

            $(function() {
                testEditor = editormd("test-editormd", {//注意1：这里的就是上面的DIV的id属性值
                    width   : "90%",
                    height  : 640,
                    syncScrolling : "single",
                    path    : "<%=request.getContextPath() %>/resources/editor.md-master/lib/",
                    toolbarIcons : function() {
                       		return  ["bold", "del", "italic", "hr", "image", "qiniu", "table", "datetime", "|", "preview", "watch", "|", "fullscreen"];
                       	},
                    saveHTMLToTextarea : true//注意3：这个配置，方便post提交表单
                });
                
                /*
                // or
                testEditor = editormd({
                    id      : "test-editormd",
                    width   : "90%",
                    height  : 640,
                    path    : "../lib/"
                });
                */
            });
        </script>

</body>
</html>