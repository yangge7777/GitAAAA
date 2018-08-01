<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
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
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
  <div>
	  <a href="<c:url value='/bookServlet?method=queryAll'/>"> 全部分类 </a>
  </div>
  <c:forEach var="CategoryBean" items="${CategoryBeanList}">

	  <div>
		  <a href="<c:url value='//bookServlet?cid=${CategoryBean.cid}&cname=${CategoryBean.cname}'/>">${CategoryBean.cname}分类</a>
	  </div>


  </c:forEach>


<%--<div>--%>
	<%--<a href="<c:url value='//bookServlet?method=queryEE'/>">JavaEE分类</a>--%>
<%--</div>--%>
<%--<div>--%>
	<%--<a href="<c:url value='//bookServlet?method=queryJS'/>">Javascript分类</a>--%>
<%--</div>--%>
  </body>
</html>
