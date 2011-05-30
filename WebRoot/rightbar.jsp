<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>

<style>
<!--
a:link, a:visited {
color: #19558D;
}
-->
</style>
<div id="nav">
  <ul>
    <%
    User me = (User) session.getAttribute("user");
    if (null != me) {
    %>
    <li>
      我的首页
    </li>
    <li>
      <a href="viewmyfollowedquestions">我关注的问题</a>
    </li>
    <li>
      <a href="topicquestions">我可能感兴趣的问题</a>
    </li>
    <%} %>
    <li>
      <a href="viewallquestions">所有问题</a>
    </li>
    <li>
      知也正在发生
    </li>
  </ul>
</div>
