<%@ page language="java" import="java.util.*,com.zhiye.model.*"
  pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    Question question = (Question) request.getAttribute("question");
%>
<div id="qright">
  <div id="urelation">
    <%
        if (null == user) {
    %>
    <%
        }
        else if (!question.getFollowerIds().contains(user.getId())) {
    %>
    <a class='green_button' id='follow_button'
      onclick="followQ('<%=question.getId()%>',
                     '<%=user.getId()%>');">关注我</a>
    <%
        }
        else {
    %>

    <a class="flat_button " id="unfollow_button"
      onclick="unfollowQ('<%=question.getId()%>',
               '<%=user.getId()%>');">无视我</a>
    <%
        }
    %>
  </div>
  <div id="questioninfo">
    <h2>
      问题状态
    </h2>
    <a href="viewuser?userid=<%=question.getAuthorId() %>"><%=question.getAuthorName()%></a>创建了该问题，
    <br>
    <%=question.getFollowerIds().size()%>人关注了该问题
  </div>
</div>

