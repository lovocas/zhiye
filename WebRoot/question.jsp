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

    <link rel="stylesheet" type="text/css" href="css/zhiye.css">
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
				} else {
					alert("服务端不想接收你的信息");
				}
			}
		}
	}
</script>

  </head>

  <body>
    <jsp:include page="headerbarv2.jsp"></jsp:include><br>
    <div id="container">
    <div id="left_wrapper">
        <div class="theask">
            <div class="topics"></div>
            <div class="title"><%=question.getTitle()%></div>
            <div class="qbody"><%=question.getBody()%></div>
            <div id="infobar">
            <a href="viewuser?userid=<%=question.getAuthorId() %>"><%=question.getAuthorName() %></a>在
                <%=question.getLastModifiedAt().toLocaleString() %>问出此问题
            </div>
        </div>
        <div class="answers" id="alist">
<%
List<Answer> anss = question.getAnswers();
for(Answer ans : anss) {
%>
            <div class="aanswer">
                <div class="ansbody">
                <%=ans.getBody() %><br>
                </div>
                <div class="ansinfo">
                    <a href="viewuser?userid=<%=ans.getAuthorId() %>"><%=ans.getAuthorName() %></a>发表于<%=ans.getUpdateAt().toLocaleString() %>
                </div>

            </div>
          
        </div>
        <div id="else">
        <%} 
        if(null == u) {
        %>
    你还没有
    <a href="login.jsp">登录</a>
    <% }else if(!u.getId().equals(question.getAuthorId())) {%>
    <div class="answer_form">
        <h2>添加答案</h2>
        <textarea rows="8" cols="30" name='answer' id="ans"></textarea>
        <input type="button" value="提交" onclick="submitToServer()">
    </div>
    <%}else{ %>
    <font size="2" color="blue">自问自答太寂寞, 不能回答自己的问题</font>
    <%} %>
        </div>
    </div>
        
    <div id="right">
    </div>
    </div>
  </body>
</html>
