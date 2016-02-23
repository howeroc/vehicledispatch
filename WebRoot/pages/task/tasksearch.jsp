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
    <link rel="stylesheet" type="text/css" href="style/css/task.css"/>
  </head>
  <body>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=4">调度管理主页</a>
	        </li>
	        <li>
	        <a href="task.action?action=add&param=0">添加任务单</a>
	        </li>
		</ul>
	  </div>
<!-- --------------------------------------------------  -->
     
   <div id="search">  
    <struts:form action="task" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>车辆信息查询</legend>
            <p>
                <label>编号：</label>
                <struts:textfield name="task.id"/>
                
                <label>车辆编号：</label>
                <struts:textfield name="task.car.id"/>
                
                <label>驾驶员编号：</label>
                <struts:textfield name="task.driver.id"/>
                
                <label>驾驶员姓名：</label>
                <struts:textfield name="task.driver.name" label="驾驶员姓名"/>
            </p>
            <p>
                <label>目的地：</label>
                <struts:textfield name="task.dest"/>
                <label>里程（公里）：</label>
                <struts:textfield name="task.mile"/>-
                <struts:textfield name="sMile"/>
                <label>状态：</label>
                <struts:select name="task.mode" label="状态" list="#{'0':'待发车','1':'已发车','2':'已回车'} "/>
           </p>
           <p>
                <label>未回车：</label>
                <input name="task.leaveTime" type="text"  onClick="WdatePicker()" readonly>-
                    <input name="lDate" type="text" onClick="WdatePicker()" readonly>
                <label>已回车：</label>
                <input name="task.backTime" type="text" onClick="WdatePicker()" readonly>-
                    <input name="bDate" type="text"onClick="WdatePicker()" readonly>
             </p>
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
				<th>车辆编号</th>
				<th>驾驶员</th>
				<th>发车时间</th>
				<th>回车时间</th>
				<th>目的地</th>
				<th>里程（公里）</th>
				<th>状态</th>
                <th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="task">
    	<tr>
				<td>${task.id}</td>  
				<td>${task.car.id}</td>
				<td>${task.driver.id}|${task.driver.name}</td>
				<td>${task.leaveTime}</td>
				<td>${task.backTime}</td>
				<td>${task.dest}</td>
                <td>${task.mile}</td>
				<td>
					<c:choose>
						<c:when test="${task.mode == 0 }">待发车</c:when>
						<c:when test="${task.mode == 1 }">已发车</c:when>
						<c:otherwise>已回车</c:otherwise>
					</c:choose>	
				<td>
				<a href="task.action?param=0&action=edit&taskId=${task.id}"><input type="button" value="修改" class="user_button"/></a>
                <a href="task.action?action=delete&taskId=${task.id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')"><input type="button" value="删除" class="user_button"/></a>
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
	        <a href="task.action?action=pager&pno=1&searchInfo=${searchInfo}">首页</a>
	        </li>
	        <li>
	        	<c:if test="${page.hasPrePage == false }">上一页</c:if>
				<c:if test="${page.hasPrePage == true }">
					<a href="task.action?action=pager&pno=${page.prePage}&searchInfo=${searchInfo}">上一页</a>
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
					<a href="task.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="task.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>
