
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Admin</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>
<a href="/admin/adminInfo">&lt;&lt; admin manage</a>

<h1>Add admin:</h1>

<div class="l-center">
<form action="/admin/registerAct" method="post">

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
                <label for="password">password</label>
            </td>
            <td>
                <input id="password" name="password" type="password">
            </td>
        </tr>
        <tr>
            <td>
                <input value="Add" type="submit">
            </td>
            <%--<td>--%>
                <%--<a href="/admin/login">login</a>--%>
            <%--</td>--%>
        </tr>
    </table>
</form>
</div>
</body>
</html>
