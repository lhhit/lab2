<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书籍信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body> 
  <h3>书籍信息：</h3> 
    	<table width="100%" border="1" cellpadding="2" cellspacing="1" align="center">
		<tr>			
			<td width="100px"><center>Title</center></td>
			<td width="100px"><center>ISBN</center></td>
			<td width="100px"><center>AuthorID</center></td>
			<td width="200px"><center>Publisher</center></td>
			<td width="100px"><center>PublishDate</center></td>
			<td width="100px"><center>Price </center></td>
		</tr>	
		<tr>
			<td width="100px"><center>${Title}</center></td>
			<td width="100px"><center>${ISBN}</center></td>
			<td width="100px"><center>${AuthorID}</center></td>
			<td width="200px"><center>${Publisher}</center></td>
			<td width="100px"><center>${PublishDate }</center></td>
			<td width="100px"><center>${Price}</center></td>
		</tr>
	</table>
	<h3>作者信息：</h3> 
   	<table width="80%" border="1" cellpadding="2" cellspacing="1">
	<tr>			
		<td width="100px"><center>Name</center></td>
		<td width="100px"><center>AuthorID</center></td>
		<td width="100px"><center>Age</center></td>
		<td width="200px"><center>Country</center></td>
	</tr>	
	<tr>
		<td width="100px"><center>${Name}</center></td>
		<td width="100px"><center>${AuthorID}</center></td>
		<td width="100px"><center>${Age}</center></td>
		<td width="200px"><center>${Country}</center></td>
	</tr>
	</table>
	<br>
	<button onclick="history.back()">返回查询界面</button>
  </body>
</html>
