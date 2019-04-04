<%@ page import="com.jtliu.dormitorymanagement.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/17
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body background="/img/background.jpg">

<a href="index">&lt;&lt; guest index</a>

    <h1>Who would you like to visit?</h1>
    <form id="searchForm" action="/guest/searchNchoose">
        <label for="name">name</label>
        <input id="name" name="name" type="text">
        <%--<button id="search" onclick="document.getElementById('searchForm')">search</button>--%>
        <input type="submit" value="search">
    </form>
    <%--<div class="l-table">--%>
    <table style="margin-top: 50px;">
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null) {
                if (users.size() == 0) {
        %>
        <p>No user found.</p>
        <%
                } else {
        %>
        <p>Please choose the person you want to visit below.</p>
        <tr>
            <th>name</th>
            <th>phone</th>
        </tr>
        <%
                for (User u :
                    users) {
        %>
        <tr>
            <td><a href="/guest/checkinPage?id=<%=u.getId()%>">
                <%=u.getName()%>
            </a></td>
            <td><a href="/guest/checkinPage?id=<%=u.getId()%>">
                <%=u.getPhone()%>
            </a></td>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
    <%--</div>--%>
</body>
<script>
    // function go() {
    //
    // }
</script>
</html>
