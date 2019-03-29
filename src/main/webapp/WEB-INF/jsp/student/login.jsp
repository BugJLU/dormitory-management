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
    <title>Student Login</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>
<a href="/index">&lt;&lt; index</a>
<h1 class="l-title">Student Login</h1>


<div class="l-center">
<table style="margin-top: 200px">
    <form  action="/student/loginAct">
        <tr>
            <td>
                <label for="phone">phone</label>
            </td>
            <td>
                <input type="text" id="phone" name="phone">
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">password</label>
            </td>
            <td>
                <input type="password" id="password" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="login" id="loginbtn">
            </td>
            <td>
                <a href="/student/register">register</a>
            </td>
        </tr>
    </form>
</table>
</div>
</body>
</html>
