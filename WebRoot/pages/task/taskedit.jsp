<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
    
	<sx:head/>
	  <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/task.css" rel="stylesheet" type="text/css">
  </head>
  <body>
      <div id="top_bar">
        <ul id="top_nav">
            <li>
            <a href="nav.action?param=4">调度管理主页</a>
            </li>
            <li>
            <a href="task.action?action=add&param=0">添加任务单</a>
            </li>
            </ul>
	</div>
<!--  --------------------------------------------------------------------- -->
  <div id="add_body">
    <fieldset>
        	<legend>修改任务单</legend>
            
    <struts:form action="task" method="post" theme="simple">	
    	
    	<struts:hidden name="action" value="edit"/>
    	
    	<struts:hidden name="param" value="1"/>
        
    	<p style="color:#F00";>
        	<span>
    			<struts:property value="message" />
            </span>
    	</p>
        
        <p>
    	<label>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
    	<struts:textfield name="task.id" readonly="true"/>
        </p>
        <p>
    	<label>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辆：</label>
    	<struts:select list="cars" listKey="id" listValue="'编号：'+id+'\t|\t品牌：'+brand+'\t|\t座位数：'+seatNum" name="task.car.id" label="选择车辆"/>
        </p>
        <p>
    	<label>司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</label>
    	<struts:select list="drivers" listKey="id" listValue="'编号：'+id+'\t|\t姓名：'+name+'\t|\t驾照：'+licenType" name="task.driver.id" label="选择司机"/>
        </p>
        <p>
    	<label>发车时间：</label>
    	<input name="task.leaveTime" type="text" onClick="WdatePicker()" value="${task.leaveTime}" readonly>
        </p>
        <label>回车时间：</label>
    	<input name="task.backTime" type="text" onClick="WdatePicker()" value="${task.backTime}" readonly>
        </p>
        <p>
    	<label>目&nbsp;&nbsp;的&nbsp;&nbsp;地：</label>
    	<struts:textfield name="task.dest" />
        </p>
        <p>
        <label>里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</label>
        <struts:textfield name="task.mile"/>公里
        </p>
         <p>
        <label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
       <struts:radio name="task.mode" label="状态" list="#{'0':'待发车','1':'已发车'} "/>
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
