<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
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
			  if (!$(":text[name=password]").val()){
				  $("#passwordError").text("不能为空(づ￣3￣)");
				  bool =false;
			  }

			  var patrn = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
				  var a =$(':text[name=email]').val();
				  if (!patrn.exec(a)){
					  $("#emailError").text("请输入正确的邮箱格式");
					bool=false;
				  }else {
					  bool=true;
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
  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/register'/> " method="post">
	<input type="hidden" name="method" value="regist"/>
	<table border="0" align="center" width="40%" style="margin-left: 100px;">
		<tr>
			<td width="100px"> 用户名</td>
			<td width="40%">
				<%--TEST!!!!!--%>
			<input type="text" name="username"value="" id="username"/><br/>
				<%--<span id="errortest"></span> <br>--%>
			</td>
			<td>
				<label id="usernameError" class="error"></label>
			</td>



		</tr>
		<tr>
			<td width="100px"> 密码</td>
			<td width="40%">
				<input type="text" name="password" /><br/>
			</td>
			<td>
				<label id="passwordError" class="error"></label>
			</td>
		</tr>
		<tr>
			<td width="100px"> 邮箱</td>
			<td width="40%">
				<input type="text" name="email" /><br/>
			</td>
			<td>
				<label id="emailError" class="error"></label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="注册" onclick="add()"/>
			</td>
		</tr>

	</table>

</form>
  </body>


  <%--<script>--%>
	  <%--$(function () {--%>
		  <%--$('#errortest').slideUp();--%>
		  <%--$('#username').blur(function () {--%>
			  <%--var username =$('#username').val();--%>
				<%--if(username==null||username==""){--%>
					<%--$('#errortest').text('用户名不能为空').slideDown();--%>
				<%--}else {--%>
					<%--$('#errortest').slideUp()--%>
				<%--}--%>
			  <%----%>
		  <%--})--%>

	  <%--})--%>
  <%--</script>--%>
</html>
