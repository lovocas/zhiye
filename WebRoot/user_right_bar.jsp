<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
User viewedUser = (User) request.getAttribute("vieweduser");
User user = (User) session.getAttribute("user");
 %>
<div id="user-right">
  <%
      if (user.equals(viewedUser)) {
  %>
  <font color="blue">这就是你自己</font>
  <%
      }
      else if (!user.getFollowingIds().contains(viewedUser.getId())) {
  %>
  <a class='green_button' id='follow_button'
    onclick="follow('<%=viewedUser.getId()%>',
                     '<%=user.getId()%>');">关注我</a>
  <%
      }
      else {
  %>

  <a class="flat_button " id="unfollow_button"
    onclick="unfollow('<%=viewedUser.getId()%>',
               '<%=user.getId()%>');">无视我</a>
  <%
      }
  %>
  <br>
  粉丝
  <font color="blue">
    <a href="viewuserfollowers?viewedid=<%=viewedUser.getId()%>"> <%=viewedUser.getFollowerIds().size()%></a>
  </font>个人
  <br>
  关注
  <font color="blue">
    <a href="viewuserfollowings?viewedid=<%=viewedUser.getId()%>"><%=viewedUser.getFollowingIds().size()%></a>
  </font>个人
</div>