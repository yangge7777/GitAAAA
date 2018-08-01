<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.activation.URLDataSource" %>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	  <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	  <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
	  <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

	  <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->
	  <script type="text/javascript" >
		  function add() {
			  $(".error").text("");
			  var bool =true ;
			  if (!$(":text[name=username]").val()){
				  $("#usernameError").text("不能为空(づ￣3￣)");
				  bool =false;
			  }
			  if (!$(":password[name=password]").val()){
				  $("#passwordError").text("不能为空(づ￣3￣)");
				  bool =false;
			  }
			  if (bool){
				  $("form").submit();
			  }

		  }
	  </script>
	  <style type="text/css">
		  .error {color:red;}
	  </style>
  </head>
  
  <body>
<%! String a ;%>
  <%
	  Cookie [] cookies =request.getCookies();
	  for (Cookie cookie : cookies) {
		  if ("username".equals(cookie.getName())){
			  a= URLDecoder.decode(cookie.getValue(),"utf8");

		  }
	  }

  %>
  <h1>登录</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/userSevlet'/>" method="post">
	<input type="hidden" name="method" value="login"/>
	<table border="0" align="center" width="40%" style="margin-left: 100px;">
		<tr>
			<td width="100px" > 用户名</td>
			<td width="40%">
				<input type="text" name="username" value="<%= a==null?"":a%>" /><br/>
			</td>
			<td>
				<label id="usernameError" class="error"></label>
			</td>
		</tr>

		<tr>
			<td width="100px"> 密码</td>
			<td width="40%">
				<input type="password" name="password" /><br/>
			</td>
			<td>
				<label id="passwordError" class="error"></label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="登录" onclick="add()"/> <a href="<c:url value="/jsps/main.jsp"/> "> <input type="button" value="返回"> </a>

			</td>

		</tr>

</table>




</form>
  </body>
</html>
