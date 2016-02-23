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
    <h4>填写事故表</h4>
    
    <struts:form action="accident" method="post">
    	
    	<struts:hidden name="action" value="add"/>
    	
    	<struts:hidden name="param" value="1"/>
    	
    	<struts:property value="message"/>
    	
    	<label>编号：</label>
    	<struts:textfield name="accident.id"/>
    	
    	<label>车辆：</label>
    	<struts:select list="cars" listKey="id" listValue="'编号：'+id+'\t|\t品牌：'+brand+'\t|\t座位数：'+seatNum" name="accident.car.id"/>
    	
    	<label>驾驶员</label>
    	<struts:select list="drivers" listKey="id" listValue="'编号：'+id+'\t|\t姓名：'+name+'\t|\t驾照：'+licenType" name="accident.driver.id"/>
    	
    	<label>发生日期：</label>
    	<sx:datetimepicker name="accident.accDate" displayFormat="yyyy-MM-dd" language="zh-cn"/>
    	
    	<label>处理费用</label>
    	<struts:textfield name="accident.cost"/>
    	
    	<struts:submit value="提交"/>     	
    </struts:form>
  </body>
</html>
