<%@ page import="java.util.List" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.User" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List:</title>
</head>
<body>
<h1>Student List：</h1>
<form action="/admin/studentInfo">
</form>
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
            <%--<input type="text" id="room" name="room"--%>
                <%--<%--%>
                <%--if( u.getRoom() != null){--%>
                <%--%>--%>
                   <%--value="<%=u.getRoom().getRoomNum()%>"--%>
                <%--<%}else{%>--%>
                    <%--value="NoRoom"--%>
                <%--<%}%>--%>
            <%-->--%>
        </td>
        <td>
            <input value="update" type="submit">
        </td>
    </tr>
    </form>
    <%
                }
            }
        }
    %>
</table>
<a href="index">index</a>
</body>
</html>

