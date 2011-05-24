<%@ page language="java" import="java.util.*,com.zhiye.model.*"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
            Question question = (Question) request.getAttribute("question");
            User u = (User)session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'asks.jsp' starting page</title>

        <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style type="text/css">
  
    #infobar{
        background-color:#E0E0E0;
    }
    
    
  </style>
     <script type="text/javascript">
    function validateAns(str) {
    	if(str.length < 3) {
    		alert("请输入经过你大脑后的信息 :-)");
    		return false;
    	}
    	return true;
    }
    function submitToServer() {
    	
    	//先验证
    	if(!validateAns(document.getElementById("ans").value)) {
    		return;
    	}
    	
        var url = "answerquestion";
        if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            //微软IE6
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (null != req) {
            req.open("POST", url, true);
            req.onreadystatechange = comeback; //回调函数的方式，就是一个监听器
            //content-type" content="text/html; charset=UTF-8"
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //req.send(null);
            req.send("answer=" + encodeURIComponent(document.getElementById("ans").value)
                  + "&qid=" + "<%=question.getId()%>");
            //post方式必须放入这里， get方式直接加之参数上
        }
    }
    function comeback() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                var respText = req.responseText;
                //alert(respText);
                if ("OK" == respText) {
                    document.getElementById("ans").value = "";
                }
                else {
                	alert("服务端不想接收你的信息");
                }
            }
        }
    }

</script>

    </head>

    <body>
        <%
            
            if (null != question) {
        %>
        <h1><%=question.getTitle()%></h1><%=question.getId()%><br>
        <p><%=question.getBody()%></p>
        <%
            } 
        %>
        <div id="infobar">
        <font size="3" color="green"><a href="viewuser?userid=<%=question.getAuthorId() %>" ><%=question.getAuthorName() %></a></font>在
        <%=question.getLastModifiedAt().toLocaleString() %>问出此问题
        </div>
        <br>—————————————————————————————————————<br>
        <%
        List<Answer> anss = question.getAnswers();
        for(Answer ans : anss) {
            
        %>
        <%=ans.getBody() %><br> 
        <div id="infobar">
        <font size="3" color="green"><a href="viewuser?userid=<%=ans.getAuthorId() %>" ><%=ans.getAuthorName() %></a></font>发表于<%=ans.getUpdateAt().toLocaleString() %>
        </div>
        <br>---------------------------------------------------------------------------<br>
        <%} 
        if(null == u) {
        %>
        你还没有<a href="login.jsp">登录</a>
        <% }else if(!u.getId().equals(question.getAuthorId())) {%>
            <font color="#cdefab">我要回答</font>:<br>
            <textarea rows="8" cols="30" name='answer' id="ans"></textarea>
            <input type="button" value="提交" onclick="submitToServer()">
            <%}else{ %>
            <font size="2" color="blue">自问自答太寂寞, 不能回答自己的问题</font>
            <%} %>
    </body>
</html>
