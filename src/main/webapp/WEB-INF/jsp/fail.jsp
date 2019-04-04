<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/17
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fail</title>
</head>
<body background="/img/background.jpg">
    <h1>${action} fail.</h1>
    <% if (request.getAttribute("reason") != null) { %>
        <p>reason: ${reason}</p>
    <% } %>
    <a href="/index">Click to return to index.</a>
</body>
</html>
