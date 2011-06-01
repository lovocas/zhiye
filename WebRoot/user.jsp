<%@ page language="java" import="java.util.*,com.zhiye.model.*, com.zhiye.dao.*, com.zhiye.model.Log.Action, com.zhiye.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
%>

 <%
User viewedUser = (User) request.getAttribute("vieweduser");
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
List<Log> logs = (List<Log>)request.getAttribute("logs");
 %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    
        <base href="<%=basePath%>">

        <title><%=viewedUser.getName() %> 知也</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
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
                <div class="ulogs">
                <%for(Log log : logs){ %>
                <%if(log instanceof UserLog){ %>
                <%=log.getTitle() %> 
                关注了
                <%
                Action action = log.getAction();
                if(action == Action.FOLLOW_USER) {
                %>
                <%UserDAO dao = new UserDAO(DB.morphia, DB.mongo); 
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
                if(action == Action.NEW) {
                %>
                <%=viewedUser.getName() %>创建了问题<a href="viewquestion?qid=<%=log.getTargetId() %>"><%=log.getTitle() %></a>
                <%} %>
                <%}else if(log instanceof AnswerLog){ %>
                <%
                Action action = log.getAction();
                if(action == Action.NEW) {
                %>
                <%=viewedUser.getName() %>回答了问题<a href="viewquestion?qid=<%=log.getTargetParentId() %> "><%=log.getTargetParentTitle() %></a><br>
                <%=log.getTitle() %>
                <%} %>
                                
                <%} %>
                
                <br>
                <%} %>
                </div>
                
            </div>
            <div id="right">
            <jsp:include page="user_right_bar.jsp"></jsp:include>            
            </div>
        </div>
      
    </body>


</html>