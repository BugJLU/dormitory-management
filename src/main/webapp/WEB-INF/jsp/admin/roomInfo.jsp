<%@ page import="com.jtliu.dormitorymanagement.model.GuestRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Manage</title>
</head>
<body>
<h1>RoomList：</h1>
<form action="/admin/roomInfo">
</form>
<table>
    <%
        Map<String,List<StudentInfo>> roomMap = ( Map<String,List<StudentInfo>>)request.getAttribute("rooms");
        if (roomMap != null) {
            if (roomMap.size() == 0) {
    %>
    <p>No Room found.</p>
    <%
    } else {
    %>
    <tr>
        <th>RoomNum</th>
        <th>Student</th>
    </tr>
    <%
        for (String key : roomMap.keySet()) {
    %>
    <tr>
        <td>
            <a><%=key%></a>
        </td>
        <td>
    <%
            for(StudentInfo s : roomMap.get(key)){
    %>
            <a><%=s.getBase().getName()%>&emsp;</a>
            <%}%>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="index">index</a>
</body>
</html>

