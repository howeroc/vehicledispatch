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
    <link rel="stylesheet" type="text/css" href="style/css/driver.css"/>
  </head>
  <body>
      <%
    	List<String> licenList = new ArrayList<String>();
		
		licenList.add("");
    	
    	licenList.add("A1");
    	
    	licenList.add("A2");
    	
    	licenList.add("A3");
    	
    	licenList.add("B1");
    	
    	licenList.add("B2");
    	
    	licenList.add("C1");
    	
    	licenList.add("C2");
    	
    	request.setAttribute("licenList",licenList);
    	
     %>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=2">驾驶员管理主页</a>
	        </li>
	        <li>
	        <a href="driver.action?action=add&param=0">添加驾驶员</a>
	        </li>
		</ul>
	  </div>
     
   <div id="search">  
    <struts:form action="driver" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>驾驶员信息查询</legend>
            <p>
                <label>编号：</label>
                <struts:textfield name="driver.id"/>
                
                <label>姓名：</label>
                <struts:textfield name="driver.name"/>
                
                <label>性别：</label>
				<struts:select name="driver.gender" list="#{'':'请选择','0':'女','1':'男'}" value=""/>
				
				<label>状态：</label>
                <struts:select name="driver.mode" list="#{'':'请选择','0':'已出车','1':'待出车','2':'离岗','3':'离职等'}" value=""/>
                
                <label>身份证：</label>
                <struts:textfield name="driver.iden"/>
           </p>
           <p>
           		<label>驾照类型：</label>
                <struts:select name="driver.licenType" list="#request.licenList" value=""/>
                <label>电话：</label>
                <struts:textfield name="driver.phone"/>
                <label>出生日期：</label>
				<input name="driver.birthdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
				<input name="bDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly> 
           
           </p>
           <p>            
				<label>始驾日期：</label>
				<input name="driver.driveYear" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
				<input name="dDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>
				<label>入职时间：</label>
				<input name="driver.workdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
				<input name="wDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>
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
				<th>姓名</th>
				<th>性别</th>
				<th>生日</th>
				<th>身份证号</th>
				<th>驾照类型</th>
				<th>始驾时间</th>
				<th>入职时间</th>
				<th>电话</th>
                <th>状态</th>
				<th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="driver">
    	<tr>
				<td>${driver.id}</td>  
				<td>${driver.name}</td>
				<td>
					<c:choose>
						<c:when test="${driver.gender == 0 }">女</c:when>
						<c:otherwise>男</c:otherwise>
					</c:choose>	
				</td>
				<td>${driver.birthdate}</td>
				<td>${driver.iden}</td>
				<td>${driver.licenType}</td>
				<td>${driver.driveYear}</td>
				<td>${driver.workdate}</td>
                <td>${driver.phone}</td>
				<td>
					<c:choose>
						<c:when test="${driver.mode == 0 }">已出车</c:when>
						<c:when test="${driver.mode == 1 }">待出车</c:when>
						<c:when test="${driver.mode == 2 }">离岗</c:when>
						<c:otherwise>离职等</c:otherwise>
					</c:choose>	
				<td>
				<a href="driver.action?param=0&action=edit&driverId=${driver.id}"><input type="button" value="修改" class="user_button"/></a>
                <a href="driver.action?action=delete&driverId=${driver.id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')"><input type="button" value="删除" class="user_button"/></a>
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
					<a href="driver.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="driver.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>
