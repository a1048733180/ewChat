<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%String path = request.getContextPath();%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面demo</title>
<style type="text/css">
	.align-center{
		position:fixed;left:50%;top:50%;margin-left:width/2;margin-top:height/2;
	}
</style>
<script>
$(function(){
	var a = ${message} ;
	if( a != null){
		alert("${message}")
	}
	
})
</script>
</head>
<body>
	<div class="align-center">
		<form id="register" action="<%=path%>/register" method="POST" >
		账号：<input type="text" id="userid" name="userid" /> <br>
		密码：<input type="text" id="password" name="password" /> <br>
		用户名称:<input type="text" id="nickname" name="nickname" /><br>
		<input type="submit" name="submit" value="注册" />
		</form>
	</div>
</body>
</html>