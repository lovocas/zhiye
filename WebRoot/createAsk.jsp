<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加问题</title>

		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="header.css">
	</head>

	<body>
  
  
    <jsp:include page="headerbar.jsp"></jsp:include><br>
		<form action="submitquestion" method="post">
            <table>
                <tr>
                <td>问题：</td>
                <td><input type="text" name="title"></td>
                </tr>
                <tr>
                <td>问题补充：</td>
                <td><textarea rows="8" cols="30" name="content"></textarea></td>
                </tr>
            </table>
			<br>
			<input type="submit" value="提交" />
		</form>



	</body>
</html>
