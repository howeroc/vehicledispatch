<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>分页</title>
  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>   
    <br/>
    <c:forEach items="${page.list }" var="task">
    	${task.id }<br/>
    </c:forEach>
    当前页面：${page.currentPage }页面总数：${page.totalPage }记录总数:${page.totalCount }
    <c:forEach begin="${page.minPage }" end="${page.maxPage }" var="i">
      [${i }]
    </c:forEach>
  </body>
</html>
