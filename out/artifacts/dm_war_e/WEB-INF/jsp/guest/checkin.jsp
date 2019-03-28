<%@ page import="com.jtliu.dormitorymanagement.model.User" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/17
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkin</title>
</head>
<body>
    <form action="/guest/checkinAct" method="post">
        <table>
            <tr>
                <td><label for="name">name</label></td>
                <td><input id="name" name="name" type="text"></td>
            </tr>
            <tr>
                <td><label for="phone">phone</label></td>
                <td><input id="phone" name="phone" type="text"></td>
            </tr>
            <tr>
                <td><label for="reason">reason to visit</label></td>
                <td><input id="reason" name="reason" type="text"></td>
            </tr>
            <tr>
                <td><label>person to visit</label></td>
                <% User host = (User) request.getAttribute("host");
                    if (host != null) { %>
                <td><span style="font-weight: bold">${host.name}</span></td>
                <% } %>
            </tr>
            <input id="host" name="host" type="text" hidden value="${host.id}">
            <%--<a href="">search and choose</a>--%>
            <input type="submit" value="checkin">
        </table>
    </form>
</body>
</html>
