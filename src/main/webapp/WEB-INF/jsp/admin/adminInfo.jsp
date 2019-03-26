<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.jtliu.dormitorymanagement.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Manage</title>
</head>
<body>
<h1>AdminList：</h1>
<form action="/admin/adminInfo">
</form>
<table>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            if (users.size() == 0) {
    %>
    <p>No admin found.</p>
    <%
    } else {
    %>
    <tr>
        <th>name</th>
        <th>phone</th>
    </tr>
    <%
        for (User u :
                users) {
    %>
    <form action="/admin/adminAct">
    <tr>
        <td>
            <input type="text" id="name" name="name"
                   value="<%=u.getName()%>">
        </td>
        <td>
            <input type="text" id="phone" name="phone"
                   value="<%=u.getPhone()%>">
        </td>
        <td>
            <input value="update" type="submit">
        </td>
        <td>
            <input type="text" id="id" name="id"
                   value="<%= u.getId()%>" hidden>
        </td>
    </tr>
    </form>
    <%
                }
            }
        }
    %>
</table>
<td>
    <a href="/admin/register">Add Admin</a>
</td>
<td>
<a href="index">index</a>
</td>
</body>
</html>

