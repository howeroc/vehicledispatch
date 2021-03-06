<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
    
	<sx:head/>
	    <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/fix.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    	  <div id="top_bar">
	  	<ul id="top_nav">
	        <li>
	        <a href="nav.action?param=6">维修信息管理</a>
	        </li>
	        <li>
	        <a href="fix.action?action=add&param=0">添加维修单</a>
	        </li>
		</ul>
	  </div>
  <div id="add_body">
    <fieldset>
        	<legend>修改维修单信息</legend>
            
    <struts:form action="fix" method="post" theme="simple">	
    	
    	<struts:hidden name="action" value="edit"/>
    	
    	<struts:hidden name="param" value="1"/>
        
    	<p style="color:#F00";>
        	<span>
    			<struts:property value="message" />
            </span>
    	</p>
        
        <p>
        <label>维修编号：</label>
    	<struts:textfield  name="fix.id" readonly="true"/>
    	</p>
    	<p>
        <label>车辆信息：</label>
    	<struts:select list="cars" listKey="id" listValue="'编号：'+id+'\t|\t品牌：'+brand+'\t|\t座位数：'+seatNum" name="fix.car.id"/>
    	</p>
        <p>
    	<label>送修时间：</label>
    	<input name="fix.leaveTime" type="text" class="input_cxcalendar" onClick="WdatePicker()" value="${fix.leaveTime}" readonly>
        </p>
        <p>
    	<label>修完时间：</label>
    	<input name="fix.backTime" type="text" class="input_cxcalendar" onClick="WdatePicker()" value="${fix.backTime}" readonly>
        </p>
        <p>
    	<label>维修费用：</label>
    	<struts:textfield  name="fix.cost" />元
        </p>
        <p style="text-align:center;">
        	<span>
            <struts:submit value="修改" cssClass="user_button"/> 
            <struts:reset value="重置" cssClass="user_button"/>
            </span> 
    	</p>
      
    </struts:form>
	</fieldset>
    </div>
  </body>
</html>
