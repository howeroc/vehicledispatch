<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
    
	<sx:head/>
	    <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/refuel.css" rel="stylesheet" type="text/css">
    
  </head>
  <body>
  	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=5">车辆管理主页</a>
	        </li>
	        <li>
	        <a href="refuel.action?action=add&param=0">添加车辆</a>
	        </li>
		</ul>
	  </div>
<!--  --------------------------------------------------------------------- -->
      
   <div id="add_body"> 
	<fieldset>
		<legend>填写加油单</legend> 
    <struts:form action="refuel" method="post" theme="simple" >
    	
    	<struts:hidden name="action" value="add"/>
    	
    	<struts:hidden name="param" value="1"/>
    	<p style="color:#F00;">
        	<span>
            <struts:property value="message" escape="false"/>
            </span>
        </p>
        
        <p>
    	<label>加油单号：</label>
    	<struts:textfield name="refuel.id" label="编号"/>
        </p>
        <p>
    	<label>加油车辆：</label>
    	<struts:select list="cars" listKey="id" listValue="'编号：'+id+'\t|\t品牌：'+brand+'\t|\t座位数：'+seatNum" name="refuel.car.id"/>
        </p>
        <p>
    	<label>加油日期：</label>
    	<input name="refuel.reDate" type="text" onClick="WdatePicker()" readonly>
        </p>
        <p>
    	<label>加油数量：</label>
    	<struts:textfield name="refuel.quan" cssClass="texbox"/>
        </p>
        <p>
    	<label>加油费用：</label>
    	<struts:textfield name="refuel.cost" cssClass="texbox"/>
        </p>
        <p style="text-align:center;">
        <span>
    	<struts:submit value="提交" cssClass="user_button"/> 
        <struts:reset value="重置" cssClass="user_button"/>
        </span>
        </p>
    </struts:form>
    </fieldset>
   </div>
  </body>
</html>
