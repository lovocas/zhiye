<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
@SuppressWarnings("unchecked")
List<Question> questions = (List<Question>)request.getAttribute("questions");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
  #xx {
  background-color: grey;
  }
  </style>
    <base href="<%=basePath%>">
    
    <title>My JSP 'questions.jsp' starting page</title>
    
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
  <% 
  for(Question q : questions ) {
      %>
      <h5><a href="viewquestion?qid=<%=q.getId() %>"><%=q.getTitle() %></a></h5>
      <p><%=q.getBody() %></p>
      <dir id="xx">&nbsp;</dir>
      <%} %>
  </body>
</html>
