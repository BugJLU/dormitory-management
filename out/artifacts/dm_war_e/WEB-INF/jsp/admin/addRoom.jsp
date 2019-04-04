<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="/admin/addRoom">
</form>
<head>
    <title>Add Room</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body background="/img/background.jpg">

<a href="/admin/roomInfo">&lt;&lt; room info</a>

<h1 class="l-title">AddRoom：</h1>

<div class="l-center">
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
</div>

</body>
</html>

