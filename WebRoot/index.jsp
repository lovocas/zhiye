<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <% User u = (User)session.getAttribute("user");
   if(null != u) {
   %>
   欢迎你<%= u.getName() %><br>
   <a href="createAsk.jsp">我有问题要问</a><br>
   <a href="viewallusers"><font color="red">查看</font></a>哪些人也在使用知也<br>
   
   <a href="viewallquestions">其他所有问题</a>
   <%}
   else {
   %>
   你木有<a href="login.jsp">登录</a>，有木有？！有木有！！！！木有！！！！！！！！
   <%} %>
  </body>
</html>
