<%@ page import="java.util.List" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.User" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Info</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>

<a href="index">&lt;&lt; index</a>

<h1>Student List：</h1>
<table>
    <%
        List<StudentInfo> users = (List<StudentInfo>) request.getAttribute("users");
        if (users != null) {
            if (users.size() == 0) {
    %>
    <p>No student found.</p>
    <%
            } else {
    %>
    <tr>
        <th>name</th>
        <th>phone</th>
        <th>studentID</th>
        <th>room</th>
    </tr>
    <%
        for (StudentInfo u :
                users) {
    %>
    <form action="/admin/studentAct">
        <input type="text" id ="id" name="id" value="<%=u.getId()%>" style="visibility:hidden" />
    <tr>
        <td>
            <input type="text" id="name" name="name"
                   value="<%=u.getBase().getName()%>">
        </td>
        <td>
            <input type="text" id="phone" name="phone"
                   value="<%=u.getBase().getPhone()%>">
        </td>
        <td>
            <input type="text" id="studentId" name="studentId"
                   value="<%=u.getStudentId()%>">
        </td>
        <td>
            <select name="room" id=room>
                <%
                    List<Room> rl = (List<Room>)request.getAttribute("rooms");
                    if(rl != null){
                        if(u.getRoom() != null){
                        for (Room r : rl) {
                        %>
                <option value="<%=r.getRoomNum()%>"
                    <%
                        if(u.getRoom().getRoomNum()==r.getRoomNum()){
                    %>
                        selected="selected"
                        <%}%>
                ><%=r.getRoomNum()%></option>
                <%
                        }
                }else{%>
                <option value="NoRoom"selected="selected">NoRoom</option>
                <%for (Room r : rl) {%>
                <option value="<%=r.getRoomNum()%>"><%=r.getRoomNum()%></option>
                <%}%>
                <%
                        }
                    }
                %>
            </select>
        </td>
        <td>
            <%if(u.getRoom() != null){%>
            <a href="/admin/updateRoom?roomNum=<%=u.getRoom().getRoomNum()%>">>></a>
            <%}else{%>
            <a>>></a>
            <%}%>
        </td>
        <td>
            <input value="update" type="submit">
        </td>
        <td>
            <a href="/admin/removeStudent?sid=<%=u.getId()%>">Remove</a>
        </td>
    </tr>
    </form>
    <%
                }
            }
        }
    %>
</table>
</body>
</html>

