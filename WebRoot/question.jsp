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
    <link rel="stylesheet" type="text/css" href="css/question.css">
    
    <script type="text/javascript" src="javascript/followQ.js"></script>
    <script type="text/javascript" src="jquery-1.6.1.js"></script>
    <script type="text/javascript" src="javascript/jquery.qeditor.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery.qeditor.css">
    

<style type="text/css">

</style>
  </head>

  <body>
    <jsp:include page="headerbarv2.jsp"></jsp:include><br>
    <div id="container">
    <div id="left_wrapper">
        <div class="theask">
            <div class="topics"></div>
            <div class="title"><h3><%=question.getTitle()%></h3></div>
            <div class="qbody"><%=question.getBody()%></div>
            <div id="infobar">
            <a href="viewuser?userid=<%=question.getAuthorId() %>"><%=question.getAuthorName() %></a>在
                <%=question.getLastModifiedAt().toLocaleString() %>问出此问题
            </div>
        </div>
        <div class="ans-count">有<%=question.getAnswers().size() %>个回答</div>
        
        <div class="answers" id="alist">
<%
List<Answer> anss = question.getAnswers();
for(Answer ans : anss) {
%>
            <div class="aanswer">
                <div class="vote_buttons"> 
                    <a rel="tipsyr" class="vote_up" onclick="return Asks.vote('4db2c4d2fd503c619300005e',1);" href="" ></a> 
                    <a rel="tipsyr" class="vote_down" onclick="return Asks.vote('4db2c4d2fd503c619300005e',0);" href="" ></a> 
                </div>
                <div class="answer-border">
                    <div class="author">
                        <a class="user" href="viewuser?userid=<%=ans.getAuthorId() %>"><%=ans.getAuthorName() %></a>
                    </div>
                    
                    <div class="votes"> 
                        <span class="num"><%=ans.getVoteCount() %></span>票
                    </div>
                    <div class="ansbody">
                        <%=ans.getBody() %><br>
                    </div>
                    <div class="action"> 
                        <a href="void" onclick="return Asks.toggleComments('answer','4db4059cfd503c7a390000d3');">添加评论</a> • <%=ans.getUpdateAt().toLocaleString() %> 
                    </div>
                    
                </div>
            </div>
          <%} %>
        </div>
        <div id="else">
        <%
        if(null == u) {
        %>
    你还没有
    <a href="login.jsp">登录</a>
    <% }else if(!u.getId().equals(question.getAuthorId())) {%>
    <div class="answer_form">
        <h2>添加答案</h2>
      
    
    <div id="textarea">
      <form id="new_answer" class="new_answer"
        method="get"
        data-remote="true" action="answerquestion" accept-charset="UTF-8">
        <input type="hidden" value="<%=question.getId() %>" name="qid">
        <div style="margin: 0; padding: 0; display: inline"></div>
        <div class="row">
          <textarea id="ans" class="text long qeditor"
            style="height: 100px; display: none;" rows="20"
            name="answer" cols="40"></textarea>
        </div>

        <div class="actions">
         <input type="submit" value="提交" ">
        </div>
      </form>
    </div>
    </div>
    <script type="text/javascript">
    $("#ans").qeditor();
    </script>
    
    <%}else{ %>
    <font size="2" color="blue">自问自答太寂寞, 不能回答自己的问题</font>
    <%} %>
        </div>
    </div>
        
    <div id="right">
    <jsp:include page="quesrightbar.jsp"></jsp:include>
    </div>
    </div>
  </body>
</html>
