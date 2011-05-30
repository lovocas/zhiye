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
        <title>登录 知也</title>
        <link rel="stylesheet" type="text/css" href="css/zhiye.css">
    </head>
    
    <body>
        <jsp:include page="headerbarv2.jsp"></jsp:include><br>
        <div id="container">
            <div id="left_wrapper">
                <form action="login" method="post">
                    Email:<input type="text" name="email"><br>
                    Password:<input type="password" name="password"><br>
                    <input type="submit" value="登录">
                </form>
        你没有<a href="reg.jsp">注册</a>?
            </div>
        </div>
    </body>
</html>
