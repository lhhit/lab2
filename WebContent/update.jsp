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


     <form name="f1" id="f1" action="<%=path%>/update" method="post">
      <table border="0" width="60%" >
        <tr>
 
          <h2><span style="color:Teal">请输入您要更新的书目信息：</span></h2>
          <h3><span style="color:Red">${tip }</span></h3>
          <tr>
	          <td>bookTitle</td>
	          <td><input type="text" name="bookTitle"></td>
          </tr>
          <tr>
	          <td>ISBN:</td>
	          <td><input type="text" name="newISBN"></td>
          </tr>
          <tr>
	          <td>Publisher:</td>
	          <td><input type="text" name="newPublisher"></td>
          </tr>           
          <tr>
	          <td>PublishDate:</td>
	          <td><input type="text" name="newPublishDate"></td>
          </tr>
          <tr>
	          <td>Price:</td>
	          <td><input type="text" name="newPrice"></td>
          </tr>
          <tr>
	          <td>AuthorName:</td>
	          <td><input type="text" name="newName"></td>
          </tr>
          
          <tr>
	          <td>AuthorAge</td>
	          <td><input type="text" name="newAge"></td>
          </tr>
          <tr>
	          <td>AuthorID:</td>
	          <td><input type="text" name="newAuthorID"></td>
          </tr>
          <tr>
	          <td>Country:</td>
	          <td><input type="text" name="newCountry"></td>
          </tr>
         	 <td colspan="2" align="center"><input type="submit" value="提交"></td>
         	 <br>
      </table>
		<br>
    </form>
    <td width="200px"><a href="query.jsp">跳到查询界面</a></td>
    	<td width="200px"><a href="add.jsp">跳到添加界面</a></td>
  </body>
</html>
