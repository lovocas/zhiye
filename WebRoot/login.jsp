<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录 知也</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="login" method="post">
    Email:<input type="text" name="email"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="登录">
  </form>
  你个二货没有<a href="reg.jsp">注册</a>?
  
  
  </body>
</html>
