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
    
    <title>图书管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form name="f1" id="f1" action="<%=path%>/search" method="post">
      <table border="0">
        <tr>
          <td>请输入作者:</td>
          <td><input type="text" name="author" id="login"></td>
          <td colspan="2" align="center"><input type="submit" value="查询"></td>
        </tr>

      </table>
		<br>
    </form>
   <br>
   ${tipQuery } 
     <br>
      <br>
    	<table width="100%" border="1" cellpadding="2" cellspacing="1">
		<tr>
			          <h3><span style="color:Red">${tipQuery}</span></h3>
			<td width="200px"><center>Title</center></td>
			<td width="200px"><center>操作</center></td>
			<td width="200px"><center>操作</center></td>
		</tr>	
		<s:iterator value="#request.list" id="Book_Author" status="st">
		<tr>
			<td width="200px"><center><a href="getdata?index=${st.index }"><s:property value="#Book_Author.Title"/></a></center></td>
			<!-- 
			<td width="200px"><center><a href="delete?index=${st.index }">删除</a></center></td>
			-->
			<td width="200px"><center><a href="update.jsp">更新</a></center></td>
			</tr>
		</s:iterator>
		
	</table>
	<br>
    	<td width="200px"><a href="update.jsp">跳到更新界面</a></td>
    	<td width="200px"><a href="add.jsp">跳到添加界面</a></td>
  </body>
</html>
