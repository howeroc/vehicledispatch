<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
	<sx:head/>
    
	<script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/user.css" rel="stylesheet" type="text/css">
  </head>
  <body>
	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=1">管理员主页</a>
	        </li>
	        <li>
	        <a href="user.action?action=add&param=0">添加管理员</a>
	        </li>
		</ul>
	  </div>
  
   <div id="search">  
    <struts:form action="user" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>管理员信息查询</legend>
            <p>
                <label>员工编号：</label>
                <struts:textfield name="user.id"/>
                
                <label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
                <struts:textfield name="user.name"/>
                
                <label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
                <struts:select name="user.gender" list="#{'':'未选择','1':'男','0':'女'}" value=""/>
                
                <label>员工类型：</label>
				<struts:select name="user.type" label="管理员类型" list="#{'':'未选择','0':'超级管理员','1':'普通管理员'}" value=""/>
			</p>
            <p>
                <label>出生日期：</label>
                <input name="user.birthdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
                <input name="user.workdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>

                <label>入职日期：</label>
                <input name="startDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
                <input name="endDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>
        	</p>
        	<p>
        		<font color="red"><struts:property value="message"/></font>
				<struts:submit value="查询" cssClass="user_button"/>
				<struts:reset value="重置" cssClass="user_button"/> 
        	</p>
    	
        </fieldset> 	
    </struts:form>
   </div>
   
		<c:if test="${page != null }">
		<div id="table_wrap">
		<table id="content_table">
			<tr>
				<th>编号</th>  
				<th>姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>入职日期</th>
				<th>电话号码</th>
				<th>管理员类型</th>
				<th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="user">
    	<tr>
				<td>${user.id}</td>  
				<td>${user.name}</td>
				<td>
					<c:choose>
						<c:when test="${user.gender == 0 }">女</c:when>
						<c:otherwise>男</c:otherwise>
					</c:choose>	
					
				</td>
				<td>${user.birthdate}</td>
				<td>${user.workdate}</td>
				<td>${user.phone}</td>
				<td>
					<c:choose>
						<c:when test="${user.type == 0 }">超级管理员</c:when>
						<c:when test="${user.type == 1 }">普通管理员</c:when>
						<c:otherwise>已离职</c:otherwise>
					</c:choose>
				</td>
				<td>
				<a href="user.action?param=0&action=edit&userId=${user.id}"><input type="button" value="修改" class="user_button"/></a>
                <a href="user.action?action=delete&userId=${user.id}" onclick="return confirm('删除后无法恢复,确定要删除吗')"><input type="button" value="删除" class="user_button"/></a>
				</td>
			</tr>
    	</c:forEach>
    	</table>
    	</div>
    	
	    <div id="page_bar">
	    <div id="bar_center">
	  	<ul id="page_nav">
	        <li>
	        <a href="user.action?action=pager&pno=1&searchInfo=${searchInfo}">首页</a>
	        </li>
	        <li>
	        	<c:if test="${page.hasPrePage == false }">上一页</c:if>
				<c:if test="${page.hasPrePage == true }">
					<a href="user.action?action=pager&pno=${page.prePage}&searchInfo=${searchInfo}">上一页</a>
				</c:if> 
	        </li>
	        <li>
				每页${page.pageSize}条记录
            <li>
            	共${page.totalRecord}条记录
            </li>
            <li>
            当前第(${page.currentPage}/${page.totalPage})页
	        </li>
	        <li>
				<c:if test="${page.hasNextPage == false }">下一页</c:if>
				<c:if test="${page.hasNextPage == true }">
					<a href="user.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="user.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>