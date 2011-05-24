<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>注册 知也</title>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<form action="regist" method="post">
		    名字：
		    <input type="text" name="name"/><br>  
			email:
			<input type="text" name="email" /><br>
			密码:
			<input type="password" name="password"><br>
            最能概括你的标志性语言:
            <input type="text" name="tagline"><br>
			 个人经历：
			<textarea rows="8" cols="30" name="bio"></textarea><br>
			个人网站：
			<input type="text" name="website" value="http://"><br>
			是否女性：
			<input type="checkbox" name="gender" value="1"><br>
			<input type="submit" value="提交">
		</form>
    
    <font color="#ff0000">每项必须认真填，不填木有霄寂寂</font>


	</body>
</html>
