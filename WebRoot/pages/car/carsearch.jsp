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
    <link rel="stylesheet" type="text/css" href="style/css/car.css"/>
  </head>
  <body>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=3">车辆管理主页</a>
	        </li>
	        <li>
	        <a href="car.action?action=add&param=0">添加车辆</a>
	        </li>
		</ul>
	  </div>
	 <%
    	List<String> brandList = new ArrayList<String>();
    	
    	brandList.add("");
    	
    	brandList.add("丰田");
    	
    	brandList.add("本田");
    	
    	brandList.add("三菱");
    	
    	brandList.add("江淮");
    	
    	brandList.add("宇通");
    	
    	brandList.add("金龙");
    	    	
    	request.setAttribute("brandList",brandList);
    	
     %>
     
   <div id="search">  
    <struts:form action="car" method="post" theme="simple">
    
		<struts:hidden name="action" value="pager"/>
		<fieldset>
        	<legend>车辆信息查询</legend>
            <p>
                <label>编号：</label>
                <struts:textfield name="car.id"/>
                
                <label>车牌号：</label>
                <struts:textfield name="car.licenId"/>
                
                <label>车辆类型：</label>
                <struts:select name="car.type" list="#{'':'请选择','大巴':'大巴','中巴':'中巴','小巴':'小巴'}" value=""/>
                
                <label>品牌：</label>
                <struts:select name="car.brand" list="#request.brandList" value=""/>
                
                <label>状态：</label>
                    <struts:select name="car.mode" list="#{'':'请选择','0':'已出车','1':'待出车','2':'维修','3':'报废等'}" value=""/>
                </p>
                <p>                
                    <label>座位数量：</label>
                    <struts:textfield name="car.seatNum"/>-<struts:textfield name="maxNum"/>
                    <label>购买日期：</label>
                    <input name="car.buyDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>-
                    <input name="maxDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>
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
				<th>车辆类型</th>
				<th>车牌号</th>
				<th>座位数</th>
				<th>品牌</th>
				<th>购置日期</th>
				<th>车辆状态</th>
				<th>操作</th>
			</tr>
		 <c:forEach items="${page.list }" var="car">
    	<tr>
				<td>${car.id}</td>  
				<td>${car.type}</td>
				<td>${car.licenId}</td>
				<td>${car.seatNum}</td>
				<td>${car.brand}</td>
				<td>${car.buyDate}</td>
				<td>
					<c:choose>
						<c:when test="${car.mode == 0 }">已出车</c:when>
						<c:when test="${car.mode == 1 }">待出车</c:when>
						<c:when test="${car.mode == 2 }">维修中</c:when>
						<c:otherwise>报废等</c:otherwise>
					</c:choose>	
				</td>
				<td>
				<a href="car.action?param=0&action=edit&carId=${car.id}"><input type="button" value="修改" class="user_button"/></a>
                <a href="car.action?action=delete&carId=${car.id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')"><input type="button" value="删除" class="user_button"/></a>
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
	        <a href="car.action?action=pager&pno=1&searchInfo=${searchInfo}">首页</a>
	        </li>
	        <li>
	        	<c:if test="${page.hasPrePage == false }">上一页</c:if>
				<c:if test="${page.hasPrePage == true }">
					<a href="car.action?action=pager&pno=${page.prePage}&searchInfo=${searchInfo}">上一页</a>
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
					<a href="car.action?action=pager&pno=${page.nextPage}&searchInfo=${searchInfo}">下一页</a>
				</c:if>
	        </li>
	        <li>
	        	<a href="car.action?action=pager&pno=${page.totalPage}&searchInfo=${searchInfo}">末页</a>
	        </li>
			</ul>
			</div>
			</div>
		</c:if>
		<div id="bar_blank"></div>
  </body>
</html>
