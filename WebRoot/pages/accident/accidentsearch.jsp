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
    <h4>查找事故信息</h4>
    
    <struts:form action="accident" method="post">
    
		<struts:hidden name="action" value="search"/>
    	
    	<label>编&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
		<struts:textfield name="accident.id"/>
		
		<label>车辆编号：</label>
		<struts:textfield name="accident.car.id"/>
		
		<label>驾驶员编号：</label>
		<struts:textfield name="accident.driver.id"/>
		
		<label>发生日期：</label>
		<sx:datetimepicker name="accident.accDate" displayFormat="yyyy-MM-dd" language="zh-cn"/>
		    	
    	<struts:submit value="查找"/>
    	
    	<struts:a href="accident.action?action=add&param=0"><input type="button" value="添加事故信息"/></struts:a>
    	
    </struts:form>
    
    <struts:form action="accident" method="post">
    
    	<struts:hidden name="action" value="listAll"/>
    	
    	<struts:submit value="显示所有任务单信息"/>
    </struts:form>
    
	<struts:property value="message"/>
    
	<table>
		 <tr>
			<td>编号</td>  
			<td>车辆编号</td>
			<td>驾驶员编号</td>
			<td>发生日期</td>
			<td>处理费用</td>
			<td>操作</td>
		</tr>  
		
		<struts:if test="accidents !=null">
		
		<struts:iterator value="accidents" id="spt">  
	    <tr>
	      <td><struts:property value="id"/></td>
	      <td><struts:property value="car.id + car.brand"/></td>
	      <td><struts:property value="driver.id+driver.name"/></td>
	      <td><struts:property value="accDate"/></td>
	      <td><struts:property value="cost"/></td>
	      <td>
				<a href="accident.action?param=0&action=edit&id=${id}">修改</a>
                <a href="accident.action?action=delete&id=${id}" onclick="return confirm('删除后无法恢复,确定要删除吗?')">删除</a>
			</td>
	    </tr> 
	     
	    </struts:iterator>  
		
		</struts:if>
		
	</table>
  </body>
</html>
