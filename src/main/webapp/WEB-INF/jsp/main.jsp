<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
</head>
<body>
	<div id="test">
		<h1>测试登录功能</h1>
		<a>登录成功跳转的页面</a>
		<a>haha2:${requestScope.message}</a><br>
	</div>
	<a>${userid}</a><br>
	<a href="<%=path%>/${userid}/message">查看个人资料</a>
</body>
</html>