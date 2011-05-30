<%@ page language="java" import="java.util.*,com.zhiye.model.*"
  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<%
    @SuppressWarnings("unchecked")
    List<Question> questions = (List<Question>) request
            .getAttribute("questions");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <base href="<%=basePath%>">

    <title>My JSP 'questions.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="css/zhiye.css">
  </head>

  <body>
    <jsp:include page="headerbarv2.jsp"></jsp:include>
    <div id="container">
      <div id="left_wrapper">
        <div id="asks" class="listing">
          <%
              for (Question q : questions) {
          %>
          <div class="ask">
            <div class="title">
              <a href="viewquestion?qid=<%=q.getId()%>"><%=q.getTitle()%></a>
            </div>
            <div class="info">
              <a href="viewuser?userid=<%=q.getAuthorId() %>" class="user"><%=q.getAuthorName()%></a>
              提出的问题 •
              <%=q.getAnswers().size()%>
              个答案 •<%=q.getCreatedAt().toLocaleString()%>
              •
            </div>
            <div class="qbody">
              <%=q.getBody()%>
            </div>
          </div>
          <%
              }
          %>
        </div>
      </div>
      <div id="right">
        <jsp:include page="rightbar.jsp"></jsp:include>
      </div>
    </div>

  </body>
</html>
