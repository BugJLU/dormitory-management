<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/18
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Register</title>
</head>
<body>
<form action="/student/registerAct" method="post">
    <table>
        <tr>
            <td>
                <label for="name">name</label>
            </td>
            <td>
                <input id="name" name="name" type="text">
            </td>
        </tr>
        <tr>
            <td>
                <label for="phone">phone</label>
            </td>
            <td>
                <input id="phone" name="phone" type="text">
            </td>
        </tr>
        <tr>
            <td>
                <label for="studentId">student ID</label>
            </td>
            <td>
                <input id="studentId" name="studentId" type="text">
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">password</label>
            </td>
            <td>
                <input id="password" name="password" type="password">
            </td>
        </tr>
        <tr>
            <td>
                <input value="register" type="submit">
            </td>
            <td>
                <a href="/student/login">login</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>