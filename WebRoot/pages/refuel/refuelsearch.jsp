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
    <link rel="stylesheet" type="text/css" href="style/css/refuel.css"/>
  </head>
  <body>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=5">加油管理主页</a>
	        </li>
	        <li>
	        <a href="refuel.action?action=add&param=0">添加加油表</a>
	        </li>
		</ul>
	  </div>
     
   <div id="search">  
    <struts:form action="refuel" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>加油单信息查询</legend>
            <p>
                <label>编号：</label>
                <struts:textfield name="refuel.id"/>
                
                <label>车辆编号：</label>
                <struts:textfield name="refuel.car.id"/>
                
                <label>加油日期：</label>
                <input name="refuel.reDate" type="text" onClick="WdatePicker()" readonly>-
                    <input name="endDate" type="text" onClick="WdatePicker()" readonly>
               </p>
               <p>                
                <label>加油量：</label>
                <struts:textfield name="refuel.quan"/>-
                <struts:textfield name="maxQuan"/>
                
                <label>费用：</label>
                    <struts:textfield name="refuel.cost"/>-
                	<struts:textfield name="maxCost"/>
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
				<th>车牌号</th>
				<th>加油日期</th>
				<th>加油量（升）</th>
				<th>费用(元)</th>
				<th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="refuel">
    	<tr>
				<td>${refuel.id}</td>  
				<td>${refuel.car.licenId}</td>
				<td>${refuel.reDate}</td>
				<td>${refuel.quan}</td>
				<td>${refuel.cost}</td>
				<td>
				<a href="refuel.action?param=0&action=edit&refuelId=${refuel.id}"><input type="button" value="修改" class="user_button"/></a>
                <a href="refuel.action?action=delete&refuelId=${refuel.id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')"><input type="button" value="删除" class="user_button"/></a>
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
	        <a href="refuel.action?action=pager&pno=1&searchInfo=${searchInfo}">首页</a>
	        </li>
	        <li>
	        	<c:if test="${page.hasPrePage == false }">上一页</c:if>
				<c:if test="${page.hasPrePage == true }">
					<a href="refuel.action?action=pager&pno=${page.prePage}&searchInfo=${searchInfo}">上一页</a>
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
					<a href="refuel.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="refuel.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>
