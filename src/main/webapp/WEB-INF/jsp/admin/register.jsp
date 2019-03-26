
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Register</title>
</head>
<body>
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
</body>
</html>
