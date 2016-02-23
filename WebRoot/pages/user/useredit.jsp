<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    

	<sx:head/>
    <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/user.css" rel="stylesheet" type="text/css">	
  </head>
  <body>
    
    <div id="top_bar">
        <ul id="top_nav">
            <li>
            <a href="nav.action?param=1">管理员主页</a>
            </li>
            <li>
            <a href="user.action?action=add&param=0">添加管理员</a>
            </li>
            </ul>
      </div>
  <div id="add_body">
    <fieldset>
        	<legend>修改管理员信息</legend>
    <struts:form action="user" method="post" theme="simple">	
    	
    	<struts:hidden name="action" value="edit"/>
    	
    	<struts:hidden name="param" value="1"/>
    	    	
    	<struts:hidden name="user.password"/>
        
    	<p>
    		<font color="#FF0000"><struts:property value="message" /></font> 
    	</p>
        
        <p>
        <label>用户编号：</label>
    	<struts:textfield  name="user.id" readonly="true"/>
    	</p>
        
        <p>
        <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
    	<struts:textfield name="user.name" label="姓名"/>
    	</p>
        
        <p>
        <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
    	<struts:radio name="user.gender" label="性别" list="#{'1':'男','0':'女'} "/>
    	</p>
        
        <p>
        <label>出生日期：</label>
    	<input name="user.birthdate" type="text" class="texbox" onClick="WdatePicker()" value=${user.birthdate} readonly>
    	</p>
        
        <p>
        <label>入职日期：</label>
    	<input name="user.workdate" type="text" class="texbox" onClick="WdatePicker()" value=${user.workdate} readonly>
    	</p>
        <p>
        <label>联系电话：</label>
    	<struts:textfield name="user.phone" />
    	</p>
        <p>
        <label>用户类型：</label>
    	<struts:radio name="user.type" label="管理员类型" list="#{'0':'超级管理员','1':'普通管理员','2':'离休'} "/>
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
