<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>

<table>
    <form action="/admin/loginAct">
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
        </tr>
    </form>
</table>
</body>
</html>
