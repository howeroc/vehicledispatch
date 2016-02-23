<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title"/></title>   
    <link href="style/css/login.css" rel="stylesheet" type="text/css">
    	
  </head>
  <body>
  	<div id="login_header"></div>
    <div id="logo">
    <img src="style/pic/logo.jpg"/>
    </div>
        <div id="container">
        <div id="darkBanner">班车管理系统</div>
        <div id="belowDarkBanner"></div>
        <div id="wrapper">
	    <struts:form action="user" method="post" theme="simple">
	    	<fieldset>
	    	<legend>管理员登陆</legend>
	    	<struts:hidden name="action" value="login"/>
	        <p style="color:#F00;">
	    		<struts:property value="message"/>
	        </p>
	        <p>
	    		<label>员工编号：</label>
	            <struts:textfield name="user.id" cssClass="textBox"/>
	        </p>
	        <p>
	        	<label>账户密码：</label>
	            <struts:password name="user.password"  cssClass="textBox"/>
	        </p>
	    	<p>
	    		<div id="loginButton">
	            	<struts:submit value="登录" cssClass="publicButton"/> 
	            	<struts:reset value="重置" cssClass="publicButton"/>
            	</div>
            </p>
	        </fieldset>
	    </struts:form>
	    </div>
    </div>
  </body>
</html>

