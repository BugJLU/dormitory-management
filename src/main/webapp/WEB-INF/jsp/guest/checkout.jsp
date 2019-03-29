<%@ page import="com.jtliu.dormitorymanagement.model.GuestRecord" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/17
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>

<a href="index">&lt;&lt; guest index</a>

    <h1 class="l-title">Search for your checkin record:</h1>

    <form id="searchForm" action="/guest/checkout">
        <label for="phone">phone</label>
        <input id="phone" name="phone" type="text">
        <%--<button id="search" onclick="document.getElementById('searchForm')">search</button>--%>
        <input type="submit" value="search">
    </form>

    <table >
    <% List<GuestRecord> records = (List<GuestRecord>) request.getAttribute("records");
        if (records != null){
            if (records.size() == 0) {
    %>
        <p>No record found.</p>
    <%
            } else {
    %>
        <p>Choose your checkin record to checkout:</p>
        <tr>
            <th>name</th>
            <th>phone</th>
            <th>reason</th>
            <th>visiting</th>
            <th>checkin time</th>
            <th></th>
        </tr>
    <%
            for (GuestRecord r :
                records){
    %>
        <tr>
            <td><%= r.getName() %></td>
            <td><%= r.getPhone() %></td>
            <td><%= r.getReason() %></td>
            <td><%= r.getHostName() %></td>
            <td><%= r.getCheckin() %></td>
            <td><a href="/guest/checkoutAct?id=<%=r.getId()%>">Checkout</a></td>
        </tr>
    <%
                }
            }
        }
    %>
    </table>
</body>
</html>
