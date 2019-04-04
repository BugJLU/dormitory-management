<%@ page import="com.jtliu.dormitorymanagement.model.Notice" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/17
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dormitory Management System</title>
</head>
<link href="/css/layout.css" rel="stylesheet">
<body background="/img/background.jpg">
<h1 class="l-title">Dormitory Management System</h1>

<div class="l-center">
    <a class="l-choices" href="/student/">Student</a>
    <a class="l-choices" href="/guest/">Guest</a>
    <a class="l-choices" href="/admin/">Administrator</a>
</div>

<div>
    <h3>Notice</h3>
        <% Notice notice = (Notice) request.getAttribute("notice");
            if (notice == null) {
        %>
        <p>No notice yet.</p>
        <%
        } else {
        %>
        <p><%=notice.getMessage()%></p>
        <div>
            By:
            <%=notice.getAdmin().getName()%>
        </div>
        <div>
            Date:
            <%=notice.getDate()%>
    </div>
    <% } %>
</div>

</body>
</html>
