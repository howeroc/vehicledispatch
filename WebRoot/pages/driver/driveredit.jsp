<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
    
	<sx:head/>
	    <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/driver.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    
    <%
    	List<String> licenList = new ArrayList<String>();
    	
    	licenList.add("A1");
    	
    	licenList.add("A2");
    	
    	licenList.add("A3");
    	
    	licenList.add("B1");
    	
    	licenList.add("B2");
    	
    	licenList.add("C1");
    	
    	licenList.add("C2");
    	
    	request.setAttribute("licenList",licenList);
    	
     %>
    <div id="top_bar">
        <ul id="top_nav">
            <li>
            <a href="nav.action?param=2">驾驶员管理主页</a>
            </li>
            <li>
            <a href="driver.action?action=add&param=0">添加驾驶员</a>
            </li>
            </ul>
      </div>
<-- --------------------------------------------------------------------- -->
      
   <div id="add_body"> 
	<fieldset>
		<legend>添加驾驶员信息</legend> 
    <struts:form action="driver" method="post" theme="simple">
    	
    	<struts:hidden name="action" value="edit"/>
    	
    	<struts:hidden name="param" value="1"/>
        
    	<p style="color:#F00;">
        	<span>
            <struts:property value="message" escape="false"/>
            </span>
        </p>
        
        <p>
    	<label>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
    	<struts:textfield name="driver.id"/>
        </p>
        <p>
    	<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
    	<struts:textfield name="driver.name"/>
        </p>
        <p>
        	<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
    		<struts:radio name="driver.gender" label="性别" list="#{'1':'男','0':'女'} "/>
        </p>
        <p>
    	<label>出生日期：</label>
    	<input name="driver.birthdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" value="${driver.birthdate}" readonly>
        </p>
        <p>
    	<label>身份证号：</label>
    	<struts:textfield name="driver.iden" />
        </p>
        <p>
    	<label>驾照类型：</label>
    	<struts:select name="driver.licenType" list="#request.licenList" />
        </p>
        <p>
    	<label>始驾日期：</label>
    	<input name="driver.driveYear" type="text" class="input_cxcalendar" onClick="WdatePicker()" value="${driver.driveYear}" readonly>
        </p>
        <p>
        <label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
        <struts:textfield name="driver.phone" />
        </p>
         <p>
    	<label>入职日期：</label>
        <input name="driver.workdate" type="text" class="input_cxcalendar" onClick="WdatePicker()" value="${driver.workdate}" readonly>
        </p>
        <p>
        <label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
        <struts:radio name="driver.mode" label="状态" list="#{'0':'已出车','1':'待出车','2':'离岗','3':'离职'} " value="1"/>
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
