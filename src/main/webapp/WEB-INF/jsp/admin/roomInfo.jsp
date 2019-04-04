<%@ page import="com.jtliu.dormitorymanagement.model.GuestRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Info</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body background="/img/background.jpg">

<a href="index">&lt;&lt; index</a>

<h1>RoomList：</h1>

<table>
    <%
        Map<String,List<StudentInfo>> roomMap = ( Map<String,List<StudentInfo>>)request.getAttribute("roomInfo");
        List<Room> rl = (List<Room>)request.getAttribute("roomList");
        if (roomMap != null) {
            if (roomMap.size() == 0) {
    %>
    <p>No Room found.</p>
    <%
    } else {
    %>
    <tr>
        <th>RoomNum</th>
        <th>StudentNumbers</th>
    </tr>
    <%
        for (Room r : rl) {
    %>
    <form action="/admin/updateRoom">
    <tr>
        <input type="text" id ="roomNum" name="roomNum" value="<%=r.getRoomNum()%>" style="visibility:hidden" />
        <td>
            <a><%=r.getRoomNum()%></a>
        </td>
        <td>
    <%
        if(roomMap.get(r.getRoomNum()) != null){
    %>
            <a>
                <%=roomMap.get(r.getRoomNum()).size()%>
            </a>
            <%
                }
            %>
        </td>
        <td>
            <input value="manage" type="submit">
        </td>
    </tr>
    </form>
    <%
                }
            }
        }
    %>
</table>

<a href="addRoom">Add Room</a>

</body>
</html>

