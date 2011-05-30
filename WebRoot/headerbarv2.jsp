<%@ page language="java" import="java.util.*,com.zhiye.model.*"
  pageEncoding="UTF-8"%>
  
<div id="header">
  <div id="center_h">
    <div id="logo">
      <a href="">知也</a>
    </div>
    <div id="add_ask">
      <input type="text" placeholder="你可以搜索ID 话题 问题">
      <a href="createAsk.jsp">提问题</a>

    </div>

    <div id="top-menu">
      <ul>
        <%
            User me = (User) session.getAttribute("user");
            if (null != me) {
        %>
        <li>
          首页
        </li>
        <li>
          <a href="viewuser?userid=<%=me.getId() %>"> <%=me.getName()%></a>
        </li>
        <li>
          设置
        </li>
        <li>
          <a href="logout"> 退出</a>
        </li>
        <%
            }
            else {
        %>
        <li>
          <a href="viewallquestions">随便看看</a>
        </li>
        <li>
          <a href="login.jsp">登录</a>
        </li>
        <li>
          <a href="reg.jsp">注册</a>
        </li>
        <%
            }
        %>
      </ul>
    </div>
  </div>
</div>
