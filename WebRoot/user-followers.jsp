<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
@SuppressWarnings("unchecked")
List<User> followers = (List<User>)request.getAttribute("followers");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user-followers.jsp' starting page</title>
    
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
  <%if(null == followers) return; %>
  <% for(User u : followers) {%>
  <a href="viewuser?userid=<%=u.getId() %>"><%=u.getName() %></a> ,<%u.getTagline(); %><br>
  <br>——————————————————————————————<br>
  <%} %>
  </body>
</html>
