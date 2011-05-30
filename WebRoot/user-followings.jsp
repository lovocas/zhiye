<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
User viewedUser = (User) request.getAttribute("vieweduser");
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
List<User> followings=(List<User>)request.getAttribute("followings");
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user-followings.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--> 
  <link rel="stylesheet" type="text/css" href="css/zhiye.css">
    <script type="text/javascript" src="javascript/follow.js"></script>

  </head>
  
  <body>
        <jsp:include page="headerbarv2.jsp"></jsp:include><br>
        <div id="container">
            <div id="left_wrapper">
                <div class="user-profile">
                    <%=viewedUser.getName()%>-<%=viewedUser.getTagline()%><br>
                    简介：<br>
                    <p><%=viewedUser.getBio()%><br>
                </div>
                <div class="tabs"><a href="viewuser?userid=<%=viewedUser.getId() %>">主页</a> <a>问过</a> <a>回答过</a> <a>关注的话题</a></div>
                <div class="followers">
                <% for(User u : followings) {%>
                    <a href="viewuser?userid=<%=u.getId() %>"><%=u.getName() %></a> ,<%u.getTagline(); %><br>
                      <%} %>
                </div>
            </div>
            <div id="right">
             <jsp:include page="user_right_bar.jsp"></jsp:include> 
            </div>
            
        </div>
  <body>
  
</html>
