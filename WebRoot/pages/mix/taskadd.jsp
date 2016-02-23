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
    <h4>填写出车表</h4>
    
    <struts:form action="task" method="post">
    	
    	<struts:hidden name="action" value="add"/>
    	
    	<struts:hidden name="param" value="1"/>
    	
    	<struts:property value="message"/>
    	
    	<struts:textfield name="task.id" label="编号"/>
    	
    	<struts:select list="cars" listKey="id" listValue="'编号：'+id+'\t|\t品牌：'+brand+'\t|\t座位数：'+seatNum" name="task.car.id" label="选择车辆"/>
    	
    	<struts:select list="drivers" listKey="id" listValue="'编号：'+id+'\t|\t姓名：'+name+'\t|\t驾照：'+licenType" name="task.driver.id" label="选择司机"/>
    	
    	<sx:datetimepicker name="task.leaveTime" displayFormat="yyyy-MM-dd" language="zh-cn" label="发车时间" />
    	
    	<struts:textfield name="task.dest" label="目的地"/>
    	
    	<struts:textfield name="task.mile" label="距离"/>
    	
    	<struts:radio name="task.mode" label="状态" list="#{'0':'待发车','1':'已发车'} " value="0"/>
    	
    	<struts:submit value="提交"/> 
    	
    </struts:form>
  </body>
</html>
