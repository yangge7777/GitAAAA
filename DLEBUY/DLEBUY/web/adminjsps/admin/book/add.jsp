<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加图书</title>

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
        body {
            background: rgb(254, 238, 189);
        }
    </style>
</head>

<body>
<h1>添加图书</h1>
<p style="font-weight: 900; color: red">${msg }</p>

<form id="form" action="<c:url value="/adminBookServlet?method=add"/> " method="post" enctype="multipart/form-data">
    图书名称：<input style="width: 150px; height: 20px;" type="text" name="bname" id="a"/><br/>
    图书图片：<input style="width: 223px; height: 20px;" type="file" name="image" id="b"/><br/>
    图书单价：<input style="width: 150px; height: 20px;" type="text" name="price" id="c"/><br/>
    图书作者：<input style="width: 150px; height: 20px;" type="text" name="author" id="d"/><br/>
    图书分类：<select style="width: 150px; height: 20px;" name="cid" id="e">

    <c:forEach var="Category" items="${CategoryBeanList}">
        <option value="${Category.cid}">${Category.cname}</option>
    </c:forEach>
    <%--<option value="">JavaEE</option>--%>
</select>
    <br/>
    <input type="button" value="添加图书" onclick="bbb()"/>

    <span id="qqq"></span> <br>
</form>

</body>
<script>
    function bbb() {
        $('#qqq').slideUp();
        var a = $('#a').val();
        var b = $('#b').val();
        var c = $('#c').val();
        var d = $('#d').val();
        var e = $('#e').val();

        if (b == null || b == ""||a == null || a == "" || c == null || c == "" || d == null || d == "" || e == null || e == "") {

            $('#qqq').slideDown().text('有未填选项,填完后点击添加');

        } else {

            $('#qqq').slideUp();
            $('#form').submit();
        }
    }


</script>
</html>
