<%@ page language="java" import="java.util.*,com.zhiye.model.*"
  pageEncoding="UTF-8"%>

<div id="bra" style="background:grey">
  <ul>
    <%
        User u = (User) session.getAttribute("user");
        if (null != u) {
    %>
    <li>
      欢迎你<%=u.getName()%>
    </li>
    <li>
      <a href="createAsk.jsp">有问题</a>
      <br>
    </li>
    <li>
      <a href="viewallusers">看看其他人</a>
      <br>
    </li>
    <li>
      <a href="viewallquestions">所有问题</a>
    </li>
    <%
        } else {
    %>
    <li>
      <a href="login.jsp">登录</a>
    </li>
    <%
        }
    %>
  </ul>
</div>
<!-- end of menu -->


