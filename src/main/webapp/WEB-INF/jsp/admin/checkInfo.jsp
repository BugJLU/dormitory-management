<%@ page import="com.jtliu.dormitorymanagement.model.GuestRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Manage</title>
</head>
<body>
<h1>AdminList：</h1>
<form action="/admin/checkInfo">
</form>
<table>
    <%
        List<GuestRecord> guestRecords = (List<GuestRecord>) request.getAttribute("records");
        if (guestRecords != null) {
            if (guestRecords.size() == 0) {
    %>
    <p>No GuestRecord found.</p>
    <%
    } else {
    %>
    <tr>
        <th>name</th>
        <th>phone</th>
        <th>reason</th>
        <th>visiting</th>
        <th>checkin time</th>
        <th>checkout time</th>
    </tr>
    <%
        for (GuestRecord r :
                guestRecords) {
    %>
    <tr>
        <td>
            <a><%=r.getName()%></a>
        </td>
        <td>
            <a><%=r.getPhone()%></a>
        </td>
        <td>
            <a><%=r.getReason()%></a>
        </td>
        <td>
            <a><%=r.getHost().getName()%></a>
        </td>
        <td>
            <a><%=r.getCheckin()%></a>
        </td>
        <td>
            <%
                if(r.getCheckout() != null){
            %>
            <a><%=r.getCheckout()%></a>
            <%
                }else{
            %>
            <a>&emsp;&emsp;Still Visiting</a>
            <%
                }
            %>
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
<script>
</script>
</html>

