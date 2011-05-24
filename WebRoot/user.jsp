<%@ page language="java" import="java.util.*,com.zhiye.model.*"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>

 <%
            User viewedUser = (User) request.getAttribute("vieweduser");
            User user = (User) session.getAttribute("user");
                    
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
        <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    --->
    <style type="text/css">
    
    a.green_button {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background: url("/images/green_button_bg.gif?1301366040") repeat-x scroll left bottom #DFF0B1;
    border-color: #8CB332 #8CB332 #648517;
    border-radius: 3px 3px 3px 3px;
    border-style: solid;
    border-width: 1px;
    color: #406A24;
    display: inline-block;
    font-weight: bold;
    line-height: 22px;
    padding: 2px 10px 1px;
    text-align: center;
    text-decoration: none;
    text-shadow: 0 1px #D4ED95;
}
a.flat_button {
    background: none repeat scroll 0 0 #DDDDDD;
    border: 1px solid #DDDDDD;
    border-radius: 3px 3px 3px 3px;
    color: #666666;
    display: inline-block;
    line-height: 22px;
    padding: 2px 10px 1px;
    text-align: center;
    text-decoration: none;
}
</style>
    <script type="text/javascript">
    var req = null;
    function unfollow(viewed, user) {
    	var url = "unfollowuser?viewedid=" + viewed + "&" + "uid=" + user;
    	if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            //微软IE6
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (null != req) {
            req.open("GET", url, true);
            req.onreadystatechange = comeback0; //回调函数的方式，就是一个监听器
            req.send(null);
        }
    }
    function comeback0() {
    	
    }
    function follow(viewd, user) {
        var url = "followuser?viewedid=" + viewd + "&" + "uid=" + user;
        if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (null != req) {
            req.open("GET", url, true);
            req.onreadystatechange = comeback; //回调函数的方式，就是一个监听器
            req.send(null);
        }
    }
    function comeback() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                var respText = req.responseText;
                if ("OK" == respText) {
                    document.getElementById("folow_button").innerHTML = "关注成功";
                }
            }
        }
    }

</script>
    </head>

    <body>

        我[<%=user.getName()%>]正在浏览
        <h6><%=viewedUser.getName()%></h6>，<%=viewedUser.getTagline()%>
        <br>
        简介：
        <br>
        <p><%=viewedUser.getBio()%>
        <%if(user.equals(viewedUser)){ %>
         <font color="blue">这就是你自己</font>
            <%
        } 
        else if (!user.getFollowingIds().contains(viewedUser.getId())) {
            %>
            <a class='green_button' id='folow_button' onclick="follow('<%=viewedUser.getId()%>',
                     '<%=user.getId()%>');">关注我</a>
            <%}else { %>
            
           <a class="flat_button " onclick="unfollow('<%=viewedUser.getId()%>',
               '<%=user.getId()%>');">取消关注</a>
            <%
            }
           %>
           <br>
           粉丝<font color="blue"><a href="viewuserfollowers?viewedid=<%=viewedUser.getId()%>"> <%=viewedUser.getFollowerIds().size() %></a></font>个人<br>
           关注<font color="blue"><%=viewedUser.getFollowingIds().size() %></font>个人
    </body>


</html>