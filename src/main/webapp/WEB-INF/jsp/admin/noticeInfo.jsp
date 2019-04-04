<%@ page import="java.util.List" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Notice" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/4/4
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoticeInfo</title>
</head>
<body background="/img/background.jpg">
<a href="index">&lt;&lt; index</a>
<h1>NoticeInfo: </h1>
    <%
        List<Notice> notices = (List<Notice>) request.getAttribute("notices");
        if (notices == null || notices.size() == 0) {
    %>
    <p>No notice yet.</p>
    <%
        } else {
    %>
    <table>
        <tr>
            <th>Admin Name</th>
            <th>Date</th>
            <th>Message</th>
        </tr>
    <%
            for (Notice n:
                 notices) {
        %>
        <tr>
            <td><%=n.getAdmin().getName()%></td>
            <td><%=n.getDate()%></td>
            <td><%=n.getMessage()%></td>
        </tr>
        <%
            }
    %>
    </table>
    <%
        }
    %>
    <a href="/admin/addNotice">Add Notice</a>
</body>
</html>
