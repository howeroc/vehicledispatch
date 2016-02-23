<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
	
  </head>
  <body>
	<div >
		<%@ include file="/WEB-INF/nav.jsp"%>
	</div>
    <h4>显示所有管理员信息</h4>
    <struts:form action="driver" method="post">
    	<struts:hidden name="action" value="listAll"/>
    	<struts:submit value="显示所有驾驶员信息"/>
    </struts:form>
      
	 <table border="1" width="50%"  cellspacing="0" cellpadding="0">  
	    <tr>
		
			<td>编号</td>  
			<td>姓名</td>
			<td>性别</td>
			<td>出生日期</td>
			<td>身份证号</td>
			<td>驾照类型</td>
			<td>驾照编号</td>
			<td>始驾时间</td>
			<td>入职日期</td>
			<td>电话号码</td>
			<td>状态</td>
			<td>操作</td>
		</tr>  
	    <struts:iterator value="driverList" id="spt">  
	    <tr>
	      <td><struts:property value="id"/></td>
	      <td><struts:property value="name"/></td>
	      <td>
	        <struts:if test="gender == 0">
			女
        	</struts:if>
	        <struts:else>男</struts:else>
	      </td>
	      <td><struts:property value="birthdate"/></td>
	      <td><struts:property value="iden"/></td>
	      <td><struts:property value="licenType"/></td>
	      <td><struts:property value="licenId"/></td>
	      <td><struts:property value="driveYear"/></td>
	      <td><struts:property value="workdate"/></td>
	      <td><struts:property value="phone"/></td>
	      <td>
			<struts:if test="mode == 0">
			已出车
        	</struts:if>
	        <struts:elseif test="type == 1">
			待出车
	        </struts:elseif>
	        <struts:elseif test="type == 2">
			离岗
	        </struts:elseif>
	        <struts:else>离职</struts:else>
	      </td>
	      <td>
				<a href="driver.action?param=0&action=edit&id=${id}">修改</a>
                <a href="driver.action?action=delete&id=${id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')">删除</a>
			</td>
	    </tr>  
	    </struts:iterator>
	    
	  </table>
  </body>
</html>
