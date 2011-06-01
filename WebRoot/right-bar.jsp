<%@ page language="java" import="java.util.*,com.zhiye.model.*"
  pageEncoding="UTF-8"%>

<div id="uquq">
  <ul>
    <%
        User me = (User) session.getAttribute("user");
        if (null != me) {
    %>
    <li>
      <a href="home">我的首页</a>
    </li>
    <li>
      <a href="followingquestions">我关注的问题</a>
    </li>
    <li>
      我可能感兴趣的问题
    </li>
    <%
        }
    %>
    <li>
      <a href="viewallquestions">所有问题</a>
    </li>
    <li>
      知也正在发生
    </li>
    <li>
     <a href="viewallusers">发现好盆友</a>
    </li>
  </ul>
</div>
