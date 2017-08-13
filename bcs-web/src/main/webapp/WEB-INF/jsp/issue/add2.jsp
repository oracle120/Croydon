<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/editor.md-master/css/editormd.css" />
<script src="<%=request.getContextPath() %>/resources/editor.md-master/editormd.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<sf:form method="post" modelAttribute="issueDto" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0" border="0">
		<thead><tr><td colspan="2">添加问题功能</td></tr></thead>
		<tr>
			<!-- <td class="rightTd" width="200px">问题标题:</td> -->
			<td class="leftTd"><sf:input path="title" size="30" value="请输入问题标题"/><sf:errors cssClass="errorContainer" path="title"/></td>
		</tr>
		<tr>
			<td class="leftTd">问题描述:<sf:input path="description" size="30" type="text" /></td>
		</tr>
		
		<tr>
			<!-- <td class="rightTd">提交人:</td> --><td><sf:input path="userid" size="30" value="admin"/></td>
		</tr>
		
		<tr>
			<td colspan="2" class="centerTd"><input type="button" id="add_user" value="添加用户"/><input type="reset"/></td>
		</tr>
		<tr>
					<td class="leftId">解决方案:</td>
					
				</tr>
				<tr>
					<td>
						<div class="editormd" id="test-editormd">
							<textarea class="editormd-markdown-textarea"
								name="test-editormd-markdown-doc"
								id="test-editormd-markdown-doc"></textarea>
							<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
							<sf:textarea class="editormd-html-textarea" name="text" id="text" path="solution" />
						</div> <%-- <sf:textarea path="solution" id="solution" size="30" /> --%>
						<sf:errors cssClass="errorContainer" path="solution" />
					</td>
				</tr>
	</table>
	</sf:form>
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