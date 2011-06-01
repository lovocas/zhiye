<%@ page language="java" import="java.util.*,com.zhiye.model.*, com.zhiye.dao.*, com.zhiye.model.Log.Action, com.zhiye.util.*"
  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<%
    @SuppressWarnings("unchecked")
    List<Log> logs = (List<Log>)request.getAttribute("logs");
UserDAO dao = new UserDAO(DB.morphia, DB.mongo); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <base href="<%=basePath%>">

    <title>首页 知也</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="css/zhiye.css">
  </head>

  <body>
    <jsp:include page="headerbarv2.jsp"></jsp:include>
    <div id="container">
      <div id="right">
            <jsp:include page="right-bar.jsp"></jsp:include>
        </div>  
      <div id="left_wrapper">
         <div class="ulogs">
                <%for(Log log : logs){ %>
                <%if(log instanceof UserLog){ %>
                <%=log.getTitle() %> 
                关注了
                <%
                Action action = log.getAction();
                if(action == Action.FOLLOW_USER) {
                %>
                <%
                  User u = dao.get(log.getTargetId());
                %>
                <a href="viewuser?userid=<%=log.getTargetId() %>"><%=u.getName() %></a>
                <%}else if(action == Action.FOLLOW_ASK) {%>
                问题
                <%
                QuestionDAO qdao = new QuestionDAO(DB.morphia, DB.mongo);
                Question q = qdao.get(log.getTargetId());
                %>
                <a href="viewquestion?qid=<%=log.getTargetId() %>"><%=q.getTitle() %></a>
                <%} %>
                
                <%}else if(log instanceof QuestionLog){ %>
                
                <%
                Action action = log.getAction();
                User u = dao.get(log.getOwnerId());
                if(action == Action.NEW) {
                %>
                <%=u.getName() %>创建了问题<a href="viewquestion?qid=<%=log.getTargetId() %>"><%=log.getTitle() %></a>
                <%} %>
                <%}else if(log instanceof AnswerLog){ %>
                <%
                Action action = log.getAction();
                User u = dao.get(log.getOwnerId());
                if(action == Action.NEW) {
                %>
                <%=u.getName() %>回答了问题<a href="viewquestion?qid=<%=log.getTargetParentId() %> "><%=log.getTargetParentTitle() %></a><br>
                <%=log.getTitle() %>
                <%} %>
                                
                <%} %>
                
                <br>
                <%} %>
                </div>
        
      </div>
      
   
      
      
    </div>

  </body>
</html>
