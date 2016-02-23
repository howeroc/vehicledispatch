<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title> 
	<link href="style/css/nav.css" rel="stylesheet" type="text/css">
    <style type="text/css" >
	#logout{
		width:50px;
		height:45px;
		position:absolute;
		top:0px;
		right:20px;
		}
	#logout a{
		color:#FFF;
		display:block;
		margin-top:15px;
		text-decoration:none;
		text-align:center;
		background:#424242;
		border-radius:3px;
		}
	#logout a:hover{
		background-color:#FFF;
		color:#3A4953;}
	</style>
  </head>
  <body>
  <div id="nav_header">
  	<div id="login_header">
    	<span id="name">班车管理系统</span>
    </div>
    <div id="logout"><a href="user.action?action=logout" >注销</a></div>
    <div id="logo">
    	<img src="style/pic/logo.jpg"/>
    </div>
  </div>
  <div id="nav_body">
      <div id="body_left">
      <div id="nav_banner">选择操作</div>
      <div id="below_banner"></div>
        <ul id="nav_list">
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=1" target="main">管理员信息管理</a>
        </li>
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=2" target="main">驾驶员信息管理</a>
        </li>
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=3" target="main">车辆信息管理</a>
        </li>
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=4" target="main">车辆调度管理</a>
        </li>
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=5" target="main">加油信息管理</a>
        </li>
        <hr class="hor"/>
        <li class="nav_item">
        <a href="nav.action?param=6" target="main">维修信息管理</a>
        </li>
        <hr class="hor"/>
		</ul>
      </div>
      <div id="body_right">
      <iframe name="main" frameborder="0" scrolling="auto" allowtransparency="true" ></iframe>
      </div>
  </div>
  </body>
</html>
