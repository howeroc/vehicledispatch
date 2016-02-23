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
    <link rel="stylesheet" type="text/css" href="style/css/fix.css"/>
  </head>
  <body>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=6">维修信息管理</a>
	        </li>
	        <li>
	        <a href="${pageContext.request.contextPath }/fix.action?action=add&param=0">添加维修单</a>
	        </li>
		</ul>
	  </div>
   <div id="search">  
    <struts:form action="fix" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>维修信息查询</legend>
            <p>
                <label>维修编号：</label>
                <struts:textfield name="fix.id"/>
                
                <label>车辆编号：</label>
                <struts:textfield name="fix.car.id"/>
                
                <label>送修时间：</label>
                	<input name="fix.leaveTime" type="text" onClick="WdatePicker()" readonly>-
                    <input name="lDate" type="text" onClick="WdatePicker()" readonly>
                </P>
                <p>
                <label>修完时间：</label>
                	<input name="fix.backTime" type="text"onClick="WdatePicker()" readonly>-
                    <input name="bDate" type="text" onClick="WdatePicker()" readonly>
                
                <label>维修费用：</label>
                <struts:textfield name="fix.cost"/>-<struts:textfield name="cCost"/>元
               
        	<p style="text-align:center;">
        		<span>
        		<font color="red"><struts:property value="message"/></font>
				<struts:submit value="查询" cssClass="user_button"/>
				<struts:reset value="重置" cssClass="user_button"/>
				</span>
        	</p>
        </fieldset> 	
    </struts:form>
   </div>
<!-- ---------------------------------------搜索结果部分-----------------------------------  -->
   
		<c:if test="${page != null }">
		<div id="table_wrap">
		<table id="content_table">
			<tr>
				<th>编号</th>  
				<th>加油车辆</th>
				<th>送修时间</th>
				<th>修完时间</th>
				<th>维修费用</th>
				<th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="fix">
    	<tr>
				<td>${fix.id}</td>  
				<td>${fix.car.id}|${fix.car.brand}</td>
				<td>${fix.leaveTime}</td>
				<td>${fix.backTime}</td>
				<td>${fix.cost}</td>
				<td>
					<a href="fix.action?param=0&action=edit&fixId=${fix.id}"><input type="button" value="修改" class="user_button"/></a>
	                <a href="fix.action?action=delete&fixId=${fix.id}"  onclick="return confirm('删除后无法恢复,确定要删除吗?')"><input type="button" value="删除" class="user_button"/></a>
				</td>
			</tr>
    	</c:forEach>
    	</table>
    	</div>
<!-- ---------------------------分页-------------------------------  --> 	
	    <div id="page_bar">
	    <div id="bar_center">
	  	<ul id="page_nav">
	        <li>
	        <a href="fix.action?action=pager&pno=1&searchInfo=${searchInfo}">首页</a>
	        </li>
	        <li>
	        	<c:if test="${page.hasPrePage == false }">上一页</c:if>
				<c:if test="${page.hasPrePage == true }">
					<a href="fix.action?action=pager&pno=${page.prePage}&searchInfo=${searchInfo}">上一页</a>
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
					<a href="fix.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="fix.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>
