<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="/admin/addRoom">
</form>
<head>
    <title>Admin Manage</title>
</head>
<body>
<h1>AddRoom：</h1>
<form action="/admin/addRoomAct">
    <table>
        <tr>
            <td>
                <label for="roomNum">RoomNum</label>
            </td>
            <td>
                <input id="roomNum" name="roomNum" type="text">
            </td>
        </tr>
        <td>
            <input value="Add" type="submit">
        </td>
    </table>
</form>
<td>
    <a href="index">index</a>
</td>
</body>
</html>

