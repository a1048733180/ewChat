<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String path =request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body>
	<form id="modifypassword" method="POST" action="<%=path%>/${userid}/modifypassword" >
	<div align="center">
		原密码：<input id="password" name="password" type="text"><br>
		新密码：<input id="npassword" name="npassword" type="text"> <br>
		确认密码：<input id="spassword" name="spassword" type="text"> <br>
		<input type="submit" id="submit" value="修改" name="submit"><br>
	</div>
	</form>
</body>
</html>