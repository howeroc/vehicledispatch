<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><struts:property value="title" escape="false"/></title>    
    
	<sx:head/>
	    <script language="javascript" type="text/javascript" src="style/My97DatePicker/WdatePicker.js"></script>
	<link href="style/css/car.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    
    <%
    	List<String> brandList = new ArrayList<String>();
    	brandList.add("丰田");
    	
    	brandList.add("本田");
    	
    	brandList.add("三菱");
    	
    	brandList.add("江淮");
    	
    	brandList.add("宇通");
    	
    	brandList.add("金龙");
    	    	
    	request.setAttribute("brandList",brandList);
    	
     %>
    <div id="top_bar">
        <ul id="top_nav">
            <li>
            <a href="nav.action?param=3">车辆管理主页</a>
            </li>
            <li>
            <a href="car.action?action=add&param=0">添加车辆</a>
            </li>
            </ul>
      </div>
<!--  --------------------------------------------------------------------- -->
      
   <div id="add_body"> 
	<fieldset>
		<legend>添加车辆信息</legend> 
    <struts:form action="car" method="post" theme="simple" cssClass="add_form">
    	
    	<struts:hidden name="action" value="add"/>
    	
    	<struts:hidden name="param" value="1"/>
    	<p style="color:#F00;">
        	<span>
            <struts:property value="message" escape="false"/>
            </span>
        </p>
        
        <p>
    	<label>车辆编号：</label>
    	<struts:textfield name="car.id" cssClass="texbox"/>
        </p>
        <p>
    	<label>车牌号：</label>
    	<struts:textfield name="car.licenId"/>
        </p>
        <p>
    	<label>车辆类型：</label>
    	<struts:select name="car.type" list="#{'大巴':'大巴','中巴':'中巴','小巴':'小巴','面包':'面包'}" value="大巴"/>
        </p>
        <p>
    	<label>座位数量：</label>
    	<struts:textfield name="car.seatNum" cssClass="texbox"/>
        </p>
        <p>
    	<label>车辆品牌：</label>
    	<struts:select name="car.brand" list="#request.brandList" value="丰田"/>
        </p>
        <p>
    	<label>购置时间：</label>
    	<input name="car.buyDate" type="text" class="input_cxcalendar" onClick="WdatePicker()" readonly>
        </p>
        <p>
        <label>车辆状态：</label>
        <struts:radio name="car.mode" label="状态" list="#{'0':'已出车','1':'待出车','2':'维修'} " value="1"/>
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
