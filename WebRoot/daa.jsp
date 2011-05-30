<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>Lovocas</title>

    <style type="text/css">
/*设置网页填满浏览器*/
body {
	margin: 0;
	padding: 0;
}

#header {
	height: 40px;
	background-color: #505759;
	color: white; /*字体为白色*/
}

#center_h { /*东西都发在中间*/
	width: 960px;
	margin: 0 auto;
	overflow: hidden;
}

#right {
	width: 210px;
	float: right;
	background-color: grey
}

#container {
	width: 960px;
	margin: 0 auto;
	overflow: hidden;
	text-align: left;
}

#right ul {
	margin: 0;
	padding: 0;
	list-style-type: none;
	font: bold 12px/22px Verdana, Arial, Helvetica, sans-serif;
	text-indent: 20px;
	letter-spacing: 1px;
	border-bottom: 1px solid #fff;
}

#logo {
	float: left;
	margin: 2px 20px 0 0;
	font-size: 30px;
}

#header #add_ask {
	display: inline-block;
	background: black;
	height: 35px;
	line-height: 35px;
	padding: 0 6px;
	overflow: hidden;
	margin: 2px 20px 0 0;
}

#header #add_ask input {
	width: 360px;
	font-size: 16px;
	height: 20px;
	line-height: 18px;
	border: 0;
	margin-top: 2px;
}

#header #add_ask a {
	color: #F0F0F0;
	font-weight: bold;
	font-size: 13px;
	padding: 10px 10px;
	text-align: center;
}

#left_wrapper {
	margin-left: 20px;
	margin-right: 20px;
	width: 605px;
	float: left;
}

#top-menu {
	width: 210px;
	float: right;
	font-size: 12px;
}

#top-menu ul {
	margin-top: 12px;
	padding: 0;
	list-style: none;
}

#top-menu ul li {
	float: left;
	padding: 0 10px;
	border-right: 1px solid #CCC;
}

#item {
	margin-bottom: 15px;
	border-bottom: 1px solid #DDD;
	padding-bottom: 10px;
}

.ask {
	margin-bottom: 15px;
	border-bottom: 1px solid #DDD;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #DDD;
	padding-bottom: 10px;
}
</style>
  </head>

  <body>
    <div id="header">
      <div id="center_h">
        <div id="logo">
          知也
        </div>
        <div id="add_ask">
          <input type="text" placeholder="你可以搜索ID 话题 问题">
          <a href="">提问题</a>

        </div>

        <div id="top-menu">
          <ul>
            <li>
              首页
            </li>
            <li>
              Lovocas
            </li>
            <li>
              设置
            </li>
            <li>
              退出
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div id="container">
      <div id="left_wrapper">
        <div id="list">
          <div id="item">
            大大
          </div>
          <div id="item">
            bb
          </div>
        </div>

      </div>

      <div id="right">
        <div id="nav">
          <ul>
            <li>
              我的首页
            </li>
            <li>
              我关注的问题
            </li>
            <li>
              我可能感兴趣的问题
            </li>
            <li>
              所有问题
            </li>
          </ul>
        </div>
      </div>

    </div>



  </body>
</html>


