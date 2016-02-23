<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
	<sx:head/>
	
  </head>
  <body>
    <h4>查找出车表信息</h4>
    
    <struts:form action="task" method="post">
    
		<struts:hidden name="action" value="search"/>
    	
		<struts:textfield name="task.id" label="任务单编号"/>
		
		<struts:textfield name="task.car.id" label="车辆编号"/>
		
		<struts:textfield name="task.driver.id" label="驾驶员编号"/>
		
		<struts:textfield name="task.driver.name" label="驾驶员姓名"/>
		
		<sx:datetimepicker name="task.leaveTime" displayFormat="yyyy-MM-dd" language="zh-cn" label="起始时间" />
		
		<sx:datetimepicker name="task.backTime" displayFormat="yyyy-MM-dd" language="zh-cn" label="截止时间" />
		
		<struts:textfield name="task.dest" label="目的地"/>
		
		<struts:select name="task.mode" label="状态" list="#{'0':'待发车','1':'已发车','2':'已回车'} "/>
    	
    	<struts:submit value="查找"/>
    	
    	<struts:a href="task.action?action=add&param=0"><input type="button" value="添加任务单"/></struts:a>
    	
    </struts:form>
    
    <struts:form action="task" method="post">
    
    	<struts:hidden name="action" value="listAll"/>
    	
    	<struts:submit value="显示所有任务单信息"/>
    </struts:form>
    
	<struts:property value="message" escape="false"/>
    
	<table border="1" width="50%"  cellspacing="0" cellpadding="0">
		 <tr>
			<td>编号</td>  
			<td>车辆编号</td>
			<td>驾驶员编号</td>
			<td>发车时间</td>
			<td>回车时间</td>
			<td>目的地</td>
			<td>历程</td>
			<td>状态</td>
			<td>操作</td>
		</tr>  
		
		<struts:if test="tasks !=null">
		
		<struts:iterator value="tasks" id="spt">  
	    <tr>
	      <td><struts:property value="id"/></td>
	      <td><struts:property value="car.id + car.brand"/></td>
	      <td><struts:property value="driver.id"/></td>
	      <td><struts:property value="leaveTime"/></td>
	      <td><struts:property value="backTime"/></td>
	      <td><struts:property value="dest"/></td>
	      <td><struts:property value="mile"/></td>
	      <td>
			<struts:if test="mode == 0">
			待出车
        	</struts:if>
	        <struts:elseif test="mode == 1">
			已出车
	        </struts:elseif>
	        <struts:else>已回车</struts:else>
	      </td>
	      <td>
				<a href="task.action?param=0&action=edit&id=${id}">修改</a>
                <a href="task.action?action=delete&id=${id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')">删除</a>
			</td>
	    </tr> 
	     
	    </struts:iterator>  
		
		</struts:if>
		
	</table>
  </body>
</html>
