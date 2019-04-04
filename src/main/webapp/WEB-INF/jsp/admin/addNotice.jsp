<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/4/4
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddNotice</title>
</head>
<body background="/img/background.jpg">
    <h1>Add Notice: </h1>
    <form action="/admin/addNoticeAct">
        <label for="msg">Notice message: </label>
        <input type="text" id="msg" name="msg">
        <input type="submit" value="Add">
    </form>
</body>
</html>
