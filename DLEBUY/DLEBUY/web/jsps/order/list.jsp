<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>我的订单</h1>

<c:forEach var="show"  items="${ordershowlist}" >
<table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${show.orderBean.oid}　成交时间：${show.orderBean.ordertime}　金额：<font color="red"><b>${show.orderBean.total}</b></font>　
			<c:if test="${show.orderBean.state=='0'}">
				<a href="<c:url value='/orderServlet?method=load&aaa=${show.orderBean.oid}'/>">付款</a>
			</c:if>
			<c:if test="${show.orderBean.state=='1'}">
				等待卖家发货 <a href="<c:url value="/orderServlet?method=ABR&aaa=${show.orderBean.oid}"/> ">确认收货</a>
			</c:if>
			<c:if test="${show.orderBean.state=='2'}">
			订单结束
		</c:if>
			<c:if test="${show.orderBean.state=='3'}">
				卖家已发货<a href="<c:url value="/orderServlet?method=ABR&aaa=${show.orderBean.oid}"/> ">确认收货</a>
			</c:if>
		</td>
	</tr>

	<c:forEach var="orderItemPlus" items="${show.orderItemPlusList}">
    <tr bordercolor="gray" align="center">
		<td width="15%">
			<div><img src="<c:url value='${orderItemPlus.image}'/>" height="75"/></div>
		</td>
		<td>书名:${orderItemPlus.bname}</td>
		<td>单价:${orderItemPlus.sglprice}</td>
		<td>作者:${orderItemPlus.author}</td>
		<td>数量:${orderItemPlus.count}</td>
		<td>小计${orderItemPlus.price}</td>
	</tr>

	</c:forEach>

</c:forEach>







<%--<table border="1" width="100%" cellspacing="0" background="black">--%>

	<%--<tr bgcolor="gray" bordercolor="gray">--%>
		<%--<td colspan="6">--%>
			<%--订单编号：abcdefg　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2</b></font>　--%>

					<%--<a href="<c:url value='/jsps/order/desc.jsp'/>">付款</a>--%>

					<%--等待发货--%>
					<%--<a href="javascript:alert('已确认收货！');">确认收货</a>--%>
					<%--订单结束--%>
		<%--</td>--%>
	<%--</tr>--%>

	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>


	<%--<tr bgcolor="gray" bordercolor="gray">--%>
		<%--<td colspan="6">--%>
			<%--订单编号：abcdefg　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2</b></font>　--%>

					<%--<a href="<c:url value='/jsps/order/desc.jsp'/>">付款</a>--%>

					<%--等待发货--%>
					<%--<a href="javascript:alert('已确认收货！');">确认收货</a>--%>
					<%--订单结束--%>
		<%--</td>--%>
	<%--</tr>--%>

	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>

	<%--<tr bgcolor="gray" bordercolor="gray">--%>
		<%--<td colspan="6">--%>
			<%--订单编号：abcdefg　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2</b></font>　--%>

					<%--<a href="<c:url value='/jsps/order/desc.jsp'/>">付款</a>--%>

					<%--等待发货--%>
					<%--<a href="javascript:alert('已确认收货！');">确认收货</a>--%>
					<%--订单结束--%>
		<%--</td>--%>
	<%--</tr>--%>

	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>

	<%--<tr bgcolor="gray" bordercolor="gray">--%>
		<%--<td colspan="6">--%>
			<%--订单编号：abcdefg　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2</b></font>　--%>

					<%--<a href="<c:url value='/jsps/order/desc.jsp'/>">付款</a>--%>

					<%--等待发货--%>
					<%--<a href="javascript:alert('已确认收货！');">确认收货</a>--%>
					<%--订单结束--%>
		<%--</td>--%>
	<%--</tr>--%>

	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>

<%--</table>--%>
  </body>
</html>
