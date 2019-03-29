<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.jtliu.dormitorymanagement.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Manage</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>

<a href="index">&lt;&lt; index</a>

<h1 class="l-title">AdminList：</h1>

<div class="l-center">
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
            <a href="/admin/removeAdmin?aid=<%=u.getId()%>">Remove</a>
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


    <a href="/admin/register">Add Admin</a>

</div>

</body>
</html>

