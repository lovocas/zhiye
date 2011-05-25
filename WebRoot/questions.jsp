<%@ page language="java" import="java.util.*, com.zhiye.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
@SuppressWarnings("unchecked")
List<Question> questions = (List<Question>)request.getAttribute("questions");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="questioncss/style.css" />
     <link rel="stylesheet" type="text/css" href="header.css">
        
    <base href="<%=basePath%>">
    
    <title>My JSP 'questions.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <jsp:include page="headerbar.jsp"></jsp:include><br>
       <div id="dd-profile">
        <div id="wrap">
            <div class="glow"></div>
            <div id="posts">
 <% 
  for(Question q : questions ) {
      %>
                <div class="post post-text" id="post-273229a0-58a2-11e0-8a27-9e1aca9c0121">
                    <div class="post-top">

                        <span class="post-time"><%=q.getCreateAt() %></span>
                    </div>
                    
                    <div class="post-content clearfix">
                            <h4 class="post-title"><a href="viewquestion?qid=<%=q.getId() %>"><%=q.getTitle() %></a></h4>
                            <div class="text-content">
                                <p> <%=q.getBody() %></p>
                            </div>

                        

                        <div class="post-info clearfix">
                            
                            <a href="viewquestion?qid=<%=q.getId() %>">看看去</a>
                            <a href="viewquestion?qid=<%=q.getId() %>">去看看</a>

                        </div><!-- END .post-info -->
                    </div><!-- END .post-content -->
                    <div class="post-bottom"></div>
                </div><!-- END .post -->
<%} %>
            </div><!-- END #posts -->



        </div><!-- END #wrap -->

    </div><!-- END #dd-profile -->
      
  </body>
</html>
