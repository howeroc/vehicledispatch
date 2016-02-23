<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>失败页面</title>

  </head>
  <body>
  <h4>糟糕,发生错误<struts:property value="message" escape="false"/>！</h4>
  </body>
</html>
