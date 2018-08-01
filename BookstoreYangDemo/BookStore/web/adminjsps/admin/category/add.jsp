<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
      <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
      <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

      <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h1>添加分类</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form id="form" action="<c:url value="/adminCategoryServlet?method=add"/>" method="post">
    	分类名称：<input type="text" name="cname" id="a"/>
    	<input type="button" value="添加分类" onclick="bbb()"/>
        <span id="qqq"></span> <br>
    </form>
  </body>
<script>
    function bbb() {
        $('#qqq').slideUp();
        var a = $('#a').val();
        if (a == null || a == "" ) {

            $('#qqq').slideDown().text('*必填');

        } else {

            $('#qqq').slideUp();
            $('#form').submit();
        }
    }






</script>
</html>
